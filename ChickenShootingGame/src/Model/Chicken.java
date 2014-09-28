package Model;


import java.util.Random;
import Controller.GameEngine;
import Controller.GameLoop;
import View.GameScreen;


/**
 * @author Andrew
 *
 * The class that specifies a chicken's properties
 * This class serves as a Model within the Model-View-Controller Design Pattern
 */
public class Chicken extends Unit {
	
	public  		int 				value 			= 0;
	public  static 	int  				directionSwitch = 0;
	public  		Random 				rand 			= new Random();
	public  static  int 				n 				= 2;

	public Chicken(String enemyType) {
		super(enemyType);	
		MobilizeEnemyInRandomXDirection(new Random().nextInt(2));
		MobilizeEnemyInRandomYDirection(new Random().nextInt(2));
	    GameEngine.PlaySound("ninjaChicken.wav");
	}



	

	
	
	/** An enemy chicken must be created
	 * 
	 */



	/** When an enemy chicken is created, it does not move
	 *  When this method is called, the enemy chicken walks to a random location
	 *  on the screen. This method has to be called repeatedly in a loop.
	 */
	public void ScatterToRandomlocation()
	{	
		
		switch(directionSwitch)
		{
		case 0:
			//	value+=1;
				unitEnemyPictureLabel.setLocation(this.unitEnemyPictureLabel.getLocation().x - value - new Random().nextInt(5), this.unitEnemyPictureLabel.getLocation().y + value);
				if(this.unitEnemyPictureLabel.getLocation().x <= 0 || 
						this.unitEnemyPictureLabel.getLocation().x >= GameScreen.GAME_WINDOW_WIDTH ||
						this.unitEnemyPictureLabel.getLocation().y <= 0 || 
						this.unitEnemyPictureLabel.getLocation().y >= GameScreen.GAME_WINDOW_HEIGHT)
				{
					//Attempt to resolve Ghost chickens that don't appear on the screen
				GameLoop.gameObjects.remove(this);
					System.out.println("Chicken has walked away from screen!");
				}
				
			break;
				
		case 1:	
			//	value+=2;
			unitEnemyPictureLabel.setLocation(this.unitEnemyPictureLabel.getLocation().x - value, this.unitEnemyPictureLabel.getLocation().y - value - new Random().nextInt(5));
				if(this.unitEnemyPictureLabel.getLocation().x <= 0 || 
						this.unitEnemyPictureLabel.getLocation().x >= GameScreen.GAME_WINDOW_WIDTH ||
						this.unitEnemyPictureLabel.getLocation().y <= 0 || 
						this.unitEnemyPictureLabel.getLocation().y >= GameScreen.GAME_WINDOW_HEIGHT)
				{
					//Attempt to resolve Ghost chickens that don't appear on the screen
					GameLoop.gameObjects.remove(this);
				System.out.println("Chicken has walked away from screen!");
				}
				
			break;
			
			
		case 2:
				//value+=1;
			unitEnemyPictureLabel.setLocation(this.unitEnemyPictureLabel.getLocation().x + value + new Random().nextInt(5), this.unitEnemyPictureLabel.getLocation().y + value);
				if(this.unitEnemyPictureLabel.getLocation().x <= 0 || 
						this.unitEnemyPictureLabel.getLocation().x >= GameScreen.GAME_WINDOW_WIDTH ||
						this.unitEnemyPictureLabel.getLocation().y <= 0 || 
						this.unitEnemyPictureLabel.getLocation().y >= GameScreen.GAME_WINDOW_HEIGHT)
				{
					//Attempt to resolve Ghost chickens that don't appear on the screen
					GameLoop.gameObjects.remove(this);
					System.out.println("Chicken has walked away from screen!");
				}
			break;
			
		case 3:
			//value+=4;
			//chicken.setLocation(this.chicken.getLocation().x + value , this.chicken.getLocation().y + value);
			if(this.unitEnemyPictureLabel.getLocation().x <= 0 || 
					this.unitEnemyPictureLabel.getLocation().x >= GameScreen.GAME_WINDOW_WIDTH ||
					this.unitEnemyPictureLabel.getLocation().y <= 0 || 
					this.unitEnemyPictureLabel.getLocation().y >= GameScreen.GAME_WINDOW_HEIGHT)
			{
				GameLoop.gameObjects.remove(this);
				System.out.println("Chicken has walked away from screen!");
			}
			
			break;
			
		
		}				
				
	}

	
	/** Print the enemy chickens location for testing purposes
	 * 
	 */
	public void PrintChickenLocation()
	{
		if(enemyKilled == false)
		{
		System.out.println("Chicken Location = " + "x: " + unitEnemyPictureLabel.getLocation().x + "y: " + unitEnemyPictureLabel.getLocation().y );
		}
	}

	

	



	

	
	
	
}
