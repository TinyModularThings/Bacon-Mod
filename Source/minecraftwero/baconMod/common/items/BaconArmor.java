package minecraftwero.baconMod.common.items;

import java.util.Random;

import minecraftwero.baconMod.Bacon;
import minecraftwero.baconMod.common.config.BaconItems;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**
 * 
 * @author MinecraftWero, Speiger
 *
 */
public class BaconArmor extends ItemArmor
{
	
	private boolean[] equickt = new boolean[4];
	public Random rand = new Random();
	public String par10;
	public String par11;
	
	public BaconArmor(int par1, ArmorMaterial par2EnumArmorMaterial, int par3, int par4, String texture, String name)
	{
		super(par2EnumArmorMaterial, par3, par4);
		par10 = name;
		this.setMaxStackSize(1);
		par11 = texture;
		this.setCreativeTab(Bacon.baconMod);
		this.setNoRepair();
		this.setTextureName(texture);
	}
	

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) 
	{
		if(slot == 2)return "minecraftwero:textures/models/armor/bacon_layer_2.png";
		else return "minecraftwero:textures/models/armor/bacon_layer_1.png";
	}



	public void onArmorTickUpdate(World par1, EntityPlayer par2, ItemStack par3) 
	{
		super.onArmorTick(par1, par2, par3);
		boolean full = false;
		
		if(BaconItems.baconBoots == null || BaconItems.baconHelmet == null || BaconItems.baconLegs == null || BaconItems.baconPlate == null)
		{
			return;
		}
		
		if(par2.getCurrentArmor(0) != null && par2.getCurrentArmor(0).itemID == BaconItems.baconBoots.itemID)
		{
			equickt[0] = true;
		}
		else
		{
			equickt[0] = false;
		}
		
		if(par2.getCurrentArmor(1) != null && par2.getCurrentArmor(1).itemID == BaconItems.baconLegs.itemID)
		{
			equickt[1] = true;
		}
		else
		{
			equickt[1] = false;
		}
		
		if(par2.getCurrentArmor(2) != null && par2.getCurrentArmor(2).itemID == BaconItems.baconPlate.itemID)
		{
			equickt[2] = true;
		}
		else
		{
			equickt[2] = false;
		}
		
		if(par2.getCurrentArmor(3) != null && par2.getCurrentArmor(3).itemID == BaconItems.baconHelmet.itemID)
		{
			equickt[3] = true;
		}
		else
		{
			equickt[3] = false;
		}
		
		if(equickt[0] && equickt[1] && equickt[2] && equickt[3])
		{
			full = true;
		}
		else
		{
			full = false;
		}
		
		if(full && par2.getFoodStats().getFoodLevel() < 19)
		{
			int randing = rand.nextInt(4);
			par2.inventory.damageArmor(180);
			
			par2.getFoodStats().addStats(2, 0.6F);
			
		}
	}
	

	public String getItemDisplayName(ItemStack par1)
	{
		return par10;
	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(par11);
	}
	
	
	
	
}