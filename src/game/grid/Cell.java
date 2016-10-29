package game.grid;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import boboy.color.CColor;

public class Cell extends Rectangle {
	
	private Image image;
	private int id;
	private static final int gap = 4;
	private boolean border = false;
	private boolean hover = false;
	
	public Cell(Image image,int id, float x, float y, int width, int height) {
		super(x,y,width,height);
		this.image = image;
		this.id = id;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void draw(Graphics g) {
		image.draw(x + gap,y + gap,width - gap, height - gap);
		
		if (hover) {
			float origLineWidth = g.getLineWidth();
			Color origColor = g.getColor();
			
			g.setColor(CColor.alphaWhite);
			g.setLineWidth(gap);
			g.fillRect(x+gap, y+gap, width - gap, height - gap);
			
			
			g.setColor(origColor);
			g.setLineWidth(origLineWidth);
		}
		
		if (border) {
			float origLineWidth = g.getLineWidth();
			Color origColor = g.getColor();
			
			g.setLineWidth(gap);
			g.setColor(CColor.alphaWhite);
			g.fillRect(x+gap, y+gap, width - gap, height - gap);
			
			g.setColor(origColor);
			g.setLineWidth(origLineWidth);
		}
		
	}

	
	public Image getImage() {
		return image;
	}

	public void swap(Cell cell) {
		float tempX = cell.getMinX();
		float tempY = cell.getMinY();
		int tempId = cell.getId();
		
		cell.setX(minX);
		cell.setY(minY);
		cell.setId(id);
		
		setX(tempX);
		setY(tempY);
		setId(tempId);
	}
	
	public boolean isIn(float x, float y) {
		if (x < minX + gap || x > maxX - gap) return false;
		if (y < minY + gap || y > maxY - gap) return false;
		
		return true;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void activate() {
		border = true;
	}
	
	public void deactivate() {
		border = false;
	}

	public void hover(boolean b) {
		this.hover = b;
	}
}
