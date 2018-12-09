package jausanca.wumpus.controllers;

import java.io.PrintStream;

import jausanca.wumpus.grid.Direction;
import jausanca.wumpus.grid.Grid;
import jausanca.wumpus.grid.Position;

public class ActionSolver extends StatusController {
	private PrintStream out;

	public ActionSolver(PrintStream out) {
		super();
		this.out = out;
	}
	
	public void checkAdjacent(GameStatus gameStatus) {
		loadStatus(gameStatus);
		checkAdjacent();
		saveStatus(gameStatus);
	}
	
	private void checkAdjacent() {
		boolean treasureAdjacent = false;
		boolean wumpusAdjacent = false;
		boolean pitAdjacent = false;
		Position playerPosition = player.getPosition();
		int x = playerPosition.getX();
		int y = playerPosition.getY();
		int[] adjacentSquares = {grid.getGridElement(x-1, y), grid.getGridElement(x, y-1),
				grid.getGridElement(x+1, y), grid.getGridElement(x, y+1)};

		for(int i = 0; i < adjacentSquares.length; i++) {
			treasureAdjacent = !treasureCollected && (treasureAdjacent || adjacentSquares[i] == Grid.TREASURE);
			wumpusAdjacent = wumpusAdjacent || adjacentSquares[i] == Grid.WUMPUS;
			pitAdjacent = pitAdjacent || adjacentSquares[i] == Grid.PIT;
		}

		if(treasureAdjacent) out.println("You percieve a shining metal.");
		if(wumpusAdjacent) out.println("You smell a Wumpus.");
		if(pitAdjacent) out.println("You feel a breeze.");
	}
	
	public void checkResult(GameStatus gameStatus) {
		loadStatus(gameStatus);
		checkResult();
		saveStatus(gameStatus);
	}
	
	private void checkResult() {
		boolean wallHit = false;
		Position playerPosition = player.getPosition();
		int x = playerPosition.getX();
		int y = playerPosition.getY();

		// Check wall collision
		if(x < 0) {
			x = 0;
			wallHit = true;
		} if(y < 0) {
			y = 0;
			wallHit = true;
		} if(x >= gridSize) {
			x = gridSize - 1;
			wallHit = true;
		} if(y >= gridSize) {
			y = gridSize - 1;
			wallHit = true;
		}
		if(wallHit) {
			out.println("You hit a wall.");
			playerPosition.setX(x);
			playerPosition.setY(y);
		}

		// Check for elements
		int element = grid.getGridElement(x, y);

		// Wumpus
		if(element == Grid.WUMPUS) {
			if(wumpusDead) {
				out.println("You are over the Wumpus body.");
			} else {
				out.println("The Wumpus eats you.");
				gameOver = "You Lose.";
				return;
			}
		}

		// Pit
		if(element == Grid.PIT) {
			out.println("You fall to your death.");
			gameOver = "You Lose.";
			return;
		}

		// Exit
		if(element == Grid.START) {
			if(treasureCollected) {
				out.println("You escape with the treasure.");
				gameOver = "You Win.";
			}
		}

		// Shoot arrow
		if(arrowShot) {
			if(!arrowsLeft) {
				out.println("You are out of arrows.");
			} else {
				Position arrowPosition = new Position(playerPosition);
				String direction = player.getDirection();
				while(grid.getGridElement(arrowPosition)!= Grid.OUT_OF_BOUNDS && grid.getGridElement(arrowPosition) != Grid.WUMPUS) {
					switch(direction) {
					case Direction.NORTH: 
						arrowPosition = arrowPosition.getNorthPosition();
						break;
					case Direction.EAST: 
						arrowPosition = arrowPosition.getEastPosition();
						break;
					case Direction.SOUTH: 
						arrowPosition = arrowPosition.getSouthPosition();
						break;
					case Direction.WEST: 
						arrowPosition = arrowPosition.getWestPosition();
						break;
					}
				}
				if(grid.getGridElement(arrowPosition) == Grid.WUMPUS && !wumpusDead) {
					out.println("You hear a scream of agony.");
					wumpusDead = true;
				} else {
					out.println("Your arrow hits the wall.");
				}
			}
		}
	}
	
}
