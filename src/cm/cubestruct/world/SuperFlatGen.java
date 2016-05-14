package cm.cubestruct.world;

import cm.cubestruct.block.Block;
import cm.cubestruct.block.DirtBlock;
import cm.cubestruct.block.EmptyBlock;
import cm.cubestruct.block.GrassBlock;

public class SuperFlatGen implements Generator {

	@Override
	public Block genBlock(int wX, int wY,int wZ, int seed) {
		Block b=wY>6?null:(wY>5?new GrassBlock(wX, wY,wZ):new DirtBlock(wX,wY,wZ));//new EmptyBlock(wX, wY,wZ):(wY>5?new GrassBlock(wX, wY,wZ):new DirtBlock(wX,wY,wZ));
		return b;
	}

	@Override
	public int maxHeight(int wX, int wZ, int seed) {
		// TODO Auto-generated method stub
		return 7;
	}

}
