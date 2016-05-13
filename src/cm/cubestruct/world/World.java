package cm.cubestruct.world;

public class World {
	public Chunk[][] chunks=new Chunk[16][16];
	public Generator gen=new SuperFlatGen();
	public World(Generator gen2){
		gen=gen2;
	}
	private void genChunk(int cX,int cZ){
		
	}
}
