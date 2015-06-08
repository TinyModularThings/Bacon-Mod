package minecraftwero.baconMod.client.render;

import minecraftwero.baconMod.common.entities.EntitySloth;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;


public class RenderSloth extends RenderLiving
{

	private static final ResourceLocation texture = new ResourceLocation("minecraftwero:textures/models/Sloth.png");
	
    public RenderSloth(ModelBase modelbase, float f)
    {
        super(modelbase, f); 
        
    }

    public void func_177_a(EntitySloth entitySloth, double d, double d1, double d2, 
            float f, float f1)
    {
        super.doRender(entitySloth, d, d1, d2, f, f1);
    }

    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, 
            float f, float f1)
    {
    	func_177_a((EntitySloth)entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
    	func_177_a((EntitySloth)entity, d, d1, d2, f, f1);
    }


	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return texture;
	}
}