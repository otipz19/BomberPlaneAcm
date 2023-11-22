import java.awt.event.*;

import acm.graphics.*;
import acm.program.*;

public class Game extends GraphicsProgram{
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 600;
	private static final double SCENE_BOTTOM_PROPORTION = 1.0 / 7;
	private static final int FRAME_UPDATE_INTERVAL = 50;
	
	public static final double SCENE_BOTTOM = SCREEN_HEIGHT - SCREEN_HEIGHT * SCENE_BOTTOM_PROPORTION;
	public static final double SCENE_HORIZONTAL_BORDER = SCREEN_WIDTH;
	
	private static final double PLANE_SPEED = 20;
	private static final double PLANE_ANGLE = 5;
	private static final double PLANE_START_X = 50;
	private static final double PLANE_START_Y = 50;
	private static final double PLANE_SIZE = 100;
	
	private GImage background;
	private Plane plane;
	private Bomb bomb;
	private EnemyManager enemyManager;
	private DialogWindow dialogWindow;
	
	private boolean isGameRunning;
	private boolean isClosed;
	
	public void init(){
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		addKeyListeners();
		addMouseListeners();
		new AudioManager();
		createBackground();
		createDialogWindow();
	}
	
	public void run(){
		while(!isClosed){
			runRound();
		}
	}
	
	public void mouseClicked(MouseEvent event){
		GObject object = getElementAt(event.getX(), event.getY());
		if(object == dialogWindow){
			switch(dialogWindow.getDialogOption(event.getX(), event.getY())){
				case PLAY:
					if(!isClosed)
						startRound();
					break;
				case CLOSE:
					isClosed = true;
					break;
				default:
			}
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
	
	private void startRound(){
		removeAll();
		dialogWindow = null;
		createBackground();
		createPlane();
		enemyManager = new EnemyManager(this);
		AudioManager.playBackground();
		isGameRunning = true;
	}
	
	private void runRound(){
		while(isGameRunning && plane.isActing() && enemyManager.anyEnemiesPresent()){
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
			if(!enemyManager.anyEnemiesPresent() || !plane.isActing()){
				createDialogWindow();
			}
		}
	}

	private void createDialogWindow() {
		double width = SCREEN_WIDTH / 4;
		double height = SCREEN_HEIGHT / 4;
		String label;
		if(enemyManager != null && !enemyManager.anyEnemiesPresent()){
			label = "You won!";
		}
		else if(plane != null && !plane.isActing()){
			label = "You lost...";
		}
		else{
			label = "Bomber Plane";
		}
		dialogWindow = new DialogWindow((SCREEN_WIDTH - width) / 2, (SCREEN_HEIGHT - height) / 2, width, height, label);
		add(dialogWindow);
	}
	
	private void createPlane() {
		plane = new Plane(PLANE_START_X, PLANE_START_Y, PLANE_SIZE, PLANE_SIZE, Direction.RIGHT, PLANE_SPEED, PLANE_ANGLE);
		add(plane);
	}

	private void createBackground() {
		background = new GImage(Images.BACKGROUND);
		background.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		add(background);
	}
}
