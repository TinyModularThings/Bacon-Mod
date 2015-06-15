package minecraftwero.baconMod.common.core;

import minecraftwero.baconMod.common.config.BaconItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaconTab extends CreativeTabs
{
	public BaconTab(int par1, String par2Str) 
	{
		super(par1, par2Str);
	}
	
	
	@SideOnly(Side.CLIENT)
		@Override
		public Item getTabIconItem() {
		
		return BaconItems.cookedBacon;
	}	

}
