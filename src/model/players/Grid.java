package model.players;

import java.util.ArrayList;
import java.util.Arrays;

import model.ships.Ship;
import model.ships.modern.GuidedMissileDestroyer;

public class Grid {
	public enum Square {EMPTY, MISS, HIT}
	
	static int gridSize = 10;
	
	private Square friendlyGrid[][];
	
	private ArrayList<Ship> fleet;
	
	public Grid(){
		friendlyGrid = new Square[gridSize][gridSize];
		for(int i=0; i < gridSize;i++){
			for(int j = 0; j < gridSize;j++){
				friendlyGrid[i][j] = Square.EMPTY;
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
		if(friendlyGrid[x][y] == Square.EMPTY){
			for(Ship s : fleet){
				if (s.isHit(x, y)){
					friendlyGrid[x][y] = Square.HIT;
					return true;
				}
			}
			friendlyGrid[x][y] = Square.MISS;
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
	
	public Square getSquare(int x,int y){
		return friendlyGrid[x][y];
	}

	public void setFriendlyGrid(Square[][] friendlyGrid) {
		this.friendlyGrid = friendlyGrid;
	}

	public String toStringFriendlyGrid() {
		
		StringBuilder res = new StringBuilder();
		for(int i=0; i < gridSize;i++){		
			for(int j = 0; j < gridSize;j++){
				res.append(this.friendlyGrid[i][j] + ";");
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
