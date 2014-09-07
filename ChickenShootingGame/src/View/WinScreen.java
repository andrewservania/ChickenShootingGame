package View;

import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.GameEngine;

/**
 * @author Andrew
 * 
 * The class which is responsible for showing the Win Screen 
 * This class serves as a 'View' within the Model-View-Controller 
 * Design Pattern
 */
public class WinScreen {

	
	/** A win screen appears if the user reaches the maximum score.
	 * 
	 */
	public WinScreen()
	{
		JFrame gameWinFrame = new JFrame("YOU WIN!");
		JLabel gameWinLabel = new JLabel(new ImageIcon(GameEngine.class.getClassLoader().getResource("WinningBackgroundFinal.png")));
		gameWinFrame.setSize(800,600);
		gameWinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWinLabel.setSize(800,600);
		gameWinFrame.add(gameWinLabel);
		gameWinFrame.setLayout(null);
		gameWinFrame.setVisible(true);
		GameEngine.PlaySound("WinningSound.wav");
		
		try
		{
			GUI.frame.setVisible(false);
			Thread.sleep(5000);
			GUI.frame.dispatchEvent(new WindowEvent(GUI.frame, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
}
