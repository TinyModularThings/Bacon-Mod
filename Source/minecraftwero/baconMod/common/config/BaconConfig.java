package minecraftwero.baconMod.common.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;
/**
 * 
 * @author Speiger
 *
 */
public class BaconConfig 
{
	public static final String general = "general";
	public static final String items = "items";
	public static final String blocks = "blocks";
	public static final String registry = "item & block register";
	public static Configuration config;
	
	public static void loadBaconConfig(File par1)
	{
		config = new Configuration(par1);
		try
		{
			config.load();
			
			BaconId.getIds();
			
			BaconItemConfig.loadItems();
		}
		catch(Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "[BaconMod] Could not load the Bacon Config!");
		}
		finally
		{
			Save();
		}
		
	}
	
    public static int getItem(String var0, String var1, int var2)
    {
        return config.getItem(var0, var1, var2).getInt();
    }

    public static boolean getBoolean(String var0, String var1, boolean var2)
    {
        return config.get(var0, var1, var2).getBoolean(var2);
    }

    public static int getBlock(String var0, String var1, int var2)
    {
        return config.getBlock(var0, var1, var2).getInt();
    }
	
	public static void Save()
	{
		config.save();
	}
	
	

}
