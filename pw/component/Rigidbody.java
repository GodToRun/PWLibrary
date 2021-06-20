package pw.component;

import pw.common.*;
import javax.swing.*;
import java.awt.*;

public class Rigidbody extends GameComponent {
	public static int gravity = 7;
	@Override
	public void onComponentActivited(GameObject obj) {
		boolean coll = false;
		for (GameObject go : Camera.Objects) {
			if (go != obj && obj.rect.intersects(go.rect)) {
				coll = true;
			}
		}
		if (!coll)
			obj.Move(0,gravity);
			
		
	}

}
