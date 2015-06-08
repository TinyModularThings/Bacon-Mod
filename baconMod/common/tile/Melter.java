package minecraftwero.baconMod.common.tile;

import minecraftwero.bacon.common.core.helper.BaconWorld;
import minecraftwero.baconMod.common.config.BaconItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class Melter extends TileEntityBacon implements IInventory, IFluidHandler
{

	public int heat = 0;
	public int progress = 0;
	public FluidTank lava = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*16);
	public ItemStack[] inv = new ItemStack[1];

	
    @Override
	public Packet getDescriptionPacket() 
    {
    	NBTTagCompound var1 = new NBTTagCompound();
    	writeToNBT(var1);
    	var1.setInteger("rotation", facing);
    	return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}
   
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) 
	{
		this.readFromNBT(pkt.data);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if(worldObj.isRemote)
		{
			return;
		}

		
		if(heat > 1500)
		{
			if(inv[0] != null && inv[0].itemID == BaconItems.rawBacon.itemID && lava.getCapacity() - 100 >= lava.getFluidAmount())
			{
				progress++;
				if(progress > 100)
				{
					progress = 0;
					lava.fill(new FluidStack(BaconItems.liquidBacon, 100), true);
				}
			}
		}
		pushFluidOut();
		
		if(worldObj.isRemote)// || worldObj.getWorldTime() % 20 != 0)
		{
			return;
		}
		
		int HottestHeat = 20;
		for(int i = 0;i<6;i++)
		{
			TileEntity tile = BaconWorld.getTileEntity(worldObj, xCoord, yCoord, zCoord, i);
			if(tile != null && tile instanceof Heater)
			{
				Heater heat = (Heater)tile;
				HottestHeat = Math.max(HottestHeat, heat.getHeat());
			}
		}
		
		if(HottestHeat > heat)
		{
			heat++;
		}
		else if(HottestHeat == heat)
		{
		}
		else
		{
			heat--;
		}
	}
	
	public void pushFluidOut()
	{
		int tanks = 0;
		boolean[] sides = new boolean[ForgeDirection.VALID_DIRECTIONS.length];
		for(int i = 0;i<sides.length;i++)
		{
			ForgeDirection side = ForgeDirection.VALID_DIRECTIONS[i];
			TileEntity tile = worldObj.getTileEntity(xCoord+side.offsetX, yCoord+side.offsetY, zCoord+side.offsetZ);
			if(tile != null && tile instanceof IFluidHandler)
			{
				tanks++;
				sides[i] = true;
			}	
		}
		
		int amountPerSide = lava.getFluidAmount();
		for(int i = 0;i<sides.length;i++)
		{
			if(sides[i])
			{
				ForgeDirection side = ForgeDirection.getOrientation(i);
				TileEntity tile = worldObj.getTileEntity(xCoord+side.offsetX, yCoord+side.offsetY, zCoord+side.offsetZ);
				if(tile != null && tile instanceof IFluidHandler && !(tile instanceof Heater) && !(tile instanceof Melter))
				{
					IFluidHandler fluid = (IFluidHandler) tile;
					if(fluid.fill(side.getOpposite(), lava.drain(amountPerSide, false), false) == amountPerSide)
					{
						amountPerSide -= fluid.fill(side.getOpposite(), lava.drain(amountPerSide, true), true);
					}
				}
			}
		}
		
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return lava.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[]{lava.getInfo()};
	}

	@Override
	public int getSizeInventory()
	{
		return inv.length;
	}

    public ItemStack getStackInSlot(int par1)
    {
        return this.inv[par1];
    }

    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.inv[par1] != null)
        {
            ItemStack itemstack;

            if (this.inv[par1].stackSize <= par2)
            {
                itemstack = this.inv[par1];
                this.inv[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inv[par1].splitStack(par2);

                if (this.inv[par1].stackSize == 0)
                {
                    this.inv[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.inv[par1] != null)
        {
            ItemStack itemstack = this.inv[par1];
            this.inv[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.inv[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

	
	public String getInvName()
	{
		return "Melter";
	}

	
	public boolean isInvNameLocalized()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return true;
	}

	public void openChest()
	{
		
	}

	
	public void closeChest()
	{		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}
	
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTBase nbttaglist = par1NBTTagCompound.getTag("Items");
        this.inv = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < ((NBTTagList) nbttaglist).tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)((Object) nbttaglist).tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inv.length)
            {
                this.inv[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        lava = lava.readFromNBT(par1NBTTagCompound);
        heat = par1NBTTagCompound.getInteger("Heat");
        progress = par1NBTTagCompound.getInteger("Pro");
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inv.length; ++i)
        {
            if (this.inv[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inv[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        par1NBTTagCompound.setTag("Items", nbttaglist);
        lava.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Heat", heat);
        par1NBTTagCompound.setInteger("Pro", progress);
    }

	public int addItem(ItemStack stack)
	{
		if(stack != null)
		{
			if(inv[0] == null)
			{
				inv[0] = stack;
				return stack.stackSize;
			}
			else
			{
				int stackSize = inv[0].stackSize;
				if(stackSize + stack.stackSize > 64)
				{
					inv[0].stackSize = 64;
					return stack.stackSize - (stackSize + stack.stackSize - 64);
				}
				else
				{
					inv[0].stackSize += stack.stackSize;
					return stack.stackSize;
				}
			}
		}
		return 0;
	}
}