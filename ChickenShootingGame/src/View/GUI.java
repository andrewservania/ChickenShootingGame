package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
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
import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.Chicken;

public class GUI extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static  final long 						serialVersionUID 		= 1L;
	public 	static  			JFrame 				frame;
	public	static  			ArrayList<Chicken> 	enemies 				= new ArrayList<Chicken>();
	public  static 	int 							score 					= 0;
	public  static 				JLabel 				scoreLabel 				= new JLabel("Score: " + score);
	public  static 	int 							numberOfEnemychickens 	= 1;
	public  static 	int 							enemyAmountChecker 		= 1;	
	public 	static	boolean 						enemyDirectionChanger 	= false;
	public  static   			JLabel 				backgroundImage 		= new JLabel(new ImageIcon(GUI.class.getClassLoader().getResource("chickenBackground.jpg")));
	public 	static 	boolean 						soundPlaying 			= false;

	//Cursor adaptation to crossHair
	public 	static				Toolkit 			toolkit 				= Toolkit.getDefaultToolkit(); 
	public	static				Image 				image 					= toolkit.getImage(GUI.class.getClassLoader().getResource("crosshair.png"));
	public	static				Point 				hotSpot 				= new Point(0,0);
	public 	static				Cursor 				cursor 					= toolkit.createCustomCursor(image, hotSpot, "crossHair");
	public  static				JLabel				hitFlash				= new JLabel(new ImageIcon(GUI.class.getClassLoader().getResource("explosionTransparent.png")));
	public  static 				Clip 				clip;
	public  static 				int					speedUP 				= 0;
	
	public GUI()
	{	
		frame = new JFrame("Chicken Shooting Game");
		frame.setLayout(null);
		frame.setSize(1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		backgroundImage.setSize(800,600);
		frame.setContentPane(backgroundImage);	
		
		scoreLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		scoreLabel.setSize(250,30);

		frame.add(scoreLabel);
		frame.setCursor(cursor);
		
		hitFlash.setSize(1024,768);
		hitFlash.setBackground(Color.RED);
		frame.add(hitFlash);
		hitFlash.setVisible(false);
		frame.setVisible(true);


	}

	public void init() {
	}

	public void start() {
		// define a new thread
		Thread th = new Thread(this);
		// start this thread
		th.start();

	}

	public void stop() {
	}

	public void destroy() {
	}

	public void paint(Graphics g) {

	}
    
	public void run() {
		// lower threadPriority
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

		// run a long while (true) this means in our case "always"
		while (true) 
		{
			
			MonitorAndLimitTheAmountOfChickenInGame();
			CheckIfChickenWasKilled();
			DetermineWetherPlayerHasReachedMaxScore();
			speedUpEnemyAppearance();
			frame.revalidate();
			frame.repaint();

			try
			{
				Thread.sleep(20);
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
			
			
			// set ThreadPriority to maximum value
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

			
			System.out.println("Running..");
		}
		
		
	}
	
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
   
    public void CreateEnemyChicken(int amountOfEnemyChickens)
	{

			for(int i = 0 ; i < amountOfEnemyChickens; i++)
			{
					Chicken chick1 = new Chicken();
					enemies.add(chick1);
					frame.add(chick1.chicken);
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

	public void CheckIfChickenWasKilled()
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
				frame.remove(chick.chicken);
				//it.remove();
			}
			
		}
		
		if(score <= -100 )
		{
			ShowGameOverScreen();
		}
		
	
	}
   	
	public void MonitorAndLimitTheAmountOfChickenInGame()
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
						ShowGameOverScreen();
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
		
	public void ShowGameOverScreen()
	{
		
		JFrame gameOverFrame = new JFrame("Game Over");
		JLabel gameOverLabel = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("GameOver.png")));
		gameOverFrame.setSize(800,600);
		gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverLabel.setSize(800,600);
		gameOverFrame.add(gameOverLabel);
		gameOverFrame.setLayout(null);
		gameOverFrame.setVisible(true);
		PlaySound("explosion.wav");
		
		try
		{
			frame.setVisible(false);
			Thread.sleep(3000);
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	public void DetermineWetherPlayerHasReachedMaxScore()
	{
		if(score >= 7000)
		{
			ShowWinScreen();
		}
		
	}
	
	public void ShowWinScreen()
	{
		JFrame gameWinFrame = new JFrame("YOU WIN!");
		JLabel gameWinLabel = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("WinningBackgroundFinal.png")));
		gameWinFrame.setSize(800,600);
		gameWinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWinLabel.setSize(800,600);
		gameWinFrame.add(gameWinLabel);
		gameWinFrame.setLayout(null);
		gameWinFrame.setVisible(true);
		PlaySound("WinningSound.wav");
		
		try
		{
			frame.setVisible(false);
			Thread.sleep(5000);
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
		
	}

	public void speedUpEnemyAppearance()
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
