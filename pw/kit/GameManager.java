package pw.kit;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.event.*;
import pw.common.Application;
import pw.common.GameObject;

abstract public class GameManager extends JFrame implements KeyListener {
	private int msdelay = 20;
	Refresh re;
	GamePanel panel;
    protected GameManager(String title,Point size){
        this.setTitle(title);
        File f = new File(System.getProperty("user.dir") + "\\src\\pw\\images\\icon.png");
        Image img = null;
		try {
			img = ImageIO.read(f);
			this.setIconImage(img);
		} 
        catch (Exception e) {
        	
		}
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GamePanel();
        this.add(panel, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        if (size != null)
        	this.setSize(size.x,size.y);
        else {
        	this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        	this.setUndecorated(true);
        }
        this.setVisible(true);
        this.addKeyListener(this);
        GameObject.SetAllObjectsDefaultSetting();
        re = new Refresh(panel); 
        re.start();
    }
    public class GamePanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            GameObject.ObjectsShow(g);
            DrawScreen(g);
        }
    }
    protected GamePanel getPanel() {
    	return this.panel;
    }
    protected void AdvencedSetting(int msdelay) {
    	this.msdelay = msdelay;
    }
    protected abstract void DrawScreen(Graphics g);
    protected abstract void MainLoop();
    class Refresh extends Thread {
    	GamePanel g;
    	public Refresh(GamePanel g) {
    		this.g = g;
    	}
    	@Override
    	public void run() { 
    		while(true) {
    			g.repaint();
    			MainLoop();
        		try {
    				this.sleep(msdelay);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}
    }
}