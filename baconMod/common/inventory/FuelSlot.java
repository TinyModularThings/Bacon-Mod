package minecraftwero.baconMod.common.inventory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class FuelSlot extends Slot 
{

	public FuelSlot(IInventory par1iInventory, int par2, int par3, int par4)
	{
		super(par1iInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) 
	{
		return TileEntityFurnace.isItemFuel(par1ItemStack);
	}

}
