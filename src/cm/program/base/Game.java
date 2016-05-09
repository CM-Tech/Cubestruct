package cm.program.base;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import javafx.scene.paint.Color;

public class Game implements Runnable{
	public long window;
	@Override
	public void run() {
		
		init();
		
		while (GLFW.glfwWindowShouldClose(window)==GL11.GL_FALSE) {
			Color c=Color.hsb(System.currentTimeMillis()/100.0, 1.0, 0.5);
			GL11.glClearColor((float) (c.getRed()/2.0),(float) (c.getGreen()/2.0),(float) (c.getBlue()/2.0), 1);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            //GL11.glMatrixMode(GL11.GL_PROJECTION);
            //GL11.glLoadIdentity();
			GLFW.glfwPollEvents();
            GLFW.glfwSwapBuffers(window);
		}
		end();
	}
	public void init(){
		GLFW.glfwInit();
		GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		
        try {
        	GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
        	GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        	GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        	GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE); 
        	window = GLFW.glfwCreateWindow(vidmode.width(), vidmode.height(), "CubeStruct - LWJGL3", 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(window == 0) {
            throw new RuntimeException("Failed to create window");
        }
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GLFW.glfwShowWindow(window);
	}
	public void end(){
		GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
	}

}
