import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class Game extends GraphicsProgram{
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 600;
	private static final double SCENE_BOTTOM_PROPORTION = 1.0 / 7;
	private static final double SCENE_BOTTOM = SCREEN_HEIGHT - SCREEN_HEIGHT * SCENE_BOTTOM_PROPORTION;
	private static final int FRAME_UPDATE_INTERVAL = 50;
	
	private static Game instance;
	
	private GImage background;
	private Plane plane;
	private StaticEnemy staticEnemy;
	private DynamicEnemy dynamicEnemy;
	
	public static GObject getObjectAt(double x, double y){
		return instance.getElementAt(x, y);
	}
	
	public void init(){
		instance = this;
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		background = new GImage(Images.BACKGROUND);
		background.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		add(background);
		plane = new Plane(50, 50, 100, 100, Direction.RIGHT, 20, 5, SCREEN_WIDTH, SCENE_BOTTOM + 50);
		add(plane);
		staticEnemy = new StaticEnemy(SCREEN_WIDTH / 2, SCENE_BOTTOM - 100, 100, 100);
		add(staticEnemy);
		dynamicEnemy = new DynamicEnemy(100, SCENE_BOTTOM - 50, 100, 50, 10, SCREEN_WIDTH, Direction.LEFT);
		add(dynamicEnemy);
	}
	
	public void run(){
		while(plane.isActing()){
			plane.act();
			dynamicEnemy.act();
			if(plane.getCollider().checkCollision(staticEnemy.getCollider())){
				plane.onCollision();
			}
			if(plane.getCollider().checkCollision(dynamicEnemy.getCollider())){
				plane.onCollision();
			}
			pause(FRAME_UPDATE_INTERVAL);
		}
	}
}
