package model.players;

import model.ships.Destroyer;

public abstract class Player {
	protected Grid grid;
	
	public Player(){
		grid = new Grid();
	}

	public boolean asLost() {
		return grid.boatStillFloats();
	}
	
	public int getGridSize(){
		return grid.getGridSize();
	}
	
	public Grid getGrid(){
		return grid;
	}
	public abstract void playTurn();
	public abstract void placeShips();
}
