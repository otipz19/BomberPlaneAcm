import acm.graphics.*;


public class StaticEnemy extends Enemy{
	public StaticEnemy(double x, double y, double width, double height){
		super(width, height);
		setLocation(x, y);
		sprite = new GImage(Images.BUNKER);
		sprite.setSize(width, height);
		add(sprite);
		collider = new Collider(0, sprite.getHeight() * 2.0 / 5, sprite.getWidth(), sprite.getHeight() / 2, this);
		add(collider);
	}

	@Override
	protected void move() {
		
	}

	@Override
	protected String getImageName() {
		return Images.BUNKER;
	}
}
