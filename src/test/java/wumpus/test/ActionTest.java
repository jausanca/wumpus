package wumpus.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jausanca.wumpus.controllers.ActionSolver;
import jausanca.wumpus.controllers.GameStatus;
import jausanca.wumpus.controllers.MenuController;
import jausanca.wumpus.grid.Direction;
import jausanca.wumpus.grid.Grid;
import jausanca.wumpus.grid.Position;
import jausanca.wumpus.player.Player;

class ActionTest {

	
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
	void testAdjacentNone() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		String[] expected = {""};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkAdjacent(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	
	@Test
	void testAdjacentTreasure() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		grid.getGridMatrix()[1][0] = Grid.TREASURE;
		String[] expected = {"You percieve a shining metal."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkAdjacent(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	
	@Test
	void testAdjacentWumpus() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		grid.getGridMatrix()[0][1] = Grid.WUMPUS;
		String[] expected = {"You smell a Wumpus."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkAdjacent(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	
	@Test
	void testAdjacentPit() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		grid.getGridMatrix()[1][2] = Grid.PIT;
		String[] expected = {"You feel a breeze."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkAdjacent(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	
	@Test
	void testAdjacentAll() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		grid.getGridMatrix()[1][0] = Grid.TREASURE;
		grid.getGridMatrix()[0][1] = Grid.WUMPUS;
		grid.getGridMatrix()[1][2] = Grid.PIT;
		String[] expected = {"You percieve a shining metal.",
				"You smell a Wumpus.",
				"You feel a breeze."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkAdjacent(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}

	@Test
	void testOutNorth() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		player.moveForward();
		player.moveForward();
		String[] expected = {"You hit a wall."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals(player.getPosition(), new Position(0, 1));
	}
	
	@Test
	void testOutEast() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		player.turnRight();
		player.moveForward();
		player.moveForward();
		String[] expected = {"You hit a wall."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals(player.getPosition(), new Position(1, 2));
	}
	
	@Test
	void testOutSouth() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		player.turnRight();
		player.turnRight();
		player.moveForward();
		player.moveForward();
		String[] expected = {"You hit a wall."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals(player.getPosition(), new Position(2, 1));
	}
	
	@Test
	void testOutWest() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		player.turnLeft();
		player.moveForward();
		player.moveForward();
		String[] expected = {"You hit a wall."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals(player.getPosition(), new Position(1, 0));
	}
	
	@Test
	void testAliveWumpus() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		grid.getGridMatrix()[1][1] = Grid.WUMPUS;
		String[] expected = {"The Wumpus eats you."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals("You Lose.", gameStatus.getGameOver());
	}
	
	@Test
	void testDeadWumpus() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, true);
		grid.getGridMatrix()[1][1] = Grid.WUMPUS;
		String[] expected = {"You are over the Wumpus body."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertNull(gameStatus.getGameOver());
	}
	
	@Test
	void testPit() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		grid.getGridMatrix()[1][1] = Grid.PIT;
		String[] expected = {"You fall to your death."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals("You Lose.", gameStatus.getGameOver());
	}
	
	@Test
	void testStartWithoutTreasure() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, false, false);
		grid.getGridMatrix()[1][1] = Grid.START;
		String[] expected = {""};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertNull(gameStatus.getGameOver());
	}
	
	@Test
	void testStartWithTreasure() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, false, false, true, false);
		grid.getGridMatrix()[1][1] = Grid.START;
		String[] expected = {"You escape with the treasure."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertEquals("You Win.", gameStatus.getGameOver());
	}
	
	
	@Test
	void testShootNorth() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, true, true, false, false);
		String[] expected = {"Your arrow hits the wall."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	
	@Test
	void testShootEast() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, true, true, false, false);
		player.turnRight();
		String[] expected = {"Your arrow hits the wall."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	
	@Test
	void testShootSouth() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, true, true, false, false);
		player.turnRight();
		player.turnRight();
		String[] expected = {"Your arrow hits the wall."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	
	@Test
	void testShootWest() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, true, true, false, false);
		player.turnLeft();
		String[] expected = {"Your arrow hits the wall."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	
	@Test
	void testShootAliveWumpus() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, true, true, false, false);
		grid.getGridMatrix()[0][1] = Grid.WUMPUS;
		String[] expected = {"You hear a scream of agony."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
		assertTrue(gameStatus.isWumpusDead());
	}
	
	@Test
	void testShootDeadWumpus() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, true, true, false, true);
		grid.getGridMatrix()[0][1] = Grid.WUMPUS;
		String[] expected = {"Your arrow hits the wall."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	
	@Test
	void testShootNoArrows() {
		GameStatus gameStatus = new GameStatus(3, 0, 1, grid, player, null, true, false, false, false);
		String[] expected = {"You are out of arrows."};
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(testOutput);
		ActionSolver actionSolver = new ActionSolver(out);
		actionSolver.checkResult(gameStatus);
		String[] result = testOutput.toString().split(System.lineSeparator());
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);	
		}
	}
	

}
