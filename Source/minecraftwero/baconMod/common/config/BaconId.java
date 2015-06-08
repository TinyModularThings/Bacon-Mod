package minecraftwero.baconMod.common.config;


public class BaconId 
{
	public static BaconItemRegister bir;
	public static BaconConfig bc;
	
	public static void getIds()
	{
		//Items
		
		bir.NutellaBaconID = bc.getItem(bc.items, "Nutella on Bacon", 23998);
		bir.GayBaconID = bc.getItem(bc.items, "Gay Bacon", 23999);
		bir.RawBaconID = bc.getItem(bc.items, "Raw Bacon", 24000);
		bir.CookedBaconID = bc.getItem(bc.items, "Cooked Bacon", 24001);
		bir.BaconShoesID = bc.getItem(bc.items, "Bacon Boots", 24002);
		bir.BaconLegginsID = bc.getItem(bc.items, "Bacon Leggings", 24003);
		bir.BaconPlateID = bc.getItem(bc.items, "Bacon Chest Plate", 24004);
		bir.BaconHelmetID = bc.getItem(bc.items, "Bacon Helmet", 24005);
		bir.BaconPickaxeID = bc.getItem(bc.items, "Bacon Pickaxe", 24006);
		bir.BaconAxeID = bc.getItem(bc.items, "Bacon Axe", 24007);
		bir.BaconShovelID = bc.getItem(bc.items, "Bacon Shovel", 24008);
		bir.BaconSwordID = bc.getItem(bc.items, "Bacon Sword", 24009);
		bir.BaconHoeID = bc.getItem(bc.items, "Bacon Hoe", 24010);
		bir.FineSwordID = bc.getItem(bc.items, "Fine Sword", 24011);
		bir.BaconDiscID = bc.getItem(bc.items, "Bacon Disk", 24012);
		
		bir.NutellaID = bc.getItem(bc.items, "Nutella", 24014);
		bir.SawMillID = bc.getItem(bc.items, "Saw Blade", 24015);
		bir.PoisonBaconID = bc.getItem(bc.items, "Poisen Bacon", 24016);
		bir.PoisonPorkchopID = bc.getItem(bc.items, "Poisen Porkchop", 24017);
		bir.CheeseWheelID = bc.getItem(bc.items, "Cheese Wheel", 24018);
		bir.CheeseSliceID = bc.getItem(bc.items, "Cheese Slice", 24019);
		bir.BaconSandwichID = bc.getItem(bc.items, "Bacon Sandwich", 24020);
		bir.MagicBaconID = bc.getItem(bc.items, "Magic Bacon", 24021);
		bir.BaconDonutID = bc.getItem(bc.items, "Bacon Donut", 24022);
		bir.SimpleDonutID = bc.getItem(bc.items, "Simple Donut", 24023);
		
		//Blocks
		bir.baconBlocksID = bc.getBlock(bc.blocks, "Bacon Maker", 2000);
		bir.BaconSaplingID = bc.getBlock(bc.blocks, "Bacon Sapling", 2001);
		bir.heaterID = bc.getBlock(bc.blocks, "Bacon Machines", 2002);
		
		//Block Disabeling
		bir.BaconMaker = bc.getBoolean(bc.registry, "Allow Bacon Maker", true);
		bir.RawBaconBlock = bc.getBoolean(bc.registry, "Allow Raw Bacon Block", true);
		bir.CookedBaconBlock = bc.getBoolean(bc.registry, "Allow Cooked Bacon Block", true);
		bir.BaconPlanks = bc.getBoolean(bc.registry, "Allow Bacon Planks", true);
		bir.BaconLog = bc.getBoolean(bc.registry, "Allow Bacon Log", true);
		bir.baconChest = bc.getBoolean(bc.registry, "Allow Food Container", true);
		bir.BaconLeaves = bc.getBoolean(bc.registry, "Allow Bacon Leaves", true);
		bir.heater = bc.getBoolean(bc.registry, "Allow Heater", true);
		bir.allowLiquidBacon = bc.getBoolean(bc.registry, "Allow Liquid Bacon", true);
	}
	

	
	
	
	
}
