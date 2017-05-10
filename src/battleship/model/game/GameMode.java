package battleship.model.game;

import battleship.model.grid.Grid;
import battleship.model.grid.Grid.Square;
import battleship.model.grid.strategies.CrossShots;
import battleship.model.grid.strategies.RandomShots;
import battleship.model.grid.strategies.Strategy;
import battleship.model.ships.ShipFactory;
import battleship.model.ships.modern.ModernShipFactory;
import battleship.model.ships.past.PastShipFactory;

/**
 * Classe abstraite representant tout les modes de jeu
 *
 */
public abstract class GameMode {
	public enum Epoch {MODERN,PAST}
	public enum Gamemode {CLASSIC}

	protected Epoch playedEpoch; 
	protected Gamemode gameMode;
	protected ShipFactory shipFactory;
	
	Grid player;
	Grid computer;
	Strategy IA;

	/**
	 * Creation d'un mode de jeu
	 */
	public GameMode(){
		player = new Grid();
		computer = new Grid();
		IA = new RandomShots();
	}
	
	public abstract void placeShips();


	

	public abstract void playTurn(int x, int y);
	
	public abstract void createFleet(Grid g);
	
	public abstract void recreateFleet();
	
	/**
	 * Setter de l'epoque
	 * @param e
	 * L'epoque de jeu
	 */
	public void setEpoch(Epoch e) {
		playedEpoch = e;
		switch(playedEpoch){
		case MODERN:
			shipFactory = new ModernShipFactory();
			break;
		case PAST:
			shipFactory = new PastShipFactory();
			break;
		}
	}

	public void setGamemode(Gamemode gameMode) {this.gameMode = gameMode;}
	public void setHumanFriendlyGrid(Square[][] grid) {player.setFriendlyGrid(grid);}
	public void setIAFriendlyGrid(Square[][] grid) {computer.setFriendlyGrid(grid);}
	public void setRandomIA() {IA = new RandomShots();}
	public void setSmartIA() {IA = new CrossShots();}
	
	public Epoch getEpoch() {return playedEpoch;}
	public Gamemode getGameMode() {return gameMode;}
	public int getGridSize() {return player.getGridSize();}
	public Grid getHumanGrid() {return player;}
	public Grid getIAGrid() {return computer;}
	public ShipFactory getShipFactory() {return shipFactory;}
}
