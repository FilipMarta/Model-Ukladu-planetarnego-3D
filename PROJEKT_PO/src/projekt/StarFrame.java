package projekt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StarFrame extends JFrame { //Marta

	int WEIGHT = 500;
	int HEIGHT = 200;
	
	JPanel pagetitle = new JPanel(new GridLayout(1,1));
	JPanel toppanel = new JPanel(new GridLayout(3,2));
	JPanel bottompanel = new JPanel(new GridLayout(1,1));
	
	JLabel title = new JLabel("Set parameters of celestial object:");
	JLabel lname = new JLabel("Name: ");
	JLabel lmass = new JLabel("Mass: ");
	JLabel lradius = new JLabel("Radius: ");
	JLabel lxlocation = new JLabel("X location: ");
	JLabel lylocation = new JLabel("Y location: ");
	JLabel lzlocation = new JLabel("Z location: ");
	JLabel lxvelocity = new JLabel("X velocity: ");
	JLabel lyvelocity = new JLabel("Y velocity: ");
	JLabel lzvelocity = new JLabel("Z velocity: ");
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
	JCheckBox starbutton = new JCheckBox("Star");
	ProjectMainFrame mainFrame = null; 
	
	
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
		bottompanel.add(starbutton);
		bottompanel.add(okbutton);
		okbutton.addActionListener(new JButtonListener());
	
		
	}
	
	public class JButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand() == "OK")
			{
					Objects ob = new Objects(name.getText(), Double.parseDouble(mass.getText()), Double.parseDouble(radius.getText()),
							Double.parseDouble(xlocation.getText()), Double.parseDouble(ylocation.getText()), Double.parseDouble(zlocation.getText()), Double.parseDouble(xvelocity.getText()),	Double.parseDouble(yvelocity.getText()), Double.parseDouble(zvelocity.getText()), starbutton.isSelected());
					mainFrame.addNewObject(ob);
					
				
				StarFrame.this.setVisible(false);
				
			}
	}
}
}
