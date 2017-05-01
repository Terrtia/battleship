package battleship.model.players.strategies;

import java.util.Random;

import battleship.model.players.Grid;
import battleship.model.players.Grid.Square;

/**
 * Strategie de tir aléatoire
 *
 */
public class RandomShots extends Strategy {
	private Random rng;
	
	public RandomShots(){
		rng = new Random();
	}
	
	/**
	 * @return 
	 * les coordonnees du tir
	 */
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
