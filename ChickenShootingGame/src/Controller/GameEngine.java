package Controller;


import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;




import Levels.BaseGameLevel;
import View.GameScreen;


/**
 * @author Andrew
 * 
 * 
 * The class which is responsible for most game related dynamics
 * This class serves as a 'Controller' within the Model-View-Controller 
 * Design Pattern
 */
public class GameEngine {



	// MOET HIER NIET MEER! Enemy units moeten NU in de linkedlist in GameLoop Komen
	//public	static  	ArrayList<Unit> 	enemies 				= new ArrayList<Unit>();
	
	
	public  static 		int 				numberOfEnemychickens 	= 1;
	public 	static		boolean				enemyDirectionChanger 	= false;

	public 	static 		boolean				soundPlaying 			= false;
	public  static		JLabel				hitFlash				= new JLabel(new ImageIcon(GameScreen.class.getClassLoader().getResource("explosionTransparent.png")));
	public  static 		Clip 				clip;
	public  static  	UserInput 			player;
	
	//Cursor adaptation to crossHair
	public 	static		Toolkit 			toolkit 				= Toolkit.getDefaultToolkit(); 
	public	static		Image 				image 					= toolkit.getImage(GameScreen.class.getClassLoader().getResource("crosshair.png"));
	public	static		Point 				hotSpot 				= new Point(0,0);
	public 	static		Cursor 				cursor 					= toolkit.createCustomCursor(image, hotSpot, "crossHair");
	public 	static		int					enemySelector 			= new Random().nextInt(3);
	
	/**
	 * @param filePath of the audio file to be played.
	 * 
	 * This method is called when chicken emerges, dies, 
	 * during start screen, game over screen and win screen
	 */
	
	public GameEngine(){

	}
	
	
	public static void PlaySound(String filePath)
	{ 
		 URL url = GameScreen.class.getClassLoader().getResource(filePath);
		 
		if(soundPlaying == false)
		{		
		
			try
			{	
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.loop(0);
			}
			catch (IOException | UnsupportedAudioFileException | LineUnavailableException   ex2)
			{
				ex2.printStackTrace();
			}
		}
	 
	}
   
	
	public void SetLevel(BaseGameLevel pLevel){
		
		pLevel.Draw(GameLoop.dt);
	}

}
