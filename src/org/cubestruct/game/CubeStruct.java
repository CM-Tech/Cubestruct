package org.cubestruct.game;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import cm.cubestruct.engine.GameItem;
import cm.cubestruct.engine.IGameLogic;
import cm.cubestruct.engine.Window;
import cm.cubestruct.engine.render.Camera;
import cm.cubestruct.engine.render.Cube;
import cm.cubestruct.engine.render.Mesh;
import cm.cubestruct.engine.render.Texture;

import org.lwjgl.BufferUtils;
import  org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.*;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.util.ArrayList;

public class CubeStruct implements IGameLogic {
	public Window window=null;
	public Camera camera=new Camera();
    private int displxInc = 0;

    private int displyInc = 0;

    private int displzInc = 0;

    private int scaleInc = 0;

    private final Renderer renderer;

    private ArrayList<GameItem> gameItems;

    public CubeStruct() {
    	camera.position.add(0, 1.5f, 0);
        renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
    	this.window=window;
    	window.gl=this;
        renderer.init(window);
        
        gameItems = new ArrayList<GameItem>();
        for(float x=-7.0f;x<=8.0f;x++){
        	for(float z=-7.0f;z<=8.0f;z++){
        GameItem gameItem = new Cube(new Vector3f(1.0f,1.0f,1.0f),"/textures/grass.png");
        gameItem.setPosition(x, 0, z);
        
        gameItems.add(gameItem);
        }
        }
        glfwSetInputMode(window.windowHandle, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    @Override
    public void input() {
        displyInc = 0;
        displxInc = 0;
        displzInc = 0;
        scaleInc = 0;
        ByteBuffer xpos = ByteBuffer.allocate(8);
		ByteBuffer ypos = ByteBuffer.allocate(8);
		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(window.windowHandle, x, y);
        
        x.rewind();
        y.rewind();

        double newX = x.get();
        double newY = y.get();
        //System.out.println(newY);
        
        camera.rotation.x=(float) Math.max(Math.min(camera.rotation.x-(newY-window.getHeight()/2.0f)/4.0f,90.0f),-90.0f);
        camera.rotation.y=(float) (camera.rotation.y-(newX-window.getWidth()/2.0f)/4.0f);
      /*  if (window.isKeyPressed(GLFW_KEY_UP)) {
        	camera.rotation.x=Math.min(camera.rotation.x+0.1f,90.0f);
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN)) {
        	camera.rotation.x=Math.max(camera.rotation.x-0.1f,-90.0f);
        } 
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
        	camera.rotation.y=camera.rotation.y+0.1f;
        } 
        if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
        	camera.rotation.y=camera.rotation.y-0.1f;
        }*/
        GLFW.glfwSetCursorPos(window.windowHandle, window.getWidth()/2.0,window.getHeight()/2.0);
    }

    @Override
    public void update(float interval) {
    	if(window.isKeyPressed(GLFW_KEY_W)){
			camera.position=camera.getPosition().add(new Vector3f(0f,0,-interval*3.0f).rotate(
					new Quaternionf().setEulerAnglesXYZ(0.0f,
							-(float) Math.toRadians(camera.getRotation().y),0.0f)));
		}
        if(window.isKeyPressed(GLFW_KEY_S)){
			camera.position=camera.getPosition().add(new Vector3f(0f,0,interval*3.0f).rotate(
					new Quaternionf().setEulerAnglesXYZ(0.0f,
							-(float) Math.toRadians(camera.getRotation().y),0.0f)));
		}
        if(window.isKeyPressed(GLFW_KEY_A)){
			camera.position=camera.getPosition().add(new Vector3f(interval*3.0f,0,0f).rotate(
					new Quaternionf().setEulerAnglesXYZ(0.0f,
							-(float) Math.toRadians(camera.getRotation().y),0.0f)));
		}
        if(window.isKeyPressed(GLFW_KEY_D)){
			camera.position=camera.getPosition().add(new Vector3f(-interval*3.0f,0,0f).rotate(
					new Quaternionf().setEulerAnglesXYZ(0.0f,
							-(float) Math.toRadians(camera.getRotation().y),0.0f)));
		}
        if(window.isKeyPressed(GLFW_KEY_SPACE)){
			camera.position=camera.getPosition().add(new Vector3f(0.0f,interval*3.0f,0f));
		}
        if(window.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT) || window.isKeyPressed(GLFW.GLFW_KEY_RIGHT_SHIFT)){
        	camera.position=camera.getPosition().add(new Vector3f(0.0f,-interval*3.0f,0f));
		}
        for (GameItem gameItem : gameItems) {
            // Update position
            Vector3f itemPos = gameItem.getPosition();
            float posx = itemPos.x + displxInc * 0.01f;
            float posy = itemPos.y + displyInc * 0.01f;
            float posz = itemPos.z + displzInc * 0.01f;
            //gameItem.setPosition(posx, posy, posz);

            // Update scale
            float scale = gameItem.getScale();
            scale += scaleInc * 0.05f;
            if (scale < 0) {
                scale = 0;
            }
            //gameItem.setScale(scale);

            // Update rotation angle
            float rotation = gameItem.getRotation().x + 1.5f;
            if (rotation > 360) {
                rotation = 0;
            }
            //gameItem.setRotation(rotation, rotation, rotation);
            
        }
    }

    @Override
    public void render() {
        renderer.render(window,gameItems,camera);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }

	@Override
	public void keyPressed(int key) {
		//System.out.println(key);
		
		// TODO Auto-generated method stub
		
	}

}
