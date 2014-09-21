package Model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import View.GUI;
import Controller.GameEngine;
import Controller.UserInput;

public class Enemy implements Runnable{

	
	public  		int 				xPosition;
	public  		int 				yPosition;
	
	public  		boolean 			enemyKilled 	= false;
	public  		JLabel 				enemy;
	
	public   	int xAxisCheck = 1;
	public  	int yAxisCheck = 1;
	
	public Enemy(String enemyType)
	{
		
		xPosition = 100 + (int) (((Math.random() * 360) % 360)*0.6);
		yPosition = 100 + (int) (((Math.random() * 360) % 360)*0.6);
		enemy = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource(enemyType)));
		enemy.setLocation(xPosition, yPosition);
		enemy.setSize(238, 249);
		
		GameEngine.player = new UserInput(this);
	}

	@Override
	public void run() {
		// enemy animation
		while(true)
		{
			
			try
			{
		
				BounceAroundScreen();

				Thread.sleep(100);
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
			
		}
		
	}

	public  void MobilizeEnemyInRandomXDirection(int x)
	{
		xAxisCheck = x;
	}
	
	public  void MobilizeEnemyInRandomYDirection(int y)
	{
		yAxisCheck = y;
	}
	
	
	
	
	public void BounceAroundScreen()
	{
		switch (xAxisCheck)
		{
			case 0: 
				enemy.setLocation(enemy.getLocation().x += 5, enemy.getLocation().y);
				;
				if	(enemy.getLocation().x >= GUI.GAME_WINDOW_WIDTH)
				{	
					xAxisCheck = 1;
				}
				break;

			case 1: 
				enemy.setLocation(enemy.getLocation().x -= 5, enemy.getLocation().y);
				if (enemy.getLocation().x <= 0)
				{	
					xAxisCheck = 0;
				}
				break;

			default:
				//nothing
				break;
		}


		switch (yAxisCheck)
		{
			case 0:

				enemy.setLocation(enemy.getLocation().x, enemy.getLocation().y+= 5);
				if (enemy.getLocation().y >= GUI.GAME_WINDOW_HEIGHT)
				{
					yAxisCheck = 1;
				}
				break;

			case 1:
				enemy.setLocation(enemy.getLocation().x, enemy.getLocation().y-= 5);
				if (enemy.getLocation().y <= 0)
				{
					yAxisCheck = 0;
				}
				break;

			default:
				//nothing
				break;
		}


	}
	
	
	/** 
	 * Called when enemy button has been clicked on
	 */
	public void EnemyKilled()
	{
			GameEngine.hitFlash.setLocation(this.enemy.getX()-400,this.enemy.getY()-300);
	
			
			Timer stopWatch  = new Timer(60,new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					GameEngine.hitFlash.setVisible(false);
				}
			});
			
			
	        stopWatch.setRepeats(false);
	        stopWatch.start();
	        
			GameEngine.hitFlash.setVisible(true);
	        
			//Update Score
			GameEngine.score += 100;
			GameEngine.scoreLabel.setForeground(Color.BLACK);
			GameEngine.scoreLabel.setText("Score: " + GameEngine.score);
			
			
			//Clear chicken from screen
			enemyKilled = true;
			enemy.setVisible(false);
			GameEngine.enemies.remove(enemy);

			//Play explosion sound
			GameEngine.PlaySound("explosion2.wav");   
	       
	        //Lower the enemy amount checker for GUI cleanliness
			GameEngine.enemyAmountChecker--;
			System.out.println("enemyAmountChecker: " + GameEngine.enemyAmountChecker);
			
		
	}
	
	
	
	
}
