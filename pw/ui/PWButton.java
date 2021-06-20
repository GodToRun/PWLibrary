package pw.ui;
import java.awt.*;

import javax.swing.*;
//버튼 디자인
public class PWButton extends JButton {
   public PWButton() { super(); decorate(); } 
   public PWButton(String text) { super(text); decorate(); } 
   public PWButton(Action action) { super(action); decorate(); } 
   public PWButton(Icon icon) { super(icon); decorate(); } 
   public PWButton(String text, Icon icon) { super(text, icon); decorate(); } 
   protected void decorate() { setBorderPainted(false); setOpaque(false); }
   @Override 
   protected void paintComponent(Graphics g) {
      Color c=new Color(255,247,242); //배경색 결정
      Color o=Color.black; //글자색 결정
      int width = getWidth(); 
      int height = getHeight(); 
      Graphics2D graphics = (Graphics2D) g; 
      graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
      if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
      else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
      else { graphics.setColor(c); } 
      graphics.fillRoundRect(0, 0, width, height, 10, 10); 
      FontMetrics fontMetrics = graphics.getFontMetrics(); 
      Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
      int textX = (width - stringBounds.width) / 2; 
      int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
      graphics.setColor(o); 
      graphics.setFont(getFont()); 
      graphics.drawString(getText(), textX, textY); 
      graphics.dispose(); 
      super.paintComponent(g); 
      }
   }
