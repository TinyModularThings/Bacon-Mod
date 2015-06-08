package minecraftwero.baconMod.common.items;

import com.minecraftwero.bacon.Bacon;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CraftingFood extends Item  
{

	private String var1;
	private String var2;
	private int var3;
	private boolean var4;
	private int var5;
	private int var6;
	private PotionEffect[] var7;
	public CraftingFood(int par1, int damage, int damageonCraft, int damageonEat, int extrafood, boolean alwaysEatable, String name, String texture, PotionEffect...effect) 
	{
		super();
		this.setCreativeTab(Bacon.baconMod);
		this.setNoRepair();
		this.var5 = damageonCraft;
		var6 = damageonEat;
		this.setMaxDamage(damage);
		this.setMaxStackSize(1);
		var1 = name;
		var2 = texture;
		var3 = extrafood;
		var4 = alwaysEatable;
		var7 = effect;
		this.setTextureName(texture);
	}
	
	public CraftingFood(int par1, int damage, int extrafood, boolean alwaysEatable, String name, String texture)
	{
		this(par1, damage, 1, 1, extrafood, alwaysEatable, name, texture);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) 
	{
		return false;
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack) 
	{
		return var1;
	}
	
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }
    
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.eat;
    }
    
	@Override
	public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) 
	{
		if(par3.canEat(var4))
		{
			par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
		}
		return par1;
	}
		
	
	@Override
	public ItemStack onEaten(ItemStack par1, World par2, EntityPlayer par3) 
	{
		par1.damageItem(var6, par3);
		par3.getFoodStats().addStats(var3, 0.6F);
        par2.playSoundAtEntity(par3, "random.burp", 0.5F, par2.rand.nextFloat() * 0.1F + 0.9F);
        addPotionEffect(par1, par2, par3);
		return par1;
	}
	
	public void addPotionEffect(ItemStack par1, World par2, EntityPlayer par3) 
	{
		for(int i = 0; i<var7.length;i++)
		{
			if(var7[i].getPotionID() > 0 && var7[i] != null)
			{
				par3.addPotionEffect(var7[i]);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(var2);
	}

	public ItemStack getContainerItemStack(ItemStack itemStack) 
	{
		itemStack.setItemDamage(itemStack.getItemDamage()+var5);
		return itemStack;
		
	}


	
	
}
	
	
