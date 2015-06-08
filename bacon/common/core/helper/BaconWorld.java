package minecraftwero.bacon.common.core.helper;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BaconWorld 
{
	
	public static TileEntity getTileEntity(World par0, int par1, int par2, int par3, int side)
	{
		TileEntity tile;
		if(side == 0)return tile = par0.getTileEntity(par1, par2-1, par3);
		if(side == 1)return tile = par0.getTileEntity(par1, par2+1, par3);
		if(side == 2)return tile = par0.getTileEntity(par1, par2, par3-1);
		if(side == 3)return tile = par0.getTileEntity(par1, par2, par3+1);
		if(side == 4)return tile = par0.getTileEntity(par1-1, par2, par3);
		if(side == 5)return tile = par0.getTileEntity(par1+1, par2, par3);
		return null;
	}
	
	public static boolean isSameBlock(World par0, int par1, int par2, int par3, int side, int BlockID)
	{
		
		if(side == 0)
		{
			if(par0.getBlockMetadata(par1, par2-1, par3) == BlockID)
			{
				return true;
			}
		}
		else if(side == 1)
		{
			if(par0.getBlockMetadata(par1, par2+1, par3) == BlockID)
			{
				return true;
			}
		}
		else if(side == 2)
		{
			if(par0.getBlockMetadata(par1, par2, par3-1) == BlockID)
			{
				return true;
			}
		}
		else if(side == 3)
		{
			if(par0.getBlockMetadata(par1, par2, par3+1) == BlockID)
			{
				return true;
			}
		}
		else if(side == 4)
		{
			if(par0.getBlockMetadata(par1-1, par2, par3) == BlockID)
			{
				return true;
			}
		}
		else if(side == 5)
		{
			if(par0.getBlockMetadata(par1+1, par2, par3) == BlockID)
			{
				return true;
			}
		}
		return false;
	}
	
	
	
	public static boolean isBlockAir(World par0, int par1, int par2, int par3, int side)
	{
		
		if(side == 0)
		{
			if(par0.getBlockMetadata(par1, par2-1, par3) == 0)
			{
				return true;
			}
		}
		else if(side == 1)
		{
			if(par0.getBlockMetadata(par1, par2+1, par3) == 0)
			{
				return true;
			}
		}
		else if(side == 2)
		{
			if(par0.getBlockMetadata(par1, par2, par3-1) == 0)
			{
				return true;
			}
		}
		else if(side == 3)
		{
			if(par0.getBlockMetadata(par1, par2, par3+1) == 0)
			{
				return true;
			}
		}
		else if(side == 4)
		{
			if(par0.getBlockMetadata(par1-1, par2, par3) == 0)
			{
				return true;
			}
		}
		else if(side == 5)
		{
			if(par0.getBlockMetadata(par1+1, par2, par3) == 0)
			{
				return true;
			}
		}
		return false;
	}



	public static void setThisBlockWithMeta(World par0, int par1, int par2, int par3, int side, Block blockID, int meta)
	{
		if(side == 0)
		{
			par0.setBlock(par1, par2-1, par3, blockID, meta, 3);
		}
		else if(side == 1)
		{
			par0.setBlock(par1, par2+1, par3, blockID, meta, 3);
		}
		else if(side == 2)
		{
			par0.setBlock(par1, par2, par3-1, blockID, meta, 3);
		}
		else if(side == 3)
		{
			par0.setBlock(par1, par2, par3+1, blockID, meta, 3);
		}
		else if(side == 4)
		{
			par0.setBlock(par1-1, par2, par3, blockID, meta, 3);
		}
		else if(side == 5)
		{
			par0.setBlock(par1+1, par2, par3, blockID, meta, 3);
		}
		
	}
	
	
	
	public static int getSidedBlockID(World par0, int par1, int par2, int par3, int side)
	{
		if(side == 0)return par0.getBlockMetadata(par1, par2-1, par3);
		else if(side == 1)return par0.getBlockMetadata(par1, par2+1, par3);
		else if(side == 2)return par0.getBlockMetadata(par1, par2, par3-1);
		else if(side == 3)return par0.getBlockMetadata(par1, par2, par3+1);
		else if(side == 4)return par0.getBlockMetadata(par1-1, par2, par3);
		else return par0.getBlockMetadata(par1+1, par2, par3);
	}
	
	public static int getSidedBlockMetadata(World par0, int par1, int par2, int par3, int side)
	{
		if(side == 0)return par0.getBlockMetadata(par1, par2-1, par3);
		else if(side == 1)return par0.getBlockMetadata(par1, par2+1, par3);
		else if(side == 2)return par0.getBlockMetadata(par1, par2, par3-1);
		else if(side == 3)return par0.getBlockMetadata(par1, par2, par3+1);
		else if(side == 4)return par0.getBlockMetadata(par1-1, par2, par3);
		else return par0.getBlockMetadata(par1+1, par2, par3);
	}
	
	public static void setSidedBlockMetadata(World par0, int par1, int par2, int par3, int side, int meta)
	{
		if(side == 0)par0.setBlockMetadataWithNotify(par1, par2-1, par3, meta, 3);
		else if(side == 1)par0.setBlockMetadataWithNotify(par1, par2+1, par3, meta, 3);
		else if(side == 2)par0.setBlockMetadataWithNotify(par1, par2, par3-1, meta, 3);
		else if(side == 3)par0.setBlockMetadataWithNotify(par1, par2, par3+1, meta, 3);
		else if(side == 4)par0.setBlockMetadataWithNotify(par1-1, par2, par3, meta, 3);
		else if(side == 5)par0.setBlockMetadataWithNotify(par1+1, par2, par3, meta, 3);
	}
	
	public static void setSidedBlockAndMetadata(World par0, int par1, int par2, int par3, int side, Block blockID, int meta)
	{
		if(side == 0)par0.setBlock(par1, par2-1, par3, blockID, meta, 3);
		else if(side == 1)par0.setBlock(par1, par2+1, par3, blockID, meta, 3);
		else if(side == 2)par0.setBlock(par1, par2, par3-1, blockID, meta, 3);
		else if(side == 3)par0.setBlock(par1, par2, par3+1, blockID, meta, 3);
		else if(side == 4)par0.setBlock(par1-1, par2, par3, blockID, meta, 3);
		else if(side == 5)par0.setBlock(par1+1, par2, par3, blockID, meta, 3);
	}
	
	public static int[] xCoordFacing = new int[]{0,0,0,0,-1,1};
	public static int[] yCoordFacing = new int[]{-1,1,0,0,0,0};
	public static int[] zCoordFacing = new int[]{0,0,-1,1,0,0};

}
