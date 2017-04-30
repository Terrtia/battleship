package model.game;

import model.players.Grid;
import model.players.IA;
import model.players.Human;
import model.players.Grid.Square;

public class ClassicGame extends GameMode {

	public ClassicGame() {
		super();
	}
	
	public void placeShips() {
		createFleet(getHumanGrid());
		createFleet(getIAGrid());
		computer.placeShips();
	}
	
	public void run(){
		// TODO Auto-generated method stub
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
	
	
	public void createFleet(Grid g){
		g.clear();
		g.addShip(shipFactory.createCarrier());
		g.addShip(shipFactory.createCruiser());
		g.addShip(shipFactory.createDestroyer());
		g.addShip(shipFactory.createSubmarine());
		g.addShip(shipFactory.createTorpedoBoat());
	}
}
