package projekt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.media.j3d.Appearance;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

import NewAnimation.*;
import projekt.StarFrame.JButtonListener;

public class ProjectMainFrame extends JFrame {
	
 // In loving memory of Stephen Hawking
	
	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem save = new JMenuItem("Save Configuration");

	ArrayList<Planet> objects = new ArrayList<Planet>();
	
	JPanel leftside = new JPanel();
	JPanel animel = new JPanel(new BorderLayout());
	Sketch2D anipanel = new Sketch2D(objects); //Filip
	JPanel bottom = new JPanel();
	
	AnimationPanel apanel = null;
	Boolean SSSelected = false;
	
	ArrayList<String> planets = new ArrayList<String>();
	
	JButton addobject = new JButton("Add an object");
	
	JComboBox<Object> planetList = new JComboBox<Object>(planets.toArray());
	
	//JLabel lstarlabel = new JLabel("Object"); //l od left (dla Filipa)
	

	
	
	JSlider sslider = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
	JLabel speed = new JLabel(String.format("Animation speed: %d", sslider.getValue()));
	
	
	JButton play = new JButton("Play");
	JCheckBox playsolarsystem = new JCheckBox("Play/add Solar System Simulation");
	JButton reverse = new JButton("Reverse");
	
	public void addNewObject(Planet p) {
		
		planets.add(p.name);
		objects.add(p);
		planetList.addItem(p.name);
		
		showData(p);
	}
	
	JPanel parameterespanel = new JPanel(new GridLayout(10,2));
	
	JLabel titlename = new JLabel("Name: ");
	JLabel titleradius = new JLabel(" Radius: ");
	JLabel titlemass = new JLabel(" Mass: ");
	JLabel titlexlocation = new JLabel("X location: ");
	JLabel titleylocation = new JLabel(" Y location: ");
	JLabel titlezlocation = new JLabel(" Z location: ");
	JLabel titlexvelocity = new JLabel("X velocity: ");
	JLabel titleyvelocity = new JLabel(" Y velocity: ");
	JLabel titlezvelocity = new JLabel(" Z velocity: ");
	JLabel titlestarornot = new JLabel("Is it a star? ");
	
	JLabel name = new JLabel("");
	JLabel radius = new JLabel("");
	JLabel mass = new JLabel("");
	JLabel xlocation = new JLabel("");
	JLabel ylocation = new JLabel("");
	JLabel zlocation = new JLabel("");
	JLabel xvelocity = new JLabel("");
	JLabel yvelocity = new JLabel("");
	JLabel zvelocity = new JLabel("");
	JLabel starornot = new JLabel("");
	
	public void showData(Planet obj) {
		
		name.setText(obj.name);
		radius.setText(String.valueOf(obj.objectRadius));
		mass.setText(String.valueOf(obj.mass));	
		xlocation.setText(String.valueOf(obj.position.x));
		ylocation.setText(String.valueOf(obj.position.y));	
		zlocation.setText(String.valueOf(obj.position.z));
		xvelocity.setText(String.valueOf(obj.velocity.x));	
		yvelocity.setText(String.valueOf(obj.velocity.y));
		zvelocity.setText(String.valueOf(obj.velocity.z));	
		//starornot.setText(String.valueOf(obj.starornot));
		
	}
	
	void makeSS() {
			Planet mercury = new Planet("Mercury", 2439,(float)(3.3011*Math.pow(10, 23)), new Point3f(0,0,(float)(-6.982*Math.pow(10,7))), new Vector3f(38.86f,0,0));
			mercury.setTexture("Mercury.jpg");
			addNewObject(mercury);
			
			Planet venus = new Planet("Venus", 6051,(float)(4.867*Math.pow(10, 24)), new Point3f(0,0,(float)(-1.0894*Math.pow(10,8))), new Vector3f(34.78f,0,0));
			venus.setTexture("Venus.jpg");
			addNewObject(venus);
			
			Planet earth = new Planet("Earth", 6371, (float)(5.97219*Math.pow(10,24)), new Point3f(0,0,(float)(-1.49598261*Math.pow(10,8))), new Vector3f(29.29f,0,0));
			earth.setTexture("Earth.jpg");
			addNewObject(earth);
			
			Planet mars = new Planet("Mars", 3389, (float)(6.4171*Math.pow(10,23)), new Point3f(0,0,(float)(-2.4923*Math.pow(10,8))), new Vector3f(21.97f,0,0));
			mars.setTexture("2k_mars.jpg");
			addNewObject(mars);
			
			Planet jupiter = new Planet("Jupiter", 69911, (float)(1.89819*Math.pow(10, 27)), new Point3f(0,0,(float)(-8.16*Math.pow(10,8))),new Vector3f(12.44f,0,0));
			addNewObject(jupiter);
			
			Planet saturn = new Planet("Saturn", 58232, (float)(5.6834*Math.pow(10,26)), new Point3f(0,0,(float)(-1.51450*Math.pow(10,9))), new Vector3f(9.09f,0,0));
			saturn.setTexture("Saturn.jpg");
			
			Appearance app = new Appearance();
			Texture texture = new TextureLoader("src/Textures/saturn_rings.png", null).getTexture();
			app.setTexture(texture);
			TextureAttributes texAtt = new TextureAttributes();
			texAtt.setTextureMode(TextureAttributes.MODULATE);
			app.setTextureAttributes(texAtt);
			//app.setMaterial(new Material());
			TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.NICEST, 0f);
			app.setTransparencyAttributes(ta);
			Cylinder rings = new Cylinder(3f*saturn.objectRadius,0.1f, Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,100, 100, app);
			saturn.planetMovement.addChild(rings);
			addNewObject(saturn);
			
			Planet uranus = new Planet("Uranus", 25362, (float)(8.6813*Math.pow(10,25)), new Point3f(0,0,(float)(-3.00362*Math.pow(10,9))), new Vector3f(6.49f,0,0));
			uranus.setTexture("Uranus.png");
			addNewObject(uranus);
			
			Planet neptune = new Planet("Neptune", 24622,(float)(1.02413*Math.pow(10, 26)), new Point3f(0,0,(float)(-4.54567*Math.pow(10,9))), new Vector3f(5.37f,0,0));
			neptune.setTexture("2k_neptune.jpg");
			addNewObject(neptune);
			
			Planet pluto = new Planet("Pluto", 1188,(float)(1.303*Math.pow(10, 22)), new Point3f(0,0,(float)(-7.375*Math.pow(10,9))), new Vector3f(3.7f,0,0));
			pluto.setTexture("Pluto.jpg");
			addNewObject(pluto);
			
		
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
	  		
	  		
	  		parameterespanel.add(titlename);
	  		parameterespanel.add(name);
	  		parameterespanel.add(titlemass);
	  		parameterespanel.add(mass);
	  		parameterespanel.add(titleradius);
	  		parameterespanel.add(radius);
	  		parameterespanel.add(titlexlocation);
	  		parameterespanel.add(xlocation);
	  		parameterespanel.add(titleylocation);
	  		parameterespanel.add(ylocation);
	  		parameterespanel.add(titlezlocation);
	  		parameterespanel.add(zlocation);
	  		parameterespanel.add(titlexvelocity);
	  		parameterespanel.add(xvelocity);
	  		parameterespanel.add(titleyvelocity);
	  		parameterespanel.add(yvelocity);
	  		parameterespanel.add(titlezvelocity);
	  		parameterespanel.add(zvelocity);
	  		parameterespanel.add(titlestarornot);
	  		parameterespanel.add(starornot);

	  		sslider.addChangeListener(new SliderChangeListener());
	  		
	  		addobject.setAlignmentX(Component.CENTER_ALIGNMENT);
	  		leftside.add(addobject);
	  		addobject.addActionListener(new JButtonListener(this));
	  		leftside.add(planetList);
	  		planetList.addActionListener(new JComboBoxListener(this));
	  		leftside.add(parameterespanel);
	  		leftside.add(Box.createRigidArea(new Dimension(0, 225)));
	  		leftside.add(Box.createRigidArea(new Dimension(0, 150)));
	  		
	  		
//	  		bottom.add(speed);
//	  		bottom.add(sslider);
	  		bottom.add(play);
	  		play.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame frame = new JFrame();
					frame.setSize(1280, 800);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setTitle("AnimationPanel test");
					
					
					if(SSSelected) {
						makeSS();
					}
					apanel = new AnimationPanel(objects);
					frame.add(apanel, BorderLayout.CENTER);
					
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
					
					
					frame.setVisible(true);
					
				}
	  			
	  		});
	  		playsolarsystem.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange()==ItemEvent.SELECTED) {
						SSSelected = true;
					}
					else {
						SSSelected = false;
					}
					
				}
	  			
	  		});
	  		bottom.add(playsolarsystem);
//	  		bottom.add(reverse);
	  		
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
