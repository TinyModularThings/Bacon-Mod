package minecraftwero.baconMod.common.core.handlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;

public class BaconMakerRecipes 
{
	private static BaconMakerRecipes recipe = new BaconMakerRecipes();
	
	private HashMap<List<Integer>, ItemStack> metaRecipes = new HashMap<List<Integer>, ItemStack>();
	
	public static BaconMakerRecipes getRecipes()
	{
		return recipe;
	}
	

	
	public void addMillRecipe(ItemStack input, ItemStack output)
	{
		metaRecipes.put(Arrays.asList(input.stackSize, input.getItemDamage()), output);
	}
	
	public ItemStack getRecipeOuput(ItemStack input)
	{
		ItemStack par1 = (ItemStack)metaRecipes.get(Arrays.asList(input.stackSize, input.getItemDamage()));
		if(par1 != null)
		{
			return par1;
		}
		return null;
	}
}
