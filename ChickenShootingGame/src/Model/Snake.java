package Model;

import java.util.Random;

import Controller.GameEngine;
import Controller.GameLoop;
import View.GameScreen;

public class Snake extends Unit {

	public Snake(String enemyType) {
		super(enemyType);
		MobilizeEnemyInRandomXDirection(new Random().nextInt(2));
		MobilizeEnemyInRandomYDirection(new Random().nextInt(2));
		
		GameEngine.PlaySound("SnakeSound.wav");
	}

}
