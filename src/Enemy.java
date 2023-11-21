import acm.graphics.*;

public abstract class Enemy extends GCompound{
	protected GImage sprite;
	protected Collider collider;
	private ExplosionAnimator explosionAnimator;
	
	protected Enemy(double width, double height){
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
		if(isAlive()){
			move();
		}
		else{
			explosionAnimator.animate();
		}
	}
	
	public void onCollision(){
		if(isAlive() && !isDying()){
			startDying();
		}
	}
	
	protected abstract void move();
	
	private boolean isAlive(){
		return sprite != null;
	}
	
	private boolean isDying(){
		return explosionAnimator.isRunning();
	}
	
	private void startDying(){
		remove(sprite);
		sprite = null;
		explosionAnimator.startAnimation();
	}
}
