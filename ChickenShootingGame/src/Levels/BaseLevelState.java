package Levels;

import javax.swing.JLabel;
import Controller.GameLoop;

/**
 * @author Andrew
 * Clean this class.
 * This class has two redundant methods with different names
 *  SetLevel(), ChangeLevel(),
 */
public class BaseLevelState implements BaseGameLevel {

	
	/**
	 * ID of a level
	 */
	public int dID = 0;
	
	/**
	 * 
	 */
	GameLoop owner;

	public  static  	JLabel 		backgroundImage;
	
	/**
	 * Initialize the state of the level by giving 
	 * it a gameloop and an ID
	 * @param game the gameLoop
	 * @param dID ID of a given level
	 */
	public BaseLevelState(GameLoop game, int dID){
		owner = game;
		this.dID = dID;
		LevelFactory.Assign(this);
	}
	
	/* (non-Javadoc)
	 * Go to the next level contained in the Level Factory
	 * @see Levels.BaseGameLevel#NextLevel()
	 */
	@Override
	public void NextLevel() {
		// TODO Auto-generated method stub
		 BaseLevelState pCurrent;
		 
		 pCurrent = LevelFactory.NextLevel(this);
		 
		// if(pCurrent == null){
		//	 pCurrent = LevelFactory.Finished();
		 //}
		
		owner.SetLevel(pCurrent);
		
	}

	/* (non-Javadoc)
	 * Get the current level ID
	 * @see Levels.BaseGameLevel#GetID()
	 */
	@Override
	public int GetID() {
		// TODO Auto-generated method stub
		return dID;
	}
	
	public void SetOwner(GameLoop gameLoop) {
		// TODO Auto-generated method stub
		owner = gameLoop;
	}
	
	public GameLoop GetOwner(){
	return owner;	
	}
	
	
	/* (non-Javadoc)
	 * Update the elements corresponding to a given level
	 * @see Levels.BaseGameLevel#Update(double)
	 */
	@Override
	public void Update(double dt) {
		// TODO Auto-generated method stub
		
	}

	
	
	/* (non-Javadoc)
	 * Draw the visual elements on screen corresponding to a given level
	 * 
	 * @see Levels.BaseGameLevel#Draw(double)
	 */
	@Override
	public void Draw(double dt) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * Change the current level.
	 * @see Levels.BaseGameLevel#ChangeLevel(Levels.BaseLevelState)
	 */
	@Override
	public void ChangeLevel(BaseLevelState pLevel) {
				owner.SetLevel(pLevel);	
	}

	
	/* (non-Javadoc)
	 * Set the current level
	 * @see Levels.BaseGameLevel#SetLevel(Levels.BaseLevelState)
	 */
	@Override
	public void SetLevel(BaseLevelState pLevel) {
		// TODO Auto-generated method stub
		owner.SetLevel(pLevel);
	}

	
	/* (non-Javadoc)
	 * @see Levels.BaseGameLevel#LoadGraphics()
	 * Load the graphics corresponding to a given level
	 */
	@Override
	public void LoadGraphics() {
		// TODO Auto-generated method stub
		
	}



}
