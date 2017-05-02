// DOA use for normal (default) game
package battleship.model.doa;

import battleship.model.Model;
import battleship.model.Model.GameStatut;
import battleship.model.game.GameMode.Epoch;
import battleship.model.game.GameMode.Gamemode;
import battleship.model.grid.Grid;

public interface DoaNormalGame {

	void sauvegarder(Gamemode gameMode, GameStatut gameStatut, Grid humain, Grid ia, Epoch epoch);
	void charger(Model model, String path) throws Exception;
   
}
