package minecraftwero.baconMod.client.gui;

import minecraftwero.baconMod.common.inventory.ContainerHeater;
import minecraftwero.baconMod.common.tile.Heater;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiHeater extends GuiContainer 
{
	private Heater heat;
    public GuiHeater(InventoryPlayer par1, Heater par2) 
    {
		super(new ContainerHeater(par1, par2));
		heat = par2;
    }

	private static final ResourceLocation gui = new ResourceLocation("minecraftwero:textures/gui/Heater.png");
    
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString("Heater", 4, 4, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 4, 4210752);
        this.fontRendererObj.drawString("Temp: "+heat.heaterHeat+"°", 86, ySize - 93, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(gui);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        if(heat.hasLava())
        {
        	i1 = heat.getLava(10);
        	this.drawTexturedModalRect(k + 34, l + 60 + 12 - i1, 176, 146 - i1, 16, i1 + 2);
        }
        
        if(heat.hasLavaHeat())
        {
        	i1 = heat.getLavaHeat(10);
        	this.drawTexturedModalRect(k + 60, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = heat.getTemperatur(10);
        this.drawTexturedModalRect(k + 87, l + 58 - i1, 176, 64 - i1, 6, i1 + 2);
        
        if(heat.hasSolidHeat())
        {
        	i1 = heat.getSolidFuel(10);
        	this.drawTexturedModalRect(k + 106, l + 50 - i1, 176, 12 - i1, 14, i1 + 2);
        }
        
    }
    
    
}
