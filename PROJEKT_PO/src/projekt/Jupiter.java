package projekt;
import java.applet.Applet;

import java.awt.Container;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import com.sun.j3d.utils.universe.*;

public class Jupiter extends Applet{
	
	Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
	Color3f red = new Color3f(0.7f, .15f, .15f);
	Color3f orange = new Color3f(1f, .5f, 0f);
	Color3f orangem = new Color3f(0.89f, 0.47f, 0.2f);
	Color3f color =  new Color3f(1f,0.498039f,0f);

	
	
	Transform3D move(Point3d point) {
		Transform3D m = new Transform3D();
		Vector3d up = new Vector3d(point.x, point.y+1, point.z);
		m.lookAt(point, new Point3d(0d, 0d, 0d), up);
		return m;
	}
	   
	public Jupiter(){
		
		SimpleUniverse universe = new SimpleUniverse();
		BranchGroup group = new BranchGroup();
		
		TextureLoader tloader = new TextureLoader("C:/Users/Filip/git/Repository/PROJEKT_PO/src/Textures/Jupiter.jpg", "LUMINANCE", new Container());
		Texture texture = tloader.getTexture();
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
		texture.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );
		   
		Appearance ap = new Appearance();
		ap.setTexture(texture);
		
		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.MODULATE);
		ap.setTextureAttributes(texAttr);
		
		ap.setMaterial(new Material(color, black, color, black, 1.0f));
		
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		
		Sphere ball = new Sphere(0.5f,primflags, ap);
		group.addChild(ball);
		
//		for(double i=0; i<2*Math.PI; i+=Math.PI/5) {
//			Sphere ball = new Sphere(.5f, primflags,ap);
//			
//			Transform3D move = new Transform3D();
//			TransformGroup tg = new TransformGroup();
//			Vector3d v = new Vector3d(0.8*Math.cos(i),0.8*Math.sin(i),0);
//			move.setTranslation(v);
//			tg.setTransform(move);
//			tg.addChild(ball);
//			
//			
//			group.addChild(ball);
//
//        }
		
		

		
		Color3f lcolor = new Color3f(1f,1f,1f);
		Vector3f lvector = new Vector3f(4,-7,10);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0), 100);
		
		DirectionalLight light = new DirectionalLight(lcolor, lvector);
		light.setInfluencingBounds(bounds);
		group.addChild(light);
		
		AmbientLight alight = new AmbientLight(new Color3f(.5f,.5f,.5f));
		alight.setInfluencingBounds(bounds);
		//group.addChild(alight);
		
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(move(new Point3d(0,0,-3)));
		universe.addBranchGraph(group);
		
		
		
	}

	public static void main(String[] args) {
//		Jupiter j = new Jupiter();
//		new MainFrame(j, 300, 300);
		new Jupiter();
	}

}
