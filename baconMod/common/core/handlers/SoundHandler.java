package minecraftwero.baconMod.common.core.handlers;

import minecraftwero.baconMod.common.lib.BaconLib;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoundHandler
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onSoundsLoaded(SoundLoadEvent event)
	{
		event.manager.soundPoolStreaming.addSound(BaconLib.modID+":BaconPancakes.ogg");
	}
}
