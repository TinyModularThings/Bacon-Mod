package minecraftwero.baconMod.common.core;

import minecraftwero.baconMod.common.config.BaconItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.sun.tools.javac.jvm.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaconTab extends CreativeTabs
{
	public BaconTab(int par1, String par2Str) 
	{
		super(par1, par2Str);
	}
	
	
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() 
	{
		if(BaconItems.rawBacon != null)
		{
			return BaconItems.rawBacon.getIdFromItem(getTabIconItem());
		}
		else if(BaconItems.cookedBacon != null)
		{
			return BaconItems.cookedBacon.getIdFromItem(getTabIconItem());
		}
		else
		{
			return Item.porkRaw.itemID;
		}
	}


	@Override
	public Item getTabIconItem() {
		return null;
	}
	
	

}
