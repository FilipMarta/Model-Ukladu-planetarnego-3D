package Animel;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.media.j3d.*;
import javax.swing.JPanel;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;

public class Planet extends Sphere{
	
	String name;
	float objectRadius;
	float mass;
	float orbitRadius;
	Point3f position;
	Vector3f velocity;
	
	Appearance appearance;
	Texture texture;
	Texture ntexture;
	TextureAttributes texAtt;

	
	TransformGroup setPosition() {
		Transform3D transform = new Transform3D();
		TransformGroup tg = new TransformGroup();
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Vector3f vector = new Vector3f(position.x, position.y, position.z);
		transform.setTranslation(vector);
		tg.setTransform(transform);
		tg.addChild(this);
		return tg;
		
	}

	void setTexture() {
		texture = new TextureLoader("src/Textures/2k_neptune.jpg", null).getTexture();
		appearance.setTexture(texture);
	}
	void setTexture2() {
		texture = new TextureLoader("src/Textures/2k_mars.jpg", null).getTexture();
		appearance.setTexture(texture);
	}
	void setTexture3() {
		texture = new TextureLoader("src/Textures/Earth.jpg", null).getTexture();
		appearance.setTexture(texture);
	}
	
	
	Planet(String name, float objectRadius, float mass, float orbitRadius, Point3f position, Vector3f velocity) throws HeadlessException {
		super(objectRadius,Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS, 100);
		
		this.name = name;
		this.objectRadius = objectRadius;
		this.mass = mass;
		this.orbitRadius = orbitRadius;
		this.position = position;
		this.velocity = velocity;
		
		
		appearance = new Appearance();
		texture = new TextureLoader("src/Textures/Jupiter.jpg", null).getTexture();
		appearance.setTexture(texture);
		texAtt = new TextureAttributes();
		texAtt.setTextureMode(TextureAttributes.MODULATE);
		appearance.setTextureAttributes(texAtt);
		Material material = new Material();
		material.setSpecularColor(new Color3f(0,0,0));
		appearance.setMaterial(material);	
		

		this.setAppearance(appearance);
		
	}
}
