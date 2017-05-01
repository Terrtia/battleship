package battleship.model.grid.strategies;

import java.util.Random;

import battleship.model.grid.Grid;

public abstract class Strategy {
	protected Random rng;

	public Strategy(){
		rng = new Random();
	}
	
	public abstract void play(Grid g);
	
	public void placeShips(Grid g){
		//TODO
	}
}
