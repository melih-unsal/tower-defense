package com.mygdx.game;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class TowerMenu extends BaseScreen {

	TextButton play;
	TextButton load_game,exit;
	Sound buton;
	Music instrumenatal;
	BitmapFont newfont;
	 public TowerMenu(BaseGame g) {
		super(g);
		
	}
	public void create() {
		// TODO Auto-generated method stub
		instrumenatal=Gdx.audio.newMusic(Gdx.files.internal("game.mp3"));
		instrumenatal.play();
		BitmapFont font=new BitmapFont(Gdx.files.internal("nurum.fnt"));
		game.skin.add("uiFont", font);
		LabelStyle style=new LabelStyle(font, Color.YELLOW);
		game.skin.add("uiLabelStyle", style);
		Label lab=new Label("Press M to return menu in the game", game.skin,"uiLabelStyle");
		TextButtonStyle buttonStyle=new TextButtonStyle();
		buttonStyle.font=font;
		buttonStyle.fontColor=Color.RED;	
		Texture upButton=new Texture("buttonUp.png");
		game.skin.add("upButton", new NinePatch(upButton,5,5,5,5));
		buttonStyle.up=game.skin.getDrawable("upButton");
		buttonStyle.overFontColor=Color.BLUE;
		buttonStyle.downFontColor=Color.PINK;
		game.skin.add("buttonStyle", buttonStyle);
		lab.setColor(new Color(50, 0, 200, 1f));
		lab.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0.8f,0f,0,1),0.5f))));
		Texture background=new Texture("main.jpg");
		game.skin.add("background", background);
		buton=Gdx.audio.newSound(Gdx.files.internal("button.ogg"));
		exit=new TextButton("EXIT", game.skin, "buttonStyle");
		exit.addListener(new InputListener()
				{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
	
				return true;
			}
				public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
					Gdx.app.exit();
					dispose();
				}
				});
		exit.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0f,0f,0,8f),0.5f))));
		play=new TextButton("Play", game.skin,"buttonStyle");
		play.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0f,0f,0,8f),0.5f))));
		play.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
		
			instrumenatal.stop();
			return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				buton.play(0.2f);
				game.setScreen(new PlayGame(game));
			}
			});
	    load_game=new TextButton("Load Game", game.skin,"buttonStyle");
		load_game.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(0.5f),Actions.color(new Color(0.5f,0.5f,0,1),0.5f))));
		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add(play).colspan(3).padTop(100).height(80).width(200);
		uiTable.row();
		uiTable.add(load_game).colspan(3).padTop(30).height(80).width(200);
		uiTable.row();
		uiTable.add(exit).colspan(3).padTop(30).height(80).width(200);
		uiTable.row();
		uiTable.add(lab).padTop(180).right();
	
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		super.render(delta);

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		buton.dispose();
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub

		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	
}
