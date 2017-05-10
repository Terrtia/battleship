// TXT DOA Factory implementation
package battleship.model.doa;

/**
 * 
 * this class create the correct Txt DOA
 *
 */
public class TxtDoaFactory extends AbstractDoaFactory {

	public TxtDoaFactory() {
		
	}

	/**
	 * create a DoaNormalGame for a normal game with Txt DOA
	 * @return DoaNormalGame ( TxtNormalGameFactory, ... )
	 */
	@Override
	public DoaNormalGame getNormalGameTxtDoa() {
		//TxtDoaFactory implement DoaNormalGame
		return new TxtNormalGameDoa();
	}

}
