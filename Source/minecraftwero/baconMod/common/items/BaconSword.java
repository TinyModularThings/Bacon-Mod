package minecraftwero.baconMod.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;
import com.minecraftwero.bacon.Bacon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author MinecraftWero
 *
 */
public class BaconSword extends Item 
{
	public String name;
	public String texture;
	
	public BaconSword(int par1, String par2, String par3) 
	{
		super();
		name = par2;
		this.setCreativeTab(Bacon.baconMod);
		this.setMaxStackSize(1);
		setMaxDamage(100);
		this.setNoRepair();
		texture = par3;
		this.setTextureName(par3);
	}
	
	


	@Override
	public boolean hitEntity(ItemStack par1, EntityLivingBase par2, EntityLivingBase par3) 
	{
		par1.damageItem(1, par3);
		if(par2 instanceof EntityAnimal)
		{
			EntityAnimal animal = (EntityAnimal) par2;
			if(animal.getHealth() > 0)
			{
				animal.setHealth(1);
			}
		}
		return true;
	}

	public boolean onBlockDestroyed(ItemStack par1, World par2, int par3, int par4, int par5, int par6, EntityLivingBase par7) 
	{
		par1.damageItem(3, par7);
		return true;
	}

	
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        if (par2Block.blockID == Block.web.blockID)
        {
            return 15.0F;
        }
        return 1F;
    }
    
    public EnumAction getItemUseAction(ItemStack par1)
    {
        return EnumAction.eat;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) 
	{
		if(par3.canEat(false))
		{
			par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
		}
		return par1;
	}
	
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }
    
	@Override
	public ItemStack onEaten(ItemStack par1, World par2, EntityPlayer par3) 
	{
		par1.damageItem(20, par3);
		par3.getFoodStats().addStats(2, 0.3F);
        par2.playSoundAtEntity(par3, "random.burp", 0.5F, par2.rand.nextFloat() * 0.1F + 0.9F);
        if(par1.getItemDamage() > 50)
        {
        	par3.addPotionEffect(new PotionEffect(Potion.weakness.id, 800, 2));
        }
		return par1;
	}

	public String getItemDisplayName(ItemStack par1ItemStack) 
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
	public Multimap getItemAttributeModifiers()
	{
		Multimap par1 = super.getItemAttributeModifiers();
		par1.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon Modifire", 2, 0));
		return par1;
	}

}

