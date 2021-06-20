package pw.example;
import pw.common.*;
import pw.component.*;
import pw.kit.*;
import pw.ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
public class UISet extends GameManager {
	static PWButton uib = null;
	public UISet() {
		super("UI Example",new Point(750,450));
	}
	public static void main(String[] args) {
		uib = new PWButton("Ya");
		UISet set = new UISet();
		set.add(uib);
		set.setLayout( new FlowLayout( ) );
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void DrawScreen(Graphics g) {
		
	}
	@Override
	protected void MainLoop() {
		// TODO Auto-generated method stub
		
	}

}
