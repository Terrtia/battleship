package battleship.model.grid.strategies;

import battleship.model.grid.Grid;
import battleship.model.grid.Grid.Square;

public class NewStrategy extends Strategy {

	@Override
	public void play(Grid g) {
		int x = -1, y = -1;
		do{
			x = rng.nextInt(g.getGridSize());
			y = rng.nextInt(g.getGridSize());
		} while(g.getSquare(x, y) != Square.EMPTY);
	}
}