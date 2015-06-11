package minecraftwero.baconMod.common.items;

import java.util.Random;

import minecraftwero.baconMod.Bacon;
import minecraftwero.baconMod.common.config.BaconItems;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
/**
 * 
 * @author Speiger
 *
 */
public class ItemFineSword extends Item 
{
	public Random rand = new Random();
	public String name;
	public String texture;
	 



	public ItemFineSword(int par1, String par2, String par3) 
	{
		super();
		this.setCreativeTab(Bacon.baconMod);
		this.setNoRepair();
		this.setMaxStackSize(1);
		this.setMaxDamage(10);
		texture = par2;
		name = par3;
		this.setTextureName(par2);
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
	{
		return false;
	}


	public ItemStack getContainerItemStack(ItemStack itemStack)
	{
		ItemStack par1 = itemStack.copy();
		par1.setItemDamage(par1.getItemDamage()+1);
		return par1;
	}


	@Override
	public boolean hitEntity(ItemStack par1, EntityLivingBase par2, EntityLivingBase par3) 
	{
		if(par2 != null && par2 instanceof EntityPig && par3 instanceof EntityPlayer)
		{
			EntityPig entity = (EntityPig) par2;
			EntityPlayer player = (EntityPlayer) par3;
			EntityItem item = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ);
			if(BaconItems.rawBacon != null)
			{
				item.setEntityItemStack(new ItemStack(BaconItems.rawBacon, 1));
			}
			if(entity.isBurning())
			{
				if(BaconItems.cookedBacon != null)
				{
					item.setEntityItemStack(new ItemStack(BaconItems.cookedBacon, 1));
				}
				
			}
			
			if(rand.nextInt(10) == 0)
			{
				if(BaconItems.rottedBacon != null)
				{
					item.setEntityItemStack(new ItemStack(BaconItems.rottedBacon));
				}
			}
			
			if(rand.nextBoolean() == true && item.getEntityItem() != null)
			{
				player.joinEntityItemWithWorld(item);
			}


		}
		par1.damageItem(1, par3);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack par1, World par2, int par3, int par4, int par5, int par6, EntityLivingBase par7) 
	{
		par1.damageItem(2, par7);
		return true;
	}

	@Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap par1 = super.getItemAttributeModifiers();
		par1.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon Modifire", 2, 0));
		return par1;
	}
	
	public String getItemDisplayName(ItemStack par1)
	{
		return name;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(texture);
	}

}
