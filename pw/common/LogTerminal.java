package pw.common;
import pw.kit.*;

import java.awt.*;
import java.awt.event.KeyEvent;

import pw.common.*;
public class LogTerminal extends GameManager {
	static LogTerminal log = null;
	String text = "";
	private LogTerminal() {
		super("Log", new Point(750,450));
		getPanel().setBackground(Color.BLACK);
	}
	public static void Run() {
		log = new LogTerminal();
	}
	public static void Log(String strtext) {
		log.text += strtext + "\n";
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
		g.setColor(Color.WHITE);
		g.drawString(text, 5, 20);
	}

	@Override
	protected void MainLoop() {
		
	}
}
