package jausanca.wumpus.player;

import java.util.Random;

import jausanca.wumpus.grid.Direction;
import jausanca.wumpus.grid.Position;

public class Player {
	private Position position;
	private String direction;
	private int arrowNumber;

	public Player(Position position, int arrowNumber) {
		this.position = new Position(position);
		Random random = new Random();
		String[] directions = {Direction.NORTH,Direction.EAST,Direction.SOUTH,Direction.WEST};
		this.direction = directions[random.nextInt(4)];
		this.arrowNumber = arrowNumber;
	}

	public void turnRight() {
		switch(direction) {
		case Direction.NORTH: 	
			direction = Direction.EAST; 
			break;
		case Direction.EAST: 	
			direction = Direction.SOUTH; 
			break;
		case Direction.SOUTH: 	
			direction = Direction.WEST; 
			break;
		case Direction.WEST: 	
			direction = Direction.NORTH; 
			break;
		}
	}

	public void turnLeft() {
		switch(direction) {
		case Direction.NORTH: 	
			direction = Direction.WEST; 
			break;
		case Direction.EAST: 	
			direction = Direction.NORTH; 
			break;
		case Direction.SOUTH: 	
			direction = Direction.EAST; 
			break;
		case Direction.WEST: 	
			direction = Direction.SOUTH; 
			break;
		}		
	}

	public void moveForward() {
		switch(direction) {
		case Direction.NORTH:	
			position = position.getNorthPosition(); 
			break;
		case Direction.EAST: 	
			position = position.getEastPosition(); 
			break;
		case Direction.SOUTH: 	
			position = position.getSouthPosition(); 
			break;
		case Direction.WEST: 	
			position = position.getWestPosition();
			break;
		}		
	}

	public boolean fireArrow() {
		if(arrowNumber > 0) {
			arrowNumber--;
			return true;
		}
		return false;
	}

	public Position getPosition() {
		return position;
	}
	
	public String getDirection() {
		return direction;
	}

}
