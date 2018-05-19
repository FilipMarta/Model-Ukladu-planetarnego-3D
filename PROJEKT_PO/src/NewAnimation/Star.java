package NewAnimation;

import java.awt.*;

import javax.media.j3d.*;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;


public class Star extends Planet{


	public Star(String name, float objectRadius, float mass, Point3f position, Vector3f velocity) throws HeadlessException {
		super(name, objectRadius/20, mass, position, velocity);
		
		starornot = true;
		this.setTexture("2k_sun.jpg");
		this.appearance.setMaterial(null);
		
		PointLight light = new PointLight();
		light.setPosition(position);
		this.getTransformGroup().addChild(light);	
		
		
	}
	
	public Star(Star s) {
		super(s);
		PointLight light = new PointLight();
		light.setPosition(position);
		this.getTransformGroup().addChild(light);

	}
}
