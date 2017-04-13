package model.players;

import java.util.ArrayList;

import model.ships.Ship;

public class Grid {
	enum square {EMPTY, MISS, HIT}
	
	static int gridSize = 10;
	
	private square friendlyGrid[][];
	
	private ArrayList<Ship> fleet;
	
	public Grid(){
		friendlyGrid = new square[gridSize][gridSize];
		fleet = new ArrayList<Ship>();
	}
	
	public void addShip(Ship ship){
		fleet.add(ship);
	}
	
	public boolean isHit(int x, int y){
		if(friendlyGrid[x][y] == square.EMPTY){
			for(Ship s : fleet){
				if (s.isHit(x, y)){
					friendlyGrid[x][y] = square.HIT;
					return true;
				}
			}
			friendlyGrid[x][y] = square.MISS;
		}
		return false;
	}

	public boolean boatStillFloats() {
		for (Ship s : fleet){
			if(s.isAlive())
				return true;
		}
		return false;
	}
}
