package Controller;

import View.GUI;

public class GameLoop implements Runnable{

	public static GUI gameGUI = new GUI();
	
	public void start() {

		Thread th = new Thread(this);
		th.start();

	}

	
	/* (non-Javadoc) This is  where the actual GAME LOOP is placed
	 * @see java.lang.Runnable#run()
	 * 
	 */
	public void run() {

		// run a long while (true) this means in our case "always"
		while (true) 
		{
			
		//	GameEngine.MonitorAndLimitTheAmountOfChickenInGame();
			GameEngine.RandomEnemyGenerator();
			GameEngine.CheckIfEnemyWasKilled();
			GameEngine.DetermineWetherPlayerHasReachedMaxScore();
			GameEngine.speedUpEnemyAppearance();
				
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
	
	
	
	
}
