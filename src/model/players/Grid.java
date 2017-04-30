package model.players;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Arrays;

import model.ships.Ship;
import model.ships.modern.GuidedMissileDestroyer;

public class Grid extends Observable{
	public enum Square {EMPTY, OCCUPIED, MISS, HIT}

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

	public boolean placeShip(Ship ship, int x, int y, boolean horizontal){
		if(ship.isPlaced())
			ship.remove();
		
		for(Ship s : fleet)
			if(s.isPlaced() && s.collide(x,y, ship.getSize(),horizontal)) return false;
	
		ship.place(x, y, horizontal);
		notifyObservers();
		return true;
	}
	
	public boolean addShip(Ship ship){
		return fleet.add(ship);
	}

	public void clear(){
		fleet = new ArrayList<Ship>();
	}
	
	public boolean isHit(int x, int y){
		if(friendlyGrid[x][y] == Square.EMPTY){
			for(Ship s : fleet){
				if (s.isHit(x, y)){

					friendlyGrid[x][y] = Square.HIT;
					notifyObservers();
					return true;
				}
			}
			friendlyGrid[x][y] = Square.MISS;
		}
		notifyObservers();
		return false;
	}

	public boolean allShipsPlaced() {
		for(Ship s: fleet)
			if(!s.isPlaced()) return false;
		return true;
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
