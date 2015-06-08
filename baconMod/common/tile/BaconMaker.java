package minecraftwero.baconMod.common.tile;

import minecraftwero.baconMod.common.core.handlers.BaconMakerRecipes;
import minecraftwero.baconMod.common.items.ItemBlade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import com.minecraftwero.bacon.common.tile.TileEntityBacon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaconMaker extends TileEntityBacon implements IInventory, ISidedInventory
{

	ItemStack[] baconInventory = new ItemStack[4];
	public int fuel = 0;
	public int progress = 0;
	public int workingTime = 0;
	public boolean update = false;
	
	@SideOnly(Side.CLIENT)
	public int getProgressBar(int i)
	{
		return progress * i / 300;
	}
	
	
	@SideOnly(Side.CLIENT)
	public int getFuel(int i)
	{
		return fuel * i / 1000;
	}
	
    public void updateBlock()
    {
        int var1 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        this.worldObj.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
        markBlockDirty(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }
	
    public void markBlockDirty(World var0, int var1, int var2, int var3)
    {
        if (var0.blockExists(var1, var2, var3))
        {
            var0.getChunkFromBlockCoords(var1, var3).setChunkModified();
        }
    }
    
    public int getSizeInventory()
    {
        return this.baconInventory.length;
    }
    

    public ItemStack getStackInSlot(int par1)
    {
        return this.baconInventory[par1];
    }


    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.baconInventory[par1] != null)
        {
            ItemStack itemstack;

            if (this.baconInventory[par1].stackSize <= par2)
            {
                itemstack = this.baconInventory[par1];
                this.baconInventory[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.baconInventory[par1].splitStack(par2);

                if (this.baconInventory[par1].stackSize == 0)
                {
                    this.baconInventory[par1] = null;
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
        if (this.baconInventory[par1] != null)
        {
            ItemStack itemstack = this.baconInventory[par1];
            this.baconInventory[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }


    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.baconInventory[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }


    public String getInvName()
    {
        return "BaconMaker";
    }



    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        fuel = par1NBTTagCompound.getInteger("fuels");
        progress = par1NBTTagCompound.getInteger("progresss");
        workingTime = par1NBTTagCompound.getInteger("workingTimes");
        this.baconInventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.baconInventory.length)
            {
                this.baconInventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }


    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("fuels", fuel);
        par1NBTTagCompound.setInteger("progresss", progress);
        par1NBTTagCompound.setInteger("workingTimes", workingTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.baconInventory.length; ++i)
        {
            if (this.baconInventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.baconInventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

    }

    public int getInventoryStackLimit()
    {
        return 64;
    }


	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) 
	{
		return true;
	}

	@Override
	public void openChest() {		
	}

	@Override
	public void closeChest() 
	{		
	}


	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public void updateEntity() 
	{
		
		
		super.updateEntity();

		
		if(fuel < 10 && canCook() && baconInventory[2] != null && baconInventory[2].getItem() instanceof ItemBlade)
		{
			addFuel();
			update = true;
		}
		cook();
		damage();
		if(fuel > 0)
		{
			fuel--;
		}
		
		if(update)
		{
			update = false;
			this.onInventoryChanged();
			updateBlock();
			updateLight();
		}

	}
	
    void updateLight()
    {
        this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
    }


	public void damage()
	{
		if(workingTime >= 600 && baconInventory[2] != null && baconInventory[2].getItem() instanceof ItemBlade)
		{
			workingTime = 0;
			ItemBlade ib = (ItemBlade) baconInventory[2].getItem();
			ib.damageBlade(baconInventory[2]);
			if(baconInventory[2].getItemDamage() >= baconInventory[2].getMaxDamage())
			{
				baconInventory[2] = null;
			}
			
		}
	}
	
	public void cook()
	{
		if(fuel > 0)
		{
			if(canCook())
			{
				if(baconInventory[2] != null && baconInventory[2].getItem() instanceof ItemBlade)
				{
					ItemBlade ib = (ItemBlade) baconInventory[2].getItem();
					progress++;
					workingTime++;
					if(progress >= 300)
					{
						progress = 0;
						ItemStack output = BaconMakerRecipes.getRecipes().getRecipeOuput(baconInventory[1]);
			            if (this.baconInventory[3] == null)
			            {
			                this.baconInventory[3] = output.copy();
			                update = true;
			            }
			            else if (this.baconInventory[3].isItemEqual(output))
			            {
			                baconInventory[3].stackSize += output.stackSize;
			                update = true;
			            }

			            --this.baconInventory[1].stackSize;

			            
			            if (this.baconInventory[1].stackSize <= 0)
			            {
			                this.baconInventory[1] = null;
			                update = true;
			            }

					}
				}
			}
			else
			{
				progress = 0;
			}
		}
	}
	
	public boolean canCook()
	{
        if (this.baconInventory[1] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = BaconMakerRecipes.getRecipes().getRecipeOuput(baconInventory[1]);
            if (itemstack == null) return false;
            if (this.baconInventory[3] == null) return true;
            if (!this.baconInventory[3].isItemEqual(itemstack)) return false;
            int result = baconInventory[3].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
	}
	
	public void addFuel()
	{
		if(hasFuel(baconInventory[0]))
		{
			int fuelbetween = this.getFuel(baconInventory[0]);
			if(fuelbetween > 0)
			{
				fuel += fuelbetween;
				baconInventory[0].stackSize--;
				update = true;
				if(baconInventory[0].stackSize <= 0)
				{
					baconInventory[0] = baconInventory[0].getItem().getContainerItemStack(baconInventory[0]);
					update = true;
				}
			}
			

		}
	}
	
	public boolean hasFuel(ItemStack par1)
	{
		return getFuel(par1) > 0;
	}
	
	public int getFuel(ItemStack par1)
	{
		if(par1 != null)
		{
			int itemid = par1.getItem().itemID;
			if(itemid == Item.coal.itemID)
			{
				return 1600;
			}
		}

		return 0;
	}



    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int par1)
    {
    	if(par1 == 0)
    	{
    		return new int[]{0};
    	}
    	else if(par1 == 1)
    	{
    		return new int[]{1};
    	}
    	else
    	{
    		return new int[]{3};
    	}
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return par3 != 1 || par1 != 2 || par2ItemStack.itemID == Item.bucketEmpty.itemID;
    }


	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
        if(i == 0)return this.hasFuel(itemstack);
        else if(i == 3)return false;
        else if(i == 1)return true;
        else return false;
	}

	
}
