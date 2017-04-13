package model.game;

import model.players.IA;
import model.players.Human;

public class ClassicGame extends GameMode {
	Human player;
	IA computer;

	public ClassicGame() {
		player = new Human();
		computer = new IA();
	}
	
	public void placeShips() {
		player.placeShips();
		computer.placeShips();
	}
	
	public void run(){
		if(!player.asLost())
			player.playTurn();
		if(!computer.asLost())
			computer.playTurn();
	}
	
}
