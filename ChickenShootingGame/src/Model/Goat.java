package Model;

import java.util.Random;

import Controller.GameEngine;

public class Goat extends Unit{

	public Goat() {
		super("goat.png");
		
	    MobilizeEnemyInRandomXDirection(new Random().nextInt(2));
		MobilizeEnemyInRandomYDirection(new Random().nextInt(2));
		GameEngine.PlaySound("GoatSound.wav");
	   
	}

}
