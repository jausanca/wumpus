package jausanca.wumpus.grid;

import java.util.Random;

public class Grid {
	public static final int OUT_OF_BOUNDS = -1;
	public static final int EMPTY = 0;
	public static final int START = 1;
	public static final int TREASURE = 2;
	public static final int WUMPUS = 3;
	public static final int PIT = 4;
	private int gridSize;
	private int[][] gridMatrix;
	private Position startPosition;
	public Grid(int gridSize, int pitNumber) {
		this.gridSize = gridSize;
		this.gridMatrix = new int[gridSize][gridSize];
		// Insert START
		insertNewElement(START);
		// Insert TREASURE
		insertNewElement(TREASURE);
		// Insert WUMPUS
		insertNewElement(WUMPUS);
		// Insert PITS
		for(int i = 0; i < pitNumber; i++) {
			insertNewElement(PIT);
		}

	}
	
	/**
	 * Inserts a new element on the grid
	 * @param Element, Element identifier (EMPTY, START, TREASURE, WUMPUS, PIT)
	 */
	private void insertNewElement(int element) {
		int newX = 0;
		int newY = 0;
		boolean inserted = false;
		Random random = new Random();
		while(!inserted) {
			newX = random.nextInt(gridSize);
			newY= random.nextInt(gridSize);
			if(gridMatrix[newX][newY] == 0) {
				gridMatrix[newX][newY] = element;
				inserted = true;
			}
		}
		if(element == START) {
			this.startPosition = new Position(newX, newY);
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				s += this.gridMatrix[i][j];
			}
			s += "\n";
		}
		return s;
	}
	
	public int[][] getGridMatrix(){
		return gridMatrix;
	}
	
	public Position getStartPosition() {
		return startPosition;
	}
	
	/**
	 * Get grid element located at given coordinates, if coordinates are not in the matrix returns OUT_OF_BOUNDS 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return
	 */
	public int getGridElement(int x, int y) {
		if(x <= -1 || y <= -1 || x >= gridSize || y >= gridSize) {
			return OUT_OF_BOUNDS;
		}
		return gridMatrix[x][y];
	}
	
	/**
	 * Get grid element located at given position, if coordinates are not in the matrix returns OUT_OF_BOUNDS 
	 * @param position position
	 * @return
	 */
	public int getGridElement(Position position) {
		return getGridElement(position.getX(),position.getY());
	}
}
