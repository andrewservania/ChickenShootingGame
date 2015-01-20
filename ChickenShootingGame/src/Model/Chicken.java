package Model;


import java.util.Random;

import Controller.GameEngine;

/**
 * @author Andrew
 *
 * The class that specifies a chicken's properties
 * This class serves as a Model within the Model-View-Controller Design Pattern
 */
public class Chicken extends Unit {
	
	public Chicken() {
		super("chickenator2.png");	
		MobilizeEnemyInRandomXDirection(new Random().nextInt(2));
		MobilizeEnemyInRandomYDirection(new Random().nextInt(2));
	    GameEngine.PlaySound("ninjaChicken.wav");
	}
	
	
}
