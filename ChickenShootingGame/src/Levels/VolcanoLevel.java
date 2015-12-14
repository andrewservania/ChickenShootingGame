package Levels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Controller.GameLoop;



//Level 2 is Volcano Level
public class VolcanoLevel extends BaseLevelState implements BaseGameLevel {


	public VolcanoLevel(GameLoop gameLoop) {
		super(gameLoop, 2);
	}

	@Override
	public void Update(double dt) {
		// TODO Auto-generated method stub
		// if ( ++m_nCount >= 3 ) ACCORDING TO BERT's StateWithFactoryMethod Code
	}

	@Override
	public void Draw(double dt) {
		// TODO Auto-generated method stub
		//Do your level related stuff HERE - According to Bert's code
	}
	
	@Override
	public void ChangeLevel(BaseLevelState pLevel) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void SetLevel(BaseLevelState pLevel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NextLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int GetID() {
		// TODO Auto-generated method stub
		return dID;
	}
	
	@Override
	public void LoadGraphics() {
		// TODO Auto-generated method stub
		backgroundImage 		= new JLabel(new ImageIcon(GameLoop.class.getClassLoader().getResource("VolcanoLevel.png")));
		backgroundImage.setSize(GameLoop.GAME_WINDOW_WIDTH,GameLoop.GAME_WINDOW_HEIGHT);
		GameLoop.frame.setContentPane(backgroundImage);
	}

}
