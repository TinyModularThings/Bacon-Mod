package minecraftwero.baconMod.common.items;

import java.util.List;

import minecraftwero.baconMod.Bacon;
import minecraftwero.baconMod.common.lib.BaconTextures;
import minecraftwero.baconMod.common.lib.IExpBottle;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdvancedBacon extends Item implements IExpBottle 
{
	private int transferlimit;
	private int maxCharge;
	public PotionEffect[] effects = new PotionEffect[]
	{
		new PotionEffect(Potion.moveSpeed.id, 9600, 2),
		new PotionEffect(Potion.digSpeed.id, 9600, 2),
		new PotionEffect(Potion.jump.id, 9600, 2),
		new PotionEffect(Potion.resistance.id, 9600, 2),
		new PotionEffect(Potion.damageBoost.id, 9600, 2),
		new PotionEffect(Potion.fireResistance.id, 9600, 2),
		new PotionEffect(Potion.regeneration.id, 9600, 2),
		new PotionEffect(Potion.heal.id, 9600, 2),
		new PotionEffect(Potion.invisibility.id, 9600, 2)
	};
	public AdvancedBacon(int par1) 
	{
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(Bacon.baconMod);
		this.setMaxDamage(0);
		transferlimit = 1;
		maxCharge = 1000;
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1) 
	{
		this.itemIcon = par1.registerIcon(BaconTextures.magicBacon);
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
		if(par3.isSneaking())
		{
			int expLevel = par3.experienceLevel;
			if(expLevel > 0)
			{
				if(this.needsExp(par1))
				{
					
					par3.addExperienceLevel(-1);
					int expCap = par3.xpBarCap();
					int max = Math.min(this.getMaxCharge(par1), this.getStoredExp(par1) + expCap);
					this.setExp(par1, max);
					if((getMaxCharge(par1) - (getStoredExp(par1) + expCap)) < 0)
					{
						expCap += (getMaxCharge(par1) - (getStoredExp(par1) + expCap));
					}
					if(expCap > 0)
					{
						par3.addExperience(expCap);
					}
				}
				else
				{
					par3.addChatComponentMessage(new ChatComponentText("Exp Bottle is Full"));
				}
			}
			else
			{
				par3.addChatComponentMessage(new ChatComponentText("You need at least 1 Exp level"));
			}
		}
		else
		{
			if(par3.canEat(true))
			{
				par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
			}
		}
		
		return par1;
	}

	public void setExp(ItemStack par1, int amount)
	{
		NBTTagCompound par4 = par1.getTagCompound().getCompoundTag("BaconFood");
		par4.setInteger("StoredExp", amount);
	}

	@Override
	public ItemStack onEaten(ItemStack par1, World par2, EntityPlayer par3) 
	{
		NBTTagCompound par4 = par1.getTagCompound().getCompoundTag("BaconFood");
		par1.stackSize--;
        par2.playSoundAtEntity(par3, "random.burp", 0.5F, par2.rand.nextFloat() * 0.1F + 0.9F);
		par3.getFoodStats().addStats(2+(par4.getInteger("StoredExp") / 50), 2F + (par4.getInteger("StoredExp") / 75));
		if(par4.getInteger("StoredExp") > 250)
		{
			int i = (par4.getInteger("StoredExp")-100) / 100;
			for(int z = 0; z<i;z++)
			{
				if(effects[z] != null && effects[z].getPotionID() > 0 && !par2.isRemote)
				{
					par3.addPotionEffect(effects[z]);
				}
				
			}
		}
		if(par4.getInteger("StoredExp") > 500)
		{
			par3.heal(1 + (par4.getInteger("StoredExp") / 100));
		}

		return par1;
	}

	public int expformer(EntityPlayer par1)
	{
		return par1.experienceLevel >= 30 ? 62 + (par1.experienceLevel - 30) * 7 : (par1.experienceLevel >= 15 ? 17 + (par1.experienceLevel - 15) * 3 : 17);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack, int pass) 
	{
		return true;
	}

	
	public String getItemDisplayName(ItemStack par1ItemStack) 
	{
		return "Magic Bacon";
	}

	@Override
	public void charge(int i, ItemStack par1) 
	{
		NBTTagCompound par4 = par1.getTagCompound().getCompoundTag("BaconFood");
		int b = par4.getInteger("StoredExp");
		par4.setInteger("StoredExp", i+b);
		if(par4.getInteger("StoredExp") >= this.maxCharge)
		{
			par1.setItemDamage(1);
		}
	}

	@Override
	public void discharge(int i, ItemStack par1) 
	{		
		NBTTagCompound par4 = par1.getTagCompound().getCompoundTag("BaconFood");
		int b = par4.getInteger("StoredExp");
		par4.setInteger("StoredExp", b-i);
		if(par4.getInteger("StoredExp")< this.maxCharge)
		{
			par1.setItemDamage(0);
		}
	}

	@Override
	public int getTransferlimit(ItemStack par1) 
	{
		return this.transferlimit;
	}

	@Override
	public int getMaxCharge(ItemStack par1) 
	{
		return maxCharge;
	}

	@Override
	public int getStoredExp(ItemStack par1) 
	{
		NBTTagCompound par4 = par1.getTagCompound().getCompoundTag("BaconFood");
		return par4.getInteger("StoredExp");
	}


	@Override
	public void onUpdate(ItemStack par1, World par2, Entity par3, int par4, boolean par5) 
	{
		super.onUpdate(par1, par2, par3, par4, par5);
		if(!par1.hasTagCompound())
		{
			NBTTagCompound par6 = new NBTTagCompound();
			par6.setTag("BaconFood", new NBTTagCompound());
			par6.getCompoundTag("BaconFood").setInteger("StoredExp", 0);
			par1.setTagCompound(par6);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1, EntityPlayer par2, List par3, boolean par4) 
	{
		if(par1.hasTagCompound())
		{
			NBTTagCompound par5 = par1.getTagCompound().getCompoundTag("BaconFood");
			if(par5.hasKey("StoredExp"))
			{
				par3.add("Stored Exp: "+par5.getInteger("StoredExp"));
			}
		}
		par3.add("Sneak Right Click to Charge. (You do need at least 1 Level");

	}


	@Override
	public boolean hasExp(ItemStack par1)
	{
		NBTTagCompound par4 = par1.getTagCompound().getCompoundTag("BaconFood");
		return par4.getInteger("StoredExp") > 0;
	}


	@Override
	public boolean needsExp(ItemStack par1)
	{
		NBTTagCompound par4 = par1.getTagCompound().getCompoundTag("BaconFood");
		return par4.getInteger("StoredExp") < maxCharge;
	}
	
	

}
