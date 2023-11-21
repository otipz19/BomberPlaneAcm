import acm.graphics.GImage;

public class DynamicEnemy extends Enemy{
	private Direction direction;
	private double speed;
	
	public DynamicEnemy(double x, double y, double width, double height, double speed, Direction direction){
		super(width, height);
		setLocation(x, y);
		this.speed = speed;
		this.direction = direction;
		changeSprite();
		collider = new Collider(sprite.getWidth() / 4, 0, sprite.getWidth() / 2, sprite.getHeight(), this);
		add(collider);
	}
	
	protected String getImageName(){
		return direction == Direction.LEFT ? Images.TANK_LEFT : Images.TANK_RIGHT;
	}
	
	protected void move(){
		if(direction == Direction.RIGHT){
			move(speed, 0);
		}
		else{
			move(-speed, 0);
		}
		handleWallCollision();
	}
	
	private void handleWallCollision(){	
		if(getX() + sprite.getWidth() > Game.SCENE_HORIZONTAL_BORDER){
			direction = Direction.LEFT;
			changeSprite();
		}
		else if(getX() < 0){
			direction = Direction.RIGHT;
			changeSprite();
		}
	}
}
