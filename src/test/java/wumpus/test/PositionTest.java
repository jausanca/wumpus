package wumpus.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jausanca.wumpus.grid.Position;

class PositionTest {

	@Test
	void testPosition() {
		Position position1 = new Position(3,5);
		assertEquals(position1.getX(),3);
		assertEquals(position1.getY(), 5);
		Position position2 = new Position(position1);
		assertEquals(position2.getX(),3);
		assertEquals(position2.getY(), 5);
		assertTrue(position2.equals(position1));
		Position position3 = new Position(null);
		assertTrue(position3.equals(new Position(0,0)));
		assertFalse(position3.equals(null));
		assertFalse(position3.equals("notPositionObject"));
		assertFalse(position3.equals(new Position(0,1)));
		assertFalse(position3.equals(new Position(1,0)));
		position3.setX(1);
		position3.setY(3);
		assertEquals(position3.getX(),1);
		assertEquals(position3.getY(),3);
	}
	
	@Test
	void testGetDirectionPosition() {
		Position position = new Position(1,1);
		assertEquals(position.getNorthPosition(), new Position(0,1));
		assertEquals(position.getEastPosition(), new Position(1,2));
		assertEquals(position.getSouthPosition(), new Position(2,1));
		assertEquals(position.getWestPosition(), new Position(1,0));
	}

}
