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
 * 
 * Tasks: 
 * 1) Make some text final!
 * 2) Clear up unused variables!
 * 3) Keep access modifiers consistent! Avoid too much public and private variables mixed up together!
 * 4) Use getters and setters instead of directly accessing variables! Creates too much dependencies!
 * 5) Add Javadoc style comments to all your methods!
 * 6) Make your methods as short as possible! 
 * 
 */
public class StartScreen extends JFrame {

	
	/** Serial Version Unique ID
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JButton startButton;
	public static JFrame startScreen;
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static double width = screenSize.getWidth();
	public static double height = screenSize.getHeight();
	private JLabel gameStartImage;
	private static final String IMAGE_BACKGROUND = "ChickenOfDutyBackground.png";
	private static final String EXPLOSION_SOUND = "explosion.wav";
	private static final String INTRO_SOUND = "ChickenOfDutyTwo.wav";
	private static final String WINDOW_NAME = "Chicken of Duty 2";
	private static final String START_BUTTON_TEXT = "Start";
	
	private static final int windowWidth = 800;
	private static final int windowHeight = 678;
	
	/** The user has to press the 'Start' on the start screen to start the game
	 */
	public StartScreen()
	{		
	
		initializeGameStartImage(windowWidth,windowHeight,IMAGE_BACKGROUND);
		initializeStartScreen();
		initializeStartButton();
		
		GameEngine.player = new UserInput(startButton);
		GameEngine.PlaySound("explosion.wav");
		
		try{
			Thread.sleep(1000);
			GameEngine.PlaySound("ChickenOfDutyTwo.wav");
			}
		catch (InterruptedException e){
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * Set the name and screen size of the start screen
	 */
	private void initializeStartScreen(){
		
		startScreen = new JFrame("Chicken of Duty 2");	
		startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startScreen.setContentPane(gameStartImage);
		startScreen.setLayout(null);
		startScreen.setSize(windowWidth,windowHeight);	
		startScreen.setLocation( (int) width/2 - windowWidth/2, (int) height/2 - windowHeight/2);
		startScreen.setVisible(true);
	}
	
	/**
	 * Set the position, color and the font of the start button 
	 */
	private void initializeStartButton()
	{
		startButton = new JButton("Start");
		startScreen.add(startButton);
		startButton.setBounds(20, 550, 200, 80);
		startButton.setBackground(Color.RED);;
		startButton.setFont(new Font("Dialog",Font.PLAIN,60));
	}
	
	/**
	 * Set the background image of the start screen
	 * @param width The width of the image
	 * @param height The height of the image
	 * @param imageResource the name of the image. example "image1.png"
	 */
	private void initializeGameStartImage(int width, int height, String imageResource)
	{
		gameStartImage = new JLabel(new ImageIcon((this.getClass().getClassLoader().getResource(imageResource))));
		gameStartImage.setSize(width,height);
	}

	
}
