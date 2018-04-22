package NewAnimation;

import java.awt.event.*;
import java.text.DecimalFormat;

import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.*;


public class Camera implements MouseListener, MouseMotionListener, KeyListener{
	
	AnimationPanel panel;
	
	ViewingPlatform platform;
	
	Point3f point = new Point3f(0,10,60);
	Vector3f direction = new Vector3f(0,-0.1f,-1);
	Vector3f updirection = new Vector3f(0,1,0);
	
	double phi=0;
	double phi_tmp;
	double startX;
	
	double tetha=-Math.atan(direction.y/direction.z);
	double tetha_tmp;
	double startY;
	
	
	Boolean w=false;
	Boolean s=false;
	Boolean a=false;
	Boolean d=false;
	Boolean spc=false;
	Boolean ctrl=false;
	Boolean shft=false;
	
	Boolean threadworks=false;
	Boolean mousepressed=false;
	
	float dist = .3f;
	
	class CameraThread extends Thread{
		
		public void run(){
			do {
			rotateCamera();
			moveCamera();
			}while(w||a||s||d||spc||ctrl||mousepressed);
			threadworks=false;
		}
	}
	
	Camera(AnimationPanel panel){
		this.panel = panel;
	}
	
	
	String position() {
		DecimalFormat df=new DecimalFormat("0.00");

		String x = df.format(point.x);
		String y = df.format(point.y);
		String z = df.format(point.z);
		
		return "Camera Position: ("+x+" ; "+y+" ; "+z+")";
	}
	
	String vector() {
		DecimalFormat df=new DecimalFormat("0.00");

		String x = df.format(-direction.x);
		String y = df.format(direction.y);
		String z = df.format(direction.z);
		
		return "The Direction of Looking: ("+x+" ; "+y+" ; "+z+")";
	}

//	void computeUp() {
//		double t = tetha+Math.PI/2;
//		updirection.x=(float)(Math.sin(phi)*Math.cos(t));
//		updirection.y = (float) Math.sin(t);
//		updirection.z=(float)(-Math.cos(phi)*Math.cos(t));
//	}
	
	void rotateCamera() {
		double p = phi+phi_tmp;
		double t = tetha+tetha_tmp;
		direction.x=(float)(Math.sin(p)*Math.cos(t));
		direction.y = (float) Math.sin(t);
		direction.z=(float)(-Math.cos(p)*Math.cos(t));
		//computeUp();
		
	}

	void moveCamera() {
		int m = 1;
			if(shft) {m=15;}
			
			if(w) {
				point.x+= direction.x*m*dist;
				point.y+= direction.y*m*dist;
				point.z+= direction.z*m*dist;
			}
			if(s) {
				point.x-= direction.x*m*dist;
				point.y-= direction.y*m*dist;
				point.z-= direction.z*m*dist;
			}
			if(a) {
				double p = phi-Math.PI/2;
				point.x+=(float)Math.sin(p)*m*dist;
				point.z+=(float)-Math.cos(p)*m*dist;
			}
			if(d) {
				double p = phi+Math.PI/2;
				point.x+=(float)Math.sin(p)*m*dist;
				point.z+=(float)-Math.cos(p)*m*dist;
			}
			if(spc) {
				double t = tetha+Math.PI/2;
				point.x+= (float)(Math.sin(phi)*Math.cos(t))*m*dist;
				point.y+= (float)Math.sin(t)*m*dist;
				point.z+= (float)(-Math.cos(phi)*Math.cos(t))*m*dist;
			}
			if(ctrl) {
				double t = tetha+Math.PI/2;
				point.x-= (float)(Math.sin(phi)*Math.cos(t))*m*dist;
				point.y-= (float)Math.sin(t)*m*dist;
				point.z-= (float)(-Math.cos(phi)*Math.cos(t))*m*dist;
			}
			
			
				try {
					Thread.sleep((long) 20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		    Transform3D cameraMovement = new Transform3D();
		    cameraMovement.lookAt(new Point3d(point), new Point3d(point.x+direction.x,point.y+direction.y,point.z+direction.z), new Vector3d(updirection));
		    cameraMovement.invert();
		    panel.cameraTransformGroup.setTransform(cameraMovement);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		
		if(e.getKeyCode()==KeyEvent.VK_SHIFT) {shft=true;}
		if(e.getKeyChar()=='w') {w=true;}
		if(e.getKeyChar()=='s') {s=true;}
		if(e.getKeyChar()=='a') {a=true;}
		if(e.getKeyChar()=='d') {d=true;}
		if(e.getKeyChar()==' ') {spc=true;}
		if(e.getKeyCode()==KeyEvent.VK_CONTROL) {ctrl=true;}
		
		
		
		if(threadworks==false) {
			threadworks=true;
			CameraThread thread = new CameraThread();
			thread.start();
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar()=='w') {w=false;}
		if(e.getKeyChar()=='s') {s=false;}
		if(e.getKeyChar()=='a') {a=false;}
		if(e.getKeyChar()=='d') {d=false;}
		if(e.getKeyChar()==' ') {spc=false;}
		if(e.getKeyCode()==KeyEvent.VK_CONTROL) {ctrl=false;}
		if(e.getKeyCode()==KeyEvent.VK_SHIFT) {shft=false;}
		
	}
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		panel.requestFocus();
		if((e.getX()>1&&e.getX()<120)&&(e.getY()>40&&e.getY()<60)) {			
			point.set(0,10,60);
			direction.set(0,-0.1f,-1);
			phi =0;
			tetha =-Math.atan(direction.y/direction.z);
		}
		if((e.getX()>1&&e.getX()<120)&&(e.getY()>70&&e.getY()<90)) {
			if(panel.disk.getAppearance().getTransparencyAttributes().getTransparency()==1f) {
				panel.disk.getAppearance().getTransparencyAttributes().setTransparency(0.8f);
			}
			else {
				panel.disk.getAppearance().getTransparencyAttributes().setTransparency(1f);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startX=e.getX();
		startY=e.getY();
		mousepressed = true;
		if(threadworks==false) {
			threadworks=true;
			CameraThread thread = new CameraThread();
			thread.start();
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		phi+=phi_tmp;
		tetha+=tetha_tmp;
		phi_tmp=0;
		tetha_tmp=0;
		mousepressed = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		
		phi_tmp = (-(double)e.getX()+startX)/(double)panel.canvas.getWidth()/5*Math.PI;
		tetha_tmp = (+(double)e.getY()-startY)/(double)panel.canvas.getHeight()/5*Math.PI;
		if(tetha+tetha_tmp<=(-Math.PI/2+0.1)) {
			tetha_tmp=(-Math.PI/2+0.1)-tetha;
		}
		if(tetha+tetha_tmp>=(Math.PI/2-0.1)) {
			tetha_tmp=(Math.PI/2-0.1)-tetha;
		}
		
		
		
		panel.requestFocus();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
