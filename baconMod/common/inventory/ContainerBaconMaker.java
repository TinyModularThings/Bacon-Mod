package minecraftwero.baconMod.common.inventory;

import minecraftwero.baconMod.common.core.handlers.BaconMakerRecipes;
import minecraftwero.baconMod.common.items.ItemBlade;
import minecraftwero.baconMod.common.tile.BaconMaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerBaconMaker extends Container 
{
	private int progressnow = 0;
	private int workingtimenow = 0;
	private int fuelnow = 0;
	private BaconMaker tile;

	public ContainerBaconMaker(InventoryPlayer par1, BaconMaker par2)
	{
		tile = par2;
		this.addSlotToContainer(new Slot(par2, 0, 56, 53)); //FuelSlot
		this.addSlotToContainer(new Slot(par2, 1, 56, 17)); //Input Slot
		this.addSlotToContainer(new Slot(par2, 2, 83, 17)); //Secret Slot
		this.addSlotToContainer(new Slot(par2, 3, 116, 35)); //Output Slot
		int i;
		
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1, i, 8 + i * 18, 142));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) 
	{
		return true;
	}
	
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.fuelnow != this.tile.fuel)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tile.fuel);
            }

            if (this.progressnow != this.tile.progress)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tile.progress);
            }

            if (this.workingtimenow != this.tile.workingTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tile.workingTime);
            }
        }

        this.fuelnow = this.tile.fuel;
        this.progressnow = this.tile.progress;
        this.workingtimenow = this.tile.workingTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.tile.fuel = par2;
        }

        if (par1 == 1)
        {
            this.tile.progress = par2;
        }

        if (par1 == 2)
        {
            this.tile.workingTime = par2;
        }
    }

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) 
	{
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 3)
            {
                if (!this.mergeItemStack(itemstack1, 4, 40, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0 && par2 != 2)
            {
                if (BaconMakerRecipes.getRecipes().getRecipeOuput(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (tile.hasFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if(itemstack1 != null && itemstack.getItem() instanceof ItemBlade)
                {
                	if(!this.mergeItemStack(itemstack1, 2, 3, false))
                	{
                		return null;
                	}
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 31, 40, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 4, 31, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 4, 40, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
	}

}
