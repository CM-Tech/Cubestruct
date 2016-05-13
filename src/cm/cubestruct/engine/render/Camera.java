package cm.cubestruct.engine.render;

import org.joml.Vector3f;

public class Camera {
public Vector3f position;
    

    public Vector3f rotation;
	public Camera() {
		position = new Vector3f(0, 0, 0);
        //scale = 1;
        rotation = new Vector3f(0, 0, 0);
		// TODO Auto-generated constructor stub
	}
	 public Vector3f getPosition() {
	        return position;
	    }

	    public void setPosition(float x, float y, float z) {
	        this.position.x = x;
	        this.position.y = y;
	        this.position.z = z;
	    }
	    

	   

	    public Vector3f getRotation() {
	        return rotation;
	    }

	    public void setRotation(float x, float y, float z) {
	        this.rotation.x = x;
	        this.rotation.y = y;
	        this.rotation.z = z;
	    }
}
