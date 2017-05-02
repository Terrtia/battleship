package battleship.model.doa;

/**
 * 
 *  Abstract factory class
 *
 */
public abstract class AbstractDoaFactory {
	
	// list of DOA types supported by the factory
	public static final int TXT_DOA = 1;
	
	// a method for each DOA that can be created
	/**
	 * create a DoaNormalGame for a normal game with Txt DOA
	 * @return DoaNormalGame ( TxtNormalGameFactory, ... )
	 */
	public abstract DoaNormalGame getNormalGameTxtDoa();
	
	/**
	 * create a factory
	 * @param factoryType int, factory number
	 * @return AbstractDoaFactory ( TxtDoaFactory, ...)
	 */
	public static AbstractDoaFactory getFactory(int factoryType) {
		
		switch(factoryType){
			case TXT_DOA:
				return new TxtDoaFactory();
			default:
				return null;
		}
	}

}
