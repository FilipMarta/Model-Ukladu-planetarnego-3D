package projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import NewAnimation.*;

public class Sketch2D extends JPanel{
	ArrayList<Planet> list;
	BufferedImage img;
	float maxdistance;
	float A;
	
	public Sketch2D(ArrayList<Planet> list) {
		this.list = list;
		
	}
	
	public void paintComponent(Graphics g) {

		
		Graphics2D g2d = (Graphics2D) g;
		
		
		
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2d.setColor(Color.WHITE);
			g2d.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
			g2d.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
			
			if(list.size()>0) {
				maxdistance = list.get(0).position.x;
				if(list.get(0).position.z>maxdistance) {
					maxdistance = list.get(0).position.z;
				}
				for(int i=0; i<list.size();i++) {
					if(list.get(i).position.x>maxdistance) {
					maxdistance = list.get(i).position.x;
					}
					if(list.get(i).position.z>maxdistance) {
					maxdistance = list.get(i).position.z;
					}
				}
				if(this.getHeight()>this.getWidth()) {
					A = (float) (0.9*this.getWidth()/2)/maxdistance;
				}
				else {
					A = (float) (0.9*this.getHeight()/2)/maxdistance;
				}
				for(int i=0;i<list.size();i++) {
					g2d.drawString(list.get(i).name, this.getWidth()/2+(int)(list.get(i).position.x*A)+10, this.getHeight()/2+(int)(list.get(i).position.z*A)-10);
					g2d.fillOval(this.getWidth()/2+(int)(list.get(i).position.x*A), this.getHeight()/2+(int)(list.get(i).position.z*A), 10, 10);

				}
				
			}
			//g2d.fillOval(100, 100, 10, 10);
			
		}
	
	
}
