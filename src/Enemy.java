import acm.graphics.*;

public abstract class Enemy extends Actor{
	protected Enemy(double width, double height){
		super(width, height);
	}
	
	protected void playDeathSound(){
		AudioManager.playExplosion();
	}
}
