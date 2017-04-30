// DOA use for normal (default) game
package model.doa;

import model.Model;
import model.Model.GameStatut;
import model.Model.GameType;
import model.players.Grid;

public interface DoaNormalGame {

	void sauvegarder(GameType gameType, GameStatut gameStatut, Grid humain, Grid ia);
	void charger(Model model) throws Exception;
   
}
