package minecraftwero.baconMod.common.core.helper;

import java.util.Random;

import minecraftwero.baconMod.common.config.BaconItems;
import minecraftwero.baconMod.common.lib.BaconLib;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HeaterHelper 
{
	public static Random rand = new Random();
	
	@SubscribeEvent
	public void onTextureLoad(TextureStitchEvent.Pre evt)
	{
		BaconItems.liquidBacon.setIcons(evt.map.registerIcon(BaconLib.modID.toLowerCase()+":liquidBacon"));
	}
	
	@SubscribeEvent
	public void onTextureLoaded(TextureStitchEvent.Post evt)
	{
		BaconItems.liquidBacon.setIcons(evt.map.registerIcon(BaconLib.modID.toLowerCase()+":liquidBacon"));
	}
	
	
	public static int getTemperatureFromBiome(BiomeGenBase par1)
	{
		if(BiomeDictionary.isBiomeOfType(par1, Type.BEACH) || BiomeDictionary.isBiomeOfType(par1, Type.SWAMP) || BiomeDictionary.isBiomeOfType(par1, Type.WATER))
		{
			return 20;
		}
		else if(BiomeDictionary.isBiomeOfType(par1, Type.DESERT))
		{
			return 50;
		}
		else if(BiomeDictionary.isBiomeOfType(par1, Type.NETHER))
		{
			return 100;
		}
		else if(BiomeDictionary.isBiomeOfType(par1, Type.FROZEN))
		{
			return -10;
		}
		else if(BiomeDictionary.isBiomeOfType(par1, Type.MOUNTAIN) || BiomeDictionary.isBiomeOfType(par1, Type.HILLS))
		{
			return 5;
		}
		else if(BiomeDictionary.isBiomeOfType(par1, Type.END))
		{
			return -100;
		}
		else if(BiomeDictionary.isBiomeOfType(par1, Type.MAGICAL))
		{
			return 15;
		}
		else
		{
			return 25;
		}
	}

	public static int addHeat(int currentheat, int lavainTank, int fuelleft)
	{
		int fuel = fuelleft;
		
		int heatSwitcher = 2000 - currentheat;
		
		int lavaConvert = 0;
		
		if(heatSwitcher <= 0)
		{
			lavaConvert = lavainTank;
		}
		else
		{
			lavaConvert = lavainTank / (heatSwitcher);
		}
		
		
		
		int consume = lavaConvert * 5;
		
		if(consume >= 100)
		{
			consume = 99;
		}
		
		int consumeChanger = 100 - consume;
		
		fuel -= consumeChanger;
		if(fuel < 0)
		{
			fuel = 0;
		}
		return fuel;
	}

	public static int consumeSolidHeat(int heatSolid, int heaterHeat)
	{
		
		int fuel = heatSolid;
		if(heaterHeat<0)
		{
			int variable = heaterHeat / 20;
			fuel += 100 * variable;
		}
		else if(heaterHeat == 0)
		{
			fuel-= 100;
		}
		else
		{
			int variable = heaterHeat / 100;
			if(variable > 0)
			{
				fuel -= 100 / variable;
			}
			else
			{
				fuel-= 100+rand.nextInt(20);
			}
		}
		return fuel;
	}	
}
