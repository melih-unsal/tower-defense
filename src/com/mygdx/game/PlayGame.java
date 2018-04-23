package com.mygdx.game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class PlayGame extends BaseScreen  {
	int livesx=70;
	int livesy=455;
	int livesWidth=40;
	static int lives=3;
	int beginingrender=200;
	int pointerx=300;
	int pointery=444;
	int pointerwidth=40;
	int pointerheight=10;
	boolean tablevisibility=true;
	boolean kulekur=true;
	static int levelnumber=0;
	int sscore=0;
	ArrayList<enemy> enemies;
	GameActor background;
	int towerWidth=40;
	int towerHeight=40;
	public static ShapeRenderer shape=new ShapeRenderer();
	int squareX=0;
	int squareY=9;
	Sound buttonSound;
	ArrayList<Tower> towers;
	Music ingame;
	Label level,score,towerpointers,livesLabel;
	Table towerMenu;
	TextButton attackTower,auraTower,defenseTower,startButton;
	Label menu;
	Tower lastTower;
	boolean drawsquare=false;
	int towerright;
	boolean sendenemy=false;
	int controlenemy=10;
	int enemyrender=200;
	int enemyrenderControl=0;
	Animation enemyAnim1,enemyAnim2,enemyAnim3,enemyAnim4;
	GameActor duration1,duration2,duration3,target;
	Texture dest,tar;
	int spawnX,spawnY;
	ArrayList<Node> foundpath1,foundpath2,foundpath3,foundpath4;
	Comparator<Node> sorter;
	int ennumber=0;
	public static ArrayList<ArrayList<Node>> paths;
	int maxattacktower=3,maxdefensetower=3,maxauratower=1;
	int attacknum=0,auranum=0,defensenum=0;
	static ArrayList<GameActor> removeList;
	ArrayList<Shoot> shoots;
	public PlayGame(BaseGame game) {
		super(game);
		removeList=new ArrayList<GameActor>();
		shoots=new ArrayList<Shoot>();
	}

	public void create () {
		spawnX=0;
		spawnY=8;
		dest=new Texture("destination.png");
		tar=new Texture("target.png");
		duration1=new GameActor();
		duration2=new GameActor();
		duration3=new GameActor();
		target=new GameActor();
		duration1.setTexture(dest);
		duration2.setTexture(dest);
		duration3.setTexture(dest);
		target.setTexture(tar);
		setAnimations();
		towerright=3;
		lastTower=null;
		towerMenu=new Table();
		attackTower=new TextButton("ATTACK TOWER", game.skin, "buttonStyle");
		auraTower=new TextButton("AURA TOWER", game.skin, "buttonStyle");
		defenseTower=new TextButton("DEFENSE TOWER", game.skin, "buttonStyle");
		startButton=new TextButton("START", game.skin, "buttonStyle");
		startButton.setTransform(true);
		attackTower.setTransform(true);
		//attackTower.setScale(0.3f);
		auraTower.setTransform(true);
		//auraTower.setScale(0.3f);
		defenseTower.setTransform(true);
		//defenseTower.setScale(0.3f);
		towerMenu.add(attackTower).width(400).height(100);
		towerMenu.row();
		towerMenu.add(defenseTower).width(400).height(100);
		towerMenu.row();
		towerMenu.add(auraTower).width(400).height(100);
		towerMenu.setFillParent(true);
		Texture back=new Texture("gray.jpg");
		game.skin.add("gray", back);
		//towerMenu.setBackground(game.skin.getDrawable("gray"));
		towerMenu.setTransform(true);
		towerMenu.setScale(0.4f);
		//towerMenu.setDebug(true);
		towerMenu.right().top();
		//uiTable.setDebug(true);
		BitmapFont bmf=new BitmapFont();
		LabelStyle ls=new LabelStyle(bmf, Color.FOREST);
		game.skin.add("gameover", ls);
		ingame=Gdx.audio.newMusic(Gdx.files.internal("play.mp3"));
		ingame.play();
		uiTable.setFillParent(true);
		towers=new ArrayList<Tower>();
		shape=new ShapeRenderer();
		enemies=new ArrayList<enemy>();
		buttonSound=Gdx.audio.newSound(Gdx.files.internal("button.ogg"));
		background=new GameActor();
		background.setTexture(new Texture("back.jpg"));
		Texture backg=new Texture("grass_template.jpg");
		game.skin.add("back", backg);
		background.setWidth(Gdx.graphics.getWidth());
		background.setHeight(Gdx.graphics.getHeight());
		
		towerpointers=new Label("Attack Tower :"+(maxattacktower-attacknum)+
				 " Defense Tower :"+(maxdefensetower-defensenum)+
				 " Aura Tower :"+(maxauratower-auranum ), ls);
		 towerpointers.setText("Attack Tower :"+3+
				 " Defense Tower :"+3+
				 " Aura Tower :"+1);
		 mainStage.addActor(towerpointers);
		 
		 attackTower.addListener(new InputListener(){
			 public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				if(kulekur && attacknum<maxattacktower){
						Tower t=new SingleTower(enemies, 80);
						 t.setTexture(new Texture("1.png"));
						 t.setPosition(squareX, squareY );
						 t.setWidth(towerWidth);
						 t.setHeight(towerHeight);
						 lastTower=t;
						 buttonSound.play(0.2f);
						 towers.add(t);
						 mainStage.addActor(t);		
						 //mainStage.addActor(t.shoot);
						 attacknum++;
						 towerpointers.setText("Attack Tower :"+(maxattacktower-attacknum)+
								 " Defense Tower :"+(maxdefensetower-defensenum)+
								 " Aura Tower :"+(maxauratower-auranum ));
				}
					return true;
				}
					public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
						
					}
		 });
		//mainStage.addActor(background);
		 defenseTower.addListener(new InputListener(){
			 public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				if(kulekur && defensenum<maxdefensetower){
						Tower t=new DefenceTower(enemies, 70);
						 t.setTexture(new Texture("2.png"));
						 t.setPosition(squareX, squareY );
						 t.setWidth(towerWidth);
						 t.setHeight(towerHeight);
						 lastTower=t;
						 buttonSound.play(0.2f);
						 towers.add(t);
						 mainStage.addActor(t);	
						 defensenum++;
						 towerpointers.setText("Attack Tower :"+(maxattacktower-attacknum)+
								 " Defense Tower :"+(maxdefensetower-defensenum)+
								 " Aura Tower :"+(maxauratower-auranum ));
				}
					return true;
				}
					public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
						
					}
		 });
		 auraTower.addListener(new InputListener(){
			 public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				 if(kulekur && auranum<maxauratower){
						Tower t=new SlowTower(enemies, 70);
						 t.setTexture(new Texture("newaura.png"));
						 t.setPosition(squareX, squareY );
						 t.setWidth(towerWidth);
						 t.setHeight(towerHeight);
						 lastTower=t;
						 buttonSound.play(0.2f);
						 towers.add(t);
						 mainStage.addActor(t);	
						 auranum++;
						 towerpointers.setText("Attack Tower :"+(maxattacktower-attacknum)+
								 " Defense Tower :"+(maxdefensetower-defensenum)+
								 " Aura Tower :"+(maxauratower-auranum ));
				 }
					return true;
				}
					public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
						
					}
		 });
		 level=new Label("WAVE ",ls);
		 score=new Label("SCORE 0",ls);
		 livesLabel=new Label("LIVES :"+3, ls);
		 mainStage.addActor(livesLabel);
		 livesLabel.setPosition(livesx, livesy);
		 livesLabel.setWidth(livesWidth);
		 towerpointers.setPosition(pointerx, pointery);
		 towerpointers.setSize(pointerwidth, pointerheight);
		 towerpointers.setColor(Color.RED);
		 startButton.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
					Actions.delay(0.5f),Actions.color(new Color(0.5f,0.5f,0,1),0.5f))));
		 startButton.addListener(new InputListener(){
			 public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				 
				 if(kulekur)
				 	ennumber=0;
				 if(ennumber==0)
					 level.setText("WAVE "+(++levelnumber));
				 enemyrender-=40;
				 	kulekur=false;
				 	sendenemy=true;
					up();
			
					return true;
				}
					public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
						
					}
		 });
		 startButton.setScale(0.4f);
		 uiTable.background(game.skin.getDrawable("back"));
		 uiTable.add(level);
		 uiTable.add(startButton).expandX().width(180).height(50).padTop(-25);
		 uiTable.add(score).padRight(10);
		 uiTable.row();
		 uiTable.add(towerMenu).colspan(3).padTop(-80).padRight(-510);
		 uiTable.top();
		 towerMenu.setVisible(false);
		
		 mainStage.addActor(duration1);
		 mainStage.addActor(duration2);
		 mainStage.addActor(duration3);
		 mainStage.addActor(target);
		 duration1.setPosition(towerWidth*3,towerHeight*10);
		 duration2.setPosition(towerWidth*8,towerHeight*10);
		 duration3.setPosition(towerWidth*3,towerHeight*4);
		 target.setPosition(towerWidth*12, towerHeight*5);
		 duration1.setSize(towerWidth, towerHeight);
		 duration2.setSize(towerWidth, towerHeight);
		 duration3.setSize(towerWidth, towerHeight);
		 target.setSize(towerWidth, towerHeight);
		 sorter = new Comparator<Node>() {
				@Override
				public int compare(Node arg0, Node arg1) {
					if(arg0.fCost > arg1.fCost) {
						return 1;
					}
					if(arg0.fCost < arg1.fCost) {
						return -1;
					}
					return 0;
				}
			};
		 
	}
	public void up(){
		//sendenemy=true;	
		//for(Tower t:towers)
			//t.shoot.setPosition(t.getX()+t.getWidth()/2, t.getY()+t.getHeight()/2);
		Position goal1 = new Position(3,4);
		 rectGraph rg=new rectGraph(12, 16,towers);
		 rg.neighbor(12,16,towers);
		 rg.euclid(goal1);
		 
		 foundpath1=pathFinder(new Position(11-spawnY, spawnX), goal1, rg);
		 foundpath1.add(new Node(new Position(11-spawnY, spawnX), null, 0, 13.5));
		
		 Position goal2 = new Position(6,9);
		 rg.neighbor(12,16,towers);
		 rg.euclid(goal2);
		 
		 rg.setNodeNull(goal1.X,goal1.Y);
		  foundpath2=pathFinder(goal1, goal2, rg);
		  foundpath2.add(new Node(goal1, null, 0, 13.5));
		  
		 Position goal3 = new Position(9,6);
		 rg.neighbor(12,16,towers);
		 rg.euclid(goal3);
		 
		 rg.setNodeNull(goal2.X,goal2.Y);
		  foundpath3=pathFinder(goal2, goal3, rg);
		  foundpath3.add(new Node(goal2, null, 0, 13.5));
		  
		 Position goal4 = new Position(9,15);
		 rg.neighbor(12,16,towers);
		 rg.euclid(goal4);

		 rg.setNodeNull(goal3.X,goal3.Y);
		  foundpath4=pathFinder(goal3, goal4, rg);
		  foundpath4.add(new Node(goal3, null, 0, 13.5));
		  
		  paths=new ArrayList<ArrayList<Node>>();
		  paths.add(foundpath1);
		  paths.add(foundpath2);
		  paths.add(foundpath3);
		  paths.add(foundpath4);

	}
	
	public void setAnimations(){
		TextureRegion[] enemyFrames=new TextureRegion[8];
		for(int i=1;i<9;i++){
			String filename="run00"+i+".png";
			Texture tex=new Texture(filename);
			tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			enemyFrames[i-1]=new TextureRegion(tex);
		}
		Array<TextureRegion> enemyFramesArray=new Array<TextureRegion>(enemyFrames);
		 enemyAnim1=new Animation(0.1f, enemyFramesArray,Animation.PlayMode.LOOP_PINGPONG);
		 
		 for(int i=1;i<9;i++){
				String filename="Walk"+(i-1)+".png";
				Texture tex=new Texture(Gdx.files.internal(filename));
				tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
				enemyFrames[i-1]=new TextureRegion(tex);
			}
		  enemyFramesArray=new Array<TextureRegion>(enemyFrames);
		 enemyAnim2=new Animation(0.1f, enemyFramesArray,Animation.PlayMode.LOOP_PINGPONG);
		 
		 
		 for(int i=0;i<8;i++){
				String filename="Die_"+i+".png";
				Texture tex=new Texture(Gdx.files.internal(filename));
				tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
				enemyFrames[i]=new TextureRegion(tex);
			}
		  enemyFramesArray=new Array<TextureRegion>(enemyFrames);
		 enemyAnim3=new Animation(0.1f, enemyFramesArray,Animation.PlayMode.LOOP_PINGPONG);
		 
		 for(int i=0;i<8;i++){
				String filename="Hurt_"+i+".png";
				Texture tex=new Texture(Gdx.files.internal(filename));
				tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
				enemyFrames[i]=new TextureRegion(tex);
			}
		  enemyFramesArray=new Array<TextureRegion>(enemyFrames);
		 enemyAnim4=new Animation(0.1f, enemyFramesArray,Animation.PlayMode.LOOP_PINGPONG);
		 
		 
	}
	public void setPhotos(){
		Texture tower1=new Texture("1.png");
		Texture tower2=new Texture("2.png");
		Texture tower3=new Texture("3.png");
		Texture tower4=new Texture("4.png");
		Texture tower5=new Texture("5.png");
		Texture tower6=new Texture("6.png");
		Texture tower7=new Texture("7.png");
		Texture tower8=new Texture("8.png");
		Texture tower9=new Texture("9.png");
		Texture tower10=new Texture("10.png");
	}
	public void createEnemy(){
		
		enemy en=new Landing(paths);
		en.storeAnimation("single", enemyAnim1);
		en.storeAnimation("second", enemyAnim2);
		en.storeAnimation("die", enemyAnim3);
		en.storeAnimation("hurt", enemyAnim4);
		en.setPosition(spawnX*towerWidth,spawnY*towerHeight);
		mainStage.addActor(en);
		enemies.add(en);
		
	}
	

	public void dispose () {
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		//createEnemy();
		super.render(delta);
		drawsquare=false;
		for(Tower t:towers){
			if(t!=null){
				t.setWidth(towerWidth);
				 t.setHeight(towerHeight);
			}
		}
 for(enemy e:enemies){
			 int pay=5;
			 if(e.hp>0.5f && e.visible){
			 shape.begin(ShapeType.Line);
			 shape.setColor(Color.BLACK);
			 shape.rect(e.getX(), e.getY()+e.getHeight()+pay, e.getWidth(), 5);
			 shape.end();
			 shape.begin(ShapeType.Filled);
			 shape.setColor(Color.GREEN);
			 shape.rect(e.getX(), e.getY()+e.getHeight()+pay, (e.hp/e.fixhp)*e.getWidth(), 5);
			 shape.setColor(Color.RED);
			 shape.rect(e.getX()+(e.hp/e.fixhp)*e.getWidth(), e.getY()+e.getHeight()+pay, (1-(e.hp/e.fixhp))*e.getWidth(), 5);
			 shape.end();
			 
			 }
 }
		
	}
	@Override 
	public void hide() {
		// TODO Auto-generated method stub
		
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
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode==Input.Keys.M){
			game.setScreen(new TowerMenu(game));
			ingame.stop();
		}
		else if(keycode==Input.Keys.T){
			tablevisibility=!tablevisibility;
			towerMenu.setVisible(tablevisibility);
		}
			
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
		boolean s=true;
		try{
			up();
			
		 }
		
		catch(Exception e){
			s=false;
		}
		for(int i=0;i<towers.size();i++){
			towers.get(i).setWidth(towerWidth);
			towers.get(i).setHeight(towerHeight);
		}
		 /*if(lastTower!=null){
		 mainStage.addActor(lastTower.shoot);
		 }*/
		if(squareX==0 && squareY==8*towerHeight)
			s=false;
		for(int i=0;i<towers.size()-1;i++)
			if((int)towers.get(i).getX()==squareX && (int)towers.get(i).getY()==squareY){
				s=false;
				break;
			}
		
		if(s)
		 lastTower=null;
		 
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
		squareX=(int)(screenX/towerWidth)*towerWidth;
		squareY=(int)((Gdx.graphics.getHeight()-screenY)/towerHeight)*towerHeight;
		if(lastTower!=null){
			if((!isInButton(towerMenu, squareX, squareY))|| (isInButton(towerMenu, squareX, squareY)&& (!tablevisibility)))
			lastTower.setPosition(squareX, squareY);
		}
		float yy=Gdx.graphics.getHeight()-screenY;
		/*if(screenX>=towerMenu.getX() && screenX<=towerMenu.getX()+towerMenu.getWidth())
			if(yy>=towerMenu.getY() && yy<=towerMenu.getY()+towerMenu.getHeight())
				towerMenu.setVisible(true);
			else 
				towerMenu.setVisible(false);
		else
			towerMenu.setVisible(false);*/
		return true;
	}
	public boolean isInButton(Table tb,float x,float y){
		if(tb.getX()+50<=x && x<=tb.getX()+tb.getWidth())
			if(tb.getY()+50<=y && y<=tb.getY()+tb.getHeight())
				return true;
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void update(float dt) {
		removeList.clear();
		livesLabel.setText("LIVES :"+lives);
		if(lives==0){
			game.setScreen(new GameOver(game));
			ingame.stop();
		}
		towerpointers.setPosition(pointerx,pointery);
		towerpointers.setSize(pointerwidth, pointerheight);
		
		livesLabel.setPosition(livesx, livesy);
		 livesLabel.setWidth(livesWidth);
		 
		if(sendenemy && enemyrenderControl%enemyrender==0 && controlenemy>ennumber){
			createEnemy();
			ennumber++;
		}

		if(controlenemy!=enemies.size())
			enemyrenderControl++;
			
		
		else{
			sendenemy=false;
			enemyrenderControl=0;
		}
		for(enemy e:enemies){
			e.setSize(30, 30);
		}
		
		 duration1.setSize(towerWidth, towerHeight);
		 duration2.setSize(towerWidth, towerHeight);
		 duration3.setSize(towerWidth, towerHeight);
		 duration1.setPosition(towerWidth*4,towerHeight*8);
		 duration2.setPosition(towerWidth*9,towerHeight*5);
		 duration3.setPosition(towerWidth*6,towerHeight*2);
		 target.setPosition(towerWidth*15, towerHeight*2);
		 target.setSize(towerWidth, towerHeight);
		 int pay=5;
		 if(towers.size()>0)
			 for(Tower t:towers){
				 if(t instanceof AuraTower)
					 ((AuraTower) t).applyAura();
				 shape.begin(ShapeType.Line);
				 if(t instanceof AttackTower)
					 shape.setColor(Color.CYAN.r,Color.CYAN.g,Color.CYAN.b,0.9f);
				 else if(t instanceof AuraTower)
					 shape.setColor(Color.RED);
				 if(t instanceof AttackTower)
					 shape.circle(t.getX()+t.getOriginX()+t.getWidth()/2, t.getY()+t.getOriginY()+t.getHeight()/2, t.menzil);
				 shape.end();
			 }
		 for(enemy e:enemies){
			 
			 if(e.hp>0.5f){
			 shape.begin(ShapeType.Line);
			 shape.setColor(Color.BLACK);
			 shape.rect(e.getX(), e.getY()+e.getHeight()+pay, e.getWidth(), 5);
			 shape.end();
			 shape.begin(ShapeType.Filled);
			 shape.setColor(Color.GREEN);
			 shape.rect(e.getX(), e.getY()+e.getHeight()+pay, (e.hp/e.fixhp)*e.getWidth(), 5);
			 shape.setColor(Color.RED);
			 shape.rect(e.getX()+(e.hp/e.fixhp)*e.getWidth(), e.getY()+e.getHeight()+pay, (1-(e.hp/e.fixhp))*e.getWidth(), 5);
			 shape.end();
			 
			
			 
		 }
			 
			 else if(e.hp<=0.5 && e.writescore){
				 removeList.add(e);
				 sscore+=50;
				 score.setText("SCORE "+sscore);
				 e.writescore=false;
			 }
		 }
		 
			 
		 for(Tower t:towers){
			 t.controllenemy();
			 enemy ene=t.hedef;
			 if(ene!=null && t.setpos){
				 Shoot s=new Shoot(200,"fire1.png");
				 s.setTexture(new Texture("fire1.png"));
				 s.setWidth(20);
				 s.setHeight(20);
				 s.setVisible(true);
				 s.setParentList(shoots);
				 shoots.add(s);
				 s.setPosition(t.getX()+t.getOriginX(), t.getY()+t.getOriginY());
				 t.setpos=false;
				 s.en=ene;
				 s.t=t;
				 mainStage.addActor(s);
				 
				 
				 
				 
				 
			 }
		 }
		 
		 for(Shoot s:shoots){
			 if(s.en!=null){
				s.setVelocityAS((float)Math.toDegrees(MathUtils.atan2((s.en.getY()+s.en.getHeight()/2-s.getY()-s.getHeight()/2),
						 s.en.getX()+s.en.getWidth()/2-s.getX()-s.getWidth()/2)), s.speed);
				 //s.setVisible(true);
				 if(s.intersect(s.en)){
					 s.en.setSize(30, 30);
					 s.en.hp-=s.t.harm;
					 s.t.setpos=true;
					 removeList.add(s);
				 }
				 
			 }
		 }
		 
		 boolean c=true;
		 if(enemies.size()>0)
			 for(enemy enem:enemies)
				 if(enem.visible ||(enemies.size()%controlenemy!=0))
					 c=false;
		 if(!c){
			 ennumber=0;
			 attacknum=0;
			 defensenum=0;
			 auranum=0;                                               
			 towerpointers.setText("Attack Tower :"+(maxattacktower-attacknum)+
					 " Defense Tower :"+(maxdefensetower-defensenum)+
					 " Aura Tower :"+(maxauratower-auranum ));
		 }
		 kulekur=c;
		 for(GameActor g:removeList){
			 g.destroy();
		 	
		 }
		 }
		 
	
	
	
	//Erkin
	 

	
	public ArrayList<Node> pathFinder(Position start, Position end, rectGraph rectangle){
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		Node current = rectangle.getNode(start.getX(), start.getY());
		open.add(current);
		while(open.size() > 0) {
			Collections.sort(open, sorter);
			//sort(open);
			current = open.get(0);
			if(current.pos.getX() == end.getX() && current.pos.getY() == end.getY()) {
				ArrayList<Node> shortpath = new ArrayList<Node>();
				while(current.parent != null) {
				shortpath.add(current);
					current = current.parent;
				}
				open.clear();
				closed.clear();
				return shortpath;
			}
			open.remove(current);
			closed.add(current);
			for(Node n : current.nb) {
				double gCost = current.gCost + euclid1(current.pos, n.pos);
				
				n.gCost = gCost;
				n.fCost = gCost + n.hCost;
				if(posContains(closed, n.pos) && n.gCost>=current.gCost) {
					continue;
				}
				if(!posContains(open , n.pos) || n.gCost<current.gCost) {
				n.parent = current;
				open.add(n);
				}
			//sort(open);			
		}
		}
		return null;
	}
	public boolean posContains(ArrayList<Node> list, Position pos) {
		for(Node n : list) {
			if(n.pos.X == pos.X && n.pos.Y == pos.Y) {
				return true;
			}
		}
		return false;
	}
	public double euclid1(Position current, Position target){
	    	int diffX = current.getX() - target.getX();
	    	int diffY = current.getY() - target.getY();
	    	return  Math.sqrt(diffX * diffX + diffY * diffY);		
	    }
	
	
	
	
}
