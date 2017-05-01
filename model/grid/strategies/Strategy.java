package battleship.model.grid.strategies;

import battleship.model.grid.Grid;

public abstract class Strategy {

	public abstract int[] getShot(Grid g);
}
