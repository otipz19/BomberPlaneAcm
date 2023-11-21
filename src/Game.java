import java.awt.event.KeyEvent;

import acm.graphics.*;
import acm.program.*;

public class Game extends GraphicsProgram{
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 600;
	private static final double SCENE_BOTTOM_PROPORTION = 1.0 / 7;
	private static final int FRAME_UPDATE_INTERVAL = 50;
	
	public static final double SCENE_BOTTOM = SCREEN_HEIGHT - SCREEN_HEIGHT * SCENE_BOTTOM_PROPORTION;
	public static final double SCENE_HORIZONTAL_BORDER = SCREEN_WIDTH;
	
	private GImage background;
	private Plane plane;
	private Bomb bomb;
	private EnemyManager enemyManager;
	private AudioManager audioManager;
	
	public void init(){
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		createBackground();
		createPlane();
		enemyManager = new EnemyManager(this);
		addKeyListeners();
		audioManager = new AudioManager();
		audioManager.playBackground();
	}
	
	public void run(){
		while(plane.isActing()){
			plane.act();
			if(bomb != null){
				if(bomb.isActing()){
					bomb.act();
					enemyManager.checkBombCollisions(bomb);
				}
				else{
					remove(bomb);
				}
			}
			enemyManager.checkPlaneCollisions(plane);
			enemyManager.act();
			pause(FRAME_UPDATE_INTERVAL);
		}
	}
	
	public void keyPressed(KeyEvent event){
		if(event.getKeyCode() == KeyEvent.VK_SPACE){
			if(plane.isActing() && (bomb == null || !bomb.isActing())){
				bomb = plane.dropBomb();
				add(bomb);
			}
		}
	}
	
	private void createPlane() {
		plane = new Plane(50, 50, 100, 100, Direction.RIGHT, 20, 5);
		add(plane);
	}

	private void createBackground() {
		background = new GImage(Images.BACKGROUND);
		background.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		add(background);
	}
}
