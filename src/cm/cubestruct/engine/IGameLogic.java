package cm.cubestruct.engine;

import cm.cubestruct.engine.Window;

public interface IGameLogic {
	
    void init(Window window) throws Exception;

    void input();
    
    void keyPressed(int key);

    void update(float interval);
    
    void render();
    void cleanup();
    
}