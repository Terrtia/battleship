package battleship.model.game;

import battleship.model.grid.Grid;

public class ClassicGame extends GameMode {

	public ClassicGame() {
		super();
		this.gameMode = Gamemode.CLASSIC;
		this.setEpoch(Epoch.MODERN);
	}
	
	public void placeShips() {
		createFleet(player);
		createFleet(computer);
		IA.placeShips(computer);
	}
	
	public void playTurn(int x, int y){
		computer.isHit(x, y);
		IA.play(player);
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
