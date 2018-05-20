package projekt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.media.j3d.Appearance;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TransformGroup;
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
	JMenuItem importing = new JMenuItem("Import Configuration");

	ArrayList<Planet> objects = new ArrayList<Planet>();
	
	JPanel leftside = new JPanel();
	JPanel animel = new JPanel(new BorderLayout());
	Sketch2D sketchpanel = new Sketch2D(objects); //Filip
	JPanel bottom = new JPanel();
	
	AnimationPanel apanel = null;
	int indexofSS;
	
	
	ArrayList<String> planets = new ArrayList<String>();
	
	JButton addobject = new JButton("   Add an object  ");
	JButton modifyobject = new JButton("Modify an object");
	JButton deleteobject = new JButton("Delete an object");
	
	JComboBox<Object> planetList = new JComboBox<Object>(planets.toArray());
	
	//JLabel lstarlabel = new JLabel("Object"); //l od left (dla Filipa)
	
		
	
	JButton play = new JButton("Play");
	JCheckBox playsolarsystem = new JCheckBox("Play/add Solar System Simulation");
	JButton reverse = new JButton("Reverse");

	
	public void addNewObject(Planet p) {
		
		planets.add(p.name);
		objects.add(p);
		planetList.addItem(p.name);
		
	}

	
	public void modifyData(Planet p) {
		
		Planet ob = null;
		Planet newob = null;
		int index = planetList.getSelectedIndex();
		if(planetList.isValid()) {
		

			StarFrame starframe = new StarFrame(this);
			ob = objects.set(index, p);
			starframe.ob = p;
			starframe.setVisible(true);
			
		}
	}
	

	JPanel parameterespanel = new JPanel(new GridLayout(15,1));
	
	DecimalFormat df=new DecimalFormat("0.00");
	JLabel titlename = new JLabel("Name: ");
	JLabel titleradius = new JLabel("Radius[km]: ");
	JLabel titlemass = new JLabel(" Mass[kg*10^25]: ");
	JLabel titlelocation = new JLabel("Location[km*10^8]: ");
	JLabel titlevelocity = new JLabel("Velocity[km/s]: ");
	JLabel titlestarornot = new JLabel("Is it a star? ");
	
	JLabel name = new JLabel("");
	JLabel radius = new JLabel("");
	JLabel mass = new JLabel("");
	JLabel location = new JLabel("");
	JLabel velocity = new JLabel("");
	JLabel starornot = new JLabel("");
	
	public void showData(Planet obj) {
		
		name.setText(obj.name);
		radius.setText(String.valueOf(df.format(obj.objectRadius*10000)));
		mass.setText(String.valueOf(df.format(obj.mass*Math.pow(10, 7))));	
		location.setText("["+df.format(obj.position.x)+" ; "+df.format(obj.position.y)+" ; "+df.format(obj.position.z)+"]");
		velocity.setText("["+df.format(obj.velocity.x*Math.pow(10,7))+" ; "+df.format(obj.velocity.y*Math.pow(10,7))+" ; "+df.format(obj.velocity.z*Math.pow(10,7))+"]");
		starornot.setText(String.valueOf(obj.starornot));
		
	}
	
	
	void makeSS() {
		
		Thread tmpthread = new Thread(new Runnable() {

			@Override
			public void run() {
				
				Star sun = new Star("Sun", 696342, (float)(1.98855*Math.pow(10, 32)), new Point3f(0,0,0), new Vector3f(0,0,0));
				addNewObject(sun);
				
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
				sketchpanel.repaint();
				
			}
			
		});
		
		tmpthread.start();
		}
	ProjectMainFrame() throws HeadlessException {
	  		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  		this.setSize(800,600);
	  		this.setBackground(Color.black);
	  		
	  		this.setJMenuBar(menubar);
	  		menubar.add(file);
	  		file.add(importing);
	  		file.add(save);
	  		
	  		
	  		
	  		leftside.setLayout(new BoxLayout(leftside, BoxLayout.Y_AXIS));
	  		leftside.setPreferredSize(new Dimension(200, this.getHeight()));
	  		this.add(leftside, BorderLayout.LINE_START);
	  		
	  		this.add(animel, BorderLayout.CENTER);
	  		animel.add(sketchpanel, BorderLayout.CENTER);
	  		animel.add(bottom, BorderLayout.PAGE_END);
	  		
	  		
	  		parameterespanel.add(titlename);
	  		parameterespanel.add(name);
	  		parameterespanel.add(titlemass);
	  		parameterespanel.add(mass);
	  		parameterespanel.add(titleradius);
	  		parameterespanel.add(radius);
	  		parameterespanel.add(titlelocation);
	  		parameterespanel.add(location);
	  		parameterespanel.add(titlevelocity);
	  		parameterespanel.add(velocity);
	  		parameterespanel.add(titlestarornot);
	  		parameterespanel.add(starornot);

	  		
	  		addobject.setAlignmentX(Component.CENTER_ALIGNMENT);
	  		leftside.add(addobject);
	  		modifyobject.setAlignmentX(Component.CENTER_ALIGNMENT);
	  		leftside.add(modifyobject);
	  		modifyobject.addActionListener(new JButtonListener1(this));
	  		deleteobject.setAlignmentX(Component.CENTER_ALIGNMENT);
	  		leftside.add(deleteobject);	
	  		deleteobject.addActionListener(new JButtonListener(this){
	  			public void actionPerformed(ActionEvent e){
	            		int index = planetList.getSelectedIndex();
	            		if(planetList.isValid()) {
	                    planetList.removeItemAt(index);
	                    objects.remove(index);
	                    sketchpanel.repaint();
	                    }
	            		
	            }});
	  		
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
					
//					Planet earth = new Planet("Earth", 6371, (float)(5.97219*Math.pow(10,24)), new Point3f(0,0,(float)(-1.49598261*Math.pow(10,8))), new Vector3f(29.29f,0,0));
//					earth.setTexture("Earth.jpg");
//					addNewObject(earth);

					
					apanel = new AnimationPanel(objects);
					frame.add(apanel, BorderLayout.CENTER);
					
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
					
					
					frame.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowClosing(WindowEvent e) {
							apanel.thread.timer.stop();
							apanel=null;
							ArrayList<Planet> tmplist = new ArrayList<Planet>();
							for(int i=0;i<objects.size();i++) {
								if(objects.get(i).starornot) {
									tmplist.add(new Star((Star) objects.get(i)));
								}
								else {
									tmplist.add(new Planet(objects.get(i)));
								}
								
							}
							objects=tmplist;
							sketchpanel.setList(objects);
							
						}

						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
					});
					
					frame.setVisible(true);
					
				}
	  			
	  		});
	  		playsolarsystem.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange()==ItemEvent.SELECTED) {
						indexofSS = objects.size();
						makeSS();
						
					}
					else {
						for(int i=0;i<10;i++) {
							objects.remove(indexofSS);
							planets.remove(indexofSS);
							planetList.removeItemAt(indexofSS);
						}
						sketchpanel.repaint();
						planetList.revalidate();
						planetList.repaint();
						
						
					}
					
				}
	  			
	  		});
	  		bottom.add(playsolarsystem);
//	  		bottom.add(reverse);
	  		
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
			if(planetList.isValid()) {
				showData(mainFrame.objects.get(index));	
			}

		}
		
	}
	
	public class JButtonListener implements ActionListener{
		
		ProjectMainFrame mainFrame = null;
		
		JButtonListener(ProjectMainFrame mainFrame){
		this.mainFrame = mainFrame;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand() == "   Add an object  ")
			{
				
				StarFrame frame = new StarFrame(mainFrame);
				frame.setVisible(true);
				
				
			}
		}
	}

	public class JButtonListener1 implements ActionListener{
		
		ProjectMainFrame mainFrame = null;
		
		JButtonListener1(ProjectMainFrame mainFrame){
		this.mainFrame = mainFrame;
		
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand() == "Modify an object")
			{

				Planet ob = null;
				
        		mainFrame.modifyData(ob);
                //sketchpanel.repaint();
	}
}
		
}
}