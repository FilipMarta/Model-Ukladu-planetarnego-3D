package projekt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StarFrame extends JFrame { //Marta

	int WEIGHT = 280;
	int HEIGHT = 180;
	
	JPanel pagetitle = new JPanel(new GridLayout(1,1));
	JPanel toppanel = new JPanel(new GridLayout(3,2));
	JPanel bottompanel = new JPanel(new GridLayout(1,3));
	
	JLabel title = new JLabel("Set parameters of your star:");
	JLabel lname = new JLabel("Name: ");
	JLabel lmass = new JLabel("Mass: ");
	JLabel lradius = new JLabel("Radius: ");
	JTextField name = new JTextField();
	JTextField mass = new JTextField();
	JTextField radius = new JTextField();
	
	
	JButton okbutton = new JButton("OK");
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
	
		bottompanel.add(okbutton);
		okbutton.addActionListener(new JButtonListener());
	
		
	}
	
	public class JButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand() == "OK")
			{
					Objects ob = new Objects(name.getText(), Double.parseDouble(mass.getText()), Double.parseDouble(radius.getText()));
					mainFrame.addNewObject(ob);
					
				
				StarFrame.this.setVisible(false);
				
			}
	}
}
}
