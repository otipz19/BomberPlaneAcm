import java.awt.Color;

import acm.graphics.*;

public class DialogWindow extends GCompound{
	private GRect background;
	private GImage playBtn;
	private GImage closeBtn;
	private GLabel title;
	
	public DialogWindow(double x, double y, double width, double height, String text){
		setLocation(x, y);
		background = new GRect(0, 0, width, height);
		background.setFillColor(new Color(0x4d4d4d));
		background.setFilled(true);
		add(background);
		title = new GLabel(text);
		title.setFont("comicsans-24");
		title.setColor(new Color(0xffe3bb));
		title.setLocation((width - title.getWidth()) / 2, title.getHeight() * 1.5);
		add(title);
		playBtn = new GImage(Images.PLAY_BTN);
		playBtn.setSize(width / 4, height / 4);
		playBtn.setLocation(width / 8, height - playBtn.getHeight() * 1.5);
		add(playBtn);
		closeBtn = new GImage(Images.CLOSE_BTN);
		closeBtn.setSize(width / 4, height / 4);
		closeBtn.setLocation((5.0 / 8) * width, height - closeBtn.getHeight() * 1.5);
		add(closeBtn);
	}
	
	public DialogOption getDialogOption(double x, double y){
		GObject object = getElementAt(x - getX(), y - getY());
		if(object == playBtn)
			return DialogOption.PLAY;
		else if(object == closeBtn)
			return DialogOption.CLOSE;
		else
			return DialogOption.NOTHING;
	}
}
