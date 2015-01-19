package View;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.GameEngine;
import Controller.GameLoop;
import Levels.LevelFactory;


/**
 * @author Andrew
 * 
 * The class which is responsible for showing the main game screen 
 * This class serves as a 'View' within the Model-View-Controller 
 * Design Pattern
 */
//public class GUI extends JFrame implements Runnable 

public class GameScreen extends JFrame
{


	private static  final 		long 				serialVersionUID 		= 1L;
	public 	static		  		JFrame 				frame;
	public 	static 				int			 		GAME_WINDOW_WIDTH		= 800;
	public	static				int					GAME_WINDOW_HEIGHT		= 600;
//	public 	static				GameLoop 			gameLoop;
	public  static				JLabel 				scoreLabel 				= new JLabel("Score: " + GameLoop.score);
	public  static				JLabel 				fpslabel;
	

	
	/**
	 * A Graphic User Interface has to be created in order to run the game.
	 * A window frame is created here and all elements such as chicken, 
	 * cross-hair, buttons and background image are added to the frame in 
	 * this constructor. 
	 */
	public GameScreen()
	{	

		fpslabel = new JLabel("FPS: " + GameLoop.fps);
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
		  fpslabel.setLocation(GAME_WINDOW_HEIGHT - 200, 0);
		  frame.add(fpslabel);
		
		
		
		
		
		frame.setCursor(GameEngine.cursor);	
		GameEngine.hitFlash.setSize(1024,768);
		frame.add(GameEngine.hitFlash);
		
	//	GameEngine.hitFlash.setVisible(false);	
		
		frame.setLocation( 
				(int) StartScreen.width/2 - GAME_WINDOW_WIDTH/2, 
				(int) StartScreen.height/2 - GAME_WINDOW_HEIGHT/2
				);
		frame.setVisible(true);


	}



}
