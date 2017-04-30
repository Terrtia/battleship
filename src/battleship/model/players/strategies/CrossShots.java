package battleship.model.players.strategies;

import java.util.Random;

import battleship.model.players.Grid;
import battleship.model.players.Grid.Square;


public class CrossShots extends Strategy {
	private Random rng;
	
	public CrossShots(){
		rng = new Random();
	}
	
	@Override
	public int[] getShot(Grid g) {
		int x = -1, y = -1;
		do{
			x = rng.nextInt(g.getGridSize());
			y = rng.nextInt(g.getGridSize()/2)*2+(x%2);
		} while(g.getSquare(x, y) != Square.EMPTY);
		int a[] = {x,y};
		return a;
	}

}
