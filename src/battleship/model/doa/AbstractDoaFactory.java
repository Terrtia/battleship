package battleship.model.doa;

public abstract class AbstractDoaFactory {
	
	// list of DOA types supported by the factory
	public static final int TXT_DOA = 1;
	
	// a method for each DOA that can be created
	public abstract DoaNormalGame getNormalGameTxtDoa();
	
	// create a factory
	public static AbstractDoaFactory getFactory(int factoryType) {
		
		switch(factoryType){
			case TXT_DOA:
				return new TxtDoaFactory();
			default:
				return null;
		}
	}

}
