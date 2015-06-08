package minecraftwero.baconMod.common.core.handlers;

import java.util.Random;

import minecraftwero.baconMod.common.config.BaconItems;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DeathDrops
{
	public Random rand = new Random();
	
	@SubscribeEvent
	public void onDeath(LivingDeathEvent e)
	{
		if(e.entityLiving instanceof EntityPig && e.source.getEntity() instanceof EntityPlayer)
		{
			EntityPig pig = (EntityPig) e.entityLiving;
			EntityPlayer player = (EntityPlayer) e.source.getEntity();
			if(player.getCurrentEquippedItem() == null ||(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem().itemID != BaconItems.fineSword.itemID))
			{
				if(rand.nextBoolean() == true)
				{
					if(BaconItems.rawBacon != null)
					{
						pig.dropItem(BaconItems.rawBacon.itemID, 1);
						return;
					}
				}
			}
			
			if(rand.nextInt(3) == 0)
			{
				if(BaconItems.rottedBacon != null)
				{
					pig.dropItem(BaconItems.rottedBacon.itemID, 1);
					return;
				}
			}
			else if(rand.nextInt(3) == 1)
			{
				if(BaconItems.rottedPorkChop != null)
				{
					pig.dropItem(BaconItems.rottedPorkChop.itemID, 1);
					return;
				}
				
			}
		}
	}
	
	//TODO Add the same for Cows when the cow item is outside
}
