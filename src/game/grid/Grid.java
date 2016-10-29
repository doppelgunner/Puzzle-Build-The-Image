package game.grid;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import boboy.color.CColor;

public class Grid extends Rectangle {
	
	private Image originalImage;
	private int rows;
	private int cols;
	
	private Cell[][] grid;
	
	private int cellWidth;
	private int cellHeight;
	
	private int imageWidth;
	private int imageHeight;
	private int imageW;
	private int imageH;
	
	private ArrayList<Cell> swapHolder;
	
	public Grid(Image image, float x, float y, int width, int height, int rows, int cols, int imageWidth, int imageHeight) {
		super(x,y,width,height);
		this.originalImage = image;
		this.rows = rows;
		this.cols = cols;
		
		imageW = imageWidth / cols;
		imageH = imageHeight / rows;
		
		cellWidth = width / (cols);
		cellHeight = height / (rows);
		
		swapHolder = new ArrayList<>(2);
		
		grid = new Cell[rows][cols];
		separateImage();
	}
	
	private void separateImage() {
		int id = 0;
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				Image subImage = originalImage.getSubImage(col * imageW, row * imageH, imageW, imageH);
				grid[row][col] = new Cell(subImage,id++, x + col * (cellWidth), y + row * (cellHeight),cellWidth,cellHeight);
			}
		}
		
	}
	
	public void draw(Graphics g) {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				grid[row][col].draw(g);
				
				
			}
		}
		
		if (swapHolder.size() == 2) {
			swapHolder.get(0).swap(swapHolder.get(1));
			
			for (Cell cell : swapHolder) {
				cell.deactivate();
			}
			
			swapHolder.clear();
		} 
		
		//drawing borders
		float origLineWidth = g.getLineWidth();
		Color origColor = g.getColor();
		
		g.setColor(CColor.normalWhite);
		g.setLineWidth(5f);
		g.drawRect(x, y, width, height);
		
		g.setColor(origColor);
		g.setLineWidth(origLineWidth);
	}
	
	public void update(Input input, int delta) {
		float x = input.getMouseX();
		float y = input.getMouseY();
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				
			
				if (grid[row][col].isIn(x,y)) {
					grid[row][col].hover(true);
				} else {
					grid[row][col].hover(false);
				}
				
			}
		}
		
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					
					//add to swapHolder
					if (grid[row][col].isIn(x,y)) {
						swapHolder.add(grid[row][col]);
						
						grid[row][col].activate();
						
					}
					
				}
			}
		}

	}
	
	public void random() {
		for (int i = 0; i < rows * cols; i++) {
			int randomCol = (int) (Math.random() * cols);
			int randomRow = (int) (Math.random() * rows);
			
			int randomCol2 = (int) (Math.random() * cols);
			int randomRow2 = (int) (Math.random() * rows);
			
			grid[randomRow][randomCol].swap(grid[randomRow2][randomCol2]);
		}
	}
	
	public boolean correct() {
		int counter = 0;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				
				//add to swapHolder
				if (grid[row][col].getId() != counter++) {
					return false;
				}
				
			}
		}
		
		return true;
	}
}
