package battleship.model.players.strategies;

import java.util.Random;

import battleship.model.players.Grid;
import battleship.model.players.Grid.Square;


public class RandomShots extends Strategy {
	private Random rng;
	
	public RandomShots(){
		rng = new Random();
	}
	
	@Override
	public int[] getShot(Grid g) {
		int x = -1, y = -1;
		do{
			x = rng.nextInt(g.getGridSize());
			y = rng.nextInt(g.getGridSize());
		} while(g.getSquare(x, y) != Square.EMPTY);
		int a[] = {x,y};
		return a;
	}

}
