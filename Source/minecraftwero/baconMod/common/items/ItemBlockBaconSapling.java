package minecraftwero.baconMod.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBaconSapling extends ItemBlock
{

	public ItemBlockBaconSapling(Block par1)
	{
		super(par1);
	}
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
	
	public String getItemDisplayName(ItemStack par1)
	{
		return "Bacon Sapling";
	}
	
}
