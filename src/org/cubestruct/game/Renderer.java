package org.cubestruct.game;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import cm.cubestruct.engine.GameItem;
import cm.cubestruct.engine.Utils;
import cm.cubestruct.engine.Window;
import cm.cubestruct.engine.render.Camera;
import cm.cubestruct.engine.render.ShaderProgram;
import cm.cubestruct.engine.render.Transformation;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

public class Renderer {

    /**
     * Field of View in Radians
     */
    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 100.f;

    private final Transformation transformation;

    private ShaderProgram shaderProgram;

    public Renderer() {
        transformation = new Transformation();
    }

    public void init(Window window) throws Exception {
        // Create shader
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("/shaders/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("/shaders/fragment.fs"));
        shaderProgram.link();
        
        // Create uniforms for world and projection matrices and texture
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("worldMatrix");
        shaderProgram.createUniform("texture_sampler");
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, GameItem[] gameItems,Camera camera) {
        clear();

        if ( window.isResized() ) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();
       
        //GL11.glLoadMatrixf(new Matrix4f().identity().get(FloatBuffer.allocate(16)));
       // GL11.glTranslatef(camera.getPosition().x, camera.getPosition().y, camera.getPosition().z);
        // Update projection Matrix
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);
        
        shaderProgram.setUniform("texture_sampler", 0);
        // Render each gameItem
        for(GameItem gameItem : gameItems) {
            // Set world matrix for this item
            Matrix4f worldMatrix = transformation.getWorldMatrix(
                    gameItem.getPosition(),
                    gameItem.getRotation(),
                    gameItem.getScale(),camera);
            shaderProgram.setUniform("worldMatrix", worldMatrix);
            
            // Render the mes for this game item
            gameItem.render();
        }

        shaderProgram.unbind();
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
