package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.GameEngine;
import Controller.UserInput;

/**
 * @author Andrew
 * 
 * The class which is responsible for showing the Game Over Screen 
 * This class serves as a 'View' within the Model-View-Controller 
 * Design Pattern
 */
public class StartScreen extends JFrame {

	
	/** Serial Version Unique ID
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JButton startButton;
	public static JFrame startScreen;
	
	// Set the location of the start Screen in the middle of the screen
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	//screen width & height
	public static double width = screenSize.getWidth();
	public static double height = screenSize.getHeight();
	
	/** The user has to press the 'Start' on the start screen to start the game
	 * 
	 */
	public StartScreen()
	{		
	
		startScreen = new JFrame("Chicken Of Duty 2");
		JLabel gameStartImage = new JLabel(new ImageIcon((this.getClass().getClassLoader().getResource("ChickenOfDutyBackground.png"))));
		
		startButton = new JButton("Start");
		startButton.setBounds(20, 550, 200, 80);
		startButton.setBackground(Color.RED);;
		startButton.setFont(new Font("Dialog",Font.PLAIN,60));
		
		// Give this class to the input class so you can detect the 'Start' button
		GameEngine.player = new UserInput(startButton);
		
		startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameStartImage.setSize(800,678);
		
		startScreen.setContentPane(gameStartImage);
		startScreen.setLayout(null);
		startScreen.add(startButton);
		startScreen.setSize(800,678);	
		startScreen.setLocation( (int) width/2 - 800/2, (int) height/2 - 678/2);
		startScreen.setVisible(true);
		
		GameEngine.PlaySound("explosion.wav");
		
		try{
			Thread.sleep(1000);
			GameEngine.PlaySound("ChickenOfDutyTwo.wav");
			}
		catch (InterruptedException e){
			e.printStackTrace();
		}	
	}

	
}
