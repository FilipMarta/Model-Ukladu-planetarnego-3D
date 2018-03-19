package projekt;

import java.awt.*;


import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;

public class Sphere3D extends JPanel{
	

	public Sphere3D() {

		
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);
		this.setLayout(new BorderLayout());
		this.add(canvas);
		SimpleUniverse universe = new SimpleUniverse(canvas);
		
		BranchGroup group = new BranchGroup();
		
		
		
		Appearance app = new Appearance();
	    Texture tex = new TextureLoader("src/Textures/Jupiter.jpg", this).getTexture();
	    app.setTexture(tex);
	    TextureAttributes texAttr = new TextureAttributes();
	    texAttr.setTextureMode(TextureAttributes.MODULATE);
	    app.setTextureAttributes(texAttr);
	    app.setMaterial(new Material());
		
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		Sphere ball = new Sphere(0.5f,primflags, app);
		
		
		group.addChild(ball);
		
		
		
		Color3f lcolor = new Color3f(1f,1f,1f);
		Vector3f lvector = new Vector3f(4,-7,-9);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0), 100);
		
		DirectionalLight light = new DirectionalLight(lcolor, lvector);
		light.setInfluencingBounds(bounds);
		group.addChild(light);
		
		universe.addBranchGraph(group);
		universe.getViewingPlatform().setNominalViewingTransform();
		
	}
	
	public static void main(String args[]) {
		new Sphere3D();
	}
//	public static void main(String args[]) {
//		JFrame frame = new JFrame();
//		frame.setSize(250, 250);
//		frame.setTitle("Kula w panelu");
//		
//		Sphere3D panel = new Sphere3D();
//		frame.add(panel, BorderLayout.CENTER);
//		
//		frame.setVisible(true);
//	}
	
}