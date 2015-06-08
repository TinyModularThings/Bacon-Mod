package minecraftwero.baconMod.common.entities;

import minecraftwero.baconMod.common.config.BaconItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class EntitySloth extends EntityTameable
{
    public EntitySloth(World par1World)
    {
        super(par1World);
        this.setSize(0.9F, 1.3F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.3D, 3.0F, 5.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 0.3D));
        this.tasks.addTask(7, new EntityAIWander(this, 0.3D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
    }
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, new Float(this.getHealth()));
        this.dataWatcher.addObject(19, new Byte((byte)0));
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    
    public boolean isAIEnabled()
    {
        return true;
    }
    protected void updateAITick()
    {
        this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
    }
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!this.worldObj.isRemote && !this.hasPath() && this.onGround)
        {
          this.worldObj.setEntityState(this, (byte)8);
        }
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.30000001192092896D);

        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
        }
    }

    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

        if (this.isTamed())
        {
            if (itemstack != null)
            {
                if (itemstack.itemID == Block.leaves.blockID || itemstack.itemID == Block.sapling.blockID)
                {
                  

                    if (this.dataWatcher.getWatchableObjectFloat(18) < 25)
                    {
                        if (!par1EntityPlayer.capabilities.isCreativeMode)
                        {
                            --itemstack.stackSize;
                        }
                        
                        this.heal(2);
                
                        if (itemstack.stackSize <= 0)
                        {
                            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                        }

                        return true;
                    }
                }
                else
                {
                    if (par1EntityPlayer.username.equalsIgnoreCase(this.getOwner()) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
                    {
                    	
                    	
                    	if(this.isSitting())
                    	{
                    		this.aiSit.setSitting(false);
                    		System.out.print("Disable Sit");
                    		par1EntityPlayer.addChatComponentMessage("I will follow you");
                    	}
                    	else
                    	{
                    		this.aiSit.setSitting(true);
                    		System.out.print("Activate Sitting");
                    		par1EntityPlayer.addChatMessage("Ok i will stay");
                    	}
           
                        System.out.println("true");
                        this.isJumping = false;
                        this.setPathToEntity((PathEntity)null);
                    }
                }
            }

        }
        else if (itemstack != null && itemstack.itemID == Item.appleGold.itemID && itemstack.getItemDamage() == 0)
        {
            if (!par1EntityPlayer.capabilities.isCreativeMode)
            {
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0)
            {
                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0)
                {
                    this.setTamed(true);
                    this.setPathToEntity((PathEntity)null);
                    this.aiSit.setSitting(true);
                    this.setHealth(25);
                    this.setOwner(par1EntityPlayer.username);
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }
        
        return super.interact(par1EntityPlayer);
        
    }
    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.cow.step", 0.15F, 1.0F);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return Item.leather.itemID;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(2);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.dropItem(Item.leather.itemID, 1);
        }

        j += this.rand.nextInt(1 + par2);

        for (k = 0; k < j; ++k)
        {
            if (this.isBurning())
            {
                this.dropItem(BaconItems.rawBacon.itemID, 1);
            }
            else
            {
            	this.dropItem(Item.beefRaw.itemID, 1);
            }

        }
    }

    public void writeEntityToNBT(NBTTagCompound par1)
    {
        super.writeEntityToNBT(par1);
        
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
    }
    
    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public EntitySloth spawnBabyAnimal(EntityAgeable par1EntityAgeable)
    {
        return new EntitySloth(this.worldObj);
    }
    
    public boolean isBreedingItem(ItemStack par1)
    {
    	if(par1 != null)
    	{
    		if(par1.getItem() == Item.appleRed)
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }
    
    public void func_70918_i(boolean par1)
    {
        if (par1)
        {
            this.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
        }
    }

}
