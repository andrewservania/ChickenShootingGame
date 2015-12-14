package Model;




public class UnitFactory {

	public static Unit create(String input)
	{
	
		switch(input)
		{
			case "Chicken": return new Chicken();
		
			case "Goat": 	return new Goat( );		
		
			case "Snake":	return new Snake( ); 
		}
		return null;
	}

}
