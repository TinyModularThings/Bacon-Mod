package minecraftwero.baconMod.common.blocks;

import java.util.List;

import minecraftwero.baconMod.Bacon;
import minecraftwero.baconMod.common.config.BaconItems;
import minecraftwero.baconMod.common.lib.BaconGuiIDs;
import minecraftwero.baconMod.common.tile.Heater;
import minecraftwero.baconMod.common.tile.Melter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachineHeater extends BlockContainer {

	public MachineHeater(int par1) 
	{
		super(Material.iron);
		this.setCreativeTab(Bacon.baconMod);
	}

	public TileEntity createNewTileEntity(World world) 
	{	
		return null;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) 
	{
		if(meta == 0)return new Heater();
		if(meta == 1)return new Melter();
		return null;
	}
	
	public void onBlockPlacedBy(World par1, int par2, int par3, int par4, EntityLivingBase par5, ItemStack par6) 
	{

		TileEntity tile = par1.getTileEntity(par2, par3, par4);
		int facing = MathHelper.floor_double(par5.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int var8 = Math.round(par5.rotationPitch);
		int rotation = 0;
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
		if(tile != null)
		{
			if(tile instanceof Heater)
			{
				Heater h = (Heater) tile;
				h.setFacing(rotation);
				h.setHeat(par2, par4);
			}
			else if(tile instanceof Melter)
			{
				Melter m = (Melter)tile;
				m.setFacing(rotation);
			}
		}
	}
	
	

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		return new ItemStack(world.getBlockMetadata(x, y, z), 1, world.getBlockMetadata(x, y, z));
	}

	@Override
	public boolean onBlockActivated(World par1, int par2, int par3, int par4, EntityPlayer par5, int par6, float par7, float par8, float par9) 
	{
		if(par5.isSneaking() && par5.getCurrentEquippedItem() != null)
		{
			return false;
		}
		
		if(!par1.isRemote)
		{
			ItemStack stack = par5.getCurrentEquippedItem();
			if(stack != null)
			{
				if(FluidContainerRegistry.isBucket(stack) || FluidContainerRegistry.isEmptyContainer(stack))
				{
					FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(stack);
					if(fluid == null)
					{
						TileEntity tile = par1.getTileEntity(par2, par3, par4);
						if(tile != null && tile instanceof Melter)
						{
							Melter tank = (Melter) tile;
							FluidStack available = tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid;
							if (available != null)
							{
								ItemStack filled = FluidContainerRegistry.fillFluidContainer(available, stack);
								fluid = FluidContainerRegistry.getFluidForFilledItem(filled);

								if (fluid != null) 
								{
									if (!BuildCraftCore.debugMode && !par5.capabilities.isCreativeMode) 
									{
										if (stack.stackSize > 1) 
										{
											if (!par5.inventory.addItemStackToInventory(filled))
											{
												return false;
											}
											else 
											{
												par5.inventory.setInventorySlotContents(par5.inventory.currentItem, consumeItem(stack));
											}
										} 
										else 
										{
											par5.inventory.setInventorySlotContents(par5.inventory.currentItem, consumeItem(stack));
											par5.inventory.setInventorySlotContents(par5.inventory.currentItem, filled);
										}
									}
									tank.drain(ForgeDirection.UNKNOWN, fluid.amount, true);
									return true;
								}
							}
						}
					}
				}
				else
				{
					if(stack.itemID == BaconItems.rawBacon.itemID)
					{
						TileEntity tile = par1.getTileEntity(par2, par3, par4);
						if(tile != null && tile instanceof Melter)
						{
							Melter melt = (Melter) tile;
							int added = melt.addItem(stack);
							stack.stackSize -= added;
							if(stack.stackSize < 0)
							{
								par5.inventory.setInventorySlotContents(par5.inventory.currentItem, null);
							}
							else
							{
								par5.inventory.setInventorySlotContents(par5.inventory.currentItem, stack);
							}
							if(added > 0)
							{
								par5.sendChatToPlayer(new ChatMessageComponent().addText("Added "+added+" Bacon to the Melter"));
							}
						}
					}
				}
			}
			else
			{
				TileEntity tile = par1.getTileEntity(par2, par3, par4);
				if(tile != null && tile instanceof Melter)
				{
					Melter m = (Melter) tile;
					if(par5.isSneaking())
					{
						par5.sendChatToPlayer(new ChatMessageComponent().addText("Temperature: "+m.heat+"*"));
					}
					else
					{
						par5.sendChatToPlayer(new ChatMessageComponent().addText("Stored Liquid Bacon: "+m.lava.getFluidAmount()+"mB"));
					}
					
					
				}
			}
		}
		
		
		if(!par1.isRemote)
		{
			TileEntity tile = par1.getTileEntity(par2, par3, par4);
			int meta = par1.getBlockMetadata(par2, par3, par4);
			if(tile != null)
			{
				int id = 0;
				if(meta == 0)
				{
					id = BaconGuiIDs.heater;
				}
				else if(meta == 1)
				{
					id = BaconGuiIDs.melter;
				}
				
				if(id != 0)
				{
					par5.openGui(Bacon.instance, id, par1, par2, par3, par4);
					return true;
				}
			}
		}
		return true;
	}

	
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Block par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
	}

	public ItemStack consumeItem(ItemStack stack) 
	{
		if (stack.stackSize == 1)
		{
			if (stack.getItem().hasContainerItem()) 
			{
				return stack.getItem().getContainerItem(stack);
			} 
			else 
			{
				return null;
			}
		} 
		else 
		{
			stack.splitStack(1);

			return stack;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
