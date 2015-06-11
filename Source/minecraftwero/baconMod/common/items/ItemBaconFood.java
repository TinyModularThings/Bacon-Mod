package minecraftwero.baconMod.common.items;

import minecraftwero.baconMod.Bacon;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Speiger
 *
 */
public class ItemBaconFood extends ItemFood 
{
	private String name;
	public String texture;
	private PotionEffect[] potion;
	
	public ItemBaconFood(int par1, int par2, boolean par3, String par4, String par5,  PotionEffect...par7) 
	{
		super(par1, par2, par3);
		this.setCreativeTab(Bacon.baconMod);
		name = par4;
		texture = par5;
		potion = par7;
		this.setTextureName(par5);
	}
	
	public ItemBaconFood(int par1, int par2, boolean par3, String par4, String par5)
	{
		this(par1, par2, par3, par4, par5, new PotionEffect(0, 0, 0));
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

	@Override
	public ItemStack onEaten(ItemStack par1, World par2, EntityPlayer par3) 
	{
        --par1.stackSize;
        par3.getFoodStats().addStats(this);
        par2.playSoundAtEntity(par3, "random.burp", 0.5F, par2.rand.nextFloat() * 0.1F + 0.9F);
        addPotions(par1, par2, par3);
		return par1;
	}

	public void addPotions(ItemStack par1, World par2, EntityPlayer par3)
	{
		for(int i = 0; i<potion.length;i++)
		{
			if(potion[i] != null && potion[i].getPotionID() > 0 && !par2.isRemote)
			{
				par3.addPotionEffect(new PotionEffect(potion[i]));
			}
		}
	}


	
	

}
