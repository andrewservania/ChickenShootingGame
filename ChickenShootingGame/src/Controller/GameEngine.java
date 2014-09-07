package Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Model.Chicken;
import View.GUI;
import View.GameOverScreen;
import View.WinScreen;

/**
 * @author Andrew
 * 
 * 
 * The class which is responsible for most game related dynamics
 * This class serves as a 'Controller' within the Model-View-Controller 
 * Design Pattern
 */
public class GameEngine {



	public	static  			ArrayList<Chicken> 	enemies 				= new ArrayList<Chicken>();
	public  static 	int 							score 					= 0;
	public  static 				JLabel 				scoreLabel 				= new JLabel("Score: " + score);
	public  static 	int 							numberOfEnemychickens 	= 1;
	public  static 	int 							enemyAmountChecker 		= 1;	
	public 	static	boolean 						enemyDirectionChanger 	= false;
	public  static   			JLabel 				backgroundImage 		= new JLabel(new ImageIcon(GUI.class.getClassLoader().getResource("chickenBackground.jpg")));
	public 	static 	boolean 						soundPlaying 			= false;
	public  static				JLabel				hitFlash				= new JLabel(new ImageIcon(GUI.class.getClassLoader().getResource("explosionTransparent.png")));
	public  static 				Clip 				clip;
	public  static 				int					speedUP 				= 0;
	
	//Cursor adaptation to crossHair
	public 	static				Toolkit 			toolkit 				= Toolkit.getDefaultToolkit(); 
	public	static				Image 				image 					= toolkit.getImage(GUI.class.getClassLoader().getResource("crosshair.png"));
	public	static				Point 				hotSpot 				= new Point(0,0);
	public 	static				Cursor 				cursor 					= toolkit.createCustomCursor(image, hotSpot, "crossHair");
	
	
	
	
	/**
	 * @param filePath of the audio file to be played.
	 * 
	 * This method is called when chicken emerges, dies, 
	 * during start screen, game over screen and win screen
	 */
	public static void PlaySound(String filePath)
	{ 
		 URL url = GUI.class.getClassLoader().getResource(filePath);
		 
		if(soundPlaying == false)
		{		
		
			try
			{	
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.loop(0);
				
		

			}
			catch (IOException | UnsupportedAudioFileException | LineUnavailableException   ex2)
			{
				ex2.printStackTrace();
			}
		}

		
		
			 
	}
   
	
    /**
     * @param amountOfEnemyChickens that needs to be created.
     * The game relies on this method to create enemy chickens
     */
    public static void CreateEnemyChicken(int amountOfEnemyChickens)
	{

			for(int i = 0 ; i < amountOfEnemyChickens; i++)
			{
					Chicken chick1 = new Chicken();
					enemies.add(chick1);
					GUI.frame.add(chick1.chicken);
				    Thread t1 = new Thread(chick1,"Chick Thread A");
				    t1.start();
				    PlaySound("ninjaChicken.wav");
				    System.out.println("Enemy created!");

			}
			
/*			for (Iterator<Chicken> it = enemies.iterator(); it.hasNext();)
			{
				Chicken chick = it.next();
				it.remove();
				enemies.remove(chick);
				
			}*/
			
			
	
	}

    
	/**
	 * If an enemy chicken drift away from the screen,
	 * it needs to be eliminated from the screen. 
	 * The score also needs to updated.
	 */
	public static void CheckIfChickenWasKilled()
	{
		
		for(Iterator<Chicken> it = enemies.iterator() ; it.hasNext();)
		{
			Chicken chick = it.next();
			if(chick.chicken.getLocation().x <= -100 || 
					chick.chicken.getLocation().x >= 1024 ||
					chick.chicken.getLocation().y <= -100 || 
							chick.chicken.getLocation().y >= 768)
			{
				it.remove();
				System.out.println("Location of Ghost Chicken: " + chick.chicken.getLocation());
				System.out.println("Chicken has walked away from screen!");
				
				chick.chickenKilled = true;	
				if(chick.chickenKilled == true)
				{
					score = score -200;
					scoreLabel.setText("Score:" + score);
					PlaySound("WarningSound.wav");
					chick.chicken.setLocation(0, 0);
				}

				
				enemies.remove(chick);
				GUI.frame.remove(chick.chicken);
				//it.remove();
			}
			
		}
		
		if(score <= -100 )
		{
			new GameOverScreen();
		}
		
	
	}
   	
	
	/**
	 * The game has to end if the score is lower than -100
	 */
	public static void MonitorAndLimitTheAmountOfChickenInGame()
	{	
		if( enemyAmountChecker > 6)
		{
				PlaySound("WarningSound.wav");
				
				try
				{
					Thread.sleep(1300);
					score = score -200;	
					scoreLabel.setForeground(Color.RED);			
					scoreLabel.setText("Score: " + score);
					
					if(score <= -100)
					{
						new GameOverScreen();
					}
				}
				catch (InterruptedException ex)
				{
						ex.printStackTrace();
				}
		
				
		}
		
		if (enemyAmountChecker > 0 && enemyAmountChecker < 50)
		{
			try
			{
				Thread.sleep((1000 - speedUP) + new Random().nextInt(300));
				CreateEnemyChicken(numberOfEnemychickens);
				enemyAmountChecker++;	
			}
			
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
				
			
		}
	
	}
	
	
	/** 
	 * The game needs to end if the player has reached a maximum score
	 */
	public static void DetermineWetherPlayerHasReachedMaxScore()
	{
		if(score >= 7000)
		{
			new WinScreen();
		}
		
	}
	
	
	/** To make the game more challenging, the speed of which
	 *  the enemy chickens appear is raised every 1000 points.
	 */
	public static void speedUpEnemyAppearance()
	{
		switch(score)
		{
			case 1000:
				if(speedUP <= 600)
				speedUP += 200;
				System.out.println("Speedup at: " + speedUP);
				break;
				
			case 2000:
				
				if(speedUP <= 600)
				speedUP += 150;
				System.out.println("Speedup at: " + speedUP);
				break;
				
			case 3000:
				if(speedUP <= 600)
				speedUP += 125;
				System.out.println("Speedup at: " + speedUP);
				break;
				
			case 4000:
				;
				if(speedUP <= 600);
				speedUP += 200;
				System.out.println("Speedup at: " + speedUP);
				
				break;
		}
	}

	
	
	
	
	
}
