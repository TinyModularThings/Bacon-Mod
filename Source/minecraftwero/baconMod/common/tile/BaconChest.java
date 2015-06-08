package minecraftwero.baconMod.common.tile;

import java.util.Random;

import minecraftwero.baconMod.common.lib.IExpBottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.minecraftwero.bacon.common.tile.TileEntityBacon;

import cpw.mods.fml.common.FMLLog;

public class BaconChest extends TileEntityBacon implements IInventory
{
	
	ItemStack[] inventory = new ItemStack[36];
	int[] chargedOnSlot = new int[36];

    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        for(int i = 0; i< chargedOnSlot.length;i++)
        {
        	this.chargedOnSlot[i] = par1NBTTagCompound.getInteger("Charge"+i);
        }
    }


    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
        for(int i = 0; i< chargedOnSlot.length;i++)
        {
        	par1NBTTagCompound.setInteger("Charge"+i, this.chargedOnSlot[i]);
        }
    }
	
	@Override
	public int getSizeInventory() {
        return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
        return this.inventory[i];

	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.inventory[i] != null)
        {
            ItemStack itemstack;

            if (this.inventory[i].stackSize <= j)
            {
                itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inventory[i].splitStack(j);

                if (this.inventory[i].stackSize == 0)
                {
                    this.inventory[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }


	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.inventory[i] != null)
        {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }


	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		   this.inventory[i] = itemstack;

	        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	        {
	        	itemstack.stackSize = this.getInventoryStackLimit();
	        }
	    }


	@Override
	public String getInvName() 
	{

		return "Bacon Chest";
	}

	@Override
	public boolean isInvNameLocalized() {
		
		return false;
	}

	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		
		return true;
	}

	@Override
	public void openChest() 
	{
	}

	@Override
	public void closeChest() 
	{
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
		return true;
	}
	
	@Override
	public void updateEntity() 
	{
		Random rand = new Random();
		
		
		if(!worldObj.isRemote)
		{
			for(int i = 0;i< this.getSizeInventory();i++)
			{
				ItemStack current = this.getStackInSlot(i);
				if(current != null && current.getItem() instanceof IExpBottle)
				{
					IExpBottle ieb = (IExpBottle) inventory[i].getItem();
					if(rand.nextInt(1000) == 0)
					{
						this.chargedOnSlot[i] += 1;
					}
					
					if(this.chargedOnSlot[i] > 10000)
					{
						this.chargedOnSlot[i] = 0;
						if(ieb.getMaxCharge(current) > ieb.getStoredExp(current))
						{
							ieb.charge(1, inventory[i]);
						}
						
					}
				}
				if(current != null && !(current.getItem() instanceof IExpBottle))
				{
					if(current.getMaxStackSize() > 1 && current.stackSize < current.getMaxStackSize())
					{
						if(rand.nextInt(1000) == 0)
						{
							this.chargedOnSlot[i] +=1;
							FMLLog.getLogger().info("Charge "+i+": "+this.chargedOnSlot[i]);
						}
						if(this.chargedOnSlot[i] > 1000)
						{
							this.chargedOnSlot[i] = 0;
							this.inventory[i].stackSize++;
						}
					}
				}
			}
		}
	}
}
