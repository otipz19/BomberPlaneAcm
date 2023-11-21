import acm.graphics.*;

public class Bomb extends GCompound{
	private GImage sprite;
	private Collider collider;
	private ExplosionAnimator explosionAnimator;
	private double speed;
	
	public Bomb(double x, double y, double width, double height, double speed){
		this.setLocation(x, y);
		sprite = new GImage(Images.BOMB);
		sprite.setSize(width, height);
		add(sprite);
		this.speed = speed;
		collider = new Collider(0, 0, width, height, this);
		add(collider);
		explosionAnimator = new ExplosionAnimator(width, height);
		add(explosionAnimator);
	}
	
	public Collider getCollider(){
		return collider;
	}
	
	public boolean isActing(){
		return isAlive() || isDying();
	}
	
	public void act(){
		if(isActing()){
			if(isAlive()){
				move();
			}
			else{
				explosionAnimator.animate();
			}
		}
	}
	
	public void onCollision(){
		if(isAlive() && !isDying()){
			remove(sprite);
			sprite = null;
		}
	}
	
	private void move(){
		move(0, speed);
		handleLandCollision();
	}
	
	private void handleLandCollision(){
		if(getY() + sprite.getHeight() > Game.SCENE_BOTTOM){
			startDying();
		}
	}
	
	private void startDying(){
		remove(sprite);
		sprite = null;
		explosionAnimator.startAnimation();
	}
	
	private boolean isAlive(){
		return sprite != null;
	}
	
	private boolean isDying(){
		return explosionAnimator.isRunning();
	}
}
