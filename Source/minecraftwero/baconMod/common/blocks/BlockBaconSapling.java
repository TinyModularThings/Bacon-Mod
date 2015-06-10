package minecraftwero.baconMod.common.blocks;
import java.util.Random;

import minecraftwero.baconMod.common.config.BaconItemRegister;
import minecraftwero.baconMod.common.config.BaconItems;
import minecraftwero.baconMod.common.gen.BaconBigTreeGen;
import minecraftwero.baconMod.common.gen.BaconTreeGen;
import minecraftwero.baconMod.common.lib.BaconTextures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import minecraftwero.baconMod.Bacon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockBaconSapling extends BlockFlower implements IPlantable
{
	public BlockBaconSapling(int i)
	{
		super(i);
		float f = 0.4F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setCreativeTab(Bacon.baconMod);
	}
	
	public void updateTick(World world, int i, int j, int k, Random random)
	{
		if(world.isRemote)
		{
			return;
		}
		super.updateTick(world, i, j, k, random);

		if(world.getBlockLightValue(i, j + 1, k) >= 9 && random.nextInt(7) == 0)
		{
			growTree(world, i, j, k, random);
		}
	}
	
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && canBlockStay(par1World, par2, par3, par4);
    }
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Block soil = blocksList[par1World.getBlockMetadata(par2, par3 - 1, par4)];
        return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && 
                (soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this));
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(BaconTextures.baconSapling);
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	public void growTree(World world, int i, int j, int k, Random random)
	{
		if(BaconItemRegister.BaconLeaves && BaconItemRegister.BaconLog && BaconItems.baconMachine != null)
		{
			world.setBlock(i, j, k, 0);
			Object obj = null;
			obj = new BaconTreeGen(false);
			
//			FIXME Error can not find the bug.
			if(random.nextInt(1000) == 0)
			{
				obj = new BaconBigTreeGen(false);
			}
			
			if(!((WorldGenerator) (obj)).generate(world, random, i, j, k))
			{
				world.setBlock(i, j, k, blockIcon, 0, 3);
			}
		}
	}

	
	public EnumPlantType getPlantType(World world, int x, int y, int z) 
	{
		return EnumPlantType.Plains;
	}

	
	public IIcon getPlantID(World world, int x, int y, int z) 
	{
		return this.blockIcon;
	}

	public int getPlantMetadata(World world, int x, int y, int z)
	{
		return 0;
	}
	
	
}