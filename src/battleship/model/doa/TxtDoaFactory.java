// TXT DOA Factory implementation
package battleship.model.doa;

public class TxtDoaFactory extends AbstractDoaFactory {

	public TxtDoaFactory() {
		
	}

	@Override
	public DoaNormalGame getNormalGameTxtDoa() {
		//TxtDoaFactory implement DoaNormalGame
		return new TxtNormalGameDoa();
	}

}
