package Controller;

import java.util.LinkedList;
import java.util.Random;

import Model.Chicken;
import Model.Goat;
import Model.Snake;
import Model.Unit;
import View.GameOverScreen;
import View.GameScreen;
import View.WinScreen;

public class GameLoop implements Runnable{

	public 		static 		GameScreen 			gameGUI 				= new GameScreen();
	public 		static 		int 				score 					= 0;
	public 		static 		LinkedList<Unit> 	gameObjects				= new LinkedList<Unit>();
	public  	static 		int 				enemyAmountChecker 		= 1;	
	public 		static		int					enemySelector 			= new Random().nextInt(3);
	public  	static 		int					speedUP 				= 0;
	public		static		int 				amountOfEnemiesKilled	= 0;
	

	public void start() {

		Thread th = new Thread(this);
		th.start();
	}
	
	public void update()
	{
		RandomEnemyGenerator();
		
		for(Unit unit : gameObjects)
		{
			unit.update();
			unit.move();
			unit.collide();
			
		}
		
		// End Game if player Loses
		if(score <= -10 )
		{
			new GameOverScreen();
		}
		
		// End Game if player wins
		if(score >= 5000)
		{
			new WinScreen();
		}
		
		// "Play Gun Reload Sound every 4 shots"
		if(amountOfEnemiesKilled == 4)
		{
			GameEngine.PlaySound("ShotgunCocking.wav");
			amountOfEnemiesKilled = 0;
		}
		
		
		
	}

	public void run() {

		while (true) 
		{
					
			update();
			
	
			try
			{
				
				
				Thread.sleep(20);
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}		
		}
		
		
	}
	
	/** Generate enemies, chickens, cows, whatever, this will be a pseudo-generic method
	 * 
	 * 
	 * 
	 */
	public static void RandomEnemyGenerator()
	{
		if (enemyAmountChecker > 0 && enemyAmountChecker < 2)
		{
			try
			{
				//TODO: Moet in een 'factory' method geconverteerd worden
				//TODO: Bas: XML uitzoeken om units in te laden
				//TODO: Switch-statement moet weg
				Thread.sleep(200);
				switch (enemySelector)
				{
					case 0:	
						gameObjects.add(new Chicken("chickenator2.png")); 	
						enemySelector = new Random().nextInt(3); 

						
						break;
						
				
					case 1:	gameObjects.add(new Goat("goat.png")); 	
						enemySelector = new Random().nextInt(3); 
					
						
						break;
				
					case 2: 
						gameObjects.add(new Snake("snake.png")); 	
						enemySelector = new Random().nextInt(3);
			
						break;
				}
				

				
				
				enemyAmountChecker++;	
			}
			
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
				
			
		}
	}
	
	/** To make the game more challenging, the speed of which
	 *  the enemy chickens appear is raised every 1000 points.
	 */
	public static void speedUpEnemyAppearance()
	{
		switch(score)
		{
			case 1000:
				if(speedUP <= 600)
				speedUP += 200;
				System.out.println("Speedup at: " + speedUP);
				break;
				
			case 2000:
				
				if(speedUP <= 600)
				speedUP += 150;
				System.out.println("Speedup at: " + speedUP);
				break;
				
			case 3000:
				if(speedUP <= 600)
				speedUP += 125;
				System.out.println("Speedup at: " + speedUP);
				break;
				
			case 4000:
				;
				if(speedUP <= 600);
				speedUP += 200;
				System.out.println("Speedup at: " + speedUP);
				
				break;
		}
	}
	
	
}
