package Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.prefs.BackingStoreException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Levels.BaseGameLevel;
import Levels.BaseLevelState;
import Levels.FarmLevel;
import Levels.LevelFactory;
import Levels.Finished;
import Levels.VolcanoLevel;
import Model.Unit;
import Model.UnitFactory;
import View.GameOverScreen;
import View.GameScreen;
import View.StartScreen;
import View.WinScreen;

public class GameLoop extends JFrame implements Runnable, KeyListener{

	//public static GameScreen gameScreen;
	public static GameEngine gameEngine;

	public static 		int 				score 					= 0;
	public static 		LinkedList<Unit> 	gameObjects				= new LinkedList<Unit>();
	public static  		int 				enemyAmountChecker 		= 1;	
	public static 		int					enemySelector 			= new Random().nextInt(3);
	public static  		int					speedUP 				= 0;
	public static		int 				amountOfEnemiesKilled	= 0;
	public		 		long 				start_time;   //-1;
	public		 		long 				end_time;
	public static		double 				dt						=1;
	public	static	 	double 				fps;
	public		 		long 				MsPerDt					=20;
	public 				int					nLevel					= 0;
	
	public 				boolean switchLevelState = false;
	
	private static BaseLevelState currentLevel;
	
	//GameEngine stuff
	
	public  static 		int 				numberOfEnemychickens 	= 3;
	public 	static		boolean				enemyDirectionChanger 	= false;

	public 	static 		boolean				soundPlaying 			= false;
	public  static		JLabel				hitFlash;
	public  static 		Clip 				clip;
	public  static  	UserInput 			player;
	
	//Cursor adaptation to crossHair
	public 	static		Toolkit 			toolkit 				= Toolkit.getDefaultToolkit(); 
	public	static		Image 				image 					= toolkit.getImage(GameScreen.class.getClassLoader().getResource("crosshair.png"));
	public	static		Point 				hotSpot 				= new Point(0,0);
	public 	static		Cursor 				cursor 					= toolkit.createCustomCursor(image, hotSpot, "crossHair");
	
	
	//GameScreen Stuff
	private static  final 		long 				serialVersionUID 		= 1L;
	public 	static		  		JFrame 				frame;
	public 	static 				int			 		GAME_WINDOW_WIDTH		= 800;
	public	static				int					GAME_WINDOW_HEIGHT		= 600;
	public  static				JLabel 				scoreLabel 				= new JLabel("Score: " + score);
	public  static				JLabel 				fpslabel;
	public static boolean isLevelChanged = false;
	public static JLabel levelLabel;
	
	public GameLoop(){
		//this is an in (This gameloop!)
		new FarmLevel(this);
		new VolcanoLevel(this);
		new Finished(this);
		ActivateGameScreen();
		SetLevel(LevelFactory.GetFirstLevel());
		
		
		scoreLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		scoreLabel.setSize(250,30);
		frame.add(scoreLabel);
		
		
		 fpslabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		  fpslabel.setSize(350, 30);
		  fpslabel.setLocation(GAME_WINDOW_HEIGHT - 100, 0);
		  frame.add(fpslabel);
		
		 frame.addKeyListener(this);
		  hitFlash = new JLabel(new ImageIcon(GameLoop.class.getClassLoader().getResource("explosionTransparent.png")));
			hitFlash.setSize(1024,768);
			hitFlash.setVisible(false);
			frame.add(hitFlash);	

		  
		  levelLabel = new JLabel("Level: " +  currentLevel.GetID());
		  levelLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		  levelLabel.setSize(350, 30);
		  levelLabel.setLocation(GAME_WINDOW_HEIGHT - 300, 0);
		  levelLabel.setVisible(true);
		  frame.add(levelLabel);
		  
	}	
	

	//GameScreen stuff
	public static void ActivateGameScreen()
	{	

		fpslabel = new JLabel("FPS: " + fps);
		frame = new JFrame("Chicken Shooting Game");
		
		
		
		frame.setLayout(null);
		frame.setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TODO: SET LEVEL BACKGROUND HERE!! ORIGINALLY IT WAS HERE!!
		

		
		scoreLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		scoreLabel.setSize(250,30);
		frame.add(scoreLabel);
		
		
		 fpslabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		  fpslabel.setSize(350, 30);
		  fpslabel.setLocation(GAME_WINDOW_HEIGHT - 100, 0);
		  frame.add(fpslabel);
		  


		
		
		
		
		frame.setCursor(GameEngine.cursor);	
		

		
		
		
		frame.setLocation( 
				(int) StartScreen.width/2 - GAME_WINDOW_WIDTH/2, 
				(int) StartScreen.height/2 - GAME_WINDOW_HEIGHT/2
				);
		frame.setVisible(true);


		
		
		
		

	}
	
	//GameEngine stuff
	public static void PlaySound(String filePath)
	{ 
		 URL url = GameLoop.class.getClassLoader().getResource(filePath);
		 
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
   
	
	public void SetLevel(BaseGameLevel pLevel){
		
		pLevel.Draw(dt);
	}
	//=====
	
	public void start() {
		Thread th = new Thread(this);
		th.start();
	}
	

	public void run() {


		
		while (true) 
		{

			
			start_time = System.currentTimeMillis();
			
			
			
			if(score >= 1500){
				switchLevelState = true;
				isLevelChanged = true;
			}
	
				
				if(isLevelChanged == true){
					

					BaseLevelState.backgroundImage = null;
					
					SetLevel(LevelFactory.NextLevel(currentLevel));
					currentLevel.LoadGraphics();
					
					scoreLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
					scoreLabel.setSize(250,30);
					frame.add(scoreLabel);
					
					
					 fpslabel.setFont(new Font("Dialog", Font.PLAIN, 40));
					  fpslabel.setSize(350, 30);
					  fpslabel.setLocation(GAME_WINDOW_HEIGHT - 100, 0);
					  frame.add(fpslabel);
					
					  levelLabel = new JLabel("Level: " +  currentLevel.GetID());
					  levelLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
					  levelLabel.setSize(350, 30);
					  levelLabel.setLocation(GAME_WINDOW_HEIGHT - 300, 0);
					  frame.add(levelLabel);
					  
					  hitFlash = new JLabel(new ImageIcon(GameLoop.class.getClassLoader().getResource("explosionTransparent.png")));
						hitFlash.setSize(1024,768);
						hitFlash.setVisible(false);
						frame.add(hitFlash);	
					  
					  
					  
					
					for(Unit unit : gameObjects)
					{
						unit.update();
					}
					
					isLevelChanged = false;
					score = 0;
					
				}
			
			//RandomEnemyGenerator();
				GenerateEnemyBasedOnLevel();
			
			for(Unit unit : gameObjects)
			{
				unit.update();
			}
			
			// End Game if player Loses
			if(score <= -10 )
			{
				new GameOverScreen();
			}
	
			// End Game if player wins
			if(score >= 1400 && currentLevel.dID == LevelFactory.GetMap().size())
			{
				LevelFactory.Finished();
			}
			
			// "Play Gun Reload Sound every 4 shots"
			if(amountOfEnemiesKilled == 4)
			{
				PlaySound("ShotgunCocking.wav");

				amountOfEnemiesKilled = 0;
				//GenerateEnemyBasedOnLevel();
			
			}

			end_time = System.currentTimeMillis();

			dt = (end_time - start_time);	
			fps = 1000 / dt;
			
			fpslabel.setForeground(Color.BLACK);
			fpslabel.setText("FPS: " + fps);

			try
			{			
				if (dt > MsPerDt) {
					long x = (long) (dt - (dt - MsPerDt));
					Thread.sleep(x);
				}
				else
				{
					Thread.sleep(MsPerDt);
				}
				
			} // end try-loop
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			} // end catch-loop
		
			
			}
			
		}	// end while-loop
		
	/** Generate enemies, chickens, cows, whatever, this will be a pseudo-generic method
	 * 
	 * 
	 * 
	 */
	public static void RandomEnemyGenerator()
	{
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (enemyAmountChecker > 0 && enemyAmountChecker < 3)
		{

				//TODO: Moet in een 'factory' method geconverteerd worden
				//TODO: Switch-statement moet weg
			
				switch (enemySelector)
				{
					case 0:	
						gameObjects.add(UnitFactory.create("Chicken"));
						enemySelector = new Random().nextInt(3); 

						
						break;
						
				
					case 1:	gameObjects.add(UnitFactory.create("Goat")); 	
						enemySelector = new Random().nextInt(3); 
					
						
						break;
				
					case 2: 
						gameObjects.add(UnitFactory.create("Snake"));
						enemySelector = new Random().nextInt(3);
			
						break;
				}

				enemyAmountChecker++;	

		}
	}

	
	public static void GenerateEnemyBasedOnLevel()
	{
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (enemyAmountChecker > 0 && enemyAmountChecker < 3)
		{

				//TODO: Moet in een 'factory' method geconverteerd worden
				//TODO: Switch-statement moet weg
			
				switch (currentLevel.dID)
				{
					case 1:	
						gameObjects.add(UnitFactory.create("Chicken"));
						enemySelector = 0; 

						
						break;
						
				
					case 2:	gameObjects.add(UnitFactory.create("Goat")); 	
						enemySelector = 1; 
					
						
						break;
				
					case 3: 
						gameObjects.add(UnitFactory.create("Snake"));
						enemySelector = 2;
			
						break;
				}

				enemyAmountChecker++;	

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
	
	
	//This method is necessary here according to Bert's StateWithFactoryMethod Code
	public void SetLevel(BaseLevelState pLevel){
		
		assert(pLevel != null);
		
		currentLevel = pLevel;
		
		if(currentLevel != null){
			currentLevel.SetOwner(this);
			currentLevel.LoadGraphics();
		}
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		BaseLevelState.backgroundImage = null;

		for(Unit unit : gameObjects){
			unit.update();
		}
		gameObjects.clear();
		GenerateEnemyBasedOnLevel();
			System.out.println("Background set to null");
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
