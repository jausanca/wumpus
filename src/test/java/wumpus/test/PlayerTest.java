package wumpus.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jausanca.wumpus.grid.Direction;
import jausanca.wumpus.grid.Position;
import jausanca.wumpus.player.Player;

class PlayerTest {

	private static Player player;
	
	@BeforeEach
	void initPlayer() {
		player = new Player(new Position(1,1),10);
		while(player.getDirection()!=Direction.NORTH) {
			player.turnRight();
		}
	}
	
	@Test
	void testTurnRight() {
		player.turnRight();
		assertEquals(player.getDirection(),Direction.EAST);
		player.turnRight();
		assertEquals(player.getDirection(),Direction.SOUTH);
		player.turnRight();
		assertEquals(player.getDirection(),Direction.WEST);
		player.turnRight();
		assertEquals(player.getDirection(),Direction.NORTH);
	}
	
	@Test
	void testTurnLeft() {
		player.turnLeft();
		assertEquals(player.getDirection(),Direction.WEST);
		player.turnLeft();
		assertEquals(player.getDirection(),Direction.SOUTH);
		player.turnLeft();
		assertEquals(player.getDirection(),Direction.EAST);
		player.turnLeft();
		assertEquals(player.getDirection(),Direction.NORTH);
	}
	
	@Test
	void testMoveNorth() {
		Position initPosition = player.getPosition();
		player.moveForward();
		Position newPosition = player.getPosition();
		assertEquals(initPosition.getNorthPosition(),newPosition);
	}
	
	@Test
	void testMoveEast() {
		player.turnRight();
		Position initPosition = player.getPosition();
		player.moveForward();
		Position newPosition = player.getPosition();
		assertEquals(initPosition.getEastPosition(),newPosition);
	}
	
	@Test
	void testMoveSouth() {
		player.turnRight();
		player.turnRight();
		Position initPosition = player.getPosition();
		player.moveForward();
		Position newPosition = player.getPosition();
		assertEquals(initPosition.getSouthPosition(),newPosition);
	}
	
	@Test
	void testMoveWest() {
		player.turnLeft();
		Position initPosition = player.getPosition();
		player.moveForward();
		Position newPosition = player.getPosition();
		assertEquals(initPosition.getWestPosition(),newPosition);
	}

	@Test
	void testFireArrow() {
		boolean arrowsLeft;
		for(int i = 0; i < 10; i++) {
			arrowsLeft = player.fireArrow();
			assertTrue(arrowsLeft);
		}
		arrowsLeft = player.fireArrow();
		assertFalse(arrowsLeft);
	}
}
