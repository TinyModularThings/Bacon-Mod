package minecraftwero.baconMod.common.lib;

/**
 * 
 * @author MinecraftWero, Speiger
 *
 */
public class BaconTextures 
{
	public static String rawBacon = getTexture("rawBacon");
	public static String cookedBacon = getTexture("cookedBacon");
	public static String baconHelmet = getTexture("baconHelmet");
	public static String baconLegs = getTexture("baconLegs");
	public static String baconBoots = getTexture("baconBoots");
	public static String baconPlate = getTexture("baconPlate");
	public static String baconPickAxe = getTexture("baconPickaxe");
	public static String baconAxe = getTexture("baconAxe");
	public static String baconSpade = getTexture("baconSpade");
	public static String baconSword = getTexture("baconSword");
	public static String baconHoe = getTexture("baconHoe");
	public static String gayBacon = getTexture("gayBacon");
	public static String fineSword = getTexture("fineSword");
	public static String baconDisc = getTexture("baconDisc");
	public static String nutella = getTexture("nutella");
	public static String baconNutella = getTexture("baconNutella");
	public static String sawMill = getTexture("sawMill");
	public static String rottedBacon = getTexture("poisonedBacon");
	public static String rottedPorkChop = getTexture("poisonedPorkchop");
	public static String cheeseWheel = getTexture("cheeseWheel");
	public static String[] baconMaker = getMultiTexture(6, "BaconMakerBottom", "BaconMakerTop", "BaconMakerFront", "BaconMakerBack", "BaconMakerSide", "BaconMakerFrontActive");
	public static String cheeseSlice = getTexture("cheeseSlice");
	public static String baconSandwich = getTexture("baconSandwich");
	public static String baconBlock = getTexture("baconBlock");
	public static String cookedBaconBlock = getTexture("cookedBaconBlock");
	public static String[] baconSandwitch = getMultiTexture(4, "baconsandwich_bottom","baconsandwich_top", "baconsandwich_side", "baconsandwich_sidecut");
	public static String baconSapling = getTexture("baconSapling");
	public static String[] baconLog = getMultiTexture(2, "baconLogTop", "baconLogSide");
	public static String[] baconLeaves = getMultiTexture(3, "NotGrowenBaconLeaves", "GrowenBaconLeaves", "OverGrowenBaconLeaves");
	public static String magicBacon = getTexture("magicBacon");
	public static String baconDonut = getTexture("baconDonut");
	public static String baconPlanks = getTexture("baconPlanks");
	public static String[] baconChest = getMultiTexture(6, "foodcontainer_front", "foodcontainer_top", "foodcontainer_bottom", "foodcontainer_side1", "foodcontainer_side2", "foodcontainer_side3");
	public static String simpleDonut = getTexture("donut");

	/**
	 * @Example: public static String itemName = getTexture("pictureName");
	 * @param par1 Texture Name
	 * @return Icon Texture String
	 */
	public static String getTexture(String par1)
	{
			return BaconLib.modID+":"+par1;
	}
	
	/**
	 * @Example: public static String[] texture = ngetMultiTexture(SIZE, "picturenames");
	 * @param par1 Size (How many textures you want to add
	 * @param par2 Textures itself! every commar does add an extraone!
	 * @return
	 */
	
	public static String[] getMultiTexture(int par1, String...par2)
	{
		String[] output = new String[par1];
		for(int i = 0; i<par1;i++)
		{
			output[i] = BaconLib.modID+":"+par2[i];
		}
		return output;
	}
	
}
