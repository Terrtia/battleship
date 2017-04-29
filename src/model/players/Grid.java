package model.players;

import java.util.ArrayList;
import java.util.Arrays;

import model.ships.Ship;
import model.ships.modern.GuidedMissileDestroyer;

public class Grid {
	public enum square {EMPTY, MISS, HIT}
	
	static int gridSize = 10;
	
	private square friendlyGrid[][];
	
	private ArrayList<Ship> fleet;
	
	public Grid(){
		friendlyGrid = new square[gridSize][gridSize];
		for(int i=0; i < gridSize;i++){
			for(int j = 0; j < gridSize;j++){
				friendlyGrid[i][j] = square.EMPTY;
			}
		}
		fleet = new ArrayList<Ship>();
		GuidedMissileDestroyer gd = new GuidedMissileDestroyer();
		addShip(gd);
		gd.place(1, 5, false);
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

	public int getGridSize() {
		return gridSize;
	}
	
	public ArrayList<Ship> getFleet(){
		return fleet;
	}
	
	public square getSquare(int x,int y){
		return friendlyGrid[x][y];
	}

	public String toStringFriendlyGrid() {
		
		StringBuilder res = new StringBuilder();
		for(int i=0; i < gridSize;i++){		
			for(int j = 0; j < gridSize;j++){
				res.append(this.friendlyGrid[i][j] + "|");
			}
		}

		return res.toString();
	}

	public String toStringFleet() {
		
		StringBuilder res = new StringBuilder();
		for(Ship ship : this.fleet){
			res.append(ship.toString());
		}
		
		return res.toString();
	}
	
	

}
