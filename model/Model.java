package battleship.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;

import battleship.model.doa.AbstractDoaFactory;
import battleship.model.doa.DoaNormalGame;
import battleship.model.game.ClassicGame;
import battleship.model.game.GameMode;
import battleship.model.game.GameMode.Epoch;
import battleship.model.game.GameMode.Gamemode;
import battleship.model.players.Grid;
import battleship.model.players.Grid.Square;
import battleship.model.ships.Ship;
import battleship.view.AttackView;
import battleship.view.ShipView;



public class Model extends Observable {
	GameMode game;
	
	public enum GameStatut{
		HUMAIN_TOUR,
		IA_TOUR,
		HUMAIN_VICTOIRE,
		HUMAIN_DEFAITE,
		EGALITE,
	}
	
	private AbstractDoaFactory doaFactory;
	
	private GameStatut gameStatut;
	
	public Model(){
		game = new ClassicGame();
		
		this.setGameStatut(GameStatut.HUMAIN_TOUR);
		
		this.sauvegarder();
	}
	
	public void newGame(Gamemode gm, Epoch e) {
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
	
	public boolean isFront(int x, int y) {
		Grid grid = getHumanGrid();
		for(Ship s: grid.getFleet()){
			if(x == s.getTopLeftX() && y == s.getTopLeftY())
				return true;
		}
		return false;
	}
	
	public boolean isRear(int x, int y) {
		Grid grid = getHumanGrid();
		for(Ship s: grid.getFleet()){
			if(s.isHorizontal()){
				if((x == s.getTopLeftX()+s.getSize()-1) && (y == s.getTopLeftY()))
					return true;
			}else{
				if((x == s.getTopLeftX()) && (y == s.getTopLeftY()+s.getSize()-1))
					return true;
			}
		}
		return false;
	}
	
	public boolean isHorizontal(int x, int y) {
		Grid grid = getHumanGrid();
		for(Ship s: grid.getFleet()){
			if(s.isHit(x,y) && s.isHorizontal())
				return true;
		}
		return false;
	}

	public GameMode getGameMode() {
		return game;
	}	
	
	public void sauvegarder() {
		// choisir le format de stockage ici
		this.doaFactory = AbstractDoaFactory.getFactory( AbstractDoaFactory.TXT_DOA );
		Gamemode gameMode = this.game.getGameMode();
		
		switch(gameMode){
		case CLASSIC:
			DoaNormalGame doa= doaFactory.getNormalGameTxtDoa();
			Epoch epoch = game.getEpoch();
			this.game.getEpoch();
			doa.sauvegarder(gameMode, this.gameStatut, this.getHumanGrid(), this.getIAGrid(), epoch);
			break;
		default:
			//erreur
		}
	}
	
	public void charger() throws Exception {
		//format du fichier
		this.doaFactory = AbstractDoaFactory.getFactory( AbstractDoaFactory.TXT_DOA );
		
		BufferedReader inFile = new BufferedReader(new FileReader("save.txt"));	
		String mode = inFile.readLine();
		inFile.close();
		if(mode.equals(Gamemode.CLASSIC.toString()) ){
			DoaNormalGame doa= doaFactory.getNormalGameTxtDoa();
			doa.charger(this);
		} else {
			//GameType in save file is incorrect
			//throw exception?
		}
			
		this.notifyObservers();
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
	public ArrayList<Ship> getIAFleet() {
		Grid grid = getIAGrid();
		return grid.getFleet();
	}
	
	public void setHumanFriendlyGrid(Square[][] grid) {
		this.game.setHumanFriendlyGrid(grid);
	}
	
	public void setIaFriendlyGrid(Square[][] iaFriendlyGrid) {
		this.game.setIAFriendlyGrid(iaFriendlyGrid);
	}
	
	public Square getHumanSquare(int x,int y){

		Grid grid = getHumanGrid();
		return grid.getSquare(x, y);
	}
	
	public Square getIASquare(int x,int y){
		Grid grid = getIAGrid();
		return grid.getSquare(x, y);
	}
	
	public Gamemode getGamemode() {
		return this.game.getGameMode();
	}

	public void setGamemode(Gamemode gameMode) {
		this.game.setGamemode(gameMode);
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
