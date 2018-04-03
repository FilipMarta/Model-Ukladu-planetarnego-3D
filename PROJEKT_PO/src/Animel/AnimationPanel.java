package Animel;

import java.awt.*;
import java.awt.event.*;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;


public class AnimationPanel extends JPanel{

	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	Canvas3D canvas = new Canvas3D(config);
	
	SimpleUniverse universe;
	BranchGroup group;
	
	BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0), 100);
	
	Point3f cameraPoint = new Point3f(0,0,20);
	Vector3f cameraDir = new Vector3f(0,0,-1);
	Vector3f cameraUp = new Vector3f(0,1,0);
	
	double phi=0;
	double pangle;
	double mxPressed;
	
	double tetha=0;
	double tangle;
	double myPressed;
	
	ViewingPlatform camera;
	View view;
	Viewer viewer;
	TransformGroup cameraTransformGroup;

	Boolean w=false;
	Boolean s=false;
	Boolean a=false;
	Boolean d=false;
	Boolean spc=false;
	Boolean ctrl=false;
	Boolean shft=false;
	
	
	void rotateCamera() {
			cameraDir.x=(float)(Math.sin(phi+pangle)*Math.cos(tetha+tangle));
			cameraDir.y = (float) Math.sin(tetha+tangle);
			cameraDir.z=(float)(-Math.cos(phi+pangle)*Math.cos(tetha+tangle));

	}
	
	void initCamera() {
		viewer = universe.getViewer();
		view = viewer.getView();
		view.setBackClipDistance(20001);
	    camera = universe.getViewingPlatform();
	    cameraTransformGroup = camera.getViewPlatformTransform();
	    Transform3D cameraInitTransform = new Transform3D();
	    cameraInitTransform.setTranslation(new Vector3f(cameraPoint.x,cameraPoint.y,cameraPoint.z));
	    cameraTransformGroup.setTransform(cameraInitTransform);
	}
	
	void moveCamera() {
		int x = 1;
		
		for(int i=0;i<15;i++) {
			if(shft) {x=100;}
			
			if(w) {
				cameraPoint.x=cameraPoint.x + cameraDir.x*x*.03f;
				cameraPoint.y=cameraPoint.y + cameraDir.y*x*.03f;
				cameraPoint.z=cameraPoint.z + cameraDir.z*x*.03f;
			}
			if(s) {
				cameraPoint.x=cameraPoint.x - cameraDir.x*x*.03f;
				cameraPoint.y=cameraPoint.y - cameraDir.y*x*.03f;
				cameraPoint.z=cameraPoint.z - cameraDir.z*x*.03f;
			}
			if(a) {
				cameraPoint.x=cameraPoint.x + cameraDir.z*x*.03f;
				cameraPoint.z=cameraPoint.z - cameraDir.x*x*.03f;
			}
			if(d) {
				cameraPoint.x=cameraPoint.x - cameraDir.z*x*.03f;
				cameraPoint.z=cameraPoint.z + cameraDir.x*x*.03f;
			}
//			if(spc) {
//				cameraPoint.x=cameraPoint.x + cameraDir.x*x*.03f;
//				cameraPoint.y=cameraPoint.y + cameraDir.y*x*.03f;
//				cameraPoint.z=cameraPoint.z + cameraDir.z*x*.03f;
//			}
			
			if(spc) {cameraPoint.y+=x*.03f;}
			if(ctrl) {cameraPoint.y-=x*.03f;}
			
				try {
					Thread.sleep((long) 2.5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		    Transform3D cameraMovement = new Transform3D();
		    cameraMovement.setTranslation(new Vector3f(cameraPoint.x,cameraPoint.y,cameraPoint.z));
		    cameraMovement.lookAt(new Point3d(cameraPoint), new Point3d(cameraPoint.x+cameraDir.x,cameraPoint.y+cameraDir.y,cameraPoint.z+cameraDir.z), new Vector3d(cameraUp));
		    cameraMovement.invert();
		    cameraTransformGroup.setTransform(cameraMovement);
			}
	}
	
	void initLights() {
		
		
		PointLight plight = new PointLight();
        plight.setColor(new Color3f(Color.WHITE));
        plight.setPosition(0.0f,0.0f,0.0f);
        plight.setInfluencingBounds(bounds);
        group.addChild(plight);
        
        AmbientLight alight = new AmbientLight();
        
        alight.setInfluencingBounds(bounds);
        group.addChild(alight);
	}

	void initBackground() {
		Sphere background = new Sphere(10000, Primitive.GENERATE_NORMALS_INWARD + Primitive.GENERATE_TEXTURE_COORDS, 100);
		Appearance appearance = new Appearance();
		Texture texture = new TextureLoader("src/Textures/8k_stars_milky_way.jpg", null).getTexture();
		appearance.setTexture(texture);
		background.setAppearance(appearance);
		
		group.addChild(background);
	}
	
	
	void initObjects() {
		Point3f point = new Point3f(10f,0,0);
		Star sun = new Star("Sun", 1.5f, 0, new Point3f(0,0,0));
		Planet jupiter = new Planet("Jupiter", 1f, 0,0, point, null);
		Planet earth = new Planet("Earth", 0.3f, 0,0,new Point3f(3f,0,-4f), null);
		earth.setTexture("Earth.jpg");
		Planet mars = new Planet("Mars", 0.2f, 0,0,new Point3f(-1f,0,2f), null);
		mars.setTexture("2k_mars.jpg");
		Planet neptune = new Planet("Neptune", 0.8f, 0,0,new Point3f(-7f,0,-10f), null);
		neptune.setTexture("2k_neptune.jpg");

		group.addChild(jupiter.setPosition());
		group.addChild(earth.setPosition());
		group.addChild(mars.setPosition());
		group.addChild(neptune.setPosition());
		group.addChild(sun);
	}
	
	
	
	AnimationPanel(){
		
		this.addKeyListener(new AnimationKeyListener(this));
		this.setFocusable(true);
		this.requestFocusInWindow(true);
		
		canvas.addMouseListener(new AnimationMouseListener(this));
		canvas.addMouseMotionListener(new AnimationMouseListener(this));
		
		
		this.setLayout(new BorderLayout());
		this.add(canvas);
		universe = new SimpleUniverse(canvas);
		
		
		group = new BranchGroup();
		
		
		initBackground();
		initObjects();
		
		initLights();
		universe.addBranchGraph(group);
		initCamera();
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setSize(1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("AnimationPanel test");
		
		AnimationPanel apanel = new AnimationPanel();
		
		
		frame.add(apanel, BorderLayout.CENTER);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		
		frame.setVisible(true);

	}





}
