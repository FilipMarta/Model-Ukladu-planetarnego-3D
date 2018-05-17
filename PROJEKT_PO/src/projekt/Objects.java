package projekt;

import javax.swing.JLabel;

public class Objects {

	String name;
	double mass;
	double radius;
	double xlocation;
	double ylocation;
	double zlocation;
	double xvelocity;
	double yvelocity;
	double zvelocity;
	boolean starornot;
	
	public Objects(String name, double mass, double radius, double xlocation, double ylocation, double zlocation, double xvelocity, double yvelocity, double zvelocity, boolean starornot) 
	{
		
		
		this.name = name;
		this.mass = mass;
		this.radius = radius;
		this.xlocation = xlocation;
		this.ylocation = ylocation;
		this.zlocation = zlocation;
		this.xvelocity = xvelocity;
		this.yvelocity = yvelocity;
		this.zvelocity = zvelocity;

		this.starornot = starornot;
		
		
	}
	
	
	
}
