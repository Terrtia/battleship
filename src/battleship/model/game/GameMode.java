package battleship.model.game;

import battleship.model.players.Grid;
import battleship.model.players.Human;
import battleship.model.players.IA;
import battleship.model.players.Grid.Square;
import battleship.model.ships.ShipFactory;
import battleship.model.ships.modern.ModernShipFactory;


public abstract class GameMode {
	public enum epoch {MODERN}
	public enum gamemode {CLASSIC}

	protected epoch playedEpoch; 
	protected ShipFactory shipFactory;
	
	Human player;
	IA computer;

	public GameMode(){
		player = new Human();
		computer = new IA();
	}
	
	public abstract void run();
	
	public abstract void placeShips();

	public void setEpoch(epoch e) {
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

	public epoch getEpoch(){
		return playedEpoch;
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
