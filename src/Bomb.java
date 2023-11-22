public class Bomb extends Actor{
	private double speed;
	
	public Bomb(double x, double y, double width, double height, double speed){
		super(width, height);
		this.setLocation(x, y);
		changeSprite();
		this.speed = speed;
		collider = new Collider(0, 1.0 / 6 * height, width, 2.0 / 3 * height, this);
		add(collider);
	}
	
	protected void move(){
		move(0, speed);
		handleLandCollision();
	}
	
	private void handleLandCollision(){
		if(getY() + sprite.getHeight() > Game.SCENE_BOTTOM){
			startDying();
		}
	}

	protected String getImageName() {
		return Images.BOMB;
	}
	
	protected void playDeathSound(){
		AudioManager.playExplosion();
	}
}
