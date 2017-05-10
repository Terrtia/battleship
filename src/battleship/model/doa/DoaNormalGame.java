// DOA use for normal (default) game
package battleship.model.doa;

import battleship.model.Model;
import battleship.model.Model.GameStatut;
import battleship.model.game.GameMode.Epoch;
import battleship.model.game.GameMode.Gamemode;
import battleship.model.grid.Grid;

/**
 * 
 * normal game, DOA interface
 *
 */
public interface DoaNormalGame {

	/**
	 * save a normal game
	 * @param gameMode Gamemode, game mode (CLASSIC, ...)
	 * @param gameStatut GameStatut, game (HUMAIN_TOUR, ...)
	 * @param humain Grid, Human Grid
	 * @param ia Grid, IA Grid
	 * @param epoch Epoch, game Epoch (MODERN, ...)
	 * @param path String, file path
	 */
	void sauvegarder(Gamemode gameMode, GameStatut gameStatut, Grid humain, Grid ia, Epoch epoch, String path);
	
	/**
	 * load a normal game
	 * @param model Model
	 * @param path String, file path
	 * @throws Exception
	 */
	void charger(Model model, String path) throws Exception;
   
}
