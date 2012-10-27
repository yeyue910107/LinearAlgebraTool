/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

/**
 *
 * @author 叶玥
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class MessagePanel extends JPanel{
    private int x = 40;
    private int y = 40;
    private int interval = 10;
    private boolean centered;
    private String message;
    
    public MessagePanel(){
        
    }
    
    public MessagePanel(String message){
        this.message = message;
    }
    
    public void setMessage(String string){
        this.message = string;
        repaint();
    }
    
    public String getMessage(){
        return this.message;
    }
    
    public int getX(){
        return this.x;
    }
    
    public void setX(int x){
        this.x = x;
        repaint();
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setY(int y){
        this.y = y;
        repaint();
    }
    
    public int getInterval(){
        return this.interval;
    }
    
    public void setInterval(int interval){
        this.interval = interval;
        repaint();
    }
    
    public boolean isCentered(){
        return centered;
    }
    
    public void setCentered(boolean centered){
        this.centered = centered;
        repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(centered){
            FontMetrics fm = g.getFontMetrics();
            
            int stringWidth = fm.stringWidth(message);
            int stringAscent = fm.getAscent();
            
            x = this.getWidth() / 2 - stringWidth / 2;
            y = this.getHeight() / 2 + stringAscent / 2;
        }
        else{
            x = 40;
            y = 40;
        }
        g.drawString(this.getMessage(), x, y);
    }
    
    public void moveLeft(){
        this.x = x - interval;
        repaint();
    }
    
    public void moveRight(){
        this.x = x + interval;
        repaint();
    }
    
    public void moveUp(){
        this.y = y - interval;
        repaint();
    }
    
    public void moveDown(){
        this.y = y + interval;
        repaint();
    }
}

