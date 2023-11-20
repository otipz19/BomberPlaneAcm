import acm.graphics.GImage;

public class DynamicEnemy extends Enemy{
	private Direction direction;
	private double speed;
	private double width;
	private double height;
	private double horizontalBorder;
	private ExplosionAnimator explosionAnimator;
	
	public DynamicEnemy(double x, double y, double width, double height, double speed, double horizontalBorder, Direction direction){
		setLocation(x, y);
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.horizontalBorder = horizontalBorder;
		this.direction = direction;
		changeSprite();
		collider = new Collider(sprite.getWidth() / 4, 0, sprite.getWidth() / 2, sprite.getHeight(), this);
		add(collider);
	}
	
	public boolean isActing(){
		return isAlive() || isDying();
	}
	
	public void act(){
		if(isAlive()){
			move();
		}
		else{
			explosionAnimator.animate();
		}
	}
	
	public Collider getCollider(){
		return collider;
	}
	
	public void onCollision(){
		if(isAlive() && !isDying()){
			startDying();
		}
	}
	
	private void move(){
		if(direction == Direction.RIGHT){
			move(speed, 0);
		}
		else{
			move(-speed, 0);
		}
		handleWallCollision();
	}
	
	private boolean isAlive(){
		return sprite != null;
	}
	
	private boolean isDying(){
		return explosionAnimator.isRunning();
	}
	
	private void handleWallCollision(){	
		if(getX() + sprite.getWidth() > horizontalBorder){
			direction = Direction.LEFT;
			changeSprite();
		}
		else if(getX() < 0){
			direction = Direction.RIGHT;
			changeSprite();
		}
	}
	
	private void startDying(){
		remove(sprite);
		sprite = null;
		explosionAnimator.startAnimation();
	}
	
	private void changeSprite(){
		if(sprite != null){
			remove(sprite);
		}
		sprite = new GImage(direction == Direction.LEFT ? Images.TANK_LEFT : Images.TANK_RIGHT);
		sprite.setSize(width, height);
		add(sprite);
	}
}
