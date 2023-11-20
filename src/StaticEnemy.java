import acm.graphics.*;


public class StaticEnemy extends Enemy{
	private GImage sprite;
	
	public StaticEnemy(double x, double y, double width, double height){
		setLocation(x, y);
		sprite = new GImage(Images.BUNKER);
		sprite.setSize(width, height);
		add(sprite);
	}
}
