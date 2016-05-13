package cm.cubestruct.engine;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.util.ArrayList;

public class Window {

	private final String title;

	private int width;

	private int height;

	public long windowHandle;

	private GLFWErrorCallback errorCallback;

	private GLFWKeyCallback keyCallback;

	private GLFWWindowSizeCallback windowSizeCallback;
	public IGameLogic gl;

	private boolean resized;

	private boolean vSync;
	private ArrayList<Integer> keysPressed = new ArrayList<Integer>();

	public Window(String title, int width, int height, boolean vSync) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.vSync = vSync;
		this.resized = false;
	}

	public void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (glfwInit() == GL_FALSE) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}

		glfwDefaultWindowHints(); // optional, the current window hints are
									// already the default
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden
												// after creation
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

		// Create the window
		windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
		if (windowHandle == NULL) {
			throw new RuntimeException("Failed to create the GLFW window");
		}

		// Setup resize callback
		glfwSetWindowSizeCallback(windowHandle, windowSizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int width, int height) {
				Window.this.width = width;
				Window.this.height = height;
				Window.this.setResized(true);
			}
		});

		// Setup a key callback. It will be called every time a key is pressed,
		// repeated or released.
		glfwSetKeyCallback(windowHandle, keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {

				if (action == GLFW_PRESS) {
					//System.out.println("pre");
					
					if (!Window.this.keysPressed.contains(new Integer(key))) {
						Window.this.keysPressed.add(new Integer(key));
					}
					if(Window.this.gl!=null){
						Window.this.gl.keyPressed(key);
					}
				}
				if (action == GLFW_REPEAT) {
					//System.out.println("rep");
					if (!Window.this.keysPressed.contains(new Integer(key))) {
						Window.this.keysPressed.add(new Integer(key));
					}
					if(Window.this.gl!=null){
						Window.this.gl.keyPressed(key);
					}
				}
				if (action == GLFW.GLFW_RELEASE) {
					System.out.println("rel");
					if (Window.this.keysPressed.contains(new Integer(key))) {
						Window.this.keysPressed.remove(new Integer(key));
					}
				}
//System.out.println(Window.this.keysPressed);
				if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
					glfwSetWindowShouldClose(window, GL_TRUE);
				}
				if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
					glfwSetWindowShouldClose(window, GL_TRUE);
				}
			}
		});

		// Get the resolution of the primary monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		// Center our window
		glfwSetWindowPos(windowHandle, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);

		// Make the OpenGL context current
		glfwMakeContextCurrent(windowHandle);

		if (isvSync()) {
			// Enable v-sync
			glfwSwapInterval(1);
		}

		// Make the window visible
		glfwShowWindow(windowHandle);

		GL.createCapabilities();
		

		// Set the clear color
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}

	public ArrayList<Integer> keysPressed() {
		/*int[] k = new int[this.keysPressed.size()];
		for (int i = 0; i < this.keysPressed.size(); i++) {
			k[i] = this.keysPressed.get(i);
		}*/
		return this.keysPressed;
	}

	public void setClearColor(float r, float g, float b, float alpha) {
		glClearColor(r, g, b, alpha);
	}

	public boolean isKeyPressed(int keyCode) {
		return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS;
	}

	public boolean windowShouldClose() {
		return glfwWindowShouldClose(windowHandle) == GL_TRUE;
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isResized() {
		return resized;
	}

	public void setResized(boolean resized) {
		this.resized = resized;
	}

	public boolean isvSync() {
		return vSync;
	}

	public void setvSync(boolean vSync) {
		this.vSync = vSync;
	}

	public void update() {
		glfwSwapBuffers(windowHandle);
		glfwPollEvents();
	}
}
