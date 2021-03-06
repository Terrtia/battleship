package battleship.model.grid;

import java.util.ArrayList;
import battleship.model.ships.Ship;
import battleship.model.ships.modern.MissileCruiser;

/**
 * Classe représentant la grille
 *
 */
public class Grid {

	/**
	 * Les différents états d'une case de la grille
	 *
	 */
	public enum Square {EMPTY, MISS, HIT}

	static int gridSize = 10;

	private Square friendlyGrid[][];

	private ArrayList<Ship> fleet;

	/**
	 * Constructeur de la grille
	 */
	public Grid(){
		friendlyGrid = new Square[gridSize][gridSize];
		for(int i=0; i < gridSize;i++){
			for(int j = 0; j < gridSize;j++){
				friendlyGrid[i][j] = Square.EMPTY;
			}
		}
		fleet = new ArrayList<Ship>();
		MissileCruiser gd = new MissileCruiser();
		addShip(gd);
		try {
			gd.place(1, 1, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Placement d'un bateau
	 * @param ship
	 * le bateau
	 * @param x
	 * coordonnée en x du point en haut a gauche
	 * @param y
	 * coordonée en y du point en haut a gauche
	 * @param horizontal
	 * boolean disant si le bateau est horizontal
	 * @return
	 * @throws Exception 
	 */
	public boolean placeShip(Ship ship, int x, int y, boolean horizontal) throws Exception{
		if(x<0 | y<0 | x>this.getGridSize() | y>this.getGridSize()){
			throw new Exception("Out of Grid");
		}

		if(ship.isPlaced())
			ship.remove();

		// Checks if the rear of the ship isn't outside the grid
		if((horizontal && x+ship.getSize() > getGridSize()) ||
		   (!horizontal && y+ship.getSize() > getGridSize()))
			return false;
			
			
		for(Ship s : fleet)
			if(s.isPlaced() && s.collide(x,y, ship.getSize(),horizontal)) return false;

		ship.place(x, y, horizontal);
		return true;
	}

	public boolean addShip(Ship ship){
		return fleet.add(ship);
	}

	public void clear(){
		fleet = new ArrayList<Ship>();
	}
	/**
	 * Fonction qui tire sur un bateau de la grille
	 * @param x
	 * coordonnée du tir en x
	 * @param y
	 * coordonnée du tir en y
	 * @return
	 * si un bateau est touche
	 */
	public boolean isHit(int x, int y){
		if(friendlyGrid[x][y] == Square.EMPTY){
			for(Ship s : fleet){
				if (s.isHit(x, y)){
					friendlyGrid[x][y] = Square.HIT;
					s.hit();
					return true;
				}
			}
			friendlyGrid[x][y] = Square.MISS;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 * true si tous les bateaux sont placés
	 */
	public boolean allShipsPlaced() {
		for(Ship s: fleet)
			if(!s.isPlaced()) return false;
		return true;
	}

	/**
	 * 
	 * @return
	 * true si il reste des bateaux en vie
	 */
	public boolean boatStillFloats() {
		for (Ship s : fleet)
			if(s.isAlive()) return true;
		return false;
	}

	public int getGridSize() {return gridSize;}
	public ArrayList<Ship> getFleet() {return fleet;}
	public Square getSquare(int x,int y) {return friendlyGrid[x][y];}
	public Square[][] getFriendlyGrid() {return friendlyGrid;}

	public void setFriendlyGrid(Square[][] friendlyGrid) {this.friendlyGrid = friendlyGrid;}

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
