import acm.graphics.GCompound;
import acm.graphics.GImage;

public class ExplosionAnimator extends GCompound{
	private static final int FIRST_FRAME = 1;
	private static final int LAST_FRAME = 8;
	
	private int currentFrame;
	private GImage sprite;
	
	private double width;
	private double height;
	
	public ExplosionAnimator(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public boolean isRunning(){
		return currentFrame >= FIRST_FRAME && currentFrame <= LAST_FRAME + 1;
	}
	
	public void startAnimation(){
		currentFrame = 1;
	}
	
	public void animate(){
		if(isRunning()){
			if(currentFrame >= LAST_FRAME){
				remove(sprite);
				currentFrame++;
				return;
			}
			else {
				if(sprite != null){
					remove(sprite);
				}
				sprite = new GImage(Images.EXPLOSION_WITHOUT_EXTENSION + currentFrame + Images.EXPLOSION_EXTENSION);
				sprite.setSize(width, height);
				sprite.setLocation(0, 0);
				add(sprite);
				currentFrame++;
			}
		}
	}
}
