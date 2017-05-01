package battleship.model.game;

import battleship.model.players.Grid;
import battleship.model.players.Human;
import battleship.model.players.IA;
import battleship.model.players.Grid.Square;
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
		createFleet(getHumanGrid());
		createFleet(getIAGrid());
		computer.placeShips();
	}
	
	public void run(){
		// TODO Auto-generated method stub
	}

	/**
	 * @return
	 * La taille de la grille
	 * 
	 */
	public int getGridSize() {
		return player.getGridSize();
	}

	/**
	 * retourne la grille du joueur
	 */
	public Grid getHumanGrid() {
		return player.getGrid();
	}

	/**
	 * retourne la grille de l'ia
	 */
	public Grid getIAGrid() {
		return computer.getGrid();
	}

	public void setHumanFriendlyGrid(Square[][] grid) {
		this.player.setFriendlyGrid(grid);
	}

	@Override
	public void setIAFriendlyGrid(Square[][] grid) {
		this.computer.setFriendlyGrid(grid);
	}
	
	/**
	 * Creation de la flotte de jeu
	 * @param g
	 * La grille de jeu
	 */
	public void createFleet(Grid g){
		g.clear();
		g.addShip(shipFactory.createCarrier());
		g.addShip(shipFactory.createCruiser());
		g.addShip(shipFactory.createDestroyer());
		g.addShip(shipFactory.createSubmarine());
		g.addShip(shipFactory.createTorpedoBoat());
	}
}
