package NewAnimation;

import java.awt.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;




public class AnimationPanel extends JPanel{

	SimpleUniverse universe;
	public BranchGroup group = new BranchGroup();
	
	BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0), 10000);
	
	Camera camera = new Camera(this);
	public AnimationThread thread;
	
	TransformGroup cameraTransformGroup;
	
	Star sun;
	Cylinder disk;
	ArrayList<Planet> planetList = new ArrayList<Planet>();
	
	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	Canvas3D canvas = new Canvas3D(config) 
	{
        public void postRender()
        {
        	this.getGraphics2D().setColor(Color.white);
        	this.getGraphics2D().drawString(camera.position(),1,15);
        	this.getGraphics2D().drawString(camera.vector(),1,30);
        	this.getGraphics2D().drawRect(1, 40, 120, 20);
        	this.getGraphics2D().drawString("Camera Reset",5,55);
        	this.getGraphics2D().drawRect(1, 70, 120, 20);
        	this.getGraphics2D().drawString("Angle Disk ON/OFF",5,85);
        	this.getGraphics2D().drawString("Speed: 1s = 50 days",1,110);
        	this.getGraphics2D().drawString("Timer: "+Integer.toString(thread.time)+" days",1,130);
            
            
            int x1 = canvas.getWidth()-120;
            int y1 = 120;
            int x2;
            int y2;
            
            double p = camera.phi+camera.phi_tmp;
            double t = camera.tetha+camera.tetha_tmp;
            
            this.getGraphics2D().setColor(Color.red);
            x2=x1+(int)(100*Math.cos(p));
            y2=y1+(int)(100*Math.sin(p)*Math.sin(t));
            this.getGraphics2D().drawString("x", x2-5, y2-5);
            this.getGraphics2D().drawLine(x1, y1, x2, y2);
            
            this.getGraphics2D().setColor(Color.blue);
            x2=x1;
            y2=y1-(int)(100*Math.cos(t));
            this.getGraphics2D().drawString("y", x2-5, y2-5);
            this.getGraphics2D().drawLine(x1, y1,x2 , y2);
            
            this.getGraphics2D().setColor(Color.green);
            x2=x1+(int)(100*Math.sin(p));
            y2=y1+(int)(-100*Math.cos(p)*Math.sin(t));
            this.getGraphics2D().drawString("z", x2-5, y2-5);
            this.getGraphics2D().drawLine(x1, y1, x2, y2);
            
            
            
            this.getGraphics2D().flush(false);
        }
	};
	
	
	
	public void addObject(Planet planet) {
		//planetList.add(planet);
		group.addChild(planet.getTransformGroup());
	}
	
	void initCamera() {
		universe.getViewer().getView().setBackClipDistance(20001);
	    camera.platform = universe.getViewingPlatform();
	    cameraTransformGroup = camera.platform.getViewPlatformTransform();
	    camera.moveCamera();
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
	

	
	void makeDisc() {
		Appearance app = new Appearance();
		Texture texture = new TextureLoader("src/Textures/Disc.png", null).getTexture();
		app.setTexture(texture);
		TextureAttributes texAtt = new TextureAttributes();
		texAtt.setTextureMode(TextureAttributes.MODULATE);
		app.setTextureAttributes(texAtt);
		TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.NICEST, 0.8f);
		ta.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
		app.setTransparencyAttributes(ta);
		
		disk = new Cylinder(200,0, Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS,100, 100, app);
		
		
		group.addChild(disk);
	}
	
	void initObjects() {
		for(int i=0;i<planetList.size();i++) {
			addObject(planetList.get(i));
		}
		
//		Text2D text = new Text2D("lolz", new Color3f(Color.WHITE), "Arial", 400, Font.PLAIN);
//		group.addChild(text);

		
		makeDisc();
		//Planet object = new Planet("NONAME", 6000,(float)(6*Math.pow(10, 24)), new Point3f((float)(2*Math.pow(10,8)),0,0), new Vector3f(10,0,-15f));
		//addObject(object);
		
//		group.addChild(sun.getTransformGroup());
	}
	
	
	
	
	
	public AnimationPanel(ArrayList<Planet> p) throws HeadlessException{
		
		this.addKeyListener(camera);
		this.setFocusable(true);
		this.requestFocusInWindow(true);
		
		canvas.addMouseListener(camera);
		canvas.addMouseMotionListener(camera);
		
		group.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		group.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		group.setCapability(BranchGroup.ALLOW_DETACH);
		
		this.setLayout(new BorderLayout());
		this.add(canvas);
		universe = new SimpleUniverse(canvas);
		planetList = p;
		
		
		initBackground();
		initObjects();
		initLights();
		
		
		universe.addBranchGraph(group);
		initCamera();
		
		thread = new AnimationThread(planetList);
		//thread.start();
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setSize(1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("AnimationPanel test");
		
		//AnimationPanel apanel = new AnimationPanel(planetList);
		
		
		//frame.add(apanel, BorderLayout.CENTER);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		
		frame.setVisible(true);

	}

}
