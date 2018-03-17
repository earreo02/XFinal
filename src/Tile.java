/*
 * File: Tile
 * Name: Eddie Trash
 * Section Leader: Travis da GOAT
 * ---------------------
 * This is the Tile Class.
 */

public class Tile {

	int x;
	int y;
	int colorIndex;
	int R;
	int G;
	int B;
	
	public Tile(int x, int y, int colorIndex) {
		this.x = x;
		this.y = y;
		this.colorIndex = colorIndex;
		if(colorIndex >= 10 || colorIndex <= 2) {
			this.R = 254;
		} else if(colorIndex == 3 || colorIndex == 9) {
			this.R = 127;
		} else {
			this.R = 0;
		}
		if(colorIndex >= 2 && colorIndex <= 6) {
			this.G = 254;
		} else if(colorIndex == 1 || colorIndex == 7) {
			this.G = 127;
		} else {
			this.G = 0;
		}
		if(colorIndex >= 6 && colorIndex <= 10) {
			this.B = 254;
		} else if(colorIndex == 5 || colorIndex == 11) {
			this.B = 127;
		} else {
			this.B = 0;
		}
	}
	
}

