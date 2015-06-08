package minecraftwero.baconMod.client.render;

import minecraftwero.baconMod.client.model.ModelSandwich;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemRendererBaconSandwich implements IItemRenderer
{
	public ModelSandwich model = new ModelSandwich();
	
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		if(item.getItemDamage() == 3)
		{
			switch (type) 
			{
				case ENTITY:
					return true;
				case EQUIPPED:
					return true;
				case EQUIPPED_FIRST_PERSON:
					return true;
				case INVENTORY:
					return true;
				default:
					return false;
			}
		}
		else
		{
			return false;
		}

	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		if(item.getItemDamage() == 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		if(item.getItemDamage() == 3)
		{
			switch(type)
			{
				case ENTITY:
					renderSandwich(-0.5F, -0.5F, -0.5F);
					break;
				case EQUIPPED:
					renderSandwich(-0.4f, 0.50f, 0.35f);
					break;
				case EQUIPPED_FIRST_PERSON:
					renderSandwich(-0.4f, 0.50f, 0.35f);
					break;
				case INVENTORY:
					renderSandwich(-0.5F, -0.5F, -0.5F);
					break;
				default:
			}
		}
	}
	
	private static final ResourceLocation texture = new ResourceLocation("minecraftwero:textures/models/ModelSandwich.png");
	
	public void renderSandwich(float par1, float par2, float par3)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par1 + 0.5f, (float) par2 + 1.5f, (float) par3 + 0.5f);
		GL11.glScalef(1.0F, 1F, 1F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}

}
