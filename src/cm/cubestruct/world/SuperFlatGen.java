package cm.cubestruct.world;

import cm.cubestruct.block.DirtBlock;
import cm.cubestruct.block.EmptyBlock;
import cm.cubestruct.block.GrassBlock;

public class SuperFlatGen implements Generator {

	@Override
	public Block genBlock(int wX, int wY,int wZ, int seed) {
		Block b=wY>6?new EmptyBlock(wX, wY,wZ):(wY>5?new GrassBlock(wX, wY,wZ):new DirtBlock(wX,wY,wZ));
		return b;
	}

}
