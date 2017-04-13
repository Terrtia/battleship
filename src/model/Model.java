package model;

import model.game.ClassicGame;
import model.game.GameMode;

public class Model {
	GameMode game;
	
	public Model(){
		game = new ClassicGame();
	}
}
