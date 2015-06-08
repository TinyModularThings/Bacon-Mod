package minecraftwero.baconMod.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;


public class LiquidSlot extends Slot 
{

	public LiquidSlot(IInventory par1iInventory, int par2, int par3, int par4) 
	{
		super(par1iInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) 
	{
		FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(par1ItemStack);
		if(liquid != null && liquid.getFluid() == FluidRegistry.LAVA)
		{
			return true;
		}
		return false;
		
	}
	

}
