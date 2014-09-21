package Model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.ImageIcon;

import Controller.GameEngine;
import Controller.UserInput;

import javax.swing.*;

import View.GUI;


/**
 * @author Andrew
 *
 * The class that specifies a chicken's properties
 * This class serves as a Model within the Model-View-Controller Design Pattern
 */
public class Chicken extends Enemy {
	

	public Chicken(String enemyType) {
		super(enemyType);
		// TODO Auto-generated constructor stub
	}


	public  		int 				value 			= 0;
	public  static 	int  				directionSwitch = 0;
	public  		Random 				rand 			= new Random();
	public  static  int 				n 				= 2;
	

	
	
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
				enemy.setLocation(this.enemy.getLocation().x - value - new Random().nextInt(5), this.enemy.getLocation().y + value);
				if(this.enemy.getLocation().x <= 0 || 
						this.enemy.getLocation().x >= GUI.GAME_WINDOW_WIDTH ||
						this.enemy.getLocation().y <= 0 || 
						this.enemy.getLocation().y >= GUI.GAME_WINDOW_HEIGHT)
				{
					//Attempt to resolve Ghost chickens that don't appear on the screen
				GameEngine.enemies.remove(this);
					System.out.println("Chicken has walked away from screen!");
				}
				
			break;
				
		case 1:	
			//	value+=2;
			enemy.setLocation(this.enemy.getLocation().x - value, this.enemy.getLocation().y - value - new Random().nextInt(5));
				if(this.enemy.getLocation().x <= 0 || 
						this.enemy.getLocation().x >= GUI.GAME_WINDOW_WIDTH ||
						this.enemy.getLocation().y <= 0 || 
						this.enemy.getLocation().y >= GUI.GAME_WINDOW_HEIGHT)
				{
					//Attempt to resolve Ghost chickens that don't appear on the screen
					GameEngine.enemies.remove(this);
				System.out.println("Chicken has walked away from screen!");
				}
				
			break;
			
			
		case 2:
				//value+=1;
			enemy.setLocation(this.enemy.getLocation().x + value + new Random().nextInt(5), this.enemy.getLocation().y + value);
				if(this.enemy.getLocation().x <= 0 || 
						this.enemy.getLocation().x >= GUI.GAME_WINDOW_WIDTH ||
						this.enemy.getLocation().y <= 0 || 
						this.enemy.getLocation().y >= GUI.GAME_WINDOW_HEIGHT)
				{
					//Attempt to resolve Ghost chickens that don't appear on the screen
					GameEngine.enemies.remove(this);
					System.out.println("Chicken has walked away from screen!");
				}
			break;
			
		case 3:
			//value+=4;
			//chicken.setLocation(this.chicken.getLocation().x + value , this.chicken.getLocation().y + value);
			if(this.enemy.getLocation().x <= 0 || 
					this.enemy.getLocation().x >= GUI.GAME_WINDOW_WIDTH ||
					this.enemy.getLocation().y <= 0 || 
					this.enemy.getLocation().y >= GUI.GAME_WINDOW_HEIGHT)
			{
				GameEngine.enemies.remove(this);
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
		System.out.println("Chicken Location = " + "x: " + enemy.getLocation().x + "y: " + enemy.getLocation().y );
		}
	}

	

	



	

	
	
	
}
