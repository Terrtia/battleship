package battleship.model.game;

import battleship.model.grid.Grid;

/**
 * Mode de jeu classique
 *
 */

public class ClassicGame extends GameMode {

	/**
	 * Constructeur du mode de jeu
	 */
	public ClassicGame() {
		super();
		this.gameMode = Gamemode.CLASSIC;
		this.setEpoch(Epoch.MODERN);
	}
	
	/**
	 * Placement des bateaux
	 */
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

	@Override
	public void recreateFleet() {
		this.createFleet(this.player);
		this.createFleet(this.computer);
	}
}
