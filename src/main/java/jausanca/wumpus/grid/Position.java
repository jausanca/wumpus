package jausanca.wumpus.grid;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position position) {
		if(position != null) {
			this.x = position.x;
			this.y = position.y;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!(obj instanceof Position))
			return false;
		Position position = (Position)obj;
		return this.x == position.x && this.y == position.y;
	}
	
	public Position getNorthPosition() {
		return new Position(x-1,y);
	}
	
	public Position getEastPosition() {
		return new Position(x,y+1);
	}
	
	public Position getSouthPosition() {
		return new Position(x+1,y);
	}
	
	public Position getWestPosition() {
		return new Position(x,y-1);
	}
	
}
