package minecraftwero.baconMod.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class BaconSlot extends Slot 
{

	public BaconSlot(IInventory par1iInventory, int par2, int par3, int par4)
	{
		super(par1iInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		if(par1ItemStack.getItem() instanceof ItemFood || par1ItemStack.getItem() instanceof minecraftwero.baconMod.common.items.CraftingFood || par1ItemStack.getItem() instanceof minecraftwero.baconMod.common.items.AdvancedBacon)
		{
			return true;
		}
		return false;
	}

	
	
}
