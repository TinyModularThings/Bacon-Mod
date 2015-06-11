package minecraftwero.baconMod.common.fluid.blocks;

import java.util.Random;

import minecraftwero.baconMod.common.config.BaconItems;
import minecraftwero.baconMod.common.core.helper.BaconWorld;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;



public class BlockLiquidBacon extends Block implements IFluidBlock {

	public Random rand = new Random();
	
	public BlockLiquidBacon(int id) 
	{
		super(Material.water);
	}

	@Override
	public Fluid getFluid() 
	{
		return BaconItems.liquidBacon;
	}

	@Override
	public FluidStack drain(World world, int x, int y, int z, boolean doDrain) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		int realMeta = 10 - meta; // reversed meta
		FluidStack fluid = new FluidStack(BaconItems.liquidBacon, 100 * realMeta);
		if(doDrain)
		{
			world.setBlockToAir(x, y, z);
		}
		
		return fluid.copy();
	}

	@Override
	public boolean canDrain(World world, int x, int y, int z) 
	{
		return true;
	}

	
	//TODO add a expand Mechanic
	//TODO Make a equalizing effect
	
	public void flow(World par0, int par1, int par2, int par3)
	{
		if(canFluidGoDown(par0, par1, par2, par3))
		{
			moveDown(par0, par1, par2, par3);
		}
		else
		{
			if(fluidCanExpand(par0, par1, par2, par3))
			{
				for(int i = 2;i<5;i++)
				{
					if(rand.nextInt(4)+2 == i)
					{
						if(canExpandToSide(par0, par1, par2, par3, i))
						{
							expandFluid(par0, par1, par2, par3, i);
						}
					}
				}
			}
		}
	}
	
	public void expandFluid(World par0, int par1, int par2, int par3, int side)
	{
		if(BaconWorld.getSidedBlockID(par0, par1, par2, par3, side) == 0)
		{
			CreateBacon(par0, par1, par2, par3, side);
			return;
		}
		else
		{
			if(BaconWorld.isSameBlock(par0, par1, par2, par3, side, this.blockID))
			{
				addBacon(par0, par1, par2, par3, side);
				return;
			}
			else
			{
				CreateBacon(par0, par1, par2, par3, side);
				return;
			}
		}
	}
	
	public void removeOneBacon(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if(meta >= 9)
		{
			world.setBlockToAir(x,y,z);
		}
		world.setBlockMetadataWithNotify(x,y,z,meta+1, 3);
	}
	
	public void addBacon(World par0, int par1, int par2, int par3, int side)
	{
		int metaside = BaconWorld.getSidedBlockMetadata(par0, par1, par2, par3, side);
		BaconWorld.setSidedBlockMetadata(par0, par1, par2, par3, side, metaside-1);
		int meta = par0.getBlockMetadata(par1, par2, par3);
		if(meta >= 9)
		{
			par0.setBlock(par1, par2, par3, 0);
		}
		else
		{
			par0.setBlockMetadataWithNotify(par1, par2, par3, meta+1, 3);
		}
		
		
	}
		
	public void CreateBacon(World par0, int par1, int par2, int par3, int i)
	{
		int b = par0.getBlockMetadata(par1, par2, par3);
		if(b >= 9)
		{
			par0.setBlock(par1, par2, par3, 0);
		}
		par0.setBlockMetadataWithNotify(par1, par2, par3, b+1, 3);
		BaconWorld.setSidedBlockAndMetadata(par0, par1, par2, par3, i, this.blockID, 9);
		
	}
	
	public boolean canExpandToSide(World par0, int par1, int par2, int par3, int i) 
	{
		if(BaconWorld.getSidedBlockID(par0, par1, par2, par3, i) == 0)
		{
			return true;
		}
		else
		{
			if(BaconWorld.isSameBlock(par0, par1, par2, par3, i, this.blockID))
			{
				if(BaconWorld.getSidedBlockMetadata(par0, par1, par2, par3, i) > 0)
				{
					return true;
				}
			}
			else
			{
				int x = par1 + BaconWorld.xCoordFacing[i];
				int y = par2 + BaconWorld.yCoordFacing[i];
				int z = par3 + BaconWorld.zCoordFacing[i];
				if(Block.blocksList[BaconWorld.getSidedBlockID(par0, par1, par2, par3, i)].isBlockReplaceable(par0, x, y, z))
				{
					return true;
				}
			}
		}
		
		return false;
	}



	public boolean fluidCanExpand(World par0, int par1, int par2, int par3) 
	{
		int counter = 0;
		for(int side = 2; side < 5;side++)
		{
			if(BaconWorld.getSidedBlockID(par0, par1, par2, par3, side) == 0)
			{
				counter++;
			}
			else
			{
				if(BaconWorld.isSameBlock(par0, par1, par2, par3, side, this.blockID))
				{
					if(BaconWorld.getSidedBlockMetadata(par0, par1, par2, par3, side) > 0)
					{
						counter++;
					}
				}
				else
				{
					int x = par1 + BaconWorld.xCoordFacing[side];
					int y = par2 + BaconWorld.yCoordFacing[side];
					int z = par3 + BaconWorld.zCoordFacing[side];
					if(Block.blocksList[BaconWorld.getSidedBlockID(par0, par1, par2, par3, side)].isBlockReplaceable(par0, x, y, z))
					{
						counter++;
					}
				}
			}
		}
		if(counter > 0)
		{
			return true;
		}
		return false;
	}

	public void moveDown(World par0, int x, int y, int z) 
	{
		if(par0.getBlockId(x, y-1, z) == this.blockID)
		{
			int downmeta = par0.getBlockMetadata(x, y-1, z);
			if(downmeta > 0)
			{
				MoveOneLiquidInAnotherDirection(par0, x, y, z, 0);
			}
		}
		else
		{
			int meta = par0.getBlockMetadata(x, y, z);
			BaconWorld.setSidedBlockAndMetadata(par0, x, y, z, 0, this.blockID, meta);
			par0.setBlockToAir(x, y, z);
			
		}
	}

	public boolean canFluidGoDown(World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y-1, z) == 0 || world.getBlockId(x, y, z) == this.blockID || Block.blocksList[world.getBlockId(x, y-1, z)].blockMaterial.isReplaceable() || Block.blocksList[world.getBlockId(x, y-1, z)].isBlockReplaceable(world, x, y, z))
		{
			return true;
		}
		return false;
	}

	
	public void MoveOneLiquidInAnotherDirection(World world, int x, int y, int z, int side)
	{
		int downmeta = world.getBlockMetadata(x, y-1, z);
		int ownMeta = world.getBlockMetadata(x, y, z);
		
		BaconWorld.setSidedBlockMetadata(world, x, y-1, z, side, downmeta-1);
		if(ownMeta >= 9)
		{
			world.setBlockToAir(x, y, z);
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, ownMeta+1, 3);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World par1, int par2, int par3, int par4, Entity par5)
	{
		
		if (rand.nextInt(150) != 0)
		{
			return;
		}
		
		if(par1.isRemote)
		{
			return;
		}
		
		if(par5 != null)
		{
			if(par5 instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) par5;
				FoodStats food = player.getFoodStats();
				
				if (food.needFood())
				{
					food.addStats(5, 5);
					this.removeOneBacon(par1, par2, par3, par4);
				}
					
			}
		}
			

	
	}

	@Override
	public float getFilledPercentage(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}
}
