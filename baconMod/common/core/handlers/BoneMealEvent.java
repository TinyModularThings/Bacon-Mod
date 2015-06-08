package minecraftwero.baconMod.common.core.handlers;

import java.util.Random;

import minecraftwero.baconMod.common.blocks.BlockBaconSapling;
import minecraftwero.baconMod.common.config.BaconItems;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BoneMealEvent 
{
	@SubscribeEvent
    public void bonemealUsed(BonemealEvent event)
    {
		if(BaconItems.baconSapling != null)
		{
		 	if(event.world.getBlockMetadata(event.x, event.y, event.z) == BaconItems.baconSapling.blockID)
		 	{
		 		((BlockBaconSapling)BaconItems.baconSapling).growTree(event.world, event.x, event.y, event.z, new Random());
		 	}
		}
    }
}
