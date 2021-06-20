/*
Example of PW Engine.
this source doesn't have license.
 */
package pw.example;
import java.awt.*;
import java.awt.event.KeyEvent;
import pw.component.*;
import java.io.*;

import javax.imageio.ImageIO;
import java.net.URL;
import javax.swing.*;
import pw.common.*;
import pw.kit.*;
public class PlayerMovement extends GameManager {
	GameObject player;
	GameObject img;
	ImageIO imgio;
	public PlayerMovement() {
		super("PlayerMovement Example",null); //null = FULLSCREEN
		player = new GameObject(500,500,50,50,ObjectType.Cube,getPanel(),Color.RED);
		img = new GameObject(500,400,100,100,ObjectType.Image,getPanel());
		try {
		File file = new File("C:/users/wjmh9/Desktop/icon.png");
		img.setImage(ImageIO.read(file));
		}
		catch (Exception e) { e.printStackTrace(); }
	}
	public static void main(String[] args) {
		new PlayerMovement();
	}
	@Override
	protected void DrawScreen(Graphics g) {
		try {
			img.Show(g);
			player.Show(g);
		} catch (NullPointerException nullpointer) { } catch (Exception e) { e.printStackTrace(); }
	}
	@Override
	protected void MainLoop() {
		if (player.rect.intersects(img.rect)) {
			JOptionPane.showConfirmDialog(null, "You Died!");
			Application.Exit();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'a') {
			player.Move(-10, 0);
		}
		else if (e.getKeyChar() == 'd') {
			player.Move(10, 0);
		}
		else if (e.getKeyChar() == 's') {
			player.Move(0, 10);
		}
		else if (e.getKeyChar() == 'w') {
			player.Move(0, -10);
		}
	}
	@Override                      
	public void keyReleased(KeyEvent e) {
		
	}

}
