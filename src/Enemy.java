import acm.graphics.*;

public abstract class Enemy extends GCompound{
	protected GImage sprite;
	protected Collider collider;
	
	public Collider getCollider(){
		return collider;
	}
}
