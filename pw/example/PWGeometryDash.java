package pw.example;
import java.awt.*;
//PWLibrary Example
import java.awt.event.KeyEvent;

import javax.swing.*;
import pw.common.*;
import pw.component.*;
import pw.kit.*;
import pw.common.*;
public class PWGeometryDash extends GameManager {
	GameObject player;
	GameObject ground;
	int SCORE = 0;
	GameObject cube,cube2,cube3,spike1,spike2,spike3;
	public PWGeometryDash() {
		super("Geometry Dash", null);
		player = new GameObject(500,700,50,50,ObjectType.Cube,getPanel(),Color.GREEN);
		ground = new GameObject(0,800,2000,400,ObjectType.Cube,getPanel(),Color.BLACK);
		cube = new GameObject(1000,750,50,50,ObjectType.Cube,getPanel(),Color.GRAY);
		cube.ShowWhenPaint = true;
		cube2 = new GameObject(1200,700,50,100,ObjectType.Cube,getPanel(),Color.GRAY);
		cube2.ShowWhenPaint = true;
		cube3 = new GameObject(1400,650,50,150,ObjectType.Cube,getPanel(),Color.GRAY);
		cube3.ShowWhenPaint = true;
		spike1 = new GameObject(940,770,50,50,ObjectType.Cube,getPanel(),Color.RED);
		spike1.ShowWhenPaint = true;
		spike1.tag = "spike";
		spike2 = new GameObject(1550,760,50,50,ObjectType.Cube,getPanel(),Color.RED);
		spike2.ShowWhenPaint = true;
		spike2.tag = "spike";
		spike3 = new GameObject(1800,760,50,50,ObjectType.Cube,getPanel(),Color.RED);
		spike3.ShowWhenPaint = true;
		spike3.tag = "spike";
		ground.moveable = false;
		player.X_FIXING = true;
		player.addComponent(new Rigidbody());
	}
	static class JUMP extends Thread {
		GameObject[] objs;
		public boolean ended = false;
		private GameObject CHAR;
		public JUMP(GameObject character) {
			this.CHAR = character;
			Rigidbody.gravity = 0;
		}
		@Override
		public void run() {
			int COOL = 0;
			int JUMP_Y = -2;
			
			while(true) {
				if (COOL < 11) {
					this.CHAR.Move(0, (int)(JUMP_Y * 1.4f));
					try {
						COOL++;
						JUMP_Y--;
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (COOL > 10 && COOL < 27) {
					JUMP_Y++;
					this.CHAR.Move(0, JUMP_Y);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					COOL++;
					JUMP_Y++;
				}/*
				else if (COOL > 36) {
					this.CHAR.Move(0, 1);*/
				else {
					ended = true;
					Rigidbody.gravity = 7;
					break;
				}	
				}	
			}
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PWGeometryDash();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == ' ' && Rigidbody.gravity != 0) { //Jump
			JUMP jump = new JUMP(player);
			jump.start();
		}
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
		g.drawString(SCORE + "", 5, 20);
		try {
			player.Show(g);
			ground.Show(g);
		}
		catch (Exception e ) {} 
	}

	@Override
	protected void MainLoop() {
		SCORE++;
		for (GameObject gobj : Camera.Objects)
			if (gobj.tag.equals("spike") && player.rect.intersects(gobj.rect)) {
				JOptionPane.showConfirmDialog(null, "You Died!", "Geometry Dash", 0);
				Application.Exit();
			}
		if (SCORE > 300) {
			JOptionPane.showConfirmDialog(null, "You Cleared!", "Congratulations!", 0);
			Application.Exit();
		}
		Camera.Move(-5, 0);
	}

}
