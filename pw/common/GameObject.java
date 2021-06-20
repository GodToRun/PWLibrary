package pw.common;
import javax.imageio.ImageIO;
import javax.swing.*;
import pw.component.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
public class GameObject {
	private ArrayList<GameComponent> compolist = new ArrayList<GameComponent>();
	public int X;
	public int Y;
	public boolean moveable = true;
	int width;
	public String tag = this.toString();
	int height;
	public boolean ShowWhenPaint = false;
	public ObjectType objtype;
	private Image image;
	Graphics graphic;
	public boolean IsGround = false;
	public boolean X_FIXING = false;
	public boolean Y_FIXING = false;
	public boolean delected = false;
	private String text = "";
	private Font font = new Font(Font.SERIF,0,25);
	public Rectangle rect;
	public Color color = Color.RED;
	JPanel panel;
	public GameObject(int x,int y,int width,int height,ObjectType type, JPanel panel) {
		Camera.Objects.add(this);
		this.width = width;
		this.height = height;
		rect = new Rectangle(x,y,width,height);
		X = x;
		Y = y;
		this.panel = panel;
		objtype = type;
	}
	public void addComponent(GameComponent component) {
		this.compolist.add(component);
	}
	public ArrayList<GameComponent> getComponentList() {
		return this.compolist;
	}
	public void removeComponent(GameComponent component) {
		this.compolist.remove(component);
	}
	public boolean CollisionWith(GameObject object) {
		if (this.rect.intersects(object.rect) && this != object)
			return true;
		return false;
	}
	public static void ObjectsShow(Graphics g) {
		for (GameObject obj : Camera.Objects) {
			if (obj.ShowWhenPaint)
				obj.Show(g);
		}
	}
	public static GameObject FindObjectWithTag(String tag) {
		for (GameObject OBJ : Camera.Objects) {
			if (OBJ.tag.equals(tag))
				return OBJ;
		}
		return null;
	}
	public GameObject CheckCollision() {
		for (GameObject go : Camera.Objects)
			if (go.rect.intersects(this.rect))
				return go;
		return null;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public Font getFont() {
		return this.font;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public void setImage(String path) throws IOException {
		File fn = new File(path);
		Image tempimg;
		tempimg = ImageIO.read(fn);
		this.image = tempimg;
	}
	public Image getImage() {
		return this.image;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return this.text;
	}
	public static void Delete(GameObject o) {
		o.delected = true;
	}
	public void Delete() {
		this.delected = true;
		Camera.Objects.remove(this);
	}
	//overloading
	public GameObject(int x,int y,int width,int height,ObjectType type, JPanel panel, Color color) {
		if (type == ObjectType.Image)
			rect = new Rectangle(x,y,width,height);
		else
			rect = new Rectangle(x,y,width,height);
		Camera.Objects.add(this);
		this.width = width;
		this.height = height;
		this.color = color;
		X = x;
		Y = y;
		this.panel = panel;
		objtype = type;
	}
	public void Show(Graphics g) {
		if (!delected) {
			graphic = g;
			if (objtype == ObjectType.Cube) {
				g.setColor(color);
				//g.fillRect(X, Y, width, height);
				Graphics2D g2d = (Graphics2D)g;
				g2d.fill(rect);
			}
			else if (objtype == ObjectType.Text) {
	 			g.setColor(color);
				g.setFont(font);
				g.drawString(text,X,Y);
			}
			else if (objtype == ObjectType.Image) {
				g.setColor(color);
				rect.setBounds(X,Y,image.getWidth(panel),image.getHeight(panel));
				g.drawImage(image, X, Y, panel);
			}
		}
	}
	public void Move(int x,int y) { //움직임. (x,y)만큼
		if (moveable) {
			if (!X_FIXING)
				this.X = X + x;
			if (!Y_FIXING)
				this.Y = Y + y;
		}
	}
	public void Move(int x,int y,boolean ForceMode) { //움직임. (x,y)만큼
			this.X = X + x;
			this.Y = Y + y;
	}
	public void SetPosition(int x,int y, boolean Force) { //움직임. (x,y)만큼
		if (moveable) {
			if (!Force && !X_FIXING && x != -1)
				this.X = x;
			if (Force && x != -1)
				this.X = x;
			if (!Force && !Y_FIXING && y != -1)
				this.Y = y;
			if (Force && y != -1)
				this.Y = y;
		}
	}
	public static GameObject getObjectWithPoint(Point point, boolean X_AND_Y) {
		for (GameObject obj : Camera.Objects) {
			if (X_AND_Y) {
				if (obj.X == point.x && obj.Y == point.y)
					return obj;
			}
			else {
				if (obj.X == point.x || obj.Y == point.y)
					return obj;
			}
		}
		return null;
	}
	public static GameObject getObjectWithRadius(Point point,int radiusx,int radiusy, boolean X_AND_Y) {
		for (GameObject obj : Camera.Objects) {
			if (X_AND_Y) {
				if (obj.X > point.x - radiusx && obj.X < point.x + radiusx &&obj.Y > point.y - radiusy && obj.Y < point.y + radiusy)
					return obj;
			}
			else {
				if (obj.X == point.x || obj.Y == point.y)
					return obj;
			}
		}
		return null;
	}
	public void runAnimation(Animation ani,int fpmdelay) throws InterruptedException {
		Image defaultimg = this.getImage();
		for (Image frame : ani.getFrameList() ) {
			Thread.sleep(fpmdelay);
			this.setImage(frame);
		}
		this.setImage(defaultimg);
	}
	public static void SetAllObjectsDefaultSetting() {
		LoopObject LO = new LoopObject();
		LO.start();
	}
	public void RefreshTempMove(int X,int Y,Graphics g) {
		panel.repaint();
		if (objtype == ObjectType.Cube) {
			g.fillRect(X, Y, width, height);
		}
	}
	public void Loop() {
		
	}
	static class LoopObject extends Thread {
		@Override
		public void run() {
			try {
				while(true) {
					for (GameObject go : Camera.Objects) {
						go.Loop(); //reference
						for (GameComponent gocom : go.compolist)
							gocom.onComponentActivited(go);
						go.rect.x = go.X;
						go.rect.y = go.Y;
						go.rect.width = go.width;
						go.rect.height = go.height;
					}
					Thread.sleep(15);
				}
			}
			catch (Exception e) {}
		}
	}
}
