package model.ships;

public abstract class Ship {
	protected int size;
	protected int hitPoints;
	
	protected int topLeftX;
	protected int topLeftY;
	
	protected boolean horizontal;
	
	public Ship(int size, int hitPoints){
		topLeftX = -1; topLeftY = -1;
		this.size = size;
		this.hitPoints = hitPoints;
	}
	
	public void place(int x, int y, boolean horizontal){
		this.topLeftX = x;
		this.topLeftY = y;
		this.horizontal = horizontal;
	}

	public void remove() {
		topLeftX = -1; 
		topLeftY = -1;
	}
	
	public boolean collide(int x, int y, int s, boolean h) {
		int x1 = topLeftX, y1 = topLeftY, x2 = x, y2 = y;
		
		if(horizontal) x1 += size;
		else y1 += size;
		if(h) x2 += s;
		else y2 += s;
		
		if(x2 >= topLeftX && x <= x1 && y2 >= topLeftY && y <= y1)
			return true;
		return false;
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
	
	public boolean isPlaced() {
		return topLeftX != -1;
	}
}

