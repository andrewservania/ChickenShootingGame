package Model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import View.GUI;
import javax.swing.*;


public class Chicken implements Runnable {
	
	public  int 				xPosition;
	public  int 				yPosition;
	public  int 				value 			= 0;
	public  static 	int  		directionSwitch = 0;
	public  boolean 			chickenKilled 	= false;
	public  JLabel 				chicken;
	public  Random 				rand 			= new Random();
	
	public Chicken()
	{
		xPosition = (int) (((Math.random() * 360) % 360)*1.3);
		yPosition = (int) (((Math.random() * 360) % 360)*1.3);
		
		directionSwitch = new Random().nextInt(4);
		
		chicken = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("chickenator2.png")));
		chicken.setLocation(xPosition, yPosition);
		chicken.setSize(238, 249);
		
		chicken.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				ChickenKilled();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
		
	
	}

	public void ScatterToRandomlocation()
	{	
		
		switch(directionSwitch)
		{
		case 0:
				value+=1;
				chicken.setLocation(this.chicken.getLocation().x - value - new Random().nextInt(50), this.chicken.getLocation().y + value);
				if(this.chicken.getLocation().x <= 0 || 
						this.chicken.getLocation().x >= 1024 ||
						this.chicken.getLocation().y <= 0 || 
						this.chicken.getLocation().y >= 768)
				{
					//Attempt to resolve Ghost chickens that don't appear on the screen
				GUI.enemies.remove(this);
				//	System.out.println("Chicken has walked away from screen!");
				}
				
			break;
				
		case 1:	
				value+=2;
				chicken.setLocation(this.chicken.getLocation().x - value, this.chicken.getLocation().y - value - new Random().nextInt(90));
				if(this.chicken.getLocation().x <= 0 || 
						this.chicken.getLocation().x >= 1024 ||
						this.chicken.getLocation().y <= 0 || 
						this.chicken.getLocation().y >= 768)
				{
					//Attempt to resolve Ghost chickens that don't appear on the screen
				GUI.enemies.remove(this);
				//	System.out.println("Chicken has walked away from screen!");
				}
				
			break;
			
			
		case 2:
				value+=1;
				chicken.setLocation(this.chicken.getLocation().x + value + new Random().nextInt(50), this.chicken.getLocation().y + value);
				if(this.chicken.getLocation().x <= 0 || 
						this.chicken.getLocation().x >= 1024 ||
						this.chicken.getLocation().y <= 0 || 
						this.chicken.getLocation().y >= 768)
				{
					//Attempt to resolve Ghost chickens that don't appear on the screen
				GUI.enemies.remove(this);
				//	System.out.println("Chicken has walked away from screen!");
				}
			break;
			
		case 3:
			value+=4;
			chicken.setLocation(this.chicken.getLocation().x + value , this.chicken.getLocation().y + value);
			if(this.chicken.getLocation().x <= 0 || 
					this.chicken.getLocation().x >= 1024 ||
					this.chicken.getLocation().y <= 0 || 
					this.chicken.getLocation().y >= 768)
			{
			
			//	System.out.println("Chicken has walked away from screen!");
			}
			
			break;
			
		
		}				
				
	}

	public void PrintChickenLocation()
	{
		if(chickenKilled == false)
		{
		System.out.println("Chicken Location = " + "x: " + chicken.getLocation().x + "y: " + chicken.getLocation().y );
		}
	}

	//Called when chicken button has been clicked on
	public void ChickenKilled()
	{
		GUI.hitFlash.setLocation(this.chicken.getX()-400,this.chicken.getY()-300);

		Timer stopWatch  = new Timer(60,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUI.hitFlash.setVisible(false);
			}
		});
        stopWatch.setRepeats(false);
        stopWatch.start();
        
        GUI.hitFlash.setVisible(true);
        
			//Update Score
			GUI.score += 100;
			GUI.scoreLabel.setForeground(Color.BLACK);
			GUI.scoreLabel.setText("Score: " + GUI.score);
			
			
			//Clear chicken from screen
			chickenKilled = true;
			chicken.setVisible(false);
			GUI.enemies.remove(chicken);

			//Play explosion sound
			GUI.PlaySound("explosion2.wav");   
	       
	        //Lower the enemy amount checker for GUI cleanliness
			GUI.enemyAmountChecker--;
			System.out.println("enemyAmountChecker: " + GUI.enemyAmountChecker);
			
		//	GUI.hitFlash.setVisible(false);
		
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
			
			try
			{
				ScatterToRandomlocation();
				Thread.sleep(200);
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
			
		}
			

	}


}
