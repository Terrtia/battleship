package model;

import java.util.ArrayList;
import java.util.Observable;

import View.AttackView;
import View.ShipView;

import model.doa.AbstractDoaFactory;
import model.doa.DoaNormalGame;
import model.game.ClassicGame;
import model.game.GameMode;
import model.game.GameMode.epoch;
import model.game.GameMode.gamemode;
import model.players.Grid;
import model.players.Grid.square;
import model.ships.Ship;

public class Model extends Observable {
	GameMode game;
	
	public enum GameType{
		CLASSIC,
	}
	
	public enum GameStatut{
		HUMAIN_TOUR,
		IA_TOUR,
		HUMAIN_VICTOIRE,
		HUMAIN_DEFAITE,
		EGALITE,
	}
	
	private AbstractDoaFactory doaFactory;
	
	private GameStatut gameStatut;
	private GameType gameType;
	
	public Model(){
		this.gameType = GameType.CLASSIC;   // a deplacer
		game = new ClassicGame();

		ShipView shipView = new ShipView(this);
		AttackView attackView = new AttackView(this);
		addObserver(shipView);
		addObserver(attackView);
		
		this.setGameStatut(GameStatut.HUMAIN_TOUR);
		
		this.sauvegarder();
	}
	
	public void newGame(gamemode gm, epoch e) {
		switch(gm){
		case CLASSIC:
			game = new ClassicGame();
			break;
		}
		game.setEpoch(e);
		game.placeShips();
	}
	
	public void shoot(int x, int y) {
		Grid grid = getIAGrid();
		grid.isHit(x,y);
		setChanged();
		notifyObservers();		
	}

	public GameMode getGameMode() {
		return game;
	}	
	
	public void sauvegarder() {
		this.doaFactory = AbstractDoaFactory.getFactory( AbstractDoaFactory.TXT_DOA );
		
		switch(this.gameType){
		case CLASSIC:
			DoaNormalGame doa= doaFactory.getNormalGameTxtDoa();
			doa.sauvegarder(this.gameType, this.gameStatut, this.getHumanGrid(), this.getIAGrid());
			break;
		default:
			//erreur
		}
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

	public square getHumanSquare(int x,int y){
		Grid grid = getHumanGrid();
		return grid.getSquare(x, y);
	}
	
	public square getIASquare(int x,int y){
		Grid grid = getIAGrid();
		return grid.getSquare(x, y);
	}
	
	public GameStatut getGameStatut() {
		return gameStatut;
	}

	public void setGameStatut(GameStatut gameStatut) {
		this.gameStatut = gameStatut;
	}
	

	public String toStringGameStatut() {
		return gameStatut.toString();
	}
}
