package NewAnimation;

import java.awt.*;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;


public class Star extends Planet{

	PointLight light;

	public Star(String name, float objectRadius, float mass, Point3f position, Vector3f velocity) throws HeadlessException {
		super(name, objectRadius/20, mass*100, position, velocity);
		
		starornot = true;
		this.setTexture("2k_sun.jpg");
		this.appearance.setMaterial(null);
		
		light = new PointLight();
        light.setColor(new Color3f(Color.WHITE));
        light.setInfluencingBounds(new BoundingSphere(new Point3d(0,0,0), 10000));
        this.planetMovement.addChild(light);
		
		
	}
	
	
	public Star(Star s) {
		super(s);
		starornot = true;
		light = new PointLight();
        light.setColor(new Color3f(Color.WHITE));
        light.setInfluencingBounds(new BoundingSphere(new Point3d(0,0,0), 10000));
        this.planetMovement.addChild(light);

	}
}
