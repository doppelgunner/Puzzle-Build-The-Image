package game.grid;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GridImage {
	
	private int gridSize;
	private int width;
	private int height;
	
	private String path;
	private Image image;
	
	public GridImage(String path, int gridSize) {
		try {
			image = new Image(path);
			width = image.getWidth();
			height = image.getHeight();
			this.gridSize = gridSize;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getGridSize() {
		return gridSize;
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getPath() {
		return path;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
