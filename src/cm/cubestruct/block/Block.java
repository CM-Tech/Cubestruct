package cm.cubestruct.block;

import org.joml.Vector3f;

import cm.cubestruct.engine.GameItem;
import cm.cubestruct.engine.render.Cube;
import cm.cubestruct.engine.render.IRenderObject;

public class Block {
	private final Vector3f position;
	public GameItem model;
	public Block(int bX,int bY,int bZ,String texture){
		position=new Vector3f(bX,bY,bZ);
		try {
			Cube c=new Cube(new Vector3f(1.0f), texture);
			c.setPosition(bX, bY, bZ);
			model=c;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Block(int bX,int bY,int bZ){

			this( bX, bY, bZ,"/textures/white.png");

		
	}
	public void render(){
		model.render();
	}
}
