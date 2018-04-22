package NewAnimation;

import java.util.ArrayList;

import javax.media.j3d.Transform3D;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Cylinder;


public class AnimationThread extends Thread{
	
	ArrayList<Planet> list;
	Star sun;
	Cylinder rings;
	final static double G = 6.67*Math.pow(10, -11);
	double dt=86400;
	int time=0;
	
	AnimationThread(ArrayList<Planet> list, Star sun){
		this.list=list;
		this.sun=sun;
	}
	
	double alfa;
	
	public void run(){

		for(;;) {
			computePosition();
			moveObjects();
			time+=1;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	Vector3f computeAcceleration(Planet p) {
		double a = G*sun.mass/Math.pow(p.orbitRadius,2);
		p.computeRadius();
		float ax = -(float) (p.position.x/p.orbitRadius*a);
		float ay = -(float) (p.position.y/p.orbitRadius*a);
		float az = -(float) (p.position.z/p.orbitRadius*a);
		Vector3f v = new Vector3f(ax,ay,az);
		return v;
	}
	
	void computePosition() {
		
		for(int i=0;i<list.size();i++) {
			
			Planet p = list.get(i);
			Vector3f a = computeAcceleration(p);
			

			p.position.x=(float) (p.position.x+p.velocity.x*dt+a.x/2*dt*dt);				
			p.position.y=(float) (p.position.y+p.velocity.y*dt+a.y/2*dt*dt);			
			p.position.z=(float) (p.position.z+p.velocity.z*dt+a.z/2*dt*dt);
			
			Vector3f a2 = computeAcceleration(p);

			p.velocity.x=(float) (p.velocity.x+(a.x+a2.x)*dt/2);
			p.velocity.y=(float) (p.velocity.y+(a.y+a2.y)*dt/2);
			p.velocity.z=(float) (p.velocity.z+(a.z+a2.z)*dt/2);

			
		}

	}
	
	void moveObjects() {
		for(int i=0;i<list.size();i++) {
			list.get(i).move();
		}
	}
	

}
