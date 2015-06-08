package minecraftwero.baconMod.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minecraftwero.baconMod.common.config.BaconItemRegister;
import minecraftwero.baconMod.common.config.BaconItems;
import minecraftwero.baconMod.common.lib.BaconGuiIDs;
import minecraftwero.baconMod.common.lib.BaconTextures;
import minecraftwero.baconMod.common.tile.BaconCake;
import minecraftwero.baconMod.common.tile.BaconChest;
import minecraftwero.baconMod.common.tile.BaconLeave;
import minecraftwero.baconMod.common.tile.BaconLog;
import minecraftwero.baconMod.common.tile.BaconMaker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

import com.minecraftwero.bacon.Bacon;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockBaconMaker extends BlockContainer implements IShearable
{
	private IIcon[] baconMaker = new IIcon[6];
	private IIcon bacon;
	private IIcon baconCookedBlock;
	private IIcon[] baconLog = new IIcon[2];
	private IIcon[] baconLeaves = new IIcon[3];
    private IIcon plank;
    private IIcon[] chest = new IIcon[6];
    private IIcon[] sand = new IIcon[4];
	int[] log;
    
	public Random rand = new Random();
	
	public BlockBaconMaker(String name, String...textures) 
	{
		super( Material.sponge);
		this.setTickRandomly(true);
		this.setCreativeTab(Bacon.baconMod);
		registerTools();
	}
	
	
	private void registerTools()
	{
		setHarvestLevel("pickaxe", 1);
		setHarvestLevel("axe", 0);
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1) 
	{
		for(int i = 0; i<BaconTextures.baconMaker.length;i++)
		{
			baconMaker[i] = par1.registerIcon(BaconTextures.baconMaker[i]);
		}
		bacon = par1.registerIcon(BaconTextures.baconBlock);
		baconCookedBlock = par1.registerIcon(BaconTextures.cookedBaconBlock);
		plank = par1.registerIcon(BaconTextures.baconPlanks);
		for(int i = 0; i<BaconTextures.baconChest.length;i++)
		{
			chest[i] = par1.registerIcon(BaconTextures.baconChest[i]);
		}
		for(int i = 0;i<2;i++)
		{
			baconLog[i] = par1.registerIcon(BaconTextures.baconLog[i]);
		}
		for(int i = 0;i<3;i++)
		{
			baconLeaves[i] = par1.registerIcon(BaconTextures.baconLeaves[i]);
		}
		for(int i = 0;i<4;i++)
		{
			sand[i] = par1.registerIcon(BaconTextures.baconSandwitch[i]);
		}
		
	}

	


	@Override
	public float getBlockHardness(World par0, int par1, int par2, int par3) 
	{
		int meta = par0.getBlockMetadata(par1, par2, par3);
		
		if(meta == 0)
		{
			return 3.5F;
		}
		else if(meta == 1)
		{
			return 1.0F;
		}
		else if(meta == 2)
		{
			return 1.5F;
		}
		else if(meta == 3)
		{
			return 1.0F;
		}
		else if(meta == 4)
		{
			return 2.0F;
		}
		else if(meta == 5)
		{
			return 0.5F;
		}
		else if(meta == 6)
		{
			return 1.5F;
		}
		else if(meta == 7)
		{
			return 1.0F;
		}
		else return 0.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) 
	{
		if(par2 == 0)
		{
			if(par1 == ForgeDirection.NORTH.getOpposite().ordinal())return baconMaker[2];
			else if(par1 == ForgeDirection.WEST.ordinal())return baconMaker[3];
			else if(par1 == ForgeDirection.UP.ordinal())return baconMaker[1];
			else return baconMaker[4];
		}
		else if(par2 == 1)
		{
			return bacon;
		}	
		else if(par2 == 2)
		{ 
			return baconCookedBlock; 
		}
		else if(par2 == 4)
		{
			if(par1 == ForgeDirection.UP.ordinal())return baconLog[0];
			else if(par1 == ForgeDirection.DOWN.ordinal())return baconLog[0];
			else return baconLog[1];
		}
		else if (par2 == 5)
		{
			return baconLeaves[1];
		}
		else if(par2 == 6)
		{
			return chest[par1];
		}
		else if(par2 == 7)
		{
			return plank;
		}
		else return null;	

	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	int meta = par1World.getBlockMetadata(par2, par3, par4);
    	if(meta == 0)
    	{
        	BaconMaker tile = (BaconMaker) par1World.getTileEntity(par2, par3, par4);
        	if(tile != null && tile.fuel > 0)
        	{
                int l = tile.getFacing();
                float f = (float)par2 + 0.5F;
                float f1 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
                float f2 = (float)par4 + 0.5F;
                float f3 = 0.52F;
                float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

                if (l == 4)
                {
                    par1World.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                    par1World.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                }
                else if (l == 5)
                {
                    par1World.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                    par1World.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                }
                else if (l == 2)
                {
                    par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                    par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                }
                else if (l == 3)
                {
                    par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                    par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                }
        	}
    	}
    }

	@Override
	public TileEntity createTileEntity(World world, int metadata) 
	{
		if(metadata == 0)return new BaconMaker();
		if(metadata == 3)return new BaconCake();
		if(metadata == 4)return new BaconLog();
		if(metadata == 5)return new BaconLeave();
		if(metadata == 6)return new BaconChest();
		return null;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		if(meta == 0)
		{
			BaconMaker tile = (BaconMaker) par1IBlockAccess.getTileEntity(par2, par3, par4);
			if(tile != null)
			{
			 	if(tile.facing == par5)
			 	{
			 		if(tile.fuel > 0)
			 		{
			 			return baconMaker[5];
			 		}
			 		else 
			 		{
			 			return baconMaker[2];
			 		}
			 	}
			 	else if(par5 == 0)return baconMaker[0];
			 	else if(par5 == 1)return baconMaker[1];
			 	else if(ForgeDirection.getOrientation(tile.facing).getOpposite().ordinal() == ForgeDirection.SOUTH.ordinal())return baconMaker[3];
			 	else return baconMaker[4];
			}
			else
			{
				return null;
			}
		}
		else if(meta == 1)
		{
			return bacon;
		}
		else if (meta == 2)
		{
			return baconCookedBlock;
		}
		else if(meta == 3)
		{
			BaconCake tile = (BaconCake)par1IBlockAccess.getTileEntity(par2, par3, par4);
			if(par5 == 0)
			{
				return sand[0];
			}
			else if(par5 == 1)
			{
				return sand[1];
			}
			else if(par5 == tile.facing)
			{
				if(tile.getEatingState() > 0)
				{
					return sand[3];
				}
				else
				{
					return sand[2];
				}
			}
			else
			{
				return sand[2];
			}
		}
		else if (meta == 4)
		{
			BaconLog tile = (BaconLog)par1IBlockAccess.getTileEntity(par2, par3, par4);
			if(par5 == tile.getFacing())return baconLog[0];
			else if(par5 == ForgeDirection.getOrientation(tile.getFacing()).getOpposite().ordinal())return baconLog[0];
			else return baconLog[1];
		}
		else if (meta == 5)
		{
			BaconLeave tile = (BaconLeave)par1IBlockAccess.getTileEntity(par2, par3, par4);
			if(tile.getGroth() >= 6500 && tile.getGroth() <= 9000)
			{
				return baconLeaves[1];
			}
			else if(tile.getGroth() > 9000)
			{
				return baconLeaves[2];
			}
			else
			{
				return baconLeaves[0];
			}
		}
		else if(meta == 6)
		{
			BaconChest tile = (BaconChest)par1IBlockAccess.getTileEntity(par2, par3, par4);
			if(par5 == tile.getFacing())return chest[0];
			else if(par5 == 1) return chest[1];
			else if(par5 == 0)return chest[2];
			else return chest[par5];
		}
		else if(meta == 7)
		{
			return plank;
		}
		else
		{
			return null;
		}
	}

	public void breakBlock(World par1, int par2, int par3, int par4, Block par5, int par6)
	{
		updateStepSound(par1, par2, par3, par4);
		int meta = par1.getBlockMetadata(par2, par3, par4);
		if(meta == 1)
		{
			this.dropBlockAsItem(par1, par2, par3, par4, new ItemStack(BaconItems.rawBacon, 9));
		}
		else if(meta == 0)
		{
			this.dropBlockAsItem(par1, par2, par3, par4, new ItemStack(BaconItems.baconMachine, 1, 0));
			dropInventory(par1, par2, par3, par4);
		}
		else if(meta == 2)
		{
			this.dropBlockAsItem(par1, par2, par3, par4, new ItemStack(BaconItems.cookedBacon, 9));
		}
		else if(meta == 5)
		{
			BaconLeave bl = (BaconLeave) par1.getTileEntity(par2, par3, par4);
			
			if(bl.getGroth() >= 6500 && bl.getGroth() <= 9000)
			{
				this.dropBlockAsItem(par1, par2, par3, par4, new ItemStack(BaconItems.rawBacon, 1 + rand.nextInt(3)));
			}
			
			if(bl.getGroth() > 9000)
			{
				this.dropBlockAsItem(par1, par2, par3, par4, new ItemStack(BaconItems.rottedBacon, 1+ rand.nextInt(2)));
			}
			
			if(par1.rand.nextInt(1000) == 0 || bl.getGroth() == 10001)
			{
				this.dropBlockAsItem(par1, par2, par3, par4, new ItemStack(BaconItems.baconSapling, 1));
			}
			
			
		}
		else if(meta == 4)
		{
			this.dropBlockAsItem(par1, par2, par3, par4, new ItemStack(this, 1, 4));
		}
		else if(meta == 6)
		{
			this.dropBlockAsItem(par1, par2, par3, par4, new ItemStack(this, 1, 6));
			dropInventory(par1, par2, par3, par4);
		}
		else if(meta == 7)
		{
			this.dropBlockAsItem(par1, par2, par3, par4, new ItemStack(this, 1, 7));
		}
		super.breakBlock(par1, par2, par3, par4, par5, par6);
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List par3) 
	{
		if(BaconItemRegister.BaconMaker)
		{
			par3.add(new ItemStack(item, 1, 0));
		}
		if(BaconItemRegister.RawBaconBlock)
		{
			par3.add(new ItemStack(item, 1, 1));
		}
		if(BaconItemRegister.CookedBaconBlock)
		{
			par3.add(new ItemStack(item, 1, 2));
		}
		if(BaconItemRegister.BaconSandwichBlock)
		{
			par3.add(new ItemStack(item, 1, 3));
		}
		if(BaconItemRegister.BaconLog)
		{
			par3.add(new ItemStack(item, 1, 4));
		}
		if(BaconItemRegister.BaconLeaves)
		{
			par3.add(new ItemStack(item, 1, 5));
		}
		if(BaconItemRegister.baconChest)
		{
			par3.add(new ItemStack(item, 1, 6));
		}
		if(BaconItemRegister.BaconPlanks)
		{
			par3.add(new ItemStack(item, 1, 7));
		}
	}

	//Start
	@Override
	public int quantityDropped(Random par1Random) 
	{
		return 0;
	}

	@Override
	public int damageDropped(int par1) 
	{
		return 0;
	}
	
	
	@Override
	public boolean isLeaves(IBlockAccess world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 5)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isWood(IBlockAccess world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) 
	{
		return false;
	}
	 
	
	//End


	public boolean onBlockActivated(World par1, int par2, int par3, int par4, EntityPlayer par5, int par6, float par7, float par8, float par9)
	{
		updateStepSound(par1, par2, par3, par4);
		if(par5.isSneaking())
		{
			return false;
		}
		int meta = par1.getBlockMetadata(par2, par3, par4);
		if(meta == 0)
		{
			BaconMaker tile = (BaconMaker) par1.getTileEntity(par2, par3, par4);
			if(tile != null)
			{
				par5.openGui(Bacon.instance, BaconGuiIDs.baconMaker, par1, par2, par3, par4);
				return true;
			}
		}
		else if(meta == 3)
		{
			if(par5.getFoodStats().needFood())
			{
				this.eatCake(par1, par2, par3, par4, par5);
				return true;
			}
		}
		else if(meta == 6)
		{
			BaconChest tile = (BaconChest) par1.getTileEntity(par2, par3, par4);
			if(tile != null)
			{
				par5.openGui(Bacon.instance, BaconGuiIDs.baconChest, par1, par2, par3, par4);
				return true;
			}
		}
		return false;
	}

	@Override
	public void onBlockPlacedBy(World par1, int par2, int par3, int par4, EntityLivingBase par5, ItemStack par6) 
	{

		TileEntity tile = par1.getTileEntity(par2, par3, par4);
		int facing = MathHelper.floor_double(par5.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int var8 = Math.round(par5.rotationPitch);
		int rotation = 0;
        if (var8 >= 65)
        {
        	rotation = 1;
        }
        else if (var8 <= -65)
        {
        	rotation = 0;
        }
        else
        {
            if (facing == 0) {
                rotation = ForgeDirection.NORTH.ordinal();
            }
            else if (facing == 1) {
                rotation = ForgeDirection.EAST.ordinal();
            }
            else if (facing == 2) {
                rotation = ForgeDirection.SOUTH.ordinal();
            }
            else if (facing == 3) {
                rotation = ForgeDirection.WEST.ordinal();
            }
            else
            {
            	rotation = ForgeDirection.NORTH.ordinal();
            }
        }

		

        if(tile != null && tile instanceof BaconMaker)
        {
        	if(rotation == 1 || rotation == 0)
        	{
        		rotation = 2;
        	}
        	BaconMaker bot = (BaconMaker) tile;
        	bot.setFacing(rotation);
        }
        
        if(tile != null && tile instanceof BaconChest)
        {
        	if(rotation == 1 || rotation == 0)
        	{
        		rotation = 2;
        	}
        	BaconChest bot = (BaconChest) tile;
        	bot.setFacing(rotation);
        }
        
        if(tile != null && tile instanceof BaconCake)
        {
        	BaconCake bc = (BaconCake) tile;
        	if(rotation == 0 || rotation == 1)
        	{
        		rotation = 2;
        	}
        	bc.setFacing(rotation);
        	FMLLog.getLogger().info("Rotation: "+rotation);
        	
        }
        
        if(tile != null && tile instanceof BaconLog)
        {
        	BaconLog bl = (BaconLog) tile;
        	bl.setFacing(rotation);
        }
        
        this.updateStepSound(par1, par2, par3, par4);
        
	}
	
	
	
	
	
	public void updateTick(World world, int i, int j, int k, Random random)
    {
		super.updateTick(world, i, j, k, random);
		notifyNeighbors(world, i, j, k);
		world.scheduleBlockUpdate(i, j, k, this, tickRate());
		if(world.getBlockMetadata(i, j, k) == 7)
		{
			decayLeaves(world, i, j, k);
		}
		
    }
	
	
	public void decayLeaves(World par0, int par2, int par3, int par4)
	{
        if (!par0.isRemote)
        {
            int l = par0.getBlockMetadata(par2, par3, par4);

            if ((l & 8) != 0 && (l & 4) == 0)
            {
                byte b0 = 4;
                int i1 = b0 + 1;
                byte b1 = 32;
                int j1 = b1 * b1;
                int k1 = b1 / 2;

                if (this.log == null)
                {
                    this.log = new int[b1 * b1 * b1];
                }

                int l1;

                if (par0.checkChunksExist(par2 - i1, par3 - i1, par4 - i1, par2 + i1, par3 + i1, par4 + i1))
                {
                    int i2;
                    int j2;
                    int k2;

                    for (l1 = -b0; l1 <= b0; ++l1)
                    {
                        for (i2 = -b0; i2 <= b0; ++i2)
                        {
                            for (j2 = -b0; j2 <= b0; ++j2)
                            {
                                k2 = par0.getBlockMetadata(par2 + l1, par3 + i2, par4 + j2);

                                Block block = Block.blocksList[k2];

                                if (block != null && block.canSustainLeaves(par0, par2 + l1, par3 + i2, par4 + j2))
                                {
                                    this.log[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
                                }
                                else if (block != null && block.isLeaves(par0, par2 + l1, par3 + i2, par4 + j2))
                                {
                                    this.log[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
                                }
                                else
                                {
                                    this.log[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
                                }
                            }
                        }
                    }

                    for (l1 = 1; l1 <= 4; ++l1)
                    {
                        for (i2 = -b0; i2 <= b0; ++i2)
                        {
                            for (j2 = -b0; j2 <= b0; ++j2)
                            {
                                for (k2 = -b0; k2 <= b0; ++k2)
                                {
                                    if (this.log[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1)
                                    {
                                        if (this.log[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
                                        {
                                            this.log[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.log[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
                                        {
                                            this.log[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.log[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
                                        {
                                            this.log[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.log[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
                                        {
                                            this.log[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.log[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2)
                                        {
                                            this.log[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
                                        }

                                        if (this.log[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
                                        {
                                            this.log[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                l1 = this.log[k1 * j1 + k1 * b1 + k1];

                if (l1 >= 0)
                {
                    ;
                }
                else
                {
                    this.removeLeaves(par0, par2, par3, par4);
                }
            }
        }
	}
	
    private void removeLeaves(World par1World, int par2, int par3, int par4)
    {
        par1World.setBlockToAir(par2, par3, par4);
        BaconLeave tile = (BaconLeave)par1World.getTileEntity(par2, par3, par4);
		if(tile.getGroth() >= 6500 && tile.getGroth() <= 9000)
		{
			this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(BaconItems.rawBacon, 1 + rand.nextInt(3)));
		}
		
		if(tile.getGroth() > 9000)
		{
			this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(BaconItems.rottedBacon, 1+ rand.nextInt(2)));
		}
        
        if(rand.nextInt(1000) == 0 ||tile.getGroth() > 10000)
        {
        	this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(BaconItems.baconSapling));
        }
        par1World.removeTileEntity(par2 , par3 ,par4);    }
	
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
		notifyNeighbors(world, i, j, k);
    }
	
	public void notifyNeighbors(World world, int i, int j, int k)
	{
		world.notifyBlocksOfNeighborChange(i, j, k, this);
		world.notifyBlocksOfNeighborChange(i, j - 1, k, this);
        world.notifyBlocksOfNeighborChange(i, j + 1, k, this);
        world.notifyBlocksOfNeighborChange(i - 1, j, k, this);
        world.notifyBlocksOfNeighborChange(i + 1, j, k, this);
        world.notifyBlocksOfNeighborChange(i, j, k - 1, this);
        world.notifyBlocksOfNeighborChange(i, j, k + 1, this);
	}
	
	
	public void onBlockAdded(World world, int i, int j, int k)
    {        
        world.scheduleBlockUpdate(i, j, k, this, tickRate());
    }
	
	public int tickRate()
	{
		return 1;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 0)
		{
			TileEntity tile =  world.getTileEntity(x, y, z);
			if(tile != null && tile instanceof BaconMaker)
			{
				BaconMaker bm = (BaconMaker) tile;
				if(bm.fuel > 0)return 8;
				else return 0;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return 0;
		}
	}
	
    private void dropInventory(World world, int x, int y, int z) 
    {

        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {

            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0) {
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, new ItemStack(itemStack.getItem(), itemStack.stackSize, itemStack.getItemDamage()));

                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
    
    //Cake Code
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int lb = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        if(lb == 3)
        {
        	TileEntity tile = par1IBlockAccess.getTileEntity(par2, par3, par4);
        	if(tile != null && tile instanceof BaconCake)
        	{
        		BaconCake bc = (BaconCake) tile;
        		int l = bc.getEatingState();
                float f = 0.0625F;
                float f1 = (float)(1 + l * 2) / 16.0F;
                float f2 = 0.5F;
                if(bc.getFacing() == 4)
                {
                	this.setBlockBounds(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
                }
                else if(bc.getFacing() == 2)
                {
                	this.setBlockBounds(f, 0.0F, f1, 1.0F - f, f2, 1.0F - f);
                }
                else if(bc.getFacing() == 5)
                {
                	this.setBlockBounds(f, 0.0F, f, 1.0F - f1, f2, 1.0F - f);
                }
                else if(bc.getFacing() == 3)
                {
                	this.setBlockBounds(f, 0.0F, f, 1.0F - f, f2, 1.0F - f1);
                }
                else
                {
                	this.setBlockBounds(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
                }
        	}
        }
        else
        {
        	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public void eatCake(World par1, int par2, int par3, int par4, EntityPlayer par5)
    {
    	int meta = par1.getBlockMetadata(par2, par3, par4);
    	TileEntity tile = par1.getTileEntity(par2, par3, par4);
    	if(meta == 3)
    	{
    		if(tile != null && tile instanceof BaconCake)
    		{
    			BaconCake bc = (BaconCake) tile;
    			if(bc.getEatingState() + 1 >= 6)
    			{
    				par1.setBlockToAir(par2, par3, par4);
    				par5.getFoodStats().addStats(2, 0.3F);
    				par1.removeTileEntity(par2, par3, par4);
    			}
    			else
    			{
    				bc.setEatedMore();
	    			par1.markBlockForUpdate(par2, par3, par4);
	    			par1.markBlockForUpdate(par2, par3, par4);
	    			par1.notifyBlockChange(par2, par3, par4, this);
    				par5.getFoodStats().addStats(2, 0.3F);
    			}
    		}
    	}
    }
    


	@Override
	public void onBlockClicked(World par1, int par2, int par3, int par4, EntityPlayer par5)
	{
		updateStepSound(par1, par2, par3, par4);
		int meta = par1.getBlockMetadata(par2, par3, par4);
		TileEntity tile = par1.getTileEntity(par2, par3, par4);
		if(meta == 3)
		{
			if(tile != null && tile instanceof BaconCake)
			{
				if(par5.getFoodStats().needFood())
				{
					this.eatCake(par1, par2, par3, par4, par5);
				}
			}
		}

	}

	public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        int lb = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity tile = par1World.getTileEntity(par2, par3, par4);
        if(lb == 3)
        {
        	if(tile != null && tile instanceof BaconCake)
        	{
        		BaconCake bc = (BaconCake) tile;
        		int l = bc.getEatingState();
                float f = 0.0625F;
                float f1 = (float)(1 + l * 2) / 16.0F;
                float f2 = 0.5F;
                if(bc.getFacing() == 4)
                {
                    return AxisAlignedBB.getBoundingBox((double)((float)par2 + f1), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)par3 + f2 - f), (double)((float)(par4 + 1) - f));
                }
                else if(bc.getFacing() == 2)
                {
                    return AxisAlignedBB.getBoundingBox((double)((float)par2 + f), (double)par3, (double)((float)par4 + f1), (double)((float)(par2 + 1) - f), (double)((float)par3 + f2 - f), (double)((float)(par4 + 1) - f));
                }
                else if(bc.getFacing() == 5)
                {
                    return AxisAlignedBB.getBoundingBox((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f1), (double)((float)par3 + f2 - f), (double)((float)(par4 + 1) - f));
                }
                else if(bc.getFacing() == 3)
                {
                    return AxisAlignedBB.getBoundingBox((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)par3 + f2 - f), (double)((float)(par4 + 1) - f1));
                }
                else
                {
                    return AxisAlignedBB.getBoundingBox((double)((float)par2 + f1), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)par3 + f2 - f), (double)((float)(par4 + 1) - f));
                }
        	}
        }
        return AxisAlignedBB.getBoundingBox(par2, par3, par4, par2+1, par3+1, par4+1);
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        int lb = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity tile = par1World.getTileEntity(par2, par3, par4);
        if(lb == 3)
        {
        	if(tile != null && tile instanceof BaconCake)
        	{
        		BaconCake bc = (BaconCake) tile;
        		int l = bc.getEatingState();
                float f = 0.0625F;
                float f1 = (float)(1 + l * 2) / 16.0F;
                float f2 = 0.5F;
                if(bc.getFacing() == 4)
                {
                    return AxisAlignedBB.getBoundingBox((double)((float)par2 + f1), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)par3 + f2 - f), (double)((float)(par4 + 1) - f));
                }
                else if(bc.getFacing() == 2)
                {
                    return AxisAlignedBB.getBoundingBox((double)((float)par2 + f), (double)par3, (double)((float)par4 + f1), (double)((float)(par2 + 1) - f), (double)((float)par3 + f2 - f), (double)((float)(par4 + 1) - f));
                }
                else if(bc.getFacing() == 5)
                {
                    return AxisAlignedBB.getBoundingBox((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f1), (double)((float)par3 + f2 - f), (double)((float)(par4 + 1) - f));
                }
                else if(bc.getFacing() == 3)
                {
                    return AxisAlignedBB.getBoundingBox((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)par3 + f2 - f), (double)((float)(par4 + 1) - f1));
                }
                else
                {
                    return AxisAlignedBB.getBoundingBox((double)((float)par2 + f1), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)par3 + f2 - f), (double)((float)(par4 + 1) - f));
                }
        	}
        }
        return AxisAlignedBB.getBoundingBox(par2, par3, par4, par2+1, par3+1, par4+1);
    }


	public boolean isShearable(ItemStack item, World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 5)
		{
			return true;
		}
		return false;
	}


	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) 
	{
		ArrayList<ItemStack> dropList = new ArrayList<ItemStack>();
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 5)
		{
			BaconLeave bl = (BaconLeave) world.getTileEntity(x, y, z);
			dropList.add(new ItemStack(this, 1, 5));
			if(bl.getGroth() >= 6500 && bl.getGroth() <= 9000)
			{
				dropList.add(new ItemStack(BaconItems.rawBacon, 1 + rand.nextInt(3)));
			}
			
			if(bl.getGroth() > 9000)
			{
				dropList.add(new ItemStack(BaconItems.rottedBacon, 1 + rand.nextInt(2)));
			}
		}
		return dropList;
	}

	
	public void	updateStepSound(World par0, int par1, int par2, int par3)
	{
		int meta = par0.getBlockMetadata(par1, par2, par3);
		switch(meta)
		{
			case 0:
			{
				this.setStepSound(Block.soundTypeMetal);
				break;
			}
			case 1:
			{
				this.setStepSound(Block.soundTypeCloth);
				break;
			}
			case 2:
			{
				this.setStepSound(Block.soundTypeCloth);
				break;
			}
			case 3:
			{
				this.setStepSound(Block.soundTypeCloth);
				break;
			}
			case 4:
			{
				this.setStepSound(Block.soundTypeWood);
				break;
			}
			case 5:
			{
				this.setStepSound(Block.soundTypeGrass);
				break;
			}
			case 6:
			{
				this.setStepSound(Block.soundTypeWood);
				break;
			}
			case 7:
			{
				this.setStepSound(Block.soundTypeWood);
				break;
			}

		}
	}
	
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) 
	{
		updateStepSound(par1World, par2, par3, par4);
	}
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		updateStepSound(par1World, par2, par3, par4);
	}


	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return null;
	}


	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x,
			int y, int z)
	{
		return false;
	}


	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world,
			int x, int y, int z, int fortune)
	{
		return null;
	}
	

}