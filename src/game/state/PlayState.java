package game.state;

import game.Game;
import game.ReferenceImage;
import game.Settings;
import game.grid.Grid;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import boboy.color.CColor;
import boboy.game.gui.GUIRectangle;

public class PlayState extends BasicGameState {

	private final int id;
	
	private final float X = 100, Y = 30;
	private final int WIDTH = 400, HEIGHT = 400;
	
	private final GUIRectangle pauseBox;
	private final GUIRectangle resumeBox;
	private final GUIRectangle infoStateBox;
	private final GUIRectangle menuBox;
	
	private final GUIRectangle statusBox;
	private final GUIRectangle scrambleBox;
	
	private boolean pause;
	
	private Color defaultColor;
	
	private Grid frameGrid;
	private ReferenceImage referenceImage;
	
	
	public PlayState(final int id) {
		this.id = id;
		
		//pause button
		int pauseWidth = 70;
		int pauseHeight = 40;
		pauseBox = new GUIRectangle(15, 10,
				pauseWidth, pauseHeight,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightYellow);
		
		//resume button
		int resumeWidth = 150;
		int resumeHeight = 60;
		resumeBox = new GUIRectangle(
				Game.WIDTH / 2 - resumeWidth / 2,Game.HEIGHT / 2 - resumeHeight / 2 - 80,
				resumeWidth,resumeHeight,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightYellow);
		
		//instruction button
		int controlWidth = 150;
		int controlHeight = 60;
		
		infoStateBox = new GUIRectangle(
				Game.WIDTH / 2 - controlWidth / 2,Game.HEIGHT / 2 - controlHeight / 2,
				controlWidth,controlHeight,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightYellow);
		
		//menu button
		int menuWidth = 130;
		int menuHeight = 55;
		menuBox = new GUIRectangle(
				Game.WIDTH / 2 - menuWidth / 2, Game.HEIGHT / 2 - menuHeight / 2 + 80,
				menuWidth, menuHeight,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightYellow);
		
		
		statusBox = new GUIRectangle(
				40,150,
				100,100,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightYellow
		);
		
		scrambleBox = new GUIRectangle(
				15,250,
				150, 40,
				CColor.darkBlack, CColor.normalWhite,
				CColor.darkBlack, CColor.lightYellow
		);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
		input.clearControlPressedRecord();
		input.clearKeyPressedRecord();
		input.clearMousePressedRecord();
		
		defaultColor = CColor.normalWhite;
		Graphics g = container.getGraphics();
		g.setColor(defaultColor);

		//pause
		pauseBox.init(g);
		pauseBox.setText("Pause");
		
		//resume
		resumeBox.init(g);
		resumeBox.setText("Resume");
		
		//instruction
		infoStateBox.init(g);
		infoStateBox.setText("Info");
		
		//menu
		menuBox.init(g);
		menuBox.setText("Menu");
		
		//status
		statusBox.init(g);
		statusBox.setText("Status: ?");
		
		pause = false;
		
		//scramble
		scrambleBox.init(g);
		scrambleBox.setText("Scramble Puzzle");
		
		
		System.out.println("Curren grid size: " + Settings.getCurrentSize());
		System.out.println("Current image width: " + Settings.getCurrentGridImgWidth());
		System.out.println("Current image height: " + Settings.getCurrentGridImgHeight());
		
		//load grid here
		
		frameGrid = new Grid(Settings.getCurrentImg(), 
				Game.WIDTH / 2 - WIDTH / 2 + X, Game.HEIGHT / 2 - HEIGHT / 2 + Y,
				WIDTH, HEIGHT,
				Settings.getCurrentSize(), Settings.getCurrentSize(),
				Settings.getCurrentGridImgWidth(), Settings.getCurrentGridImgHeight());
		
		referenceImage = new ReferenceImage(Settings.getCurrentImg(), 40,80,100,100);
		referenceImage.setFrameColor(CColor.normalWhite);
		referenceImage.setFrameSize(5f);
		
		frameGrid.random();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		//status
		statusBox.drawTextOnly(g);
		
		//scramble
		scrambleBox.draw(g);
		
		//reference image
		referenceImage.draw(g);
		
		//draw always even if game is paused
		frameGrid.draw(g);
		//end
		
		
		if (!pause) {
			
			//gui
			 pauseBox.draw(g);
		} else {
			g.setColor(CColor.alphaDarkYellow);
			g.fillRect(10,10, Game.WIDTH - 20, Game.HEIGHT - 20);
			g.setColor(CColor.lightYellow);
			g.drawRect(10,10, Game.WIDTH - 20, Game.HEIGHT - 20);
			
			//gui
			resumeBox.draw(g);
			infoStateBox.draw(g);
			menuBox.draw(g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		Input input = container.getInput();
		
		if (!container.hasFocus()) {
			pause = true;
		}
		
		if (!pause) {
			
			//when pause is clicked
			if (pauseBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
					input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				
				//play sound
				Game.BUTTON_SOUND.play(1.2f,.3f);
				
				pause = true;
			}
			
			if (scrambleBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
					input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				
				//play sound
				Game.BUTTON_SOUND.play(1.2f,.3f);
				frameGrid.random();
			}
			
			frameGrid.update(input, delta);
		
		} else {
			
			//when resume is clicked
			if (resumeBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
					input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				
				//play sound
				Game.BUTTON_SOUND.play(1.2f,.3f);
				
				//resume game
				pause = false;
			}
			
			//when control is clicked
			if (infoStateBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
					input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				
				//play sound
				Game.BUTTON_SOUND.play(1.2f,.3f);
				Game.CONTROL.init(container, game);
				game.enterState(Game.CONTROL.getID());
			}
			
			//when menu is clicked
			if (menuBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
					input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				
				//play sound
				Game.BUTTON_SOUND.play(1.2f,.3f);
				
				game.enterState(Game.MENU.getID());
			}
		}

		statusBox.setText(frameGrid.correct() ? "Status: correct" : "Status: wrong");
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
