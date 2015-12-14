package Levels;



public abstract interface BaseGameLevel {
	
		public void Update(double dt);
		
		public void Draw(double dt);
		
		public void ChangeLevel(BaseLevelState pLevel);
		
		public void SetLevel(BaseLevelState pLevel);

		public void NextLevel();
		
		public int GetID();

		public void LoadGraphics();

}
