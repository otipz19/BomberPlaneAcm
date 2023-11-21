import acm.graphics.GCompound;
import acm.graphics.GImage;

public abstract class Actor extends GCompound{
	protected GImage sprite;
	protected Collider collider;
	private ExplosionAnimator explosionAnimator;
	private double width;
	private double height;
	
	protected Actor(double width, double height){
		this.width = width;
		this.height = height;
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
	
	protected abstract String getImageName();
	
	protected abstract void playDeathSound();
	
	protected boolean isAlive(){
		return sprite != null;
	}
	
	protected boolean isDying(){
		return explosionAnimator.isRunning();
	}
	
	protected void startDying(){
		remove(sprite);
		sprite = null;
		explosionAnimator.startAnimation();
		playDeathSound();
	}
	
	protected void changeSprite(){
		if(sprite != null){
			remove(sprite);
		}
		sprite = new GImage(getImageName());
		sprite.setSize(width, height);
		add(sprite);
	}
}
