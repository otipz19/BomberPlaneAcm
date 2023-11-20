import acm.graphics.*;


public class StaticEnemy extends Enemy{
	private GImage sprite;
	private Collider collider;
	
	public StaticEnemy(double x, double y, double width, double height){
		setLocation(x, y);
		sprite = new GImage(Images.BUNKER);
		sprite.setSize(width, height);
		add(sprite);
		collider = new Collider(0, sprite.getHeight() * 2.0 / 5, sprite.getWidth(), sprite.getHeight() / 2, this);
		add(collider);
	}
	
	public Collider getCollider(){
		return collider;
	}
}
