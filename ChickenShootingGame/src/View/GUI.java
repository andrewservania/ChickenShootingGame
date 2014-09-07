package View;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import Controller.GameEngine;


/**
 * @author Andrew
 * 
 * The class which is responsible for showing the main game screen 
 * This class serves as a 'View' within the Model-View-Controller 
 * Design Pattern
 */
public class GUI extends JFrame implements Runnable {


	private static  final 		long 				serialVersionUID 		= 1L;
	public 	static  			JFrame 				frame;
	
	/**
	 * A Graphic User Interface has to be created in order to run the game.
	 * A window frame is created here and all elements such as chicken, 
	 * cross-hair, buttons and background image are added to the frame in 
	 * this constructor. 
	 */
	public GUI()
	{	
		frame = new JFrame("Chicken Shooting Game");
		frame.setLayout(null);
		frame.setSize(1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameEngine.backgroundImage.setSize(800,600);
		frame.setContentPane(GameEngine.backgroundImage);	
		
		GameEngine.scoreLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		GameEngine.scoreLabel.setSize(250,30);

		frame.add(GameEngine.scoreLabel);
		frame.setCursor(GameEngine.cursor);
		
		GameEngine.hitFlash.setSize(1024,768);
		GameEngine.hitFlash.setBackground(Color.RED);
		frame.add(GameEngine.hitFlash);
		GameEngine.hitFlash.setVisible(false);
		frame.setVisible(true);


	}


	
	/** The Game has to be updated constantly in order to show the moving 
	 * enemy chickens on the screen.
	 */
	public void start() {
		// define a new thread
		Thread th = new Thread(this);
		// start this thread
		th.start();

	}

	
	/* (non-Javadoc) This where the actual GAME LOOP is placed
	 * @see java.lang.Runnable#run()
	 * 
	 */
	public void run() {
		// lower threadPriority
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

		// run a long while (true) this means in our case "always"
		while (true) 
		{
			
			GameEngine.MonitorAndLimitTheAmountOfChickenInGame();
			GameEngine.CheckIfChickenWasKilled();
			GameEngine.DetermineWetherPlayerHasReachedMaxScore();
			GameEngine.speedUpEnemyAppearance();
			
			frame.revalidate();
			frame.repaint();
			System.out.println("Running..");
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

			
			
		}
		
		
	}
	
	
}
