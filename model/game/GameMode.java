package battleship.model.game;

import battleship.model.players.Grid;
import battleship.model.players.Human;
import battleship.model.players.IA;
import battleship.model.players.Grid.Square;
import battleship.model.ships.ShipFactory;
import battleship.model.ships.modern.ModernShipFactory;

/**
 * Classe abstraite representant tout les modes de jeu
 *
 */
public abstract class GameMode {
	public enum Epoch {MODERN}
	public enum Gamemode {CLASSIC}

	protected Epoch playedEpoch; 
	protected Gamemode gameMode;
	protected ShipFactory shipFactory;
	
	Human player;
	IA computer;

	/**
	 * Creation d'un mode de jeu
	 */
	public GameMode(){
		player = new Human();
		computer = new IA();
	}
	
	public abstract void run();
	
	public abstract void placeShips();

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
		}
	}
	
	public ShipFactory getShipFactory(){
		return shipFactory;
	}
	
	
	public abstract void setHumanFriendlyGrid(Square[][] grid);
	
	public abstract void setIAFriendlyGrid(Square[][] grid);

	public Epoch getEpoch(){
		return playedEpoch;
	}

	public Gamemode getGameMode() {
		return gameMode;
	}

	public void setGamemode(Gamemode gameMode) {
		this.gameMode = gameMode;
	}

	public int getGridSize() {
		return player.getGridSize();
	}

	public Grid getHumanGrid() {
		return player.getGrid();
	}

	public Grid getIAGrid() {
		return computer.getGrid();
	}
}
