public class Plane extends Actor{	
	private Direction direction;
	private double speed;
	private double angle;
	
	public Plane(double x, double y, double width, double height, Direction direction, double speed, double angle){
		super(width, height);
		this.direction = direction;
		this.speed = speed;
		this.angle = angle;
		changeSprite();
		this.setLocation(x, y);
		collider = new Collider(0, sprite.getHeight() * 1.5 / 5, sprite.getWidth(), sprite.getHeight() * 2.0 / 5, this);
		add(collider);
	}
	
	public Bomb dropBomb(){
		double width = getWidth() / 3;
		double height = getHeight() / 2;
		return new Bomb(getX(), getY(), width, height, 35);
	}
	
	protected String getImageName(){
		return direction == Direction.LEFT ? Images.PLANE_LEFT : Images.PLANE_RIGHT;
	}
	
	protected void move(){
		if(direction == Direction.RIGHT){
			movePolar(speed, -angle);
		}
		else{
			movePolar(-speed, angle);
		}
		handleLandCollision();
		if(isAlive()){
			handleWallCollision();
		}
	}
	
	protected void playDeathSound(){
		AudioManager.playPlaneCrash();
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
	
	private void handleLandCollision(){
		if(getY() + sprite.getHeight() > Game.SCENE_BOTTOM){
			startDying();
		}
	}
}
