package battleship.model.grid.strategies;

import battleship.model.grid.Grid;
import battleship.model.grid.Grid.Square;


public class RandomShots extends Strategy {
	
	public RandomShots() {super();}
	
	/**
	 * tire au hasard
	 */
	public void play(Grid g) {
		int x = -1, y = -1;
		do{
			x = rng.nextInt(g.getGridSize());
			y = rng.nextInt(g.getGridSize());
		} while(g.getSquare(x, y) != Square.EMPTY);
		g.isHit(x, y);
	}

}
