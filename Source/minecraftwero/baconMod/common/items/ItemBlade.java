package minecraftwero.baconMod.common.items;

import minecraftwero.baconMod.Bacon;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlade extends Item 
{
	private String names;
	private String textures;
	public ItemBlade(int par1, int maxDamage, String name, String texture) 
	{
		super();
		names = name;
		textures = texture;
		this.setCreativeTab(Bacon.baconMod);
		this.setMaxStackSize(1);
		this.setMaxDamage(maxDamage);
		this.setTextureName(texture);
	}



	public void damageBlade(ItemStack itemStack) 
	{
		if(itemStack.getItemDamage() < itemStack.getMaxDamage())
		{
			itemStack.setItemDamage(itemStack.getItemDamage()+1);
		}
		else
		{
			itemStack = null;
		}
		
	}
	
	public String getItemDisplayName(ItemStack par1)
	{
		return names;
	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(textures);
	}

}
