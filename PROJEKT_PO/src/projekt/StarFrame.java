package projekt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import NewAnimation.AnimationPanel;
import NewAnimation.Planet;
import NewAnimation.Star;

public class StarFrame extends JFrame { //Marta

	int WEIGHT = 800;
	int HEIGHT = 200;
	
	JPanel pagetitle = new JPanel(new GridLayout(1,1));
	JPanel toppanel = new JPanel(new GridLayout(3,2));
	JPanel bottompanel = new JPanel(new GridLayout(1,1));
	
	JLabel title = new JLabel("Set parameters of celestial object:");
	JLabel lname = new JLabel("Name: ");
	JLabel lmass = new JLabel("Mass [kg*10^25]: ");
	JLabel lradius = new JLabel("Radius [km]: ");
	JLabel lxlocation = new JLabel("X location [km*10^8]: ");
	JLabel lylocation = new JLabel("Y location [km*10^8]: ");
	JLabel lzlocation = new JLabel("Z location [km*10^8]: ");
	JLabel lxvelocity = new JLabel("X velocity [km/s]: ");
	JLabel lyvelocity = new JLabel("Y velocity [km/s]: ");
	JLabel lzvelocity = new JLabel("Z velocity [km/s]: ");
	JTextField name = new JTextField();
	JTextField mass = new JTextField();
	JTextField radius = new JTextField();
	JTextField xlocation = new JTextField();
	JTextField ylocation = new JTextField();
	JTextField zlocation = new JTextField();
	JTextField xvelocity = new JTextField();
	JTextField yvelocity = new JTextField();
	JTextField zvelocity = new JTextField();
	
	
	JButton okbutton = new JButton("OK");
	JCheckBox starbutton = new JCheckBox("Star?");
	
	JComboBox<String> texturebox = new JComboBox<String>();
	String textures[] = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"};
	String tex = "Mercury.jpg";
	
	Boolean starornot=false;

	Planet ob;
	
	ProjectMainFrame mainFrame = null; 
	
	Boolean updaterequest = false; 
	
	public void loadData() {
		
		name.setText(ob.name);
		mass.setText(String.valueOf(ob.mass*Math.pow(10,7)));
		radius.setText(String.valueOf(ob.objectRadius*10000));
		xlocation.setText(String.valueOf(ob.position.x*Math.pow(10,7)));
		ylocation.setText(String.valueOf(ob.position.y*Math.pow(10,7)));
		zlocation.setText(String.valueOf(ob.position.z*Math.pow(10,7)));
		xvelocity.setText(String.valueOf(ob.velocity.x*Math.pow(10,7)));
		yvelocity.setText(String.valueOf(ob.velocity.y*Math.pow(10,7)));
		zvelocity.setText(String.valueOf(ob.velocity.z*Math.pow(10,7)));
		//starbutton.setSelected(ob.starornot);
		
		updaterequest = true;
		
	}
	
	public void refreshObject() {
		
		ob.name=name.getText();
		ob.mass=Float.parseFloat(mass.getText());
		ob.objectRadius=Float.parseFloat(radius.getText());
		ob.position.x=Float.parseFloat(xlocation.getText());
		ob.position.y=Float.parseFloat(ylocation.getText());
		ob.position.z=Float.parseFloat(zlocation.getText());
		ob.velocity.x=Float.parseFloat(xvelocity.getText());
		ob.velocity.y=Float.parseFloat(yvelocity.getText());
		ob.velocity.z=Float.parseFloat(zvelocity.getText());

		//objects.set(selectedindex, p);
		
		
	}

	public StarFrame(ProjectMainFrame mainFrame){
		this.setSize(WEIGHT, HEIGHT);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Set the parameters of your star");
		
		this.mainFrame = mainFrame; //przekazujemy obiekt
		this.add(pagetitle, BorderLayout.PAGE_START);
		this.add(toppanel, BorderLayout.CENTER);
  		this.add(bottompanel, BorderLayout.PAGE_END);
		
  		pagetitle.add(title);
		toppanel.add(lname);
		toppanel.add(name);
		toppanel.add(lmass);
		toppanel.add(mass);
		toppanel.add(lradius);
		toppanel.add(radius);
		toppanel.add(lxlocation);
		toppanel.add(xlocation);
		toppanel.add(lylocation);
		toppanel.add(ylocation);
		toppanel.add(lzlocation);
		toppanel.add(zlocation);
		toppanel.add(lxvelocity);
		toppanel.add(xvelocity);
		toppanel.add(lyvelocity);
		toppanel.add(yvelocity);
		toppanel.add(lzvelocity);
		toppanel.add(zvelocity);
	
		
		
		
		starbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		starbutton.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					starornot = true;
				}
				else {
					starornot = false;
				}
			}
			
		});
		bottompanel.add(starbutton);
		
		for(int i=0;i<textures.length;i++) {
			texturebox.addItem(textures[i]);
		}
		texturebox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED) {
					String iselected = (String)arg0.getItem();
					switch(iselected) {
				  	  case "Mercury":
				  		tex = "Mercury.jpg";
				  		  break;
				  	  case "Venus":
				  		tex = "Venus.jpg";
				  		  break;
				  	  case "Earth":
				  		  tex = "Earth.jpg";
				  		  break;
				  	  case "Mars":
				  		  tex = "2k_mars.jpg";
				  		  break;
				  	  case "Jupiter":
				  	  	tex = "Jupiter.jpg";
				  	  	break;
				  	  case "Saturn":
				  		  tex = "Saturn.jpg";
				  		  break;
				  	  case "Uranus":
				  		  tex = "Uranus.png";
				  		  break;
				  	  case "Neptune":
				  		  tex = "2k_neptune.jpg";
				  		  break;
				  	  case "Pluto":
				  		  tex = "Pluto.jpg";
				  		  break;
				  		  
				  }
				}
				
			}
			
		});
		bottompanel.add(texturebox);
		
		
		
		
		bottompanel.add(okbutton);
		okbutton.addActionListener(new JButtonListener());

		
	}
	
	public class JButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand() == "OK")
			{
				if(updaterequest == false) {
				Planet ob;
				if(starornot) {
					ob = new Star(name.getText(), Float.parseFloat(radius.getText()), (float)(Float.parseFloat(mass.getText())*Math.pow(10, 25)),
							new Point3f(Float.parseFloat(xlocation.getText())*(float)Math.pow(10, 8),Float.parseFloat(ylocation.getText())*(float)Math.pow(10, 8), Float.parseFloat(zlocation.getText())*(float)Math.pow(10, 8)),
						new Vector3f(Float.parseFloat(xvelocity.getText()),	Float.parseFloat(yvelocity.getText()), Float.parseFloat(zvelocity.getText())));//, starbutton.isSelected());
				}
				else {
					ob = new Planet(name.getText(), Float.parseFloat(radius.getText()), (float)(Float.parseFloat(mass.getText())*Math.pow(10, 25)),
							new Point3f(Float.parseFloat(xlocation.getText())*(float)Math.pow(10, 7),Float.parseFloat(ylocation.getText())*(float)Math.pow(10, 7), Float.parseFloat(zlocation.getText())*(float)Math.pow(10, 7)),
						new Vector3f(Float.parseFloat(xvelocity.getText()),	Float.parseFloat(yvelocity.getText()), Float.parseFloat(zvelocity.getText())));//, starbutton.isSelected());
					ob.setTexture(tex);
				}
				
				
				mainFrame.addNewObject(ob);
				mainFrame.sketchpanel.repaint();
				StarFrame.this.setVisible(false);
				mainFrame.showData(ob);
				}
				else {
					refreshObject();
					mainFrame.sketchpanel.repaint();
					StarFrame.this.setVisible(false);
					mainFrame.showData(ob);
					
				}
			}
	}
}
}
