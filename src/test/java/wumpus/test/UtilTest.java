package wumpus.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jausanca.wumpus.utils.Util;

class UtilTest extends Util{

	@Test
	void testIsValidGridSize() {
		assertEquals(false,Util.isValidGridSize("a"));
		assertEquals(false,Util.isValidGridSize("1 3 5"));
		assertEquals(false,Util.isValidGridSize("-26"));
		assertEquals(false,Util.isValidGridSize("1"));
		assertEquals(true,Util.isValidGridSize("2"));
		assertEquals(true,Util.isValidGridSize("10"));
	}

	@Test
	void testIsValidPitNumber() {
		assertEquals(false,Util.isValidPitNumber("3", 2));
		assertEquals(false,Util.isValidPitNumber("155", 10));
		assertEquals(true,Util.isValidPitNumber("22", 5));
		assertEquals(true,Util.isValidPitNumber("5", 5));
		assertEquals(false,Util.isValidPitNumber("ad2", 5));
		assertEquals(false,Util.isValidPitNumber("-2", 5));
	}
	
	@Test
	void testIsValidArrowNumber() {
		assertEquals(false,Util.isValidArrowNumber("31 2"));
		assertEquals(true,Util.isValidArrowNumber("10"));
		assertEquals(true,Util.isValidArrowNumber("0"));
		assertEquals(false,Util.isValidArrowNumber("-1"));
		assertEquals(false,Util.isValidArrowNumber("af10jk3"));
	}
}
