package projekt;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.j3d.utils.universe.*;

public class Sphere3D2 extends JPanel{

	public Sphere3D2() {
		SimpleUniverse uni = new SimpleUniverse();
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setSize(250, 250);
		frame.setTitle("Kula w panelu");
		
		Sphere3D2 panel = new Sphere3D2();
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	
}
