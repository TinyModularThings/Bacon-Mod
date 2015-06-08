package minecraftwero.baconMod.common.recipes;

import minecraftwero.baconMod.common.config.BaconItemRegister;
import minecraftwero.baconMod.common.config.BaconItems;
import minecraftwero.baconMod.common.core.handlers.BaconMakerRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class BaconRecipes 
{

	public static void loadRecipes() 
	{
		if(BaconItems.baconHoe != null && BaconItems.cookedBacon != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconHoe, 1),new Object[] { "BB", "S ", "S ", 'B', BaconItems.cookedBacon,'S', Item.stick });
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconHoe, 1),new Object[] { "BB", " S", " S", 'B', BaconItems.cookedBacon,'S', Item.stick });
		}
		
		if(BaconItems.baconPickaxe != null && BaconItems.cookedBacon != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconPickaxe, 1),new Object[] { "BBB", " S ", " S ", 'B', BaconItems.cookedBacon,'S', Item.stick });
		}
		
		if(BaconItems.baconAxe != null && BaconItems.cookedBacon != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconAxe, 1),new Object[] { "BB", "SB", "S ", 'B', BaconItems.cookedBacon,'S', Item.stick });
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconAxe, 1),new Object[] { "BB", "BS", " S", 'B', BaconItems.cookedBacon,'S', Item.stick });
		}
		
		if(BaconItems.baconSpade != null && BaconItems.cookedBacon != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconSpade, 1),new Object[] { "B", "S", "S", 'B', BaconItems.cookedBacon,'S', Item.stick });
		}
		
		if(BaconItems.baconHelmet != null && BaconItems.cookedBacon != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconHelmet, 1),new Object[] { "BBB", "B B", "   ", 'B',BaconItems.cookedBacon });
		}
		
		if(BaconItems.baconPlate != null && BaconItems.cookedBacon != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconPlate, 1), new Object[] {"B B", "BBB", "BBB", 'B', BaconItems.cookedBacon });
		}
		
		if(BaconItems.baconLegs != null && BaconItems.cookedBacon != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconLegs, 1), new Object[] {"BBB", "B B", "B B", 'B', BaconItems.cookedBacon });
		}
	
		if(BaconItems.baconBoots != null && BaconItems.cookedBacon != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconBoots, 1), new Object[] { "B B", "B B", 'B', BaconItems.cookedBacon });
		}
		
		if(BaconItems.fineSword != null)
		{		
			GameRegistry.addRecipe(new ItemStack(BaconItems.fineSword,1), new Object [] {"CX", "CX", "C ", 'C', Item.stick, 'X', Item.ingotIron});
		}

		if(BaconItems.baconSword != null && BaconItems.cookedBacon !=null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconSword, 1), new Object[] {"B", "B", "S", 'B', BaconItems.cookedBacon,'S', Item.stick });
		}
		
		if(BaconItems.nutella != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.nutella, 1), new Object[] {"SSS", "CCC", " M ", 'S', Item.sugar, 'C', new ItemStack(Item.dyePowder, 1, 3), 'M', Item.bucketMilk});
		}
		
		if(BaconItems.baconNutella != null && BaconItems.cookedBacon !=null && BaconItems.nutella != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconNutella, 1), new Object[] {"B", "N", 'B', BaconItems.cookedBacon, 'N', new ItemStack(BaconItems.nutella, 1, OreDictionary.WILDCARD_VALUE)});
		}
		
		if(BaconItems.sawMill != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.sawMill, 1), new Object[] {"III", "IWI", "III", 'W', Item.ingotIron, 'I', Block.fenceIron});
		}
		
		if(BaconItems.baconMachine != null && BaconItemRegister.BaconMaker)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconMachine, 1), new Object[]{"BDB", "C C", "YXY", 'D', Item.stick, 'B', Block.woodenButton, 'X', Block.furnaceIdle, 'Y', Block.stone, 'C', Block.pistonBase});
		}
		
		if(BaconItems.gayBacon != null && BaconItems.rawBacon !=null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.gayBacon, 1), new Object[]{" S ", "SRS", " S ", 'S', Item.sugar, 'R', BaconItems.rawBacon});
		}
		
		if(BaconItems.baconSandwich != null && BaconItems.cookedBacon !=null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconSandwich, 1), new Object[]{" B ", " C ", " B ", 'B', Item.bread, 'C', BaconItems.cookedBacon});
		}
		
		if(BaconItems.cheeseSlice != null && BaconItems.cheeseWheel !=null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.cheeseSlice, 1), new Object[]{" F ", " C ",  'C',new ItemStack(BaconItems.cheeseWheel, 1, OreDictionary.WILDCARD_VALUE), 'F', new ItemStack(BaconItems.fineSword, 1, OreDictionary.WILDCARD_VALUE)});
			for(int i = 0; i<9;i++)
			{
				BaconMakerRecipes.getRecipes().addMillRecipe(new ItemStack(BaconItems.cheeseWheel, 1, i), new ItemStack(BaconItems.cheeseSlice, 9 - i));
			}
		}
		
		if(BaconItems.baconMachine != null && BaconItemRegister.RawBaconBlock)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconMachine, 1, 1), new Object[]{"XXX", "XXX", "XXX", 'X', new ItemStack(BaconItems.rawBacon)});
		}
		
		if(BaconItems.baconMachine != null && BaconItemRegister.CookedBaconBlock)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconMachine, 1, 2), new Object[]{"XXX", "XXX", "XXX", 'X', new ItemStack(BaconItems.cookedBacon)});
		}
		if(BaconItemRegister.BaconLog && BaconItems.baconMachine != null)
		{
			OreDictionary.registerOre("logWood", new ItemStack(BaconItems.baconMachine, 1, 4));
		}
		
		if(BaconItems.baconMachine != null && BaconItemRegister.BaconPlanks && BaconItemRegister.BaconLog)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconMachine, 4, 7), new Object[]{"XXX", "XXX", "XXX", 'X', new ItemStack(BaconItems.baconMachine, 1, 4)});
			OreDictionary.registerOre("plankWood", new ItemStack(BaconItems.baconMachine, 1, 7));
		}
		
		if(BaconItems.baconDonut != null && BaconItems.cookedBacon != null && BaconItems.nutella != null && BaconItems.simpleDonut != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconDonut, 1), new Object []{"BBB", " N ", " S ", 'B', BaconItems.cookedBacon, 'N', new ItemStack (BaconItems.nutella,1, OreDictionary.WILDCARD_VALUE), 'S', BaconItems.simpleDonut});
		}
		
		if(BaconItems.baconSapling != null && BaconItems.cookedBacon !=null && BaconItems.rawBacon !=null && BaconItems.gayBacon !=null && BaconItems.magicBacon !=null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconSapling, 1), new Object[]{"RCR", "NSN", "MCM", 'R', BaconItems.rawBacon, 'C', BaconItems.cookedBacon, 'N', BaconItems.gayBacon, 'M', BaconItems.magicBacon, 'S', Block.sapling});
		}
		
		if(BaconItems.simpleDonut != null)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.simpleDonut, 2), new Object[]{"WWW", "WBW", "WWW", 'W', Item.wheat, 'B', Item.bread});
		}
		
		if(BaconItems.rawBacon != null && BaconItems.cookedBacon != null)
		{
			GameRegistry.addSmelting(BaconItems.rawBacon.itemID, new ItemStack(BaconItems.cookedBacon), 0.1F);
		}
		
		if(BaconItems.rawBacon != null)
		{
			BaconMakerRecipes.getRecipes().addMillRecipe(new ItemStack(Item.porkRaw),  new ItemStack(BaconItems.rawBacon, 3));
		}
		
		if(BaconItems.cheeseWheel != null)
		{
			GameRegistry.addSmelting(Item.bucketMilk.itemID, new ItemStack(BaconItems.cheeseWheel), 0.1F);
		}
		
		if(BaconItems.baconMachine != null && BaconItemRegister.baconChest)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconMachine, 1, 6), new Object[]{"XCX", "XVX", "XCX", 'X', Item.bucketWater, 'V', Block.chest, 'C', BaconItems.rawBacon});
		}
		
		if(BaconItems.baconMachine != null && BaconItemRegister.BaconSandwichBlock)
		{
			GameRegistry.addRecipe(new ItemStack(BaconItems.baconMachine, 1, 3), new Object[]{" X ", "XYX", " X ", 'X', BaconItems.baconSandwich, 'Y', BaconItems.cookedBacon});
		}
		
		


		
		
	}

	
}
