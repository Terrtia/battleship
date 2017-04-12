package model.players;

import model.ships.Ship;

public class Grid {
	static int gridSize = 10;
	
	private Ship friendlyGrid[][];
	private int firedGrid[][];
	
	public Grid(){
		friendlyGrid = new Ship[gridSize][gridSize];
		firedGrid = new int[gridSize][gridSize];
	}
	
	public boolean isHit(int x, int y){
		return friendlyGrid[x][y] != null;
	}
}
