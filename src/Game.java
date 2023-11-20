import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class Game extends GraphicsProgram{
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 600;
	private static final double SCENE_BOTTOM_PROPORTION = 2.0 / 3;
	private static final int FRAME_UPDATE_INTERVAL = 50;
	
	private static Game instance;
	
	private GImage background;
	private Plane plane;
	private StaticEnemy staticEnemy;
	
	public static GObject getObjectAt(double x, double y){
		return instance.getElementAt(x, y);
	}
	
	public void init(){
		instance = this;
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		background = new GImage(Images.BACKGROUND);
		background.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		add(background);
		plane = new Plane(50, 50, 100, 100, Direction.RIGHT, 20, 30, SCREEN_WIDTH, SCREEN_HEIGHT);
		add(plane);
		staticEnemy = new StaticEnemy(600, SCREEN_HEIGHT * SCENE_BOTTOM_PROPORTION, 100, 100);
		add(staticEnemy);
	}
	
	public void run(){
		while(plane.isActing()){
			plane.act();
			if(plane.getCollider().checkCollision(staticEnemy.getCollider())){
				plane.onCollision();
			}
			pause(FRAME_UPDATE_INTERVAL);
		}
	}
}
