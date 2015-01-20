package View;

import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Controller.GameEngine;
import Controller.GameLoop;


/**
 * @author Andrew
 *
 * The class which is responsible for showing the Game Over Screen 
 * This class serves as a 'View' within the Model-View-Controller 
 * Design Pattern
 */
public class GameOverScreen {

	/** A game over screen appears if the user does not eliminate chickens quickly
	 * 
	 */
	public GameOverScreen()
	{	
		JFrame gameOverFrame = new JFrame("Game Over");
		JLabel gameOverLabel = new JLabel(new ImageIcon(GameEngine.class.getClassLoader().getResource("GameOver.png")));
		gameOverFrame.setSize(800,600);
		gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverLabel.setSize(800,600);
		gameOverFrame.add(gameOverLabel);
		gameOverFrame.setLayout(null);
		gameOverFrame.setVisible(true);
		GameEngine.PlaySound("explosion.wav");
		
		try
		{
			GameLoop.frame.setVisible(false);
			Thread.sleep(3000);
			GameLoop.frame.dispatchEvent(new WindowEvent(GameLoop.frame, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
}
