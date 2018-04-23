package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameOver extends BaseScreen {
	Label over;
	public GameOver(BaseGame game) {
		super(game);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		over=new Label("GAME OVER",game.skin,"gameover");
		uiTable.add(over).width(400).height(200);
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
	}
	
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode==Input.Keys.M){
			game.setScreen(new TowerMenu(game));
			PlayGame.lives=3;
		}
		
			
		return true;
	}

}
