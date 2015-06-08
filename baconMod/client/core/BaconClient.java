package minecraftwero.baconMod.client.core;

import minecraftwero.baconMod.client.model.ModelSloth;
import minecraftwero.baconMod.client.render.ItemRendererBaconSandwich;
import minecraftwero.baconMod.client.render.RenderSloth;
import minecraftwero.baconMod.common.config.BaconItemRegister;
import minecraftwero.baconMod.common.config.BaconItems;
import minecraftwero.baconMod.common.core.BaconCore;
import minecraftwero.baconMod.common.core.helper.HeaterHelper;
import minecraftwero.baconMod.common.entities.EntitySloth;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
/**
 * 
 * @author MinecraftWero
 *
 */
public class BaconClient extends BaconCore
{
	@Override
	public void registerRenderInformation()
	{
		if(BaconItems.baconMachine != null && BaconItemRegister.BaconSandwichBlock)
		{
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BaconItems.baconMachine), new ItemRendererBaconSandwich());
		}
		RenderingRegistry.registerEntityRenderingHandler(EntitySloth.class, new RenderSloth(new ModelSloth(), 0.362F));
		MinecraftForge.EVENT_BUS.register(new HeaterHelper());
	}

	
	@Override
	public int getArmorTexture(String s) 
	{
		return RenderingRegistry.addNewArmourRendererPrefix(s);
	}

}


