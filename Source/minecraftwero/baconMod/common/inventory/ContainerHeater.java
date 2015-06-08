package minecraftwero.baconMod.common.inventory;

import minecraftwero.baconMod.common.tile.Heater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerHeater extends Container 
{
	private Heater hear;
	private int liquidSize = 0;
	private int currentliquidHeat = 0;
	private int currentHeat = 0;
	private int solidHeat = 0;
	
	
	public ContainerHeater(InventoryPlayer par1, Heater par2)
	{
		hear = par2;
		this.addSlotToContainer(new LiquidSlot(par2, 0, 8, 36));
		this.addSlotToContainer(new FuelSlot(par2, 1, 128, 35));
		this.addSlotToContainer(new FuelSlot(par2, 2, 153, 16));
		this.addSlotToContainer(new FuelSlot(par2, 3, 153, 34));
		this.addSlotToContainer(new FuelSlot(par2, 4, 153, 52));
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
	
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (hear.lava.getFluid() != null && this.liquidSize != this.hear.lava.getInfo().fluid.amount)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.hear.lava.getInfo().fluid.amount);
            }
            else if(hear.lava.getFluid() == null)
            {
            	icrafting.sendProgressBarUpdate(this, 0, 0);
            }
            
            if(this.currentliquidHeat != hear.heatLiquid)
            {
            	icrafting.sendProgressBarUpdate(this, 1, this.hear.heatLiquid);
            }
            if(this.currentHeat != hear.heaterHeat)
            {
            	icrafting.sendProgressBarUpdate(this, 2, this.hear.heaterHeat);
            }
            if(solidHeat != hear.heatSolid)
            {
            	icrafting.sendProgressBarUpdate(this, 3, this.hear.heatSolid);
            }
        }

        if(hear.lava.getFluid() != null)
        {
        	this.liquidSize = this.hear.lava.getInfo().fluid.amount;
        }
        else
        {
        	this.liquidSize = 0;
        }
        this.currentliquidHeat = this.hear.heatLiquid;
        this.currentHeat = hear.heaterHeat;
        solidHeat = hear.heatSolid;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
        	if(hear.lava.getFluid() != null)
        	{
                this.hear.lava.getInfo().fluid.amount = par2;
        	}
        }
        if(par1 == 1)
        {
        	this.hear.heatLiquid = par2;
        }
        if(par1 == 2)
        {
        	hear.heaterHeat = par2;
        }
        if(par1 == 3)
        {
        	hear.heatSolid = par2;
        }
    }
	

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) 
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		return null;
	}
	
	

}
