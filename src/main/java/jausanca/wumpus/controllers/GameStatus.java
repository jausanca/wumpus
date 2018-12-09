package jausanca.wumpus.controllers;

import jausanca.wumpus.grid.Grid;
import jausanca.wumpus.player.Player;

public class GameStatus {

	private int gridSize;
	private int pitNumber;
	private int arrowNumber;
	private Grid grid;
	private Player player;
	private String gameOver;

	private boolean arrowShot;
	private boolean arrowsLeft;
	private boolean treasureCollected;
	private boolean wumpusDead;

	public GameStatus() {
		
	}
	
	
	
	public GameStatus(int gridSize, int pitNumber, int arrowNumber, Grid grid, Player player, String gameOver,
			boolean arrowShot, boolean arrowsLeft, boolean treasureCollected, boolean wumpusDead) {
		super();
		this.gridSize = gridSize;
		this.pitNumber = pitNumber;
		this.arrowNumber = arrowNumber;
		this.grid = grid;
		this.player = player;
		this.gameOver = gameOver;
		this.arrowShot = arrowShot;
		this.arrowsLeft = arrowsLeft;
		this.treasureCollected = treasureCollected;
		this.wumpusDead = wumpusDead;
	}



	public int getGridSize() {
		return gridSize;
	}
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
	public int getPitNumber() {
		return pitNumber;
	}
	public void setPitNumber(int pitNumber) {
		this.pitNumber = pitNumber;
	}
	public int getArrowNumber() {
		return arrowNumber;
	}
	public void setArrowNumber(int arrowNumber) {
		this.arrowNumber = arrowNumber;
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getGameOver() {
		return gameOver;
	}
	public void setGameOver(String gameOver) {
		this.gameOver = gameOver;
	}
	public boolean isArrowShot() {
		return arrowShot;
	}
	public void setArrowShot(boolean arrowShot) {
		this.arrowShot = arrowShot;
	}
	public boolean isArrowsLeft() {
		return arrowsLeft;
	}
	public void setArrowsLeft(boolean arrowsLeft) {
		this.arrowsLeft = arrowsLeft;
	}
	public boolean isTreasureCollected() {
		return treasureCollected;
	}
	public void setTreasureCollected(boolean treasureCollected) {
		this.treasureCollected = treasureCollected;
	}
	public boolean isWumpusDead() {
		return wumpusDead;
	}
	public void setWumpusDead(boolean wumpusDead) {
		this.wumpusDead = wumpusDead;
	}
	
}
