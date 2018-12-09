package wumpus.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jausanca.wumpus.grid.Grid;
import jausanca.wumpus.grid.Position;

class GridTest{
	private static Grid grid;
	private static int gridSize = 5, pitNumber = 7;

	@BeforeAll
	public static void initGrid() {
		grid = new Grid(gridSize,pitNumber);
	}

	@Test
	void testGridSize() {
		int[][] gridMatrix = grid.getGridMatrix();
		assertEquals(gridMatrix.length,gridSize);
		for(int i = 0; i < gridSize; i++)
			assertEquals(gridMatrix[i].length, gridSize);
	}

	@Test
	void testElementNumber() {
		int emptyElements = 0;
		int startElements = 0;
		int treasureElements = 0;
		int wumpusElements = 0;
		int pitElements = 0;

		int[][] gridMatrix = grid.getGridMatrix();

		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				switch(gridMatrix[i][j]) {
				case Grid.EMPTY: emptyElements += 1;
				break;
				case Grid.START: startElements += 1;
				break;
				case Grid.TREASURE: treasureElements += 1;
				break;
				case Grid.WUMPUS: wumpusElements += 1;
				break;
				case Grid.PIT: pitElements += 1;
				}
			}
		}
		
		assertEquals(emptyElements + startElements + treasureElements + wumpusElements + pitElements, gridSize*gridSize);
		assertEquals(startElements,1);
		assertEquals(treasureElements,1);
		assertEquals(wumpusElements,1);
		assertEquals(pitElements,pitNumber);
	}
	
	@Test
	void testGetStartPosition() {
		Position startPosition = grid.getStartPosition();
		int[][] gridMatrix = grid.getGridMatrix();
		assertEquals(Grid.START, gridMatrix[startPosition.getX()][startPosition.getY()]);
	}
	
	@Test
	void testToString() {
		String gridString = grid.toString();
		assertEquals(gridString.split("\n").length,gridSize);
		assertEquals(gridString.split("\n")[0].length(),gridSize);
	}
	
	@Test
	void testGetGridElement(){
		int[][] gridMatrix = grid.getGridMatrix();
		assertEquals(gridMatrix[0][0],grid.getGridElement(0, 0));
		assertEquals(gridMatrix[0][gridSize-1],grid.getGridElement(0, gridSize-1));
		assertEquals(gridMatrix[gridSize-1][0],grid.getGridElement(gridSize-1, 0));
		assertEquals(gridMatrix[gridSize-1][gridSize-1],grid.getGridElement(gridSize-1, gridSize-1));
		assertEquals(Grid.OUT_OF_BOUNDS,grid.getGridElement(-1, 0));
		assertEquals(Grid.OUT_OF_BOUNDS,grid.getGridElement(0, -1));
		assertEquals(Grid.OUT_OF_BOUNDS,grid.getGridElement(gridSize, 0));
		assertEquals(Grid.OUT_OF_BOUNDS,grid.getGridElement(0, gridSize));
		assertEquals(gridMatrix[0][0],grid.getGridElement(new Position(0, 0)));
	}
}


