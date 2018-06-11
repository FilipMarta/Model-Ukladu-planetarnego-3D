package NewAnimation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.Timer;
import javax.vecmath.*;




public class AnimationThread implements ActionListener{
	
	ArrayList<Planet> list;
	final static double G = 6.67408*Math.pow(10, -11);
	float dt=86400/2;
	float time=0;
	float speed=1;
	
	AnimationPanel panel;
	
	public Timer timer= new Timer(10,this);
	
	AnimationThread(ArrayList<Planet> list, AnimationPanel panel){
		this.list=list;
		this.panel = panel;
	}
	public void start() {
		timer.start();
	}

	Vector3f vectorBetween(Planet p1, Planet p2) {
		Vector3f v= new Vector3f();
		v.x=p1.position.x-p2.position.x;
		v.y=p1.position.y-p2.position.y;
		v.z=p1.position.z-p2.position.z;
		return v;
		
	}
	
	Vector3f computeAcceleration(Planet p1, Planet p2) {
		Vector3f vector = vectorBetween(p1,p2);

			double a = G*p2.mass/Math.pow(vector.length(),2);
			float ax = -(float) (vector.x/vector.length()*a);
			float ay = -(float) (vector.y/vector.length()*a);
			float az = -(float) (vector.z/vector.length()*a);
			Vector3f v = new Vector3f(ax,ay,az);
			return v;
	}
	
	
	void computePosition() {
		float t=dt*speed;
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
			
			
			p.position.x=(float) (p.position.x+p.velocity.x*t+a.x/2*t*t);				
			p.position.y=(float) (p.position.y+p.velocity.y*t+a.y/2*t*t);			
			p.position.z=(float) (p.position.z+p.velocity.z*t+a.z/2*t*t);
			
			Vector3f a2 =  new Vector3f(0,0,0);
			
			for(int n=0;n<list.size();n++) {
				if(n!=i) {
					a.x+=computeAcceleration(p, list.get(n)).x;
					a.y+=computeAcceleration(p, list.get(n)).y;
					a.z+=computeAcceleration(p, list.get(n)).z;
				}
			}

			p.velocity.x=(float) (p.velocity.x+(a.x+a2.x)*t/2);
			p.velocity.y=(float) (p.velocity.y+(a.y+a2.y)*t/2);
			p.velocity.z=(float) (p.velocity.z+(a.z+a2.z)*t/2);

			
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
			time+=1*speed;

	}
	

}
