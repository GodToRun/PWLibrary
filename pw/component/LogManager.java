package pw.component;

import java.awt.Point;

import pw.common.GameObject;

public class LogManager extends GameComponent { //this is a example component class.

	@Override
	public void onComponentActivited(GameObject obj) {
		System.out.println(obj.tag + " " + new Point(obj.X,obj.Y));
	}

}
