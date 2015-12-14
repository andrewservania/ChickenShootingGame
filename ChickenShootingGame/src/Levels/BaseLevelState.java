package Levels;

import javax.swing.JLabel;
import Controller.GameLoop;

public class BaseLevelState implements BaseGameLevel {

	public int dID = 0;
	GameLoop pOwner;

	public  static  	JLabel 		backgroundImage;
	
	public BaseLevelState(GameLoop game, int dID){
		pOwner = game;
		this.dID = dID;
		LevelFactory.Assign(this);
	}
	
	@Override
	public void NextLevel() {
		// TODO Auto-generated method stub
		 BaseLevelState pCurrent;
		 
		 pCurrent = LevelFactory.NextLevel(this);
		 
		// if(pCurrent == null){
		//	 pCurrent = LevelFactory.Finished();
		 //}
		
		pOwner.SetLevel(pCurrent);
		
	}

	@Override
	public int GetID() {
		// TODO Auto-generated method stub
		return dID;
	}
	
	public void SetOwner(GameLoop gameLoop) {
		// TODO Auto-generated method stub
		pOwner = gameLoop;
	}
	
	public GameLoop GetOwner(){
	return pOwner;	
	}
	
	@Override
	public void Update(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Draw(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ChangeLevel(BaseLevelState pLevel) {
				pOwner.SetLevel(pLevel);	
	}

	@Override
	public void SetLevel(BaseLevelState pLevel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LoadGraphics() {
		// TODO Auto-generated method stub
		
	}



}
