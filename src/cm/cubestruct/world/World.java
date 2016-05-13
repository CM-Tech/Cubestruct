package cm.cubestruct.world;

import java.util.ArrayList;

import cm.cubestruct.engine.GameItem;
import cm.cubestruct.engine.render.IRenderObject;

public class World {
	public Chunk[][] chunks=new Chunk[16][16];
	public Generator gen=new SuperFlatGen();
	public static final int WORLD_HEIGHT=64;
	public World(Generator gen2){
		gen=gen2;
		genChunk(8,8);
	}
	public Chunk getChunk(int cX,int cZ){
		return chunks[cX][cZ];
	}
	private void genChunk(int cX,int cZ){
		if(chunks[cX][cZ]==null){
			chunks[cX][cZ]=new Chunk(cX-8,cZ-8,gen);
		}
	}
	public void render(){
		 for(int x=0;x<16;x++){
	        	for(int z=0;z<16;z++){
			if(chunks[x][z]!=null){
				chunks[x][z].render();
			}
	        	}
		}
	}
	public ArrayList<GameItem> renderItems(){
		ArrayList<GameItem> c=new ArrayList<GameItem>();
		for(int x=0;x<16;x++){
        	for(int z=0;z<16;z++){
		if(chunks[x][z]!=null){
			c.addAll(chunks[x][z].renderItems());
		}
        	}
	}
		return c;
	}
	public void cleanup() {
		for(int x=0;x<16;x++){
        	for(int z=0;z<16;z++){
		if(chunks[x][z]!=null){
			chunks[x][z].cleanup();
		}
        	}
	}
      
    }
}
