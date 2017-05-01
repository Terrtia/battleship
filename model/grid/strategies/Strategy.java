package battleship.model.grid.strategies;

import java.util.Random;

import battleship.model.grid.Grid;
import battleship.model.grid.Grid.Square;
import battleship.model.ships.Ship;

public abstract class Strategy {
	protected Random rng;

	public Strategy(){
		rng = new Random();
	}
	
	public abstract void play(Grid g);
	
	public void placeShips(Grid g){
		int x = -1, y = -1, h = -1;
		for(Ship s : g.getFleet()){
			do{
				x = rng.nextInt(g.getGridSize());
				y = rng.nextInt(g.getGridSize());
				h = rng.nextInt(1);
			} while(!g.placeShip(s, x, y, h==0));
		}
	}
}
