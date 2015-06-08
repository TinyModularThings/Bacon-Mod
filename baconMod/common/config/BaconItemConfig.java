package minecraftwero.baconMod.common.config;

import minecraftwero.baconMod.common.blocks.BlockBaconMaker;
import minecraftwero.baconMod.common.blocks.BlockBaconSapling;
import minecraftwero.baconMod.common.blocks.MachineHeater;
import minecraftwero.baconMod.common.entities.FineSwordDamageSource;
import minecraftwero.baconMod.common.fluid.fluids.FluidBacon;
import minecraftwero.baconMod.common.items.AdvancedBacon;
import minecraftwero.baconMod.common.items.BaconArmor;
import minecraftwero.baconMod.common.items.BaconRecords;
import minecraftwero.baconMod.common.items.BaconSword;
import minecraftwero.baconMod.common.items.BaconTool;
import minecraftwero.baconMod.common.items.CraftingFood;
import minecraftwero.baconMod.common.items.ItemBaconFood;
import minecraftwero.baconMod.common.items.ItemBlade;
import minecraftwero.baconMod.common.items.ItemBlockBaconMachine;
import minecraftwero.baconMod.common.items.ItemBlockBaconSapling;
import minecraftwero.baconMod.common.items.ItemBlockMachineHeater;
import minecraftwero.baconMod.common.items.ItemFineSword;
import minecraftwero.baconMod.common.lib.BaconLib;
import minecraftwero.baconMod.common.lib.BaconTextures;
import minecraftwero.baconMod.common.tile.BaconCake;
import minecraftwero.baconMod.common.tile.BaconChest;
import minecraftwero.baconMod.common.tile.BaconLeave;
import minecraftwero.baconMod.common.tile.BaconLog;
import minecraftwero.baconMod.common.tile.BaconMaker;
import minecraftwero.baconMod.common.tile.Heater;
import minecraftwero.baconMod.common.tile.Melter;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fluids.FluidRegistry;

import com.minecraftwero.bacon.Bacon;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BaconItemConfig 
{
	public static BaconItems bi;
	public static BaconConfig bc;
	public static BaconTextures bt;
	public static BaconItemRegister bir;
	public static void loadItems() 
	{
		if(bir.GayBaconID > 0)
		{
			bi.gayBacon = (new ItemBaconFood(bir.GayBaconID, 4, true, "Gay Bacon", bt.gayBacon, new PotionEffect(Potion.digSpeed.id, 3000, 1), new PotionEffect(Potion.confusion.id, 1500, 1), new PotionEffect(Potion.moveSpeed.id, 3000, 1), new PotionEffect(Potion.weakness.id, 3000, 1), new PotionEffect(Potion.regeneration.id, 600, 1), new PotionEffect(Potion.heal.id, 600, 1)));
			GameRegistry.registerItem(bi.gayBacon, "Gay Bacon");	
		}
		
		if(bir.RawBaconID > 0)
		{
			bi.rawBacon = (new ItemBaconFood(bir.RawBaconID, 3, true, "Raw Bacon", bt.rawBacon));
			GameRegistry.registerItem(bi.rawBacon, "Raw Bacon");	
		}
		
		if(bir.CookedBaconID > 0)
		{
			bi.cookedBacon = (new ItemBaconFood(bir.CookedBaconID, 5, true, "Cooked Bacon", bt.cookedBacon));
			GameRegistry.registerItem(bi.cookedBacon, "Cooked Bacon");
		}
		
		if(bir.BaconShoesID > 0)
		{
			bi.baconBoots = (new BaconArmor(bir.BaconShoesID, bi.baconArmor, Bacon.core.getArmorTexture("bacon"), 3, bt.baconBoots, "Bacon Boots"));
			GameRegistry.registerItem(bi.baconBoots, "Bacon Boots");
		}
		
		if(bir.BaconLegginsID > 0)
		{	
			bi.baconLegs = (new BaconArmor(bir.BaconLegginsID, bi.baconArmor, Bacon.core.getArmorTexture("bacon"), 2, bt.baconLegs, "Bacon Leggings"));
			GameRegistry.registerItem(bi.baconLegs, "Bacon Legs");
		}

		if(bir.BaconPlateID > 0)
		{
			bi.baconPlate = (new BaconArmor(bir.BaconPlateID, bi.baconArmor, Bacon.core.getArmorTexture("bacon"), 1, bt.baconPlate, "Bacon Chest Plate"));
			GameRegistry.registerItem(bi.baconPlate, "Bacon Plate");
		}
		
		if(bir.BaconHelmetID > 0)
		{
			bi.baconHelmet = (new BaconArmor(bir.BaconHelmetID, bi.baconArmor, Bacon.core.getArmorTexture("bacon"), 0, bt.baconHelmet, "Bacon Helmet"));
			GameRegistry.registerItem(bi.baconHelmet, "Bacon Helmet");
		}
		
		if(bir.BaconPickaxeID > 0)
		{
			bi.baconPickaxe = new BaconTool(bir.BaconPickaxeID, bt.baconPickAxe, "Bacon Pickaxe", "pickaxe", 1, 5F, 100);
			GameRegistry.registerItem(bi.baconPickaxe, "Bacon Pickaxe");
		}
		
		if(bir.BaconAxeID > 0)
		{
			bi.baconAxe = new BaconTool(bir.BaconAxeID, bt.baconAxe, "Bacon Axe", "axe", 1, 5F, 100);
			GameRegistry.registerItem(bi.baconAxe, "Bacon Axe");
		}
		
		if(bir.BaconShovelID > 0)
		{
			bi.baconSpade = new BaconTool(bir.BaconShovelID, bt.baconSpade, "Bacon Shovel", "shovel", 1, 5F, 100);
			GameRegistry.registerItem(bi.baconSpade, "Bacon Spade");
		}
		
		if(bir.BaconSwordID > 0)
		{
			bi.baconSword = new BaconSword(bir.BaconSwordID, "Bacon Sword", bt.baconSword);
			GameRegistry.registerItem(bi.baconSword, "Bacon Sword");
		}
		
		if(bir.BaconHoeID > 0)
		{
			bi.baconHoe = new BaconTool(bir.BaconHoeID, bt.baconHoe, "Bacon Hoe", "hoe", 1, 5F, 100);
			GameRegistry.registerItem(bi.baconHoe, "Bacon Hoe");
		}
		
		if(bir.FineSwordID > 0)
		{
			bi.fineSword = (new ItemFineSword(bir.FineSwordID,bt.fineSword ,"Fine Sword"));
			bi.fineSword.setContainerItem(bi.fineSword);
			GameRegistry.registerItem(bi.fineSword, "Fine Sword");
		}

		if(bir.BaconDiscID > 0)
		{
			bi.baconDisk = new BaconRecords(bir.BaconDiscID, BaconLib.modID+":BaconPancakes", "Bacon Disc", bt.baconDisc);
			GameRegistry.registerItem(bi.baconDisk, "BaconPancakes");
		}
		
		if(bir.SawMillID > 0)
		{
			bi.sawMill = new ItemBlade (bir.SawMillID, 6000, "Saw Blade", bt.sawMill);
			GameRegistry.registerItem(bi.sawMill, "Saw Blade");
		}
		
		if(bir.PoisonBaconID > 0)
		{
			bi.rottedBacon = new ItemBaconFood(bir.PoisonBaconID, 2, false, "Poisend Bacon", bt.rottedBacon, new PotionEffect(Potion.hunger.id, 200, 1), new PotionEffect(Potion.confusion.id, 1500, 1));
			GameRegistry.registerItem(bi.rottedBacon, "Poisoned Bacon");
		}
		
		if(bir.PoisonPorkchopID > 0)
		{
			bi.rottedPorkChop = new ItemBaconFood(bir.PoisonPorkchopID, 2, false, "Poisend Porkchop", bt.rottedPorkChop , new PotionEffect(Potion.hunger.id, 200, 1), new PotionEffect(Potion.confusion.id, 1500, 1));
			GameRegistry.registerItem(bi.rottedPorkChop, "Poisoned Porkchop");
		}

		
		if(bir.baconBlocksID > 0)
		{
			bi.baconMachine = new BlockBaconMaker("Bacon Maker", bt.baconMaker);
			GameRegistry.registerBlock(bi.baconMachine, ItemBlockBaconMachine.class, "Bacon Converter");
			GameRegistry.registerTileEntity(BaconMaker.class, "BM");
			GameRegistry.registerTileEntity(BaconCake.class, "BC");
			GameRegistry.registerTileEntity(BaconLog.class, "BL");
			GameRegistry.registerTileEntity(BaconLeave.class, "BCL");
			GameRegistry.registerTileEntity(BaconChest.class, "BCC");
		}
		
		if(bir.BaconSaplingID > 0)
		{
			bi.baconSapling = new BlockBaconSapling(bir.BaconSaplingID);
			GameRegistry.registerBlock(bi.baconSapling, ItemBlockBaconSapling.class, "Bacon Sapling");
		}
		
		if(bir.CheeseWheelID > 0)
		{
			bi.cheeseWheel = new CraftingFood(bir.CheeseWheelID, 8, 1, true, "Cheese Wheel", bt.cheeseWheel);
			bi.cheeseWheel.setContainerItem(bi.cheeseWheel);
			GameRegistry.registerItem(bi.cheeseWheel, "Cheese Wheel");
		}
		
		if(bir.NutellaBaconID > 0)
		{
			bi.baconNutella = (new ItemBaconFood(bir.NutellaBaconID, 4, true, "Nutella on Bacon", bt.baconNutella));
			GameRegistry.registerItem(bi.baconNutella, "Nutella on Bacon");
		}
		
		if(bir.NutellaID > 0)
		{
			bi.nutella = new CraftingFood(bir.NutellaID, 10, 2, true, "Nutella", bt.nutella);
			bi.nutella.setContainerItem(bi.nutella);
			GameRegistry.registerItem(bi.nutella, "Nutella");
		}
		
		if(bir.CheeseSliceID > 0)
		{
			bi.cheeseSlice = new ItemBaconFood(bir.CheeseSliceID, 1, true, "Cheese Slice", bt.cheeseSlice);
			GameRegistry.registerItem(bi.cheeseSlice, "Cheese Slice");
		}
		
		if(bir.BaconSandwichID > 0)
		{
			bi.baconSandwich = new ItemBaconFood(bir.BaconSandwichID, 5, true, "Bacon Sandwich", bt.baconSandwich);
			GameRegistry.registerItem(bi.baconSandwich, "Bacon Sandwich");
		}
		
		if(bir.MagicBaconID > 0)
		{
			bi.magicBacon = new AdvancedBacon(bir.MagicBaconID);
			GameRegistry.registerItem(bi.magicBacon, "Magic Bacon");
		}
		
		if(bir.BaconDonutID > 0)
		{
			bi.baconDonut = new ItemBaconFood(bir.BaconDonutID, 7, true, "Bacon Donut", bt.baconDonut);
			GameRegistry.registerItem(bi.baconDonut, "Bacon Donut");
		}
		
		if(bir.SimpleDonutID > 0)
		{
			bi.simpleDonut = new ItemBaconFood(bir.SimpleDonutID, 1, true, "Simple Donut", bt.simpleDonut);
			GameRegistry.registerItem(bi.simpleDonut, "Simple Donut");
		}
		
		if(bir.heaterID > 0)
		{
			bi.heater = new MachineHeater(bir.heaterID);
			GameRegistry.registerBlock(bi.heater, ItemBlockMachineHeater.class, "Heater");
			GameRegistry.registerTileEntity(Heater.class, "Heater");
			GameRegistry.registerTileEntity(Melter.class, "Melter");
		}
		
		//DamageSource
		bi.FineSword = new FineSwordDamageSource("fineSword");
		LanguageRegistry.instance().addStringLocalization("death.attack.fineSword", "You chop off your own arm!!");
	
		if(bir.allowLiquidBacon)
		{
			bi.liquidBacon = new FluidBacon("Liquid Bacon");
			FluidRegistry.registerFluid(bi.liquidBacon);
		}
	}

}