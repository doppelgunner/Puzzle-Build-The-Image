package game.state;

import game.Game;
import game.GridType;
import game.Settings;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import boboy.color.CColor;
import boboy.game.gui.GUIRectangle;

public class SettingsState extends BasicGameState {
	
	private final int id;

	private final GUIRectangle goBackBox;
	
	private final int choiceSize = 100;
	
	private final GUIRectangle[][] choices;
	private final int choicesRows;
	private final int choicesCols;
	
	public SettingsState(final int id) {
		this.id = id;
		
		//pause button
		int goBackWidth = 70;
		int goBacHeight = 40;
		goBackBox = new GUIRectangle(50 - goBackWidth / 2, 30 - goBacHeight / 2,
				goBackWidth, goBacHeight,
				Color.transparent, CColor.normalWhite,
				Color.transparent, CColor.lightYellow);
		
		choicesRows = 1;
		choicesCols = 3;
		int choicesSpacesX = 0;
		int choicesSpacesY = 0;
		choices = new GUIRectangle[choicesRows][choicesCols];
		for (int row = 0; row < choices.length; row++) {
			for (int col = 0; col < choices[row].length; col++) {
				choices[row][col] = new GUIRectangle(
						100 + col * choiceSize + choicesSpacesX,
						100 + row * choiceSize + choicesSpacesY,
						choiceSize, choiceSize,
						Color.transparent, CColor.normalWhite,
						Color.transparent, CColor.lightYellow);
				
				choicesSpacesX += 10;
			}
			choicesSpacesY += 10;
			choicesSpacesX = 0;
		}
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		Graphics g = container.getGraphics();
		
		//goBack
		goBackBox.init(g);
		goBackBox.setText("Back");
		
		choices[0][0].init(g);
		choices[0][0].setText("3x3");
		
		choices[0][1].init(g);
		choices[0][1].setText("4x4");
		
		choices[0][2].init(g);
		choices[0][2].setText("6x6");
	
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		
		goBackBox.draw(g);
		
		for (int row = 0; row < choices.length; row ++) {
			for (int col = 0; col < choices[row].length; col++) {
				choices[row][col].draw(g);
			}
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		
		if (goBackBox.isMouseInside(input.getMouseX(), input.getMouseY()) &&
				input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			//play sound
			Game.BUTTON_SOUND.play(1.2f,.3f);
			Game.MENU.init(container, game);
			game.enterState(Game.MENU.getID());
		}

		if (choices[0][0].isMouseInside(input.getMouseX(), input.getMouseY()) 
				&& input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			Game.BUTTON_SOUND.play(1.2f,.3f);
			Settings.setCurrentSize(GridType._3x3);
			
			Game.MENU.init(container, game);
			game.enterState(Game.MENU.getID());
		} else if (choices[0][1].isMouseInside(input.getMouseX(), input.getMouseY()) 
				&& input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			Game.BUTTON_SOUND.play(1.2f,.3f);
			Settings.setCurrentSize(GridType._4x4);
			
			Game.MENU.init(container, game);
			game.enterState(Game.MENU.getID());
		} else if (choices[0][2].isMouseInside(input.getMouseX(), input.getMouseY()) 
				&& input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			Game.BUTTON_SOUND.play(1.2f,.3f);
			Settings.setCurrentSize(GridType._6x6);
			
			Game.MENU.init(container, game);
			game.enterState(Game.MENU.getID());
		}
	}

	@Override
	public int getID() {
		
		return id;
	}

}
