package Animel;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class CoordSystem extends BranchGroup{

	CoordSystem(){
		super();

		
		for (float x = -1.0f; x <= 1.0f; x = x + 0.1f)

		   {

		      Sphere xAxis = new Sphere(0.02f);

		      TransformGroup tg = new TransformGroup();

		      Transform3D transform = new Transform3D();

		      Vector3f vector = new Vector3f( x, .0f, .0f);

		      transform.setTranslation(vector);

		      tg.setTransform(transform);

		      tg.addChild(xAxis);

		      super.addChild(tg);

		   }

		   // Y axis made of cones

		   for (float y = -1.0f; y <= 1.0f; y = y + 0.1f)

		   {

		      TransformGroup tg = new TransformGroup();

		      Transform3D transform = new Transform3D();

		      Sphere yAxis = new Sphere(0.02f);

		      Vector3f vector = new Vector3f(.0f, y, .0f);

		      transform.setTranslation(vector);

		      tg.setTransform(transform);

		      tg.addChild(yAxis);

		      super.addChild(tg);

		   }

		   // Z axis made of cylinders

		   for (float z = -1.0f; z <= 1.0f; z = z+ 0.1f)

		   {

		      TransformGroup tg = new TransformGroup();

		      Transform3D transform = new Transform3D();

		      Sphere zAxis = new Sphere(0.02f);

		      Vector3f vector = new Vector3f(.0f, .0f, z);

		      transform.setTranslation(vector);

		      tg.setTransform(transform);

		       tg.addChild(zAxis);

		       super.addChild(tg);

		   }
		   
		   BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0), 1000);
		   
		   AmbientLight csalight = new AmbientLight();
		   csalight.setInfluencingBounds(bounds);
		   //csalight.setScope(this, csalight.indexOfScope(this));
		   super.addChild(csalight);
		   
		   DirectionalLight pluslight = new DirectionalLight(new Color3f(.1f, 1.4f, .1f), new Vector3f(-1f,-1f,-1f));
		   pluslight.setInfluencingBounds(bounds);
		   super.addChild(pluslight);
		   
		   DirectionalLight minuslight = new DirectionalLight(new Color3f(0.7f, .15f, .15f), new Vector3f(1f,1f,1f));
		   minuslight.setInfluencingBounds(bounds);
		   super.addChild(minuslight);
		   
	}
	
}
