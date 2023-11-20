import acm.graphics.*;


public class Plane extends GCompound{
	private GImage sprite;
	private ExplosionAnimator explosionAnimator;
	
	private Direction direction;
	private double width;
	private double height;
	private double speed;
	private double angle;
	private double horizontalBorder;
	private double verticalBorder;
	
	public Plane(double x, double y, double width, double height, Direction direction, double speed, double angle, double horizontalBorder, double verticalBorder){
		this.width = width;
		this.height = height;
		this.direction = direction;
		this.speed = speed;
		this.angle = angle;
		this.horizontalBorder = horizontalBorder;
		this.verticalBorder = verticalBorder;
		changeSprite();
		explosionAnimator = new ExplosionAnimator(width, height);
		add(explosionAnimator);
		this.setLocation(x, y);
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
	
	private void move(){
		if(direction == Direction.RIGHT){
			movePolar(speed, -angle);
		}
		else{
			movePolar(-speed, angle);
		}
		handleEnemyCollision();
		if(isAlive()){
			handleLandCollision();
		}
		if(isAlive()){
			handleWallCollision();
		}
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
	
	private void handleLandCollision(){
		if(getY() + sprite.getHeight() > verticalBorder){
			startDying();
		}
	}
	
	private void handleEnemyCollision(){
		for(double x = getX(); x <= getX() + sprite.getWidth(); x += sprite.getWidth()){
			for(double y = getY(); y <= getY() + sprite.getHeight() / 2 ; y += sprite.getHeight() / 2){
				GObject object = Game.getObjectAt(x, y);
				if(object != null && object instanceof Enemy){
					startDying();
					return;
				}
			}
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
		sprite = new GImage(direction == Direction.LEFT ? Images.PLANE_LEFT : Images.PLANE_RIGHT);
		sprite.setSize(width, height);
		add(sprite);
	}
}
