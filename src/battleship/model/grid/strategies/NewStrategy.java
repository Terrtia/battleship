package battleship.model.grid.strategies;

import battleship.model.grid.Grid;
import battleship.model.grid.Grid.Square;

public class NewStrategy extends Strategy {
	private int x = -1;
	private int y = 0;
	
	@Override
	public void play(Grid g) {
		x++;
		if(x >= g.getGridSize()) {
			x = 0;
			y++;
		}
		
		if(y >= g.getGridSize()) {
			y = 0;
		}
		
		g.isHit(x, y);
	}
}