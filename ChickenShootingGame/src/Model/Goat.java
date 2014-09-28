package Model;

import java.util.Random;

import Controller.GameEngine;
import Controller.GameLoop;
import View.GameScreen;

public class Goat extends Unit{

	public Goat(String enemyType) {
		super(enemyType);
		
	    MobilizeEnemyInRandomXDirection(new Random().nextInt(2));
		MobilizeEnemyInRandomYDirection(new Random().nextInt(2));
		GameEngine.PlaySound("GoatSound.wav");
	   
	}

}
