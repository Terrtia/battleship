package model.ships;

public abstract class Ship {
	protected int size;
	protected int hitPoints;
	
	
	public Ship(int size, int hitPoints){
		this.size = size;
		this.hitPoints = hitPoints;
	}
	
	public boolean isAlive(){
		return hitPoints > 0;
	}
	
	public int getSize(){
		return size;
	}
}
