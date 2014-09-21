package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import Model.Chicken;
import Model.Enemy;
import View.StartScreen;

/**
 * @author Andrew
 *
 * UserInput class which serves as a 'Controller' within the MVC Design Pattern
 */
public class UserInput {
	
	public UserInput(final Enemy enemy)
	{
		enemy.enemy.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				enemy.EnemyKilled();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public UserInput(JButton startButton)
	{
		
		startButton.addActionListener(new ActionListener() {
		
			
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// If start button is clicked, launch game screen

			GameEngine.PlaySound("Chckns02.wav");
			
			new Thread(new GameLoop()).start();
			
			StartScreen.startScreen.setVisible(false);
			
		}
	});
	}
}
