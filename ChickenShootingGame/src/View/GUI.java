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
//public class GUI extends JFrame implements Runnable 

public class GUI extends JFrame
{


	private static  final 		long 				serialVersionUID 		= 1L;
	public 	static  			JFrame 				frame;
	public 	static 				int			 		GAME_WINDOW_WIDTH		= 800;
	public	static				int					GAME_WINDOW_HEIGHT		= 600;
	
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
		frame.setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameEngine.backgroundImage.setSize(GAME_WINDOW_WIDTH,GAME_WINDOW_HEIGHT);
		frame.setContentPane(GameEngine.backgroundImage);	
		
		GameEngine.scoreLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		GameEngine.scoreLabel.setSize(250,30);

		frame.add(GameEngine.scoreLabel);
		frame.setCursor(GameEngine.cursor);
		
		GameEngine.hitFlash.setSize(1024,768);
		//GameEngine.hitFlash.setBackground(Color.RED);
		frame.add(GameEngine.hitFlash);
		
		GameEngine.hitFlash.setVisible(false);
		
		frame.setLocation( 
				(int) StartScreen.width/2 - GAME_WINDOW_WIDTH/2, 
				(int) StartScreen.height/2 - GAME_WINDOW_HEIGHT/2
				);
		frame.setVisible(true);


	}



}
