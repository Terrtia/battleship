package model.game;

import model.players.Grid;

public abstract class GameMode {

	public abstract int getGridSize();

	public abstract Grid getHumanGrid();
	
	public abstract Grid getIAGrid();

}
