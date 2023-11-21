import java.awt.event.KeyEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class Game extends GraphicsProgram{
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 600;
	private static final double SCENE_BOTTOM_PROPORTION = 1.0 / 7;
	private static final int FRAME_UPDATE_INTERVAL = 50;
	
	public static final double SCENE_BOTTOM = SCREEN_HEIGHT - SCREEN_HEIGHT * SCENE_BOTTOM_PROPORTION;
	public static final double SCENE_HORIZONTAL_BORDER = SCREEN_WIDTH;
	
	private static Game instance;
	
	private GImage background;
	private Plane plane;
	private StaticEnemy staticEnemy;
	private DynamicEnemy dynamicEnemy;
	private Bomb bomb;
	
	public static GObject getObjectAt(double x, double y){
		return instance.getElementAt(x, y);
	}
	
	public void init(){
		instance = this;
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		createBackground();
		createPlane();
		staticEnemy = new StaticEnemy(SCREEN_WIDTH / 2, SCENE_BOTTOM - 100, 100, 100);
		add(staticEnemy);
		dynamicEnemy = new DynamicEnemy(100, SCENE_BOTTOM - 50, 100, 50, 10, Direction.LEFT);
		add(dynamicEnemy);
		addKeyListeners();
	}
	
	public void run(){
		while(plane.isActing()){
			plane.act();
			if(dynamicEnemy.isActing()){
				dynamicEnemy.act();
			}
			if(bomb != null){
				if(bomb.isActing()){
					bomb.act();
				}
				else{
					remove(bomb);
				}
			}
			if(plane.getCollider().checkCollision(staticEnemy.getCollider())){
				plane.onCollision();
			}
			if(plane.getCollider().checkCollision(dynamicEnemy.getCollider())){
				plane.onCollision();
			}
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
