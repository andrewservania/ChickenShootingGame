package Controller;


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
import Model.Goat;
import Model.Snake;
import View.GUI;
import View.GameOverScreen;
import View.WinScreen;
import Model.Enemy;

/**
 * @author Andrew
 * 
 * 
 * The class which is responsible for most game related dynamics
 * This class serves as a 'Controller' within the Model-View-Controller 
 * Design Pattern
 */
public class GameEngine {



	public	static  	ArrayList<Enemy> 	enemies 				= new ArrayList<Enemy>();
	public  static 		int 				score 					= 0;
	public  static 		JLabel 				scoreLabel 				= new JLabel("Score: " + score);
	public  static 		int 				numberOfEnemychickens 	= 1;
	public  static 		int 				enemyAmountChecker 		= 1;	
	public 	static		boolean				enemyDirectionChanger 	= false;
	public  static  	JLabel 				backgroundImage 		= new JLabel(new ImageIcon(GUI.class.getClassLoader().getResource("chickenBackground.jpg")));
	public 	static 		boolean				soundPlaying 			= false;
	public  static		JLabel				hitFlash				= new JLabel(new ImageIcon(GUI.class.getClassLoader().getResource("explosionTransparent.png")));
	public  static 		Clip 				clip;
	public  static 		int					speedUP 				= 0;
	public  static  	UserInput 			player;

	
	//Cursor adaptation to crossHair
	public 	static		Toolkit 			toolkit 				= Toolkit.getDefaultToolkit(); 
	public	static		Image 				image 					= toolkit.getImage(GUI.class.getClassLoader().getResource("crosshair.png"));
	public	static		Point 				hotSpot 				= new Point(0,0);
	public 	static		Cursor 				cursor 					= toolkit.createCustomCursor(image, hotSpot, "crossHair");
	
	public 	static		int					enemySelector 			= new Random().nextInt(3);
	
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

		//	for(int i = 0 ; i < amountOfEnemyChickens; i++)
		//	{
				//	Chicken chick1 = new Chicken();
					
				//	enemies.add(chick1);
					
					
				//	GUI.frame.add(chick1.chicken);

					
					// What the hell does this mean?? Oh my goodness
					// Answer: That's because all chickens have a run method
					// The Chicken class has a start() method which
					// can run seperately in a thread
					// giving every individual chicken the ability
					// to act/react or behave in their own unique way
					// and with 'act/react or behave in their own unique way' I mean
					// perform any particular method on them
					// The 'Chick Thread A' is just a name for the thread
				//    Thread t1 = new Thread(chick1,"Chick Thread A");
				//   t1.start();	   
				//   PlaySound("ninjaChicken.wav");


		//	}
	}

    
	/**
	 * If an enemy chicken drift away from the screen,
	 * it needs to be eliminated from the screen. 
	 * The score also needs to updated.
	 */
	public static void CheckIfEnemyWasKilled()
	{
		
		for(Iterator<Enemy> it = enemies.iterator() ; it.hasNext();)
		{
			Enemy enemy = it.next();
			if(enemy.enemy.getLocation().x <= 0 || 
					enemy.enemy.getLocation().x >= GUI.GAME_WINDOW_WIDTH ||
					enemy.enemy.getLocation().y <= 0 || 
							enemy.enemy.getLocation().y >= GUI.GAME_WINDOW_HEIGHT)
			{

				System.out.println("Location of Ghost Chicken: " + enemy.enemy.getLocation());
				System.out.println("Chicken has walked away from screen!");
				
			//	chick.chickenKilled = true;	
				if(enemy.enemyKilled == true)
				{
					score = score -50;
					
					scoreLabel.setText("Score:" + score);
					
					PlaySound("WarningSound.wav");
					
				//	chick.chicken.setLocation(0, 0);
					
					it.remove();
					
					enemies.remove(enemy);
					
					GUI.frame.remove(enemy.enemy);
				}
				
			}
			
		}
		
		
		if(score <= -10 )
		{
			new GameOverScreen();
		}
		
	
	}
   	
	
	
	
	/** 
	 * The game needs to end if the player has reached a maximum score
	 */
	public static void DetermineWetherPlayerHasReachedMaxScore()
	{
		if(score >= 2000)
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

	
	/** Generate enemies, chickens, cows, whatever, this will be a pseudo-generic method
	 * 
	 */
	public static void RandomEnemyGenerator()
	{
		if (enemyAmountChecker > 0 && enemyAmountChecker < 50)
		{
			try
			{
				Thread.sleep((1000 - speedUP) + new Random().nextInt(300));
				
				switch (enemySelector)
				{
					case 0:	create("chicken"); 	enemySelector = new Random().nextInt(3); 	break;
				
					case 1:	create("goat"); 	enemySelector = new Random().nextInt(3); 	break;
				
					case 2: create("snake"); 	enemySelector = new Random().nextInt(3); 	break;
				}
				

				
				
				enemyAmountChecker++;	
			}
			
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
				
			
		}
	}
	
	/** Factory method for creating units
	 *  In this case chicken, goats, snakes, etc.
	 */
	public static void create(String enemyType)
	{
		switch(enemyType)
		{
		case "chicken":
			
			Chicken chicken = new Chicken("chickenator2.png");
			
			chicken.MobilizeEnemyInRandomXDirection(new Random().nextInt(2));
			chicken.MobilizeEnemyInRandomYDirection(new Random().nextInt(2));
			enemies.add(chicken);
			GUI.frame.add(chicken.enemy);	
		    Thread t1 = new Thread(chicken,"Chick Thread A");
		    t1.start();
		    PlaySound("ninjaChicken.wav");
		   
			break;
			
		case "goat":

			Goat goat = new Goat("goat.png");
			goat.MobilizeEnemyInRandomXDirection(new Random().nextInt(2));
			goat.MobilizeEnemyInRandomYDirection(new Random().nextInt(2));
			enemies.add(goat);
			GUI.frame.add(goat.enemy);
			
		    Thread t2 = new Thread(goat,"Goat Thread A"); 
		    t2.start();
		    PlaySound("GoatSound.wav");
		   
			break;
			
		case "snake":

			Snake snake = new Snake("snake.png");
			snake.MobilizeEnemyInRandomXDirection(new Random().nextInt(2));
			snake.MobilizeEnemyInRandomYDirection(new Random().nextInt(2));
			enemies.add(snake);
			GUI.frame.add(snake.enemy);
			
		    Thread t3 = new Thread(snake,"Snake Thread A");  
		    t3.start();
		    PlaySound("SnakeSound.wav");

			break;
			
			
			
			
		}
		
	}
	
	
	
	
}
