package model.game;

import model.players.Grid;
import model.players.IA;
import model.players.Human;

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
	
	public void createFleet(Grid g){
		g.clear();
		g.addShip(shipFactory.createCarrier());
		g.addShip(shipFactory.createCruiser());
		g.addShip(shipFactory.createDestroyer());
		g.addShip(shipFactory.createSubmarine());
		g.addShip(shipFactory.createTorpedoBoat());
	}
}
