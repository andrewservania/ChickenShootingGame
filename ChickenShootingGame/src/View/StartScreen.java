package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartScreen extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StartScreen()
	{		
	
		final JFrame startScreen = new JFrame("Chicken Of Duty 2");
		JLabel gameStartImage = new JLabel(new ImageIcon((this.getClass().getClassLoader().getResource("ChickenOfDutyBackground.png"))));
		
		JButton startButton = new JButton("Start");
		startButton.setBounds(20, 550, 200, 80);
		startButton.setBackground(Color.RED);;
		startButton.setFont(new Font("Dialog",Font.PLAIN,60));
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				startScreen.setVisible(false);
				GUI.PlaySound("Chckns02.wav");
				(new Thread(new GUI())).start();
			}
		});
		
		startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameStartImage.setSize(800,678);
		
		startScreen.setContentPane(gameStartImage);
		startScreen.setLayout(null);
		startScreen.add(startButton);
		startScreen.setSize(800,678);	
		startScreen.setVisible(true);
		
		GUI.PlaySound("explosion.wav");
		
	}
	
	
	
}
