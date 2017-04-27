package model;

import java.util.ArrayList;
import java.util.Observable;

import View.AttackView;
import View.ShipView;

import model.game.ClassicGame;
import model.game.GameMode;
import model.players.Grid;
import model.players.Grid.square;
import model.ships.Ship;

public class Model extends Observable {
	GameMode game;
	
	public Model(){
		game = new ClassicGame();
		ShipView shipView = new ShipView(this);
		AttackView attackView = new AttackView(this);
		addObserver(shipView);
		addObserver(attackView);
	}
	
	public Grid getHumanGrid(){
		return game.getHumanGrid();
	}
	
	public Grid getIAGrid(){
		return game.getIAGrid();
	}
	
	public int getGridSize(){
		return game.getGridSize();
	}

	
	public ArrayList<Ship> getHumanFleet() {
		Grid grid = getHumanGrid();
		return grid.getFleet();
	}

	public void shoot(int x, int y) {
		Grid grid = getIAGrid();
		grid.isHit(x,y);
		setChanged();
		notifyObservers();
		
	}
	
	public square getHumanSquare(int x,int y){
		Grid grid = getHumanGrid();
		return grid.getSquare(x, y);
	}
	
	public square getIASquare(int x,int y){
		Grid grid = getIAGrid();
		return grid.getSquare(x, y);
	}

	/**
	 * 
	 * @param boats
	 * boats est un tableau de dimension [types de bateaux][4]
	 * le premier champs =
	 * 0 pour Carrier
	 * 1 pour Cruiser 
	 * 2 pour Destroyer
	 * 3 pour Submarine
	 * 4 pour TorpedoBoat
	 * le deuxieme champs =
	 * 0 pour la coordonnée en x 
	 * 1 pour la coordonnée en y
	 * 2 pour savoir si c'est horizontal ou vertical (0 horizontal 1 vertival)
	 * 3 la taille du bateau
	 * @param gameMode
	 * 0 pour mode classique
	 * 1 pour mode alternatif
	 * @param epoque
	 * 0 moderne
	 * 1 epoque passe
	 */
	public void newGame(int[][] boats,int gameMode,int epoque) {
		
	}

	public boolean newGameValide(int[][] boats) {
		for(int i =0; i < boats.length;i++){
			if(boats[i][0] == -1){
				System.out.println("Tous les navires ne sont pas placés");
				return false;
			}
			for(int j = i+1; j <boats.length;j++){
				//si le bateau est a l'horizontal
				if(boats[i][2] == 0){
					if(boats[j][2] == 0){
						return !(boats[j][1] == boats[i][1] &&
								((boats[j][0] >= boats[i][0] && boats[j][0] <= boats[i][0]+boats[i][3])||
								(boats[j][0]+boats[j][3] >= boats[i][0] && boats[j][0]+boats[j][3] <= boats[i][0]+boats[i][3])));
					}else{
						return!( (boats[j][0] >= boats[i][0] && boats[j][0] <= boats[i][0]+boats[i][3])&&
								(boats[i][1]>= boats[j][1] && boats[i][1] <= boats[j][1]+boats[j][3]));
					}
				}else{
					if(boats[j][2] == 1){
						return !(boats[j][0] == boats[i][0] &&
								((boats[j][1] >= boats[i][1] && boats[j][1] <= boats[i][1]+boats[i][3])||
								(boats[j][1]+boats[j][3] >= boats[i][1] && boats[j][1]+boats[j][3] <= boats[i][1]+boats[i][3])));
					}else{
						return!( (boats[j][1] >= boats[i][1] && boats[j][1] <= boats[i][1]+boats[i][3])&&
								(boats[i][0]>= boats[j][0] && boats[i][0] <= boats[j][0]+boats[j][3]));
					}
				}
			}
		}	
		return false;
	}
	
}
