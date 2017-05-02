package battleship.model.ships;

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
	
	public void place(int x, int y, boolean horizontal) throws Exception {
		if(x<0 | y<0){
			throw new Exception("Error negative x or y");
		}
		
		this.topLeftX = x;
		this.topLeftY = y;
		this.horizontal = horizontal;
	}

	public void remove() {
		topLeftX = -1; 
		topLeftY = -1;
	}
	
	public boolean collide(int x, int y, int s, boolean h) throws Exception {
		if(x<0 | y<0 | s<=0){
			throw new Exception("Error negative x or y or size");
		}
		
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
				return true;
			}
		} else {
			if(x == topLeftX && y >= topLeftY && y < (topLeftY+size)){
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

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(size);
		res.append(";");
		res.append(hitPoints);
		res.append(";");
		res.append(topLeftX);
		res.append(";");
		res.append(topLeftY);
		res.append(";");
		res.append(horizontal);
		res.append(System.lineSeparator());
		return res.toString();
	}

	public void hit() {
		hitPoints--;
		
	}

}

