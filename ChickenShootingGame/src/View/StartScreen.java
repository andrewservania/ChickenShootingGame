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
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static double width = screenSize.getWidth();
	public static double height = screenSize.getHeight();
	private JLabel gameStartImage;
	private static final String IMAGE_BACKGROUND = "ChickenOfDutyBackground.png";
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
	
	
	private void initializeStartScreen(){
		
		startScreen = new JFrame("Chicken of Duty 2");	
		startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startScreen.setContentPane(gameStartImage);
		startScreen.setLayout(null);
		startScreen.setSize(windowWidth,windowHeight);	
		startScreen.setLocation( (int) width/2 - windowWidth/2, (int) height/2 - windowHeight/2);
		startScreen.setVisible(true);
	}
	
	private void initializeStartButton()
	{
		startButton = new JButton("Start");
		startScreen.add(startButton);
		startButton.setBounds(20, 550, 200, 80);
		startButton.setBackground(Color.RED);;
		startButton.setFont(new Font("Dialog",Font.PLAIN,60));
	}
	
	private void initializeGameStartImage(int width, int height, String imageResource)
	{
		gameStartImage = new JLabel(new ImageIcon((this.getClass().getClassLoader().getResource(imageResource))));
		gameStartImage.setSize(width,height);
	}

	
}
