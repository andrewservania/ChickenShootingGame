package Levels;

import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import View.StartScreen;
import Controller.GameEngine;
import Controller.GameLoop;

public class FinishedLevel extends BaseLevelState implements BaseGameLevel{

	public FinishedLevel(GameLoop game) {
		super(game, -1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Update(double dt) {
		// TODO Auto-generated method stub
	}

	@Override
	public void Draw(double dt) {
		

		
	}

	@Override
	public void ChangeLevel(BaseLevelState pLevel) {
		// TODO Auto-generated method stub
		// Necessary according to powerpoint
		assert(pLevel != null);
		

		
		
	}

	@Override
	public void NextLevel() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public int GetID() {
	
		return dID;
	}

	@Override
	public void SetLevel(BaseLevelState pLevel) {
		
		
	}

	@Override
	public void LoadGraphics() {
	
		JFrame gameWinFrame = new JFrame("YOU WIN!");
		JLabel gameWinLabel = new JLabel(new ImageIcon(GameEngine.class.getClassLoader().getResource("WinningBackgroundFinal.png")));
		gameWinFrame.setSize(800,600);
		gameWinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWinLabel.setSize(800,600);
		gameWinFrame.add(gameWinLabel);
		gameWinFrame.setLayout(null);
		gameWinFrame.setVisible(true);
		GameLoop.PlaySound("WinningSound.wav");
		
		gameWinFrame.setLocation( 
				(int) StartScreen.width/2 - GameLoop.GAME_WINDOW_WIDTH/2, 
				(int) StartScreen.height/2 - GameLoop.GAME_WINDOW_HEIGHT/2
				);
		
		try
		{
			GameLoop.frame.setVisible(false);
			Thread.sleep(5000);
			GameLoop.frame.dispatchEvent(new WindowEvent(GameLoop.frame, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
		
		
		
		
		
	}

}
