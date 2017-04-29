package model;

import java.util.ArrayList;
import java.util.Observable;

import model.game.ClassicGame;
import model.game.GameMode;
import model.game.GameMode.epoch;
import model.game.GameMode.gamemode;
import model.players.Grid;
import model.players.Grid.square;
import model.ships.Ship;

public class Model extends Observable {
	GameMode game;
	
	public Model(){
		game = new ClassicGame();
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
}
