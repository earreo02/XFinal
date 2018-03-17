/*
 * File: TileArt
 * Name: Eddie Trash
 * Section Leader: Travis da GOAT
 * ---------------------
 * This is gonna be great. watch
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class TileArt extends GraphicsProgram {
	
	private static final int TILE_WIDTH = 10;
	private static final int TILE_HEIGHT = 20;
	private static final int SPACE_WIDTH = 3;
	private static final int SPACE_HEIGHT = 6;
	private static final int WINDOW_WIDTH = 750;
	private static final int WINDOW_HEIGHT = 500;
	private static final int TILE_ROW_NUM = (WINDOW_HEIGHT/(TILE_HEIGHT+SPACE_HEIGHT));
	private static final int TILE_COL_NUM = (WINDOW_WIDTH/(TILE_WIDTH+SPACE_WIDTH));
	private static final double WAIT_TIME = .00125;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private Tile[][] tiles = new Tile[TILE_ROW_NUM][TILE_COL_NUM];
	
	private static HashMap<Color, Integer> colorIndexs = new HashMap<Color, Integer>();
	static {
		colorIndexs.put(Color.RED, 0);
		colorIndexs.put(Color.ORANGE, 1);
		colorIndexs.put(Color.YELLOW, 2);
		colorIndexs.put(Color.GREEN, 4);
		colorIndexs.put(Color.CYAN, 6);
		colorIndexs.put(Color.BLUE, 8);
		colorIndexs.put(Color.MAGENTA, 10);
	}
	/*
	 * Your gotta figure how to have the colors flip independent of a click. (concurrency)
	 * You gotta figure out how to exclude the tiles you select, perhaps you can just remove the ones you click?!!!
	 * You have to draw ball with each click and figure out the direction parameter (0-7) directions
	 * Perhaps have the ball move? ya bruh. 
	 */
	
	//create an ArrayList of ArrayList(tilesToChange), so that you can call each next collection of tiles you want to flip.
	public void init() {
		addMouseListeners();
	}
	
	public void run() {
		GRect[][] drawnTiles = new GRect [TILE_ROW_NUM][TILE_COL_NUM];
		int startColorIndex = rgen.nextInt(0, 11);
		setUpWall(drawnTiles, startColorIndex); 
		int patternNum = rgen.nextInt(1, 5);
		choosePattern(drawnTiles, 1);
	
	}
	
	private void setUpWall(GRect[][] drawnTiles, int startColorIndex) {
		setBackground(Color.DARK_GRAY);
		setUpTiles(drawnTiles, startColorIndex);
	}
	
	private void setUpTiles(GRect[][] drawnTiles, int startColorIndex) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile( j, i, startColorIndex); //The parameters are switched bc rows for 2D array is up/down (y)
				drawnTiles[i][j] = new GRect(TILE_WIDTH, TILE_HEIGHT);
			}
		}
		updateBoard(drawnTiles);
	}
	
	private void updateBoard(GRect[][] drawnTiles) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				GRect curDrawnTile = drawnTiles[i][j];
				Color curColor = new Color(tiles[i][j].R, tiles[i][j].G, tiles[i][j].B);
				int xLocation = ((TILE_WIDTH+SPACE_WIDTH)*j+SPACE_WIDTH);
				int yLocation = ((TILE_HEIGHT+SPACE_HEIGHT)*i+SPACE_HEIGHT);
				curDrawnTile.setColor(curColor);
				curDrawnTile.setFilled(true);
				curDrawnTile.setFillColor(curColor);
				add(curDrawnTile, xLocation, yLocation);
			}
		}
	}
	
	private void choosePattern(GRect[][] drawnTiles, int patternNum) {
		ArrayList<Tile> tilesToChange = new ArrayList<Tile>();
		int nextColorIndex = rgen.nextInt(0, 7) + 2 + tiles[0][0].colorIndex;
		if(patternNum == 1) runPatternOne(drawnTiles, tilesToChange, nextColorIndex);
		else if(patternNum == 2) runPatternTwo(drawnTiles, tilesToChange, nextColorIndex);
		else if(patternNum == 3) runPatternThree(drawnTiles, tilesToChange, nextColorIndex);
		else if(patternNum == 4) runPatternFour(drawnTiles, tilesToChange, nextColorIndex);
		else if(patternNum == 5) runPatternFive(drawnTiles, tilesToChange, nextColorIndex);
	}
	
	private void increaseByUno(ArrayList<Tile> tilesToChange, GRect[][] drawnTiles, int nextColorIndex) {
		if(tilesToChange.get(0).colorIndex != nextColorIndex) {
			changeColor(tilesToChange, drawnTiles, nextColorIndex);
		}
	}
	
	private void changeColor(ArrayList<Tile> tilesToChange, GRect[][] drawnTiles, int nextColorIndex) {
		int curColorIndex = tilesToChange.get(0).colorIndex;
		if(curColorIndex % 12 == 0 || curColorIndex % 12 == 1) {
			for(int i = 1; i <= 127; i++) {
				for(int j = 0; j < tilesToChange.size(); j++) {
					Tile curTile = tilesToChange.get(j);
					curTile.G ++;
				}
				updateBoard(drawnTiles);
				pause(WAIT_TIME);
			}
		}
		else if(curColorIndex % 12 == 2 || curColorIndex % 12 == 3) {
			for(int i = 1; i <= 127; i++) {
				for(int j = 0; j < tilesToChange.size(); j++) {
					Tile curTile = tilesToChange.get(j);
					curTile.R --;
				}
				updateBoard(drawnTiles);
				pause(WAIT_TIME);
			}
		}
		else if(curColorIndex % 12 == 4 || curColorIndex % 12 == 5) {
			for(int i = 1; i <= 127; i++) {
				for(int j = 0; j < tilesToChange.size(); j++) {
					Tile curTile = tilesToChange.get(j);
					curTile.B ++;
				}
				updateBoard(drawnTiles);
				pause(WAIT_TIME);
			}
		}
		else if(curColorIndex % 12 == 6 || curColorIndex % 12 == 7) {
			for(int i = 1; i <= 127; i++) {
				for(int j = 0; j < tilesToChange.size(); j++) {
					Tile curTile = tilesToChange.get(j);
					curTile.G --;
				}
				updateBoard(drawnTiles);
				pause(WAIT_TIME);
			}
		}
		else if(curColorIndex % 12 == 8 || curColorIndex % 12 == 9) {
			for(int i = 1; i <= 127; i++) {
				for(int j = 0; j < tilesToChange.size(); j++) {
					Tile curTile = tilesToChange.get(j);
					curTile.R ++;
				}
				updateBoard(drawnTiles);
				pause(WAIT_TIME);
			}
		}
		else if(curColorIndex % 12 == 10 || curColorIndex % 12 == 11) {
			for(int i = 1; i <= 127; i++) {
				for(int j = 0; j < tilesToChange.size(); j++) {
					Tile curTile = tilesToChange.get(j);
					curTile.B --;
				}
				updateBoard(drawnTiles);
				pause(WAIT_TIME);
			}
		}
		for(int j = 0; j < tilesToChange.size(); j++) {
			Tile curTile = tilesToChange.get(j);
			curTile.colorIndex ++;
		}
		increaseByUno(tilesToChange, drawnTiles, nextColorIndex);
	}
	
	private void runPatternOne(GRect[][] drawnTiles, ArrayList<Tile> tilesToChange, int nextColorIndex) {
		for(int j = 0; j < tiles.length; j ++) {
			for(int i = 0; i < tiles[j].length ; i++) {
				tilesToChange.add(tiles[j][i]);
			}
			increaseByUno(tilesToChange, drawnTiles, nextColorIndex);
			tilesToChange.removeAll(tilesToChange);
		}
	}
	
	private void runPatternTwo(GRect[][] drawnTiles, ArrayList<Tile> tilesToChange, int nextColorIndex) {
		//Have the columns flip instead of rows
	}

	private void runPatternThree(GRect[][] drawnTiles, ArrayList<Tile> tilesToChange, int nextColorIndex) {
		//Have the pattern spread from a corner	
	}

	private void runPatternFour(GRect[][] drawnTiles, ArrayList<Tile> tilesToChange, int nextColorIndex) {
		//perhaps a variation of the previous but from different locations
	}

	private void runPatternFive(GRect[][] drawnTiles, ArrayList<Tile> tilesToChange, int nextColorIndex) {
		//eh
	}
	
	public void mouseClicked(MouseEvent e) {
//		removeAll();
		if(getElementAt(e.getX(), e.getY()) != null) {
			GObject clickedRect = getElementAt(e.getX(), e.getY());
			Color curColor = clickedRect.getColor();
			Color oppoColor = getOppoColor(curColor);
			clickedRect.setColor(oppoColor);
			int x = e.getX()/(TILE_WIDTH+SPACE_WIDTH);
			int y = e.getY()/(TILE_HEIGHT+SPACE_HEIGHT);
			//int colorIndex = colorIndexs.get(oppoColor);
			tiles[y][x] = new Tile(x, y, 5);
			//add(clickedRect, 100, 100);
		}
	}
	
	
	private Color getOppoColor(Color curColor) {
		int R = curColor.getRed();
		int G = curColor.getGreen();
		int B = curColor.getBlue();
		Color oppoColor = new Color(R, G, B);
		return oppoColor;
	}
	
}
