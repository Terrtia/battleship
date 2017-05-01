package battleship.model.grid.strategies;

import battleship.model.grid.Grid;
import battleship.model.grid.Grid.Square;


public class CrossShots extends Strategy {
	
	public CrossShots() {super();}
	
	/**
	 * tire en forme de croix
	 */
	public void play(Grid g) {
		int x = -1, y = -1;
		do{
			x = rng.nextInt(g.getGridSize());
			y = rng.nextInt(g.getGridSize()/2)*2+(x%2);
		} while(g.getSquare(x, y) != Square.EMPTY);
		g.isHit(x, y);
	}

}
