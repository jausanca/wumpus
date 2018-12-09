package wumpus.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jausanca.wumpus.controllers.GameStatus;
import jausanca.wumpus.controllers.MenuController;
import jausanca.wumpus.grid.Direction;
import jausanca.wumpus.grid.Grid;
import jausanca.wumpus.player.Player;

class MenuTest {
	
	private Grid grid;
	private Player player;
	
	@BeforeEach
	void initStatus() {
		grid = new Grid(3,0);
		grid.getStartPosition().setX(1);
		grid.getStartPosition().setY(1);
		int[][] gridMatrix = grid.getGridMatrix();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				gridMatrix[i][j] = Grid.EMPTY;
			}
		}
		player = new Player(grid.getStartPosition(), 1);
		while(!player.getDirection().equals(Direction.NORTH)) {
			player.turnRight();
		}
	}
	
	@Test
	void testStartMenu() {
		GameStatus gameStatus = new GameStatus(0, 0, 0, null, null, null, false, false, false, false);
		
		String testInput = "awdonui28hhv\n" // Wrong Map Size Input
				+ "6\n" // Correct Map Size Input
				+ "-1\n" // Wrong Pit Number Input
				+ "2\n" // Correct Pit Number Input
				+ "134 2\n" // Wrong Arrow Number Input
				+ "5"; // Correct Arrow Number Input
		
		String[] expected = {"Welcome to Wumpus!!", // Welcome message
				"Enter map size:", // Request Map Size
				"Invalid value, only values greater than one are allowed.", // Wrong Map Size Output
				"Enter map size:", // Request Map Size
				"Enter number of pits:", // Request Pit Number
				"Invalid value, make sure to introduce a zero or positive integer "
				+ "that leaves room for the player, the wumpus and the treasure to spawn.", // Wrong Pit Number Output
				"Enter number of pits:", // Request Pit Number
				"Enter number of arrows:", // Request Arrow Number
				"Invalid value, make sure to introduce a zero or positive integer.", // Wrong Arrow Number Output
				"Enter number of arrows:"}; // Request Arrow Number
		
		InputStream in = new ByteArrayInputStream(testInput.getBytes());
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		MenuController menuController = new MenuController(in, out);
		menuController.startMenu(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals(gameStatus.getGrid().getGridMatrix().length, 6);
	}
	
	@Test 
	void testActionMove() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		String testInput = "1\n"; // Move Forward
		String[] expected = {"What will you do?",
				"1. Move Forward",
				"2. Turn Left",
				"3. Turn Right",
				"4. Shoot Arrow"};
		InputStream in = new ByteArrayInputStream(testInput.getBytes());
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		MenuController menuController = new MenuController(in, out);
		menuController.makeAction(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals(player.getPosition(), grid.getStartPosition().getNorthPosition());
	}
	
	@Test 
	void testActionTurnLeft() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		String testInput = "2\n"; // Turn Left
		String[] expected = {"What will you do?",
				"1. Move Forward",
				"2. Turn Left",
				"3. Turn Right",
				"4. Shoot Arrow"};
		InputStream in = new ByteArrayInputStream(testInput.getBytes());
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		MenuController menuController = new MenuController(in, out);
		menuController.makeAction(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals(player.getDirection(), Direction.WEST);
	}
	
	@Test 
	void testActionTurnRight() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		String testInput = "3\n"; // Turn Right
		String[] expected = {"What will you do?",
				"1. Move Forward",
				"2. Turn Left",
				"3. Turn Right",
				"4. Shoot Arrow"};
		InputStream in = new ByteArrayInputStream(testInput.getBytes());
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		MenuController menuController = new MenuController(in, out);
		menuController.makeAction(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals(player.getDirection(), Direction.EAST);
	}
	
	@Test
	void testActionShootArrow1() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		String testInput = "4\n"; // Shoot Arrow
		String[] expected = {"What will you do?",
				"1. Move Forward",
				"2. Turn Left",
				"3. Turn Right",
				"4. Shoot Arrow"};
		InputStream in = new ByteArrayInputStream(testInput.getBytes());
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		MenuController menuController = new MenuController(in, out);
		menuController.makeAction(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertTrue(gameStatus.isArrowShot());
		assertTrue(gameStatus.isArrowsLeft());
	}
	
	@Test
	void testActionShootArrow2() {
		player.fireArrow();
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		String testInput = "4\n"; // Shoot Arrow
		String[] expected = {"What will you do?",
				"1. Move Forward",
				"2. Turn Left",
				"3. Turn Right",
				"4. Shoot Arrow"};
		InputStream in = new ByteArrayInputStream(testInput.getBytes());
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		MenuController menuController = new MenuController(in, out);
		menuController.makeAction(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertTrue(gameStatus.isArrowShot());
		assertFalse(gameStatus.isArrowsLeft());
	}
	
	@Test
	void testActionCollectTreasure() {
		grid.getGridMatrix()[1][1] = Grid.TREASURE;
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		String testInput = "5\n"; // Collect Treasure
		String[] expected = {"What will you do?",
				"1. Move Forward",
				"2. Turn Left",
				"3. Turn Right",
				"4. Shoot Arrow",
				"5. Collect Treasure"};
		InputStream in = new ByteArrayInputStream(testInput.getBytes());
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		MenuController menuController = new MenuController(in, out);
		menuController.makeAction(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertTrue(gameStatus.isTreasureCollected());
	}
	
	@Test
	void testIllegalAction() {
		grid.getGridMatrix()[1][1] = Grid.TREASURE;
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, true, false);
		String testInput = "5\n"
				+ "6\n"
				+ "awfawfd awd\n"
				+ "1\n";
		String[] expected = {"What will you do?",
				"1. Move Forward",
				"2. Turn Left",
				"3. Turn Right",
				"4. Shoot Arrow",
				"Illegal action.",
				"Illegal action.",
				"Illegal action."};
		InputStream in = new ByteArrayInputStream(testInput.getBytes());
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		MenuController menuController = new MenuController(in, out);
		menuController.makeAction(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	
	
}
