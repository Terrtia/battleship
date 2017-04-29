package model.ships;

public abstract class Ship {
	protected int size;
	protected int hitPoints;
	
	protected int topLeftX;
	protected int topLeftY;
	

	protected boolean horizontal;
	
	public Ship(int size, int hitPoints){
		this.size = size;
		this.hitPoints = hitPoints;
	}
	
	public void place(int x, int y, boolean horizontal){
		this.topLeftX = x;
		this.topLeftY = y;
		this.horizontal = horizontal;
	}
	
	public boolean isAlive(){
		return hitPoints > 0;
	}
	
	public boolean isHit(int x, int y){
		if(horizontal){
			if(y == topLeftY && x >= topLeftX && x < (topLeftX+size)){
				hitPoints--;
				return true;
			}
		} else {
			if(x == topLeftX && y >= topLeftY && y < (topLeftY+size)){
				hitPoints--;
				return true;	
			}
		}
		return false;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getTopLeftX() {
		return topLeftX;
	}

	public int getTopLeftY() {
		return topLeftY;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	@Override
	public String toString() {
		return "size=" + size + ", hitPoints=" + hitPoints
				+ ", topLeftX=" + topLeftX + ", topLeftY=" + topLeftY
				+ ", horizontal=" + horizontal + "|";
	}
	
}

