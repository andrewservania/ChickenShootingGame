package Model;

import java.util.Random;

import Controller.GameEngine;
import Controller.GameLoop;


public class Snake extends Unit {

	public Snake() {
		super("snake.png");
		MobilizeEnemyInRandomXDirection(new Random().nextInt(2));
		MobilizeEnemyInRandomYDirection(new Random().nextInt(2));
		GameEngine.PlaySound("SnakeSound.wav");
	}

}
