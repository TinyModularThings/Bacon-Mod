package minecraftwero.baconMod.common.items;

import minecraftwero.baconMod.Bacon;
import minecraftwero.baconMod.common.config.BaconItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import com.google.common.collect.Multimap;
import com.ibm.icu.util.BytesTrie.Result;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author MinecraftWero,  Speiger
 *
 */
public class BaconTool extends Item 
{
	public String name;
	public String texture;
	public float speed;
	
	public BaconTool(int par1, String par2, String par3, String toolkind, int strenght, float miningspeed, int maxDamage) 
	{
		super();
		this.setCreativeTab(Bacon.baconMod);
		this.setMaxDamage(maxDamage);
		this.setMaxStackSize(1);
		this.setNoRepair();
		name = par3; 
		texture = par2;
		speed = miningspeed;
		this.setTextureName(par2);
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack) 
	{
		return name;
	}
	
	@SideOnly(Side.CLIENT)
	
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(texture);
	}
	
    public float getStrVsBlock(ItemStack stack, Block block, int meta) 
    {
        if (ForgeHooks.isToolEffective(stack, block, meta))
        {
            return speed;
        }
        return getStrVsBlock(stack, block);
    }

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) 
	{
		return 1F;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) 
	{
		if(BaconItems.baconHoe == null && par3.canEat(false))
		{
			par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
			return par1;
		}
		
		if(par1.getItem() == BaconItems.baconHoe && par3.canEat(false) && par3.isSneaking())
		{
			par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
			
		}
		
		if(par3.canEat(false) && par1.getItem() != BaconItems.baconHoe)
		{
			par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
		}
		return par1;
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

	@Override
	public boolean hitEntity(ItemStack par1, EntityLivingBase par2, EntityLivingBase par3) 
	{
		par1.damageItem(2, par3);
		
		return true;
	}

	
	public boolean onBlockDestroyed(ItemStack par1, World par2, int par3, int par4, int par5, int par6, EntityLivingBase par7) 
	{
		par1.damageItem(1, par7);
		return true;
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass)
    {
		return 1;
		
    }
	
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else if(BaconItems.baconHoe != null && par1ItemStack.getItem() != BaconItems.baconHoe)
        {
        	return false;
        }
        else if(par2EntityPlayer.isSneaking())
        {
        	return false;
        }
        else
        {
            UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return false;
            }

            if (event.getResult() == cpw.mods.fml.common.eventhandler.Event.Result.ALLOW)
            {
                par1ItemStack.damageItem(1, par2EntityPlayer);
                return true;
            }

            Block i1 = par3World.getBlock(par4, par5, par6);
            Block j1 = par3World.getBlock(par4, par5 + 1, par6);

            if ((par7 == 0 || j1 != Blocks.air || i1 != Blocks.grass && i1 != Blocks.dirt))
            {
                return false;
            }
            else
            {
                Block block = Blocks.farmland;
                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

                if (par3World.isRemote)
                {
                    return true;
                }
                else
                {
                    par3World.setBlock(par4, par5, par6, block);
                    par1ItemStack.damageItem(1, par2EntityPlayer);
                    return true;
                }
            }
        }
    }
    
	@Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap par1 = super.getItemAttributeModifiers();
		par1.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool Modifire", 2, 0));
		return par1;
	}

}
