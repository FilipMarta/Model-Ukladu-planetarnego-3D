package Animel;

import java.awt.*;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;


public class Star extends Sphere{
	
	
	String name;
	float objectRadius;
	float mass;
	Point3f position;
	
	Appearance appearance;
	Texture texture;
	TextureAttributes texAtt;


	Star(String name, float objectRadius, float mass, Point3f position) throws HeadlessException {
		super(objectRadius,Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS, 100);
		
		this.name = name;
		this.objectRadius = objectRadius;
		this.mass = mass;
		this.position = position;
		
		
		appearance = new Appearance();
		texture = new TextureLoader("src/Textures/2k_sun.jpg", null).getTexture();
		appearance.setTexture(texture);
		texAtt = new TextureAttributes();
		texAtt.setTextureMode(TextureAttributes.MODULATE);
		appearance.setTextureAttributes(texAtt);
		
//		Material material = new Material();
//		material.setAmbientColor(1, 1, 1);
//		appearance.setMaterial(material);	
		

		this.setAppearance(appearance);
		
	}
}
