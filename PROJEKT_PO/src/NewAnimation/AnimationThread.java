package NewAnimation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.media.j3d.Transform3D;
import javax.swing.Timer;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Cylinder;


public class AnimationThread implements ActionListener{
	
	ArrayList<Planet> list;
	final static double G = 6.67*Math.pow(10, -11);
	double dt=86400;
	int time=0;
	
	public Timer timer= new Timer(20,this);
	
	AnimationThread(ArrayList<Planet> list){
		this.list=list;
	}
	public void start() {
		timer.start();
	}
//	
//	
//	public void run(){
//
//		for(;;) {
//			computePosition();
//			moveObjects();
//			time+=1;
//			try {
//				Thread.sleep(20);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
	
//	Vector3f computeAcceleration(Planet p1, Planet p2) {
//		double a = G*p2.mass/Math.pow(p1.position.distance(p2.position),2);
//		float ax = -(float) (p.position.x/p.orbitRadius*a);
//		float ay = -(float) (p.position.y/p.orbitRadius*a);
//		float az = -(float) (p.position.z/p.orbitRadius*a);
//		Vector3f v = new Vector3f(ax,ay,az);
//		return v;
//	}
//	
//	void computePosition() {
//		
//		for(int i=0;i<list.size();i++) {
//			
//			Planet p = list.get(i);
//			Vector3f a = computeAcceleration(p);
//			
//
//			p.position.x=(float) (p.position.x+p.velocity.x*dt+a.x/2*dt*dt);				
//			p.position.y=(float) (p.position.y+p.velocity.y*dt+a.y/2*dt*dt);			
//			p.position.z=(float) (p.position.z+p.velocity.z*dt+a.z/2*dt*dt);
//			
//			Vector3f a2 = computeAcceleration(p);
//
//			p.velocity.x=(float) (p.velocity.x+(a.x+a2.x)*dt/2);
//			p.velocity.y=(float) (p.velocity.y+(a.y+a2.y)*dt/2);
//			p.velocity.z=(float) (p.velocity.z+(a.z+a2.z)*dt/2);
//
//			
//		}
//
//	}
	Vector3f vectorBetween(Planet p1, Planet p2) {
		Vector3f v= new Vector3f();
		v.x=p1.position.x-p2.position.x;
		v.y=p1.position.y-p2.position.y;
		v.z=p1.position.z-p2.position.z;
		return v;
		
	}
	
	Vector3f computeAcceleration(Planet p1, Planet p2) {
		Vector3f vector = vectorBetween(p1,p2);
		//if(vector.length()<=p1.objectRadius+p2.objectRadius) {System.out.println(p1.name+" & "+p2.name+" collision");}
		
		double a = G*p2.mass/Math.pow(vector.length(),2);
		float ax = -(float) (vector.x/vector.length()*a);
		float ay = -(float) (vector.y/vector.length()*a);
		float az = -(float) (vector.z/vector.length()*a);
		Vector3f v = new Vector3f(ax,ay,az);
		return v;
	}
	
	
	void computePosition() {
		
		for(int i=0;i<list.size();i++) {
			Vector3f a = new Vector3f(0,0,0);
			Planet p = list.get(i);
			
			for(int n=0;n<list.size();n++) {
				if(n!=i) {
					a.x+=computeAcceleration(p, list.get(n)).x;
					a.y+=computeAcceleration(p, list.get(n)).y;
					a.z+=computeAcceleration(p, list.get(n)).z;
				}
			}
			
			
			p.position.x=(float) (p.position.x+p.velocity.x*dt+a.x/2*dt*dt);				
			p.position.y=(float) (p.position.y+p.velocity.y*dt+a.y/2*dt*dt);			
			p.position.z=(float) (p.position.z+p.velocity.z*dt+a.z/2*dt*dt);
			
			Vector3f a2 =  new Vector3f(0,0,0);
			
			for(int n=0;n<list.size();n++) {
				if(n!=i) {
					a.x+=computeAcceleration(p, list.get(n)).x;
					a.y+=computeAcceleration(p, list.get(n)).y;
					a.z+=computeAcceleration(p, list.get(n)).z;
				}
			}

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

	@Override
	public void actionPerformed(ActionEvent e) {

			computePosition();
			moveObjects();
			time+=1;

	}
	

}
