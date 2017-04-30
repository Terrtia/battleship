package battleship.model.players.strategies;

import battleship.model.players.Grid;

public abstract class Strategy {

	public abstract int[] getShot(Grid g);
}
