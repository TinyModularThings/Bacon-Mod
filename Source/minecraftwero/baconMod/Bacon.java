package minecraftwero.baconMod;

import java.io.File;

import minecraftwero.baconMod.common.config.BaconConfig;
import minecraftwero.baconMod.common.core.BaconCore;
import minecraftwero.baconMod.common.core.BaconTab;
import minecraftwero.baconMod.common.core.handlers.BoneMealEvent;
import minecraftwero.baconMod.common.core.handlers.DeathDrops;
import minecraftwero.baconMod.common.core.handlers.SoundHandler;
import minecraftwero.baconMod.common.lib.BaconLib;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = BaconLib.modID, name = BaconLib.modName, version = BaconLib.modVersion)
/**
 * 
 * @author MinecraftWero, Speiger
 *
 */
public class Bacon 
{

    @SidedProxy(clientSide = BaconLib.clientSide, serverSide = BaconLib.coreSide)
    public static BaconCore core;
    
    @Instance(BaconLib.modID)
    public static Bacon instance;
    
    public static CreativeTabs baconMod = new BaconTab(CreativeTabs.getNextID(), BaconLib.modID);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent par1)
    {
        BaconConfig.loadBaconConfig(new File(par1.getModConfigurationDirectory(), "BaconConfig.cfg"));
    }
    
    //FIXME Fix the Textures of the BaconCake
    
    @EventHandler
    public void load(FMLInitializationEvent evt)
    {
        instance = this;
        NetworkRegistry.INSTANCE.registerGuiHandler(this, core);
        
        
//        BaconRecipes.loadRecipes();
        
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
        MinecraftForge.EVENT_BUS.register(new BoneMealEvent());
        MinecraftForge.EVENT_BUS.register(new DeathDrops());
        core.registerEverything();
        // PortalGun, Make sure "minecraftwero.common.BaconMod" always matches the class (including package) where the static item reference is found. //
        // PortalGun, Make sure "rawBacon" is the same name as the item field you want to be used in the recipe. //
        cpw.mods.fml.common.event.FMLInterModComms.sendMessage("PortalGun", "BaconMod", "minecraftwero.baconMod.common.config.BaconItems, rawBacon");
        core.registerRenderInformation();
        core.registerEntities();
    }
    
    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent evt) 
    {
//        if(Loader.isModLoaded("IC2"))
//        {
//            GameRegistry.addRecipe(new ItemStack(BaconItems.magicBacon), new Object[] {"XCX", "CYC", "XCX", 'C', Item.netherStar,  'X', Block.blockEmerald, 'Y', BaconItems.cookedBacon});
//        }
//        else
//        {
//            GameRegistry.addRecipe(new ItemStack(BaconItems.magicBacon), new Object[] {"XXX", "XYX", "XXX", 'X', Item.emerald, 'Y', BaconItems.cookedBacon});
//        }
    }
    
    
}