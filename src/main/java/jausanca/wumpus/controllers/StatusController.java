package jausanca.wumpus.controllers;

import jausanca.wumpus.grid.Grid;
import jausanca.wumpus.player.Player;

public class StatusController {
	protected int gridSize;
	protected int pitNumber;
	protected int arrowNumber;
	protected Grid grid;
	protected Player player;
	protected String gameOver;

	protected boolean arrowShot;
	protected boolean arrowsLeft;
	protected boolean treasureCollected;
	protected boolean wumpusDead;

	protected void loadStatus(GameStatus gameStatus) {
		gridSize = gameStatus.getGridSize();
		pitNumber = gameStatus.getPitNumber();
		arrowNumber = gameStatus.getArrowNumber();
		grid = gameStatus.getGrid();
		player = gameStatus.getPlayer();
		gameOver = gameStatus.getGameOver();
		arrowShot = gameStatus.isArrowShot();
		arrowsLeft = gameStatus.isArrowsLeft();
		treasureCollected = gameStatus.isTreasureCollected();
		wumpusDead = gameStatus.isWumpusDead();
	}

	protected void saveStatus(GameStatus gameStatus) {
		gameStatus.setGridSize(gridSize);
		gameStatus.setPitNumber(pitNumber);
		gameStatus.setArrowNumber(arrowNumber);
		gameStatus.setGrid(grid);
		gameStatus.setPlayer(player);
		gameStatus.setGameOver(gameOver);
		gameStatus.setArrowShot(arrowShot);
		gameStatus.setArrowsLeft(arrowsLeft);
		gameStatus.setTreasureCollected(treasureCollected);
		gameStatus.setWumpusDead(wumpusDead);	
	}

}
