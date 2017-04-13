package model.players;

public abstract class Player {
	protected Grid grid;
	
	public Player(){
		grid = new Grid();
	}

	public boolean asLost() {
		return grid.boatStillFloats();
	}
	
	public abstract void playTurn();
	public abstract void placeShips();
}
