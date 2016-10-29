package game;

import game.grid.GridImage;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Settings {
	
	private static GridImage currentGridImg;
	private static GridImage doppelgunnerGridImg, lizaGridImg, spideyGridImg;
	
	public static final String 
		PATH_DOPPELGUNNER = "resources/images/doppelgunner.png",
		PATH_LIZA = "resources/images/liza.png",
		PATH_SPIDEY = "resources/images/spidey.jpg"
		;
	public static final int 
		SIZE_DOPPELGUNNER = 3,	//3x3
		SIZE_LIZA = 4,			//4x4
		SIZE_SPIDEY = 6;		//6x6
	
	public static int getCurrentSize() {
		if (currentGridImg != null) {
			return currentGridImg.getGridSize();
		}
		
		currentGridImg = getDoppelgunnerGridImg();
		return currentGridImg.getGridSize();
	}
	
	public static int getCurrentGridImgWidth() {
		return currentGridImg.getWidth();
	}
	
	public static int getCurrentGridImgHeight() {
		return currentGridImg.getHeight();
	}
	
	public static Image getCurrentImg() {
		if (currentGridImg != null) {
			return currentGridImg.getImage();
		} else {
			currentGridImg = getDoppelgunnerGridImg();
		}
		
		return null;
	}
		
	public static void loadAllGridImg() {
		System.out.println("loading all images...");
		doppelgunnerGridImg = load(PATH_DOPPELGUNNER, SIZE_DOPPELGUNNER);
		lizaGridImg = load(PATH_LIZA, SIZE_LIZA);
		spideyGridImg = load(PATH_SPIDEY, SIZE_SPIDEY);
		System.out.println("all images loaded");
	}
	
	private static GridImage load(String path, int size) {
		return new GridImage(path, size);
	}
	
	public static void setCurrentSize(GridType gridType) {
		switch (gridType) {
		case _3x3:
			currentGridImg = getDoppelgunnerGridImg();
			break;
		case _4x4:
			currentGridImg = getLizaGridImg();
			break;
		case _6x6:
			currentGridImg = getSpideyGridImg();
			break;
			
		default:
			currentGridImg = getDoppelgunnerGridImg();
		}
	}
	
	public static GridImage getDoppelgunnerGridImg() {
		if (doppelgunnerGridImg != null) {
			return doppelgunnerGridImg;
		} else {
			doppelgunnerGridImg = load(PATH_DOPPELGUNNER, SIZE_DOPPELGUNNER);
			return doppelgunnerGridImg;
		}
	}
	
	public static GridImage getLizaGridImg() {
		if (lizaGridImg != null) {
			return lizaGridImg;
		} else {
			lizaGridImg = load(PATH_LIZA, SIZE_LIZA);
			return lizaGridImg;
		}
	}
	
	public static GridImage getSpideyGridImg() {
		if (spideyGridImg != null) {
			return spideyGridImg;
		} else {
			spideyGridImg = load(PATH_SPIDEY, SIZE_SPIDEY);
			return spideyGridImg;
		}
	}
}
