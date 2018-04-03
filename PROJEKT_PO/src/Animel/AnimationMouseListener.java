package Animel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class AnimationMouseListener implements MouseListener, MouseMotionListener{

	AnimationPanel panel;
	AnimationMouseListener(AnimationPanel panel){
		this.panel=panel;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		panel.requestFocus();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		panel.mxPressed=e.getX();
		panel.myPressed=e.getY();
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		panel.phi+=panel.pangle;
		panel.tetha+=panel.tangle;
		
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
		panel.pangle = (-(double)e.getX()+panel.mxPressed)/(double)panel.canvas.getWidth()/5*Math.PI;
		panel.tangle = (+(double)e.getY()-panel.myPressed)/(double)panel.canvas.getHeight()/5*Math.PI;
		panel.rotateCamera();
		panel.moveCamera();
		panel.requestFocus();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
