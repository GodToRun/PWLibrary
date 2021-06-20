package pw.common;
import java.util.*;
import java.awt.*;
public class Animation {
	private ArrayList<Image> frames = new ArrayList<Image>();
	public void addFrame(Image img) {
		frames.add(img);
	}
	public ArrayList<Image> getFrameList() {
		return this.frames;
	}
	public void removeFrame(Image img) {
		frames.remove(img);
	}
}
