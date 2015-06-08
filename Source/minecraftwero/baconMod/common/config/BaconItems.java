package minecraftwero.baconMod.common.config;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;



public class BaconItems 
{
	//Items//								Blocks
	public static Item rawBacon;			public static Block baconMachine;
	public static Item cookedBacon;			public static Block baconSapling;
	public static Item gayBacon;			public static Block heater;
	public static Item baconNutella;
	public static Item baconHelmet;
	public static Item baconPlate;
	public static Item baconLegs;
	public static Item baconBoots;
	public static Item baconSword;
	public static Item baconHoe;
	public static Item baconPickaxe;
	public static Item baconAxe;
	public static Item baconSpade;
	public static Item fineSword;
	public static Item baconDisk;
	public static Item nutella;
	public static Item sawMill;
	public static Item rottedBacon;
	public static Item rottedPorkChop;
	public static Item cheeseWheel;
	public static Item cheeseSlice;
	public static Item baconSandwich;
	public static Item magicBacon;
	public static Item baconDonut;
	public static Item simpleDonut;
	//TODO Add a Liquid Bacon.
	public static Fluid liquidBacon;
	
	public static ArmorMaterial baconArmor = EnumHelper.addArmorMaterial("Bacon", 60, new int[]{ 2, 5, 4, 1 }, 12);

	//Damage Sources
	public static DamageSource FineSword;
	

}
