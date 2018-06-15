package NewAnimation;

import java.awt.HeadlessException;
import java.net.URL;
import java.util.Random;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;

public class Planet extends Sphere{
	
	public String name;
	public float objectRadius;
	public float mass;
	public float orbitRadius;
	public Point3f position;
	public Vector3f velocity;
	public Boolean starornot = false;
	
	public Appearance appearance;
	Texture texture;
	TextureAttributes texAtt;
	
	public TransformGroup planetMovement = new TransformGroup();

	
	public void randomTexture() {
		Random rand = new Random();
		int r = rand.nextInt(8);
		switch(r) {
	  		case 0:
	  		setTexture("Mercury.jpg");
	  		break;
	  	  case 1:
	  		setTexture("Venus.jpg");
	  		  break;
	  	  case 2:
	  		setTexture("Earth.jpg");
	  		  break;
	  	  case 4:
	  		setTexture("2k_mars.jpg");
	  		  break;
	  	  case 5:
	  		setTexture("Jupiter.jpg");
	  	  	break;
	  	  case 6:
	  		setTexture("Saturn.jpg");
	  		  break;
	  	  case 7:
	  		setTexture("Uranus.png");
	  		  break;
	  	  case 8:
	  		setTexture("2k_neptune.jpg");
	  		  break;
	  	  case 3:
	  		setTexture("Pluto.jpg");
	  		  break;
		}
	}
	
	
	TransformGroup getTransformGroup() {
		return planetMovement;
	}
	
	
	public void setTexture(String filename) {
		URL res = getClass().getResource("/textures/"+filename);
		texture = new TextureLoader(res, null).getTexture();
		appearance.setTexture(texture);
	}

	public void move() {
		Transform3D transform = new Transform3D();
		Vector3f vector = new Vector3f(position);
		transform.setTranslation(vector);
		planetMovement.setTransform(transform);
	}
	
	float toLocal(float f) {
		f/=Math.pow(10, 7);
		return f;
	}
	
	float toNormal(float f) {
		f*=Math.pow(10, 7);
		return f;
	}
	
	void computeRadius() {
		this.orbitRadius=(float) Math.sqrt(position.x*position.x+position.y*position.y+position.z*position.z);
	}
	
	
	public Planet(String name, float objectRadius, float mass, Point3f position, Vector3f velocity) throws HeadlessException {
		super(objectRadius/10000,Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS, 100);
		
		this.name = name;
		this.objectRadius = objectRadius/10000;
		this.mass = (float) (mass/Math.pow(10,30));
		this.position = new Point3f(toLocal(position.x),toLocal(position.y),toLocal(position.z));
		computeRadius();
		this.velocity = new Vector3f(toLocal(velocity.x), toLocal(velocity.y),toLocal(velocity.z));
		
		planetMovement.setCapability(BranchGroup.ALLOW_DETACH);
		
		appearance = new Appearance();
		setTexture("Jupiter.jpg");
		texAtt = new TextureAttributes();
		texAtt.setTextureMode(TextureAttributes.MODULATE);
		appearance.setTextureAttributes(texAtt);
		Material material = new Material();
		material.setSpecularColor(new Color3f(0,0,0));
		appearance.setMaterial(material);	
		appearance.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		appearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
		appearance.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE);
		
		
		planetMovement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		planetMovement.addChild(this);
		this.move();
		this.setAppearance(appearance);
		
	}
	public Planet(Planet p) {
		super(p.objectRadius,Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS, 100);
		this.name = p.name;
		this.objectRadius = p.objectRadius;
		this.mass = p.mass;
		this.position = p.position;
		computeRadius();
		this.velocity = p.velocity;
		
		planetMovement.setCapability(BranchGroup.ALLOW_DETACH);
		
		appearance = p.appearance;
	
		

		planetMovement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		planetMovement.addChild(this);
		this.move();
		this.setAppearance(appearance);
	}
}
