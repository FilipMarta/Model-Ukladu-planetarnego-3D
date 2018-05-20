package NewAnimation;

import java.awt.HeadlessException;



import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;

import projekt.StarFrame;

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

	
	
	TransformGroup getTransformGroup() {
		return planetMovement;
	}
	
	void setPosition(Point3f newposition) {
		this.position.x=newposition.x;
		this.position.y=newposition.y;
		this.position.z=newposition.z;
	}
	
	void setVelocity(Vector3f newvelocity) {
		this.position.x=newvelocity.x;
		this.position.y=newvelocity.y;
		this.position.z=newvelocity.z;
	}
	
	
	public void setTexture(String filename) {
		texture = new TextureLoader("src/Textures/"+filename, null).getTexture();
		appearance.setTexture(texture);
	}

	void move() {
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
		this.mass = (float) (mass/Math.pow(10,32));
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
		

		planetMovement.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		planetMovement.addChild(this);
		this.move();
		this.setAppearance(appearance);
		//System.out.println(position.x/Math.pow(10, 7));
		
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
