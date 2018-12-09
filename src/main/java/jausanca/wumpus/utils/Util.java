package jausanca.wumpus.utils;

public class Util {

	/**
	 * Checks if a <b>String</b> can be parsed to a valid grid size.
	 * @param s: String to parse
	 * @return
	 */
	public static boolean isValidGridSize(String s) {
		try {
			int n = Integer.parseInt(s);
			if(n > 1) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Checks if a <b>String</b> can be parsed to a valid pit number, given a grid size.
	 * @param s: String to parse
	 * @param gridSize
	 * @return
	 */
	public static boolean isValidPitNumber(String s, int gridSize) {
		try {
			int n = Integer.parseInt(s);
			// Pit number must be lower than total squares minus 3 to leave room for Player, Treasure and Wumpus squares
			if(n > -1 && n <= gridSize*gridSize - 3) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Checks if a <b>String</b> can be parsed to a valid number of Arrows.
	 * @param s: String to parse
	 * @return
	 */
	public static boolean isValidArrowNumber(String s) {
		try {
			int n = Integer.parseInt(s);
			if(n >= 0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
	}
}
