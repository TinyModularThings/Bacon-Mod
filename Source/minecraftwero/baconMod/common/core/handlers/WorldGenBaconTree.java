package minecraftwero.baconMod.common.core.handlers;

import java.util.Random;

import minecraftwero.baconMod.common.config.BaconItemRegister;
import minecraftwero.baconMod.common.config.BaconItems;
import minecraftwero.baconMod.common.gen.BaconTreeGen;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenBaconTree implements IWorldGenerator 
{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World par0, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		switch(par0.provider.dimensionId)
		{
			case 0:
			{
				generateOverworld(par0, chunkX, chunkZ, random);
			}
		}
	}

	public void generateOverworld(World par0, int par1, int par2, Random rand) 
	{
		for(int i = 0; i<6;i++)
		{
			if(BaconItems.baconSapling != null && BaconItems.baconMachine != null && BaconItemRegister.BaconLeaves && BaconItemRegister.BaconLog)
			{
				if(par0.getBiomeGenForCoords(par1, par2).getEnableSnow())
				{
					if(rand.nextInt(100) == 0)
					{
					    int xCoord = par1 + rand.nextInt(16)+8;
						int zCoord = par2 + rand.nextInt(16)+8;
						int yCoord = par0.getHeightValue(xCoord, zCoord);
						(new BaconTreeGen(false)).generate(par0, par0.rand, xCoord, yCoord, zCoord);
					}
				}
			}
		}
		
	}

}
