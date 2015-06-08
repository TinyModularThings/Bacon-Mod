package minecraftwero.baconMod.common.lib;

import net.minecraft.item.ItemStack;

public interface IExpBottle 
{
	public abstract void charge(int i, ItemStack par1);
	
	public abstract void discharge(int i, ItemStack par1);
	
	public abstract int getTransferlimit(ItemStack par1);
	
	public abstract int getMaxCharge(ItemStack par1);
	
	public abstract int getStoredExp(ItemStack par1);
	
	public abstract boolean hasExp(ItemStack par1);
	
	public abstract boolean needsExp(ItemStack par1);
}
