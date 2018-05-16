package projekt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import projekt.StarFrame.JButtonListener;

public class ProjectMainFrame extends JFrame {
	
 // In loving memory of Stephen Hawking
	
	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem save = new JMenuItem("Save Configuration");

	
	JPanel leftside = new JPanel();
	JPanel anipanel = new JPanel();
	//Sphere3D anipanel = new Sphere3D();
	JPanel animel = new JPanel(new BorderLayout()); //Filip
	JPanel bottom = new JPanel();
	
	
	ArrayList<String> planets = new ArrayList<String>();
	
	JButton addobject = new JButton("Add an object");
	
	JComboBox<Object> planetList = new JComboBox<Object>(planets.toArray());
	
	JLabel lstarlabel = new JLabel("Object"); //l od left (dla Filipa)
	
	ArrayList<Objects> objects = new ArrayList<Objects>();
	
	
	JSlider sslider = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
	JLabel speed = new JLabel(String.format("Animation speed: %d", sslider.getValue()));
	
	
	JButton play = new JButton("Play");
	JButton pause = new JButton("Pause");
	JButton reverse = new JButton("Reverse");
	
	public void addNewObject(Objects o) {
		
		planets.add(o.name);
		objects.add(o);
		planetList.addItem(o.name);
		
		showData(o);
	}
	
	JPanel parameterespanel = new JPanel(new GridLayout(3,2));
	
	JLabel titlename = new JLabel("Name: ");
	JLabel titlemass = new JLabel("Mass: ");
	JLabel titleradius = new JLabel("Radius: ");
	JLabel name = new JLabel("");
	JLabel mass = new JLabel("");
	JLabel radius = new JLabel("");
	
	public void showData(Objects obj) {
		
		name.setText(obj.name);
		mass.setText(String.valueOf(obj.mass));
		radius.setText(String.valueOf(obj.radius));	
	}
	
	ProjectMainFrame() throws HeadlessException {
	  		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	  		this.setSize(800,600);
	  		this.setBackground(Color.black);
	  		
	  		this.setJMenuBar(menubar);
	  		menubar.add(file);
	  		file.add(save);
	  		
	  		this.add(leftside, BorderLayout.LINE_START);
	  		leftside.setLayout(new BoxLayout(leftside, BoxLayout.Y_AXIS));
	  		this.add(animel, BorderLayout.CENTER);
	  		animel.add(anipanel, BorderLayout.CENTER);
	  		animel.add(bottom, BorderLayout.PAGE_END);
	  		anipanel.setBackground(Color.black); 
	  		
	  		parameterespanel.add(titlename);
	  		parameterespanel.add(name);
	  		parameterespanel.add(titlemass);
	  		parameterespanel.add(mass);
	  		parameterespanel.add(titleradius);
	  		parameterespanel.add(radius);
	  		
	  		
	  		//Border border = BorderFactory.createLineBorder(Color.yellow, 2);
	  		
	  		
	  		
	  		sslider.addChangeListener(new SliderChangeListener());
	  		
	  		leftside.add(addobject);
	  		addobject.addActionListener(new JButtonListener(this));
	  		leftside.add(planetList);
	  		planetList.addActionListener(new JComboBoxListener(this));
	  		leftside.add(parameterespanel);
	  		leftside.add(Box.createRigidArea(new Dimension(0, 225)));
	  		leftside.add(Box.createRigidArea(new Dimension(0, 150)));
	  		
	  		
	  		bottom.add(speed);
	  		bottom.add(sslider);
	  		bottom.add(play);
	  		bottom.add(pause);
	  		bottom.add(reverse);
	  		
	  		
	}
	
	public class SliderChangeListener implements ChangeListener{
		
		public void stateChanged(ChangeEvent arg0) {
			String value = String.format("Animation speed: %d", sslider.getValue());
			speed.setText(value);
		}
	}
	
	public class JComboBoxListener implements ActionListener {

		ProjectMainFrame mainFrame = null;
		
		public JComboBoxListener(ProjectMainFrame mainFrame) {
			
			this.mainFrame = mainFrame;
			
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			mainFrame.planetList.getSelectedIndex();
			int index = mainFrame.planetList.getSelectedIndex();
			
			showData(mainFrame.objects.get(index));
		}
		
	}
	
	public class JButtonListener implements ActionListener{
		
		ProjectMainFrame mainFrame = null;
		
		JButtonListener(ProjectMainFrame mainFrame){
		this.mainFrame = mainFrame;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand() == "Add an object")
			{
				
				StarFrame frame = new StarFrame(mainFrame);
				frame.setVisible(true);
				
				
			}
		}
	}


}
