package cm.cubestruct.engine.render;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import cm.cubestruct.engine.GameItem;


public class Cube extends GameItem {

    private final Mesh mesh;
    
    private final Vector3f position;
    
    private float scale;

    private final Vector3f rotation;

    public Cube(Vector3f size,String texturePath) throws Exception {
    	super(null);
		  // Create the Mesh
      float[] positions = new float[] {
          // V0
          -0.5f, 0.5f, 0.5f,
          // V1
          -0.5f, -0.5f, 0.5f,
          // V2
          0.5f, -0.5f, 0.5f,
          // V3
          0.5f, 0.5f, 0.5f,
          // V4
          -0.5f, 0.5f, -0.5f,
          // V5
          0.5f, 0.5f, -0.5f,
          // V6
          -0.5f, -0.5f, -0.5f,
          // V7
          0.5f, -0.5f, -0.5f,
          
          // For text coords in top face
          // V8: V4 repeated
          -0.5f, 0.5f, -0.5f,
          // V9: V5 repeated
          0.5f, 0.5f, -0.5f,
          // V10: V0 repeated
          -0.5f, 0.5f, 0.5f,
          // V11: V3 repeated
          0.5f, 0.5f, 0.5f,

          // For text coords in right face
          // V12: V3 repeated
          0.5f, 0.5f, 0.5f,
          // V13: V2 repeated
          0.5f, -0.5f, 0.5f,

          // For text coords in left face
          // V14: V0 repeated
          -0.5f, 0.5f, 0.5f,
          // V15: V1 repeated
          -0.5f, -0.5f, 0.5f,

          // For text coords in bottom face
          // V16: V6 repeated
          -0.5f, -0.5f, -0.5f,
          // V17: V7 repeated
          0.5f, -0.5f, -0.5f,
          // V18: V1 repeated
          -0.5f, -0.5f, 0.5f,
          // V19: V2 repeated
          0.5f, -0.5f, 0.5f,
      };
      for(int i=0;i<positions.length;i+=3){
    	  positions[i]=positions[i]*size.x;
    	  positions[i+1]=positions[i+1]*size.y;
    	  positions[i+2]=positions[i+2]*size.z;
      }
      float[] textCoords = new float[]{
          0.0f, 0.0f,
          0.0f, 0.5f,
          0.5f, 0.5f,
          0.5f, 0.0f,
          
          0.0f, 0.0f,
          0.5f, 0.0f,
          0.0f, 0.5f,
          0.5f, 0.5f,
          
          // For text coords in top face
          0.0f, 0.5f,
          0.5f, 0.5f,
          0.0f, 1.0f,
          0.5f, 1.0f,

          // For text coords in right face
          0.0f, 0.0f,
          0.0f, 0.5f,

          // For text coords in left face
          0.5f, 0.0f,
          0.5f, 0.5f,

          // For text coords in bottom face
          0.5f, 0.0f,
          1.0f, 0.0f,
          0.5f, 0.5f,
          1.0f, 0.5f,
      };
      int[] indices = new int[]{
          // Front face
          0, 1, 3, 3, 1, 2,
          // Top Face
          8, 10, 11, 9, 8, 11,
          // Right face
          12, 13, 7, 5, 12, 7,
          // Left face
          14, 15, 6, 4, 14, 6,
          // Bottom face
          16, 18, 19, 17, 16, 19,
          // Back face
          4, 6, 7, 5, 4, 7,};
      Texture texture = new Texture(texturePath);
      Mesh mesh = new Mesh(positions, textCoords, indices, texture);
      this.mesh = mesh;
      position = new Vector3f(0, 0, 0);
      scale = 1;
      rotation = new Vector3f(0, 0, 0);
      
	}

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }
    
    public Mesh getMesh() {
        return mesh;
    }

	@Override
	public void render() {
		mesh.render();
		
	}

	@Override
	public int shouldRender() {
		// TODO Auto-generated method stub
		return GL11.GL_TRUE;
	}
}
	
	


