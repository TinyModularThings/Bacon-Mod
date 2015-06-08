package minecraftwero.baconMod.client.model;
 
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
 
import org.lwjgl.opengl.GL11;
 
public class ModelSloth extends ModelBase {
        // fields
        ModelRenderer WolfHead;
        ModelRenderer Body;
        ModelRenderer leftarm;
        ModelRenderer rightarm;
        ModelRenderer leg2;
        ModelRenderer leg1;
 
        public ModelSloth() {
                textureWidth = 256;
                textureHeight = 128;
 
                WolfHead = new ModelRenderer(this, 38, 0);
                WolfHead.addBox(-3F, -3F, -2F, 6, 6, 4);
                WolfHead.setRotationPoint(-1F, 20.5F, -7F);
                WolfHead.setTextureSize(256, 128);
                WolfHead.mirror = true;
                setRotation(WolfHead, 0F, 0F, 0F);
                Body = new ModelRenderer(this, 45, 14);
                Body.addBox(-4F, -2F, -3F, 6, 10, 5);
                Body.setRotationPoint(0F, 21F, -3F);
                Body.setTextureSize(256, 128);
                Body.mirror = true;
                setRotation(Body, 1.570796F, 0F, 0F);
                leftarm = new ModelRenderer(this, 0, 50);
                leftarm.addBox(-1F, 0F, -1F, 11, 2, 2);
                leftarm.setRotationPoint(-11.5F, 22F, -6F);
                leftarm.setTextureSize(256, 128);
                leftarm.mirror = true;
                setRotation(leftarm, 0F, -0.5205006F, 0F);
                rightarm = new ModelRenderer(this, 0, 60);
                rightarm.addBox(-1F, 0F, -1F, 11, 2, 2);
                rightarm.setRotationPoint(0.5F, 22F, -1F);
                rightarm.setTextureSize(256, 128);
                rightarm.mirror = true;
                setRotation(rightarm, 0F, 0.3717861F, 0F);
                leg2 = new ModelRenderer(this, 9, 18);
                leg2.addBox(-1F, 0F, -1F, 2, 2, 8);
                leg2.setRotationPoint(0F, 22F, 4F);
                leg2.setTextureSize(256, 128);
                leg2.mirror = true;
                setRotation(leg2, 0F, 0.669215F, 0F);
                leg1 = new ModelRenderer(this, 0, 0);
                leg1.addBox(0F, 0F, 0F, 2, 2, 8);
                leg1.setRotationPoint(-3F, 22F, 1F);
                leg1.setTextureSize(256, 128);
                leg1.mirror = true;
                setRotation(leg1, 0F, -0.4089647F, 0F);
        }
 
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
 
        if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 5.0F * par7, 2.0F * par7);
            this.WolfHead.render(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            this.Body.render(par7);
            this.leg1.render(par7);
            this.leg2.render(par7);
            this.leftarm.render(par7);
            this.rightarm.render(par7);
            GL11.glPopMatrix();
        }
        else
        {
            this.WolfHead.renderWithRotation(par7);
            this.Body.render(par7);
            this.leg1.render(par7);
            this.leg2.render(par7);
            this.leftarm.render(par7);
            this.rightarm.render(par7);
 
        }
    }
 
        private void setRotation(ModelRenderer model, float x, float y, float z)
        {
                model.rotateAngleX = x;
                model.rotateAngleY = y;
                model.rotateAngleZ = z;
        }
       
        public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
        {
                super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
                leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
                leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.0F * f1;
                leg1.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
                leg2.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.0F * f1;
        }
 
 
        public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
          {
               
                this.WolfHead.rotateAngleY = par4 / (180F / (float)Math.PI);
                this.WolfHead.rotateAngleX = par5 / (180F / (float)Math.PI);
                float f6 = ((float)Math.PI / 4F);
                this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
                this.leg1.rotateAngleY = 0.0F;
                this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
                this.leg2.rotateAngleY = 0.0F;
                this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
                this.rightarm.rotateAngleY = 0.0F;
                this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
                this.leftarm.rotateAngleY = 0.0F;
}
}