package Levels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Controller.GameLoop;


//Level 1 is Farm Level
public class FarmLevel extends BaseLevelState implements BaseGameLevel{

	
	public FarmLevel(GameLoop gameLoop) {
		super(gameLoop, 1);

	}

	@Override
	public void Update(double dt) {
		// TODO Auto-generated method stub
		// if ( ++m_nCount >= 3 ) ACCORDING TO BERT's StateWithFactoryMethod Code
	}

	@Override
	public void Draw(double dt) {
	}

	@Override
	public void ChangeLevel(BaseLevelState pLevel) {
		// TODO Auto-generated method stub
		// Necessary according to powerpoint
		assert(pLevel != null);
	}

	@Override
	public void NextLevel() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public int GetID() {
	return dID;
	}

	@Override
	public void SetLevel(BaseLevelState pLevel) {	
	}

	@Override
	public void LoadGraphics() {
		backgroundImage 		= new JLabel(new ImageIcon(GameLoop.class.getClassLoader().getResource("chickenBackground.jpg")));
		backgroundImage.setSize(GameLoop.GAME_WINDOW_WIDTH,GameLoop.GAME_WINDOW_HEIGHT);
		GameLoop.frame.setContentPane(backgroundImage);
	}


}
