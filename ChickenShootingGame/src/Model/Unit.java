package Model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import View.GameScreen;
import Controller.GameEngine;
import Controller.GameLoop;
import Controller.UserInput;


//public class Unit implements Runnable{
public class Unit{
	
	public Unit unit;
	
	public  		int 				xPosition;
	public  		int 				yPosition;
	
	public  		boolean 			enemyKilled 	= false;
	public  		JLabel 				unitEnemyPictureLabel;
	
	public   	int xAxisCollisionCheck = 1;
	public  	int yAxisCollisionCheck = 1;
	
	public Unit(String enemyType)
	{
		unitEnemyPictureLabel = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource(enemyType)));	
	//	GameLoop.gameObjects.add(this);
		GameScreen.frame.add(unitEnemyPictureLabel);
		GameEngine.player = new UserInput(this);
		
		xPosition = 100 + (int) (((Math.random() * 360) % 360)*0.6);
		yPosition = 100 + (int) (((Math.random() * 360) % 360)*0.6);
		unitEnemyPictureLabel.setLocation(xPosition, yPosition);
		unitEnemyPictureLabel.setSize(238, 249);
	
		
	}
	
	public void draw()
	{
		// What do I put here actually? 
		// I already call the necessary methods within the constructor..
		//TODO: Gotta figure this one out.
		//TODO: Find out whether it's necessary to use this method
		//unit.draw(); 
	}

	public  void MobilizeEnemyInRandomXDirection(int x)
	{
		xAxisCollisionCheck = x;
	}
	
	public  void MobilizeEnemyInRandomYDirection(int y)
	{
		yAxisCollisionCheck = y;
	}

	public void collide()
	{
		switch (xAxisCollisionCheck)
		{
			case 0:
				;
				if	(unitEnemyPictureLabel.getLocation().x >= GameScreen.GAME_WINDOW_WIDTH)
				{	
					xAxisCollisionCheck = 1;
				}
				break;

			case 1: 
		
				if (unitEnemyPictureLabel.getLocation().x <= 0)
				{	
					xAxisCollisionCheck = 0;
				}
				break;

			default:
				//nothing
				break;
		}


		switch (yAxisCollisionCheck)
		{
			case 0:

			
				if (unitEnemyPictureLabel.getLocation().y >= GameScreen.GAME_WINDOW_HEIGHT)
				{
					yAxisCollisionCheck = 1;
				}
				break;

			case 1:
	
				if (unitEnemyPictureLabel.getLocation().y <= 0)
				{
					yAxisCollisionCheck = 0;
				}
				break;

			default:
				//nothing
				break;
		}


	}
	
	public void move()
	{
		switch (xAxisCollisionCheck)
		{
			case 0: 
				unitEnemyPictureLabel.setLocation(unitEnemyPictureLabel.getLocation().x += 4, unitEnemyPictureLabel.getLocation().y);
				break;

			case 1: 
				unitEnemyPictureLabel.setLocation(unitEnemyPictureLabel.getLocation().x -= 4, unitEnemyPictureLabel.getLocation().y);
				break;

			default:
				//nothing
				break;
		}


		switch (yAxisCollisionCheck)
		{
			case 0:
				unitEnemyPictureLabel.setLocation(unitEnemyPictureLabel.getLocation().x, unitEnemyPictureLabel.getLocation().y+= 4);
				break;
			case 1:
				unitEnemyPictureLabel.setLocation(unitEnemyPictureLabel.getLocation().x, unitEnemyPictureLabel.getLocation().y-= 4);
				break;

			default:
				//nothing
				break;
		}


	}
	
	public void update()
	{
		if(unitEnemyPictureLabel.getLocation().x <= 0 || 
				unitEnemyPictureLabel.getLocation().x >= GameScreen.GAME_WINDOW_WIDTH ||
				unitEnemyPictureLabel.getLocation().y <= 0 || 
						unitEnemyPictureLabel.getLocation().y >= GameScreen.GAME_WINDOW_HEIGHT)
		{

			System.out.println("Location of Ghost Chicken: " + unitEnemyPictureLabel.getLocation());
			System.out.println("Chicken has walked away from screen!");
			
		//	chick.chickenKilled = true;	
			
			// WHY if enemy killed you lose points? Does not make
			// any sense!
			if(enemyKilled == true)
			{
				//GameLoop.score = GameLoop.score -50;
				
				//GameScreen.scoreLabel.setText("Score:" + GameLoop.score);
				
				//GameEngine.PlaySound("WarningSound.wav");
				
			//	chick.chicken.setLocation(0, 0);
				
				// Misschien moet ik dit ook gebruiken?
				//GameLoop.gameObjects.remove();
				
				GameLoop.gameObjects.remove(unitEnemyPictureLabel);
				
				GameScreen.frame.remove(unitEnemyPictureLabel);
			}
			
		}
	}
	
	/** 
	 * Called when enemy button has been clicked on
	 */
	public void EnemyKilled()
	{
			//If enemy is killed. Show Gun Flash
			GameEngine.hitFlash.setLocation(this.unitEnemyPictureLabel.getX()-400,this.unitEnemyPictureLabel.getY()-300);	
			
			//Show Gun Flash for 60 milliseconds
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
			GameLoop.score += 100;
			GameLoop.amountOfEnemiesKilled+= 1;
			GameScreen.scoreLabel.setForeground(Color.BLACK);
			GameScreen.scoreLabel.setText("Score: " + GameLoop.score);
			
			
			//Clear chicken from screen
			enemyKilled = true;
			unitEnemyPictureLabel.setVisible(false);
			GameLoop.gameObjects.remove(unitEnemyPictureLabel);

			//Play explosion sound
			GameEngine.PlaySound("realShotgun.wav");   
	       
	        //Lower the enemy amount checker for GUI cleanliness
			GameLoop.enemyAmountChecker--;
			System.out.println("enemyAmountChecker: " + GameLoop.enemyAmountChecker);
			
		
	}
	
	
	
	
}
