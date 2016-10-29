package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ReferenceImage {
	
	private Image image;
	private Color frameColor;
	private float frameSize;
	private float x,y;
	private float imageDrawWidth;
	private float imageDrawHeight;
	
	public ReferenceImage(Image image, float x, float y, float imageDrawWidth, float imageDrawHeight) {
		this.x = x; this.y = y;
		this.image = image;
		this.imageDrawWidth = imageDrawWidth;
		this.imageDrawHeight = imageDrawHeight;
	}
	
	public void setFrameColor(Color frameColor) {
		this.frameColor = frameColor;
	}
	
	public void setFrameSize(float frameSize) {
		this.frameSize = frameSize;
	}
	
	public void draw(Graphics g) {
		image.draw(x,y,imageDrawWidth,imageDrawHeight);
		
		if (frameColor != null) {
			float origLineWidth = g.getLineWidth();
			Color origColor = g.getColor();
			g.setLineWidth(frameSize);
			g.setColor(frameColor);
			
			g.drawRect(x,y,imageDrawWidth,imageDrawHeight);
			
			g.setLineWidth(origLineWidth);
			g.setColor(origColor);
		}
	}
}
