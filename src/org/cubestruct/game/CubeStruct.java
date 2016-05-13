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

import static org.lwjgl.glfw.GLFW.*;

public class CubeStruct implements IGameLogic {
	public Window window=null;
	public Camera camera=new Camera();
    private int displxInc = 0;

    private int displyInc = 0;

    private int displzInc = 0;

    private int scaleInc = 0;

    private final Renderer renderer;

    private GameItem[] gameItems;

    public CubeStruct() {
        renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
    	this.window=window;
    	window.gl=this;
        renderer.init(window);
        
  
        GameItem gameItem = new Cube(new Vector3f(1.0f,1.0f,1.0f),"/textures/grass.png");
        gameItem.setPosition(0, 0, -6);
        gameItems = new GameItem[]{gameItem};
    }

    @Override
    public void input() {
        displyInc = 0;
        displxInc = 0;
        displzInc = 0;
        scaleInc = 0;
        if(window.isKeyPressed(GLFW_KEY_W)){
			camera.position=camera.getPosition().add(new Vector3f(0f,0,-0.01f).rotate(
					new Quaternionf().setEulerAnglesZYX(0f,
							-(float) Math.toRadians(camera.getRotation().y), 
							-(float) Math.toRadians(camera.getRotation().x))));
		}
        if(window.isKeyPressed(GLFW_KEY_S)){
			camera.position=camera.getPosition().add(new Vector3f(0f,0,0.01f).rotate(
					new Quaternionf().setEulerAnglesZYX(0f,
							-(float) Math.toRadians(camera.getRotation().y), 
							-(float) Math.toRadians(camera.getRotation().x))));
		}
        if (window.isKeyPressed(GLFW_KEY_UP)) {
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
        }
    }

    @Override
    public void update(float interval) {
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
        renderer.render(window, gameItems,camera);
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
		System.out.println(key);
		
		// TODO Auto-generated method stub
		
	}

}
