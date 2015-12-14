package Levels;
import java.util.HashMap;
import java.util.Map;
import Controller.GameLoop;

public class LevelFactory {
	
	private static Map<Integer,BaseLevelState> levelMap = new HashMap<Integer, BaseLevelState>() ;
	
	public static BaseLevelState NextLevel(BaseGameLevel pCurrent){
		if(pCurrent == null) {
			return null;
		}
		
		int nextLevelID = pCurrent.GetID()+1;
		
		if(levelMap.containsKey(nextLevelID)){
			GameLoop.PlaySound("Level"+nextLevelID+".wav");
			return levelMap.get(nextLevelID);
		}
		else{
			return levelMap.entrySet().iterator().next().getValue();
		//	return null;
		}
		
		
		
		
	}

	public static Map<Integer,BaseLevelState> GetMap(){
		return  levelMap;
	}
	
	public static BaseLevelState Finished(){
		if(levelMap.containsKey(-1)){
			return levelMap.get(-1);
		}else{
			return null;
		}
		

	}

	public static BaseLevelState Get(double dID){
		// IF ID is the same as the FINAL level. Stay at the FINAL level
		if(dID == levelMap.get(levelMap.size()-1).dID){
			return null;
		}
		//ELSE get the Level with the given ID
		else{
			return levelMap.get(dID);
		}
	}
		
	public static BaseLevelState GetFirstLevel() {
		if(levelMap != null){
		
			GameLoop.PlaySound("Level1.wav");
			return levelMap.entrySet().iterator().next().getValue();
		}
		else{
			return null;
		}
		
		
		
	}

	public static void Assign(BaseLevelState pLevel) {
		if(pLevel != null){
			levelMap.put(pLevel.GetID(), pLevel);
		}	
	}
	
	
}
