// DOA use for normal (default) game
package battleship.model.doa;

import battleship.model.Model;
import battleship.model.Model.GameStatut;
import battleship.model.Model.GameType;
import battleship.model.players.Grid;

public interface DoaNormalGame {

	void sauvegarder(GameType gameType, GameStatut gameStatut, Grid humain, Grid ia);
	void charger(Model model) throws Exception;
   
}
