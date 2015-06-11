package minecraftwero.baconMod.common.core.handlers;

import java.util.List;
import java.util.Random;

import minecraftwero.baconMod.common.config.BaconItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class BaconCraftingHandler implements ICrafting, IFuelHandler
{
	public Random rand = new Random();
	
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) 
	{
		
		if(BaconItems.FineSword != null)
		{
			for(int i = 0; i<craftMatrix.getSizeInventory();i++)
			{
				ItemStack par1 = craftMatrix.getStackInSlot(i);
				if(par1 != null && par1.itemID == BaconItems.fineSword.itemID)
				{
					if(rand.nextInt(3) == 0)
					{
						player.attackEntityFrom(BaconItems.FineSword, 2F);
					}
				}
			}
		}
		

		
	}

	
	public void onSmelting(EntityPlayer player, ItemStack item) 
	{
		
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void sendContainerAndContentsToPlayer(Container p_71110_1_,
			List p_71110_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendSlotContents(Container p_71111_1_, int p_71111_2_,
			ItemStack p_71111_3_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendProgressBarUpdate(Container p_71112_1_, int p_71112_2_,
			int p_71112_3_) {
		// TODO Auto-generated method stub
		
	}
	
}
