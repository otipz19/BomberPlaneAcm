import java.awt.Color;

import acm.graphics.*;

public class Collider extends GRect{
	private GObject parent;
	
	public Collider(double x, double y, double width, double height, GObject parent) {
		super(x, y, width, height);
		setState(parent);
	}

	public Collider(double width, double height, GObject parent) {
		super(width, height);
		setState(parent);
	}
	
	public boolean checkCollision(Collider other){
		GPoint thisPivot = getGlobalCoordinates();
		GPoint otherPivot = other.getGlobalCoordinates();
		for(double x = thisPivot.getX(); x <= thisPivot.getX() + getWidth(); x += getWidth()){
			for(double y = thisPivot.getY(); y <= thisPivot.getY() + getHeight() ; y += getHeight()){
				if(isCornerInRect(new GPoint(x, y), other, otherPivot)){
					return true;
				}
			}
		}
		return false;
	}
	
	private GPoint getGlobalCoordinates(){
		return new GPoint(parent.getX() + getX(), parent.getY() + getY());
	}
	
	private void setState(GObject parent) {
		this.parent = parent;
		setColor(Color.RED);
		setVisible(true);
	}
	
	private boolean isCornerInRect(GPoint corner, GRect rect, GPoint rectPivot){
		double x = corner.getX(); 
		double y = corner.getY();
		return x > rectPivot.getX() && x < rectPivot.getX() + rect.getWidth() && y > rectPivot.getY() && y < rectPivot.getY() + rect.getWidth();
	}
}
