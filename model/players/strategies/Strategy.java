package battleship.model.players.strategies;

import battleship.model.players.Grid;

public abstract class Strategy {

	/**
	 * @return 
	 * les coordonnees du tir
	 */
	public abstract int[] getShot(Grid g);
}
