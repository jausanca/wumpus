package jausanca.wumpus.controllers;

import java.io.InputStream;
import java.io.PrintStream;

public class GameController {
	private PrintStream out;
	
	private GameStatus gameStatus;
	private MenuController menuController;
	private ActionSolver actionSolver;
	
	public GameController(InputStream in, PrintStream out) {
		this.out = out;
		gameStatus = new GameStatus(0, 0, 0, null, null, null, false, true, false, false);
		menuController = new MenuController(in, out);
		actionSolver = new ActionSolver(out);
	}
	
	public GameController() {
		this(System.in, System.out);
	}
	

	public void start() {
		menuController.startMenu(gameStatus);
		mainLoop();
		out.println(gameStatus.getGameOver());
	}

	private void mainLoop() {
		while(gameStatus.getGameOver() == null) {
			actionSolver.checkAdjacent(gameStatus);
			menuController.makeAction(gameStatus);
			actionSolver.checkResult(gameStatus);
		}
	}
}
