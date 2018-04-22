package NewAnimation;

import java.awt.*;

import javax.media.j3d.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;


public class Star extends Sphere{
	
	
	String name;
	float objectRadius;
	float mass;
	
	Appearance appearance;
	Texture texture;
	TextureAttributes texAtt;

	
	float toLocal(float f) {
		f/=Math.pow(10, 7);
		return f;
	}
	
	float toNormal(float f) {
		f*=Math.pow(10, 7);
		return f;
	}

	Star(String name, float objectRadius, float mass) throws HeadlessException {
		super(objectRadius/200000,Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS, 100);
		
		this.name = name;
		this.objectRadius = objectRadius/100000;
		this.mass = (float)(toLocal(mass)/Math.pow(10, 25));
		
		
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
