package minecraftwero.baconMod.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBaconMachine extends ItemBlock
{

	public ItemBlockBaconMachine(Block par1) 
	{
		super(par1);
		this.setHasSubtypes(true);
	}

	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		switch(par1ItemStack.getItemDamage())
		{
			case 0: return "Bacon Maker";
			case 1: return "Bacon Block";
			case 2: return "Cooked Bacon Block";
			case 3: return "Bacon Sandwitch";
			case 4: return "Bacon Log";
			case 5: return "Bacon Leaves";
			case 6: return "Food Container";
			case 7: return "Bacon Planks";
			default: return null;
		}
	}

	@Override
	public int getMetadata(int par1) 
	{
		return par1;
	}


	
	
	

}
