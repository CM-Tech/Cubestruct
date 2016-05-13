package cm.cubestruct.world;

import java.util.ArrayList;

import org.joml.Vector3f;

import cm.cubestruct.engine.GameItem;
import cm.cubestruct.engine.render.Cube;
import cm.cubestruct.engine.render.IRenderObject;

public class Chunk {
	public Block[][][] blocks;
	public Chunk(int cx,int cz,Generator g){
		blocks=new Block[16][World.WORLD_HEIGHT][16];
		int wX=cx*16;
		int wZ=cz*16;
		for(int y=0;y<World.WORLD_HEIGHT;y++){
	        for(int x=0;x<16;x++){
	        	for(int z=0;z<16;z++){
	       Block block = g.genBlock(wX+x, y, wZ+z, 0);
	        //block.setPosition(x+8.0f, y, z+8.0f);
	        
	        blocks[x][y][z]=block;
	        }
	        }
	        }
	}
	public void render(){
		for(int y=0;y<World.WORLD_HEIGHT;y++){
	        for(int x=0;x<16;x++){
	        	for(int z=0;z<16;z++){
	        	blocks[x][y][z].render();
	        	}
	        }
		}
	}
	public ArrayList<GameItem> renderItems(){
		ArrayList<GameItem> c=new ArrayList<GameItem>();
		for(int y=0;y<World.WORLD_HEIGHT;y++){
	        for(int x=0;x<16;x++){
	        	for(int z=0;z<16;z++){
	        	c.add(blocks[x][y][z].model);
	        	}
	        }
		}
		return c;
	}
	public void cleanup() {
		for(int y=0;y<World.WORLD_HEIGHT;y++){
	        for(int x=0;x<16;x++){
	        	for(int z=0;z<16;z++){
	        	blocks[x][y][z].model.cleanup();
	        	}
	        }
		}
      
    }
}
