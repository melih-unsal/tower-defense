package com.mygdx.game;
import com.badlogic.gdx.Game;

public class TowerGame extends BaseGame {
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		TowerMenu menu=new TowerMenu(this);
		setScreen(menu);
	}

}
