package jausanca.wumpus.controllers;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import jausanca.wumpus.grid.Grid;
import jausanca.wumpus.player.Player;
import jausanca.wumpus.utils.Util;

public class MenuController extends StatusController {
	
	private InputStream in; 
	private PrintStream out;
	
	public MenuController(InputStream in, PrintStream out) {
		super();
		this.in = in;
		this.out = out;
	}
	
	public void startMenu(GameStatus gameStatus) {
		loadStatus(gameStatus);
		startMenu();
		saveStatus(gameStatus);
	}
	
	private void startMenu() {
		out.println("Welcome to Wumpus!!");
		Scanner input = new Scanner(in);
		
		while(true) {
			String gridSize;
			out.println("Enter map size:");
			gridSize = input.nextLine();
			if(Util.isValidGridSize(gridSize)) {
				this.gridSize = Integer.parseInt(gridSize);
				break;
			} else {
				out.println("Invalid value, only values greater than one are allowed.");
			}
		}
		while(true) {
			String pitNumber;
			out.println("Enter number of pits:");
			pitNumber = input.nextLine();
			if(Util.isValidPitNumber(pitNumber, this.gridSize)) {
				this.pitNumber = Integer.parseInt(pitNumber);
				break;
			} else {
				out.println("Invalid value, make sure to introduce a zero or positive integer that leaves room for the player, the wumpus and the treasure to spawn.");
			}
		}

		while(true) {
			String arrowNumber;
			out.println("Enter number of arrows:");
			arrowNumber = input.nextLine();
			if(Util.isValidArrowNumber(arrowNumber)) {
				this.arrowNumber = Integer.parseInt(arrowNumber);
				break;
			} else {
				out.println("Invalid value, make sure to introduce a zero or positive integer.");
			}
		}
		grid = new Grid(gridSize, pitNumber);
		player = new Player(grid.getStartPosition(), arrowNumber);
	}
	
	public void makeAction(GameStatus gameStatus) {
		loadStatus(gameStatus);
		makeAction();
		saveStatus(gameStatus);
	}
	
	private void makeAction() {
		boolean isTreasure = grid.getGridElement(player.getPosition().getX(), player.getPosition().getY()) == Grid.TREASURE;
		arrowShot = false;
		out.println("What will you do?");
		out.println("1. Move Forward");
		out.println("2. Turn Left");
		out.println("3. Turn Right");
		out.println("4. Shoot Arrow");
		if(isTreasure && !treasureCollected)out.println("5. Collect Treasure");
		Scanner input = new Scanner(in);
		while(true) {
			String action = input.nextLine();
			switch(action.trim()) {
			case "1": 
				player.moveForward();
				return;	
			case "2": 
				player.turnLeft();
				return;
			case "3": 
				player.turnRight();
				return;
			case "4": 
				arrowsLeft = player.fireArrow();
				arrowShot = true;
				return;
			case "5":
				if(isTreasure && !treasureCollected) {
					treasureCollected = true;
					return;
				}
				break;
			}
			out.println("Illegal action.");
		}

	}
	
}
