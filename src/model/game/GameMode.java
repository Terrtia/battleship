package model.game;

import model.players.Grid;
import model.players.Grid.Square;

public abstract class GameMode {

	public abstract int getGridSize();

	public abstract Grid getHumanGrid();
	
	public abstract Grid getIAGrid();
	
	public abstract void setHumanFriendlyGrid(Square[][] grid);
	
	public abstract void setIAFriendlyGrid(Square[][] grid);

}
