package Animel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JPanel;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class AnimationKeyListener implements KeyListener{

	AnimationPanel panel;
	
	AnimationKeyListener(AnimationPanel panel){
		this.panel = panel;
	}
	
	
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyChar()=='r') {
			panel.cameraPoint.set(0, 0, 30f);
			panel.cameraDir.set(0,0,-1);
			panel.phi =0;
			panel.tetha =0;
		}
		
		if(e.getKeyChar()=='c') {
			System.out.println(panel.cameraPoint.x + "  ,  "+ panel.cameraPoint.y+"  ,  "+panel.cameraPoint.z);
			System.out.println(panel.cameraDir.x + "  ,  "+ panel.cameraDir.y+"  ,  "+panel.cameraDir.z);
			System.out.println(panel.cameraUp.x + "  ,  "+ panel.cameraUp.y+"  ,  "+panel.cameraUp.z);
			System.out.println();
		}
		
		if(e.getKeyChar()=='p') {
			System.out.println(panel.canvas.getWidth() + "  ,  "+ panel.canvas.getHeight());
		}

		if(e.getKeyCode()==KeyEvent.VK_SHIFT) {panel.shft=true;}
		if(e.getKeyChar()=='w') {panel.w=true;}
		if(e.getKeyChar()=='s') {panel.s=true;}
		if(e.getKeyChar()=='a') {panel.a=true;}
		if(e.getKeyChar()=='d') {panel.d=true;}
		if(e.getKeyChar()==' ') {panel.spc=true;}
		if(e.getKeyCode()==KeyEvent.VK_CONTROL) {panel.ctrl=true;}
		
		panel.moveCamera();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar()=='w') {panel.w=false;}
		if(e.getKeyChar()=='s') {panel.s=false;}
		if(e.getKeyChar()=='a') {panel.a=false;}
		if(e.getKeyChar()=='d') {panel.d=false;}
		if(e.getKeyChar()==' ') {panel.spc=false;}
		if(e.getKeyCode()==KeyEvent.VK_CONTROL) {panel.ctrl=false;}
		if(e.getKeyCode()==KeyEvent.VK_SHIFT) {panel.shft=false;}
		
	}
}
