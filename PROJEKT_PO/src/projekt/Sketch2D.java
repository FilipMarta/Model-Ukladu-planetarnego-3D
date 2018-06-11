package projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import NewAnimation.*;

public class Sketch2D extends JPanel{
	ArrayList<Planet> list;
	float maxdistance;
	float A;
	
	public Sketch2D(ArrayList<Planet> list) {
		this.list = list;
		
	}
	
	public void setList(ArrayList<Planet> list) {
		this.list = list;
	}
	
	public void paintComponent(Graphics g) {

		
		Graphics2D g2d = (Graphics2D) g;
		
		
		
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2d.setColor(Color.WHITE);
			
			g2d.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
			g2d.drawString("x", this.getWidth()-10, this.getHeight()/2-10);
			g2d.drawString(">", this.getWidth()-8, this.getHeight()/2+5);
			
			g2d.drawLine(this.getWidth()/2+2, 0, this.getWidth()/2+2, this.getHeight());
			g2d.drawString("z", this.getWidth()/2-12, this.getHeight()-10);
			g2d.drawString("V", this.getWidth()/2-1, this.getHeight());
			
			for(int i=0; i<20;i++) {
				g2d.drawString("_", this.getWidth()/2-1, (this.getHeight()-2)*i/20);
				g2d.drawString("|", (this.getWidth()+2)*i/20, this.getHeight()/2+4);
			}
			
			if(list.size()>0) {
				maxdistance = Math.abs(list.get(0).position.x);
				if(Math.abs(list.get(0).position.z)>maxdistance) {
					maxdistance = Math.abs(list.get(0).position.z);
				}
				for(int i=0; i<list.size();i++) {
					if(Math.abs(list.get(i).position.x)>maxdistance) {
						maxdistance = Math.abs(list.get(i).position.x);
					}
					else if(Math.abs(list.get(i).position.z)>maxdistance) {
						maxdistance = Math.abs(list.get(i).position.z);
					}
				}
				if(this.getHeight()>this.getWidth()) {
					A = (float) (0.9*this.getWidth()/2)/maxdistance;
				}
				else {
					A = (float) (0.9*this.getHeight()/2)/maxdistance;
				}
				for(int i=0;i<list.size();i++) {
					g2d.drawString(list.get(i).name, this.getWidth()/2+(int)(list.get(i).position.x*A)-15, this.getHeight()/2+(int)(list.get(i).position.z*A)-5);
					g2d.fillOval(this.getWidth()/2+(int)(list.get(i).position.x*A), this.getHeight()/2+(int)(list.get(i).position.z*A), 5, 5);

				}
				DecimalFormat df=new DecimalFormat("0.00");
				String xdig = df.format(this.getWidth()/A/20);
				String zdig = df.format(this.getHeight()/A/20);
				g2d.drawString(xdig, this.getWidth()-40,this.getHeight()/2+20);
				g2d.drawString(zdig, this.getWidth()/2+10,this.getHeight()-22);

			}
			
			
			
		}
	
	
}
