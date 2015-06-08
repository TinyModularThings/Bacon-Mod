package minecraftwero.baconMod.common.inventory;

import minecraftwero.baconMod.common.tile.BaconChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBaconChest extends Container 
{
	private BaconChest tile;

	public ContainerBaconChest(InventoryPlayer par1, BaconChest par2)
	{
		tile = par2;
		int slot = 0;
		for(int x = 0;x < 9;x++)
		{
			for(int y = 0; y<4;y++)
			{
				this.addSlotToContainer(new BaconSlot(par2, slot, 8 + (x * 18), 54 + (y * 18)));
				slot++;
			}
		}
		
		
		int i;
		
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1, i, 8 + i * 18, 198));
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
