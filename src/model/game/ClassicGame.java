package model.game;

import model.players.Grid;
import model.players.IA;
import model.players.Human;
import model.players.Grid.Square;

public class ClassicGame extends GameMode {
	Human player;
	IA computer;

	public ClassicGame() {
		player = new Human();
		computer = new IA();
	}
	
	public void placeShips() {
		player.placeShips();
		computer.placeShips();
	}
	
	public void run(){
		while(!player.asLost() || !computer.asLost()){
			if(!player.asLost())
				player.playTurn();
			if(!computer.asLost())
				computer.playTurn();
		}
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

	@Override
	public void setHumanFriendlyGrid(Square[][] grid) {
		this.player.setFriendlyGrid(grid);
	}

	@Override
	public void setIAFriendlyGrid(Square[][] grid) {
		this.computer.setFriendlyGrid(grid);
	}
	
	
	
}
