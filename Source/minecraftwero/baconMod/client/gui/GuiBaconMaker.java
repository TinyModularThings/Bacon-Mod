package minecraftwero.baconMod.client.gui;

import minecraftwero.baconMod.common.inventory.ContainerBaconMaker;
import minecraftwero.baconMod.common.tile.BaconMaker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiBaconMaker extends GuiContainer {

	private BaconMaker tile;
    private static final ResourceLocation gui = new ResourceLocation("minecraftwero:textures/gui/BaconMaker.png");
    
    
	public GuiBaconMaker(InventoryPlayer par1, BaconMaker par2) 
    {
		super(new ContainerBaconMaker(par1, par2));
		tile = par2;
		
    }

	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString("Bacon Maker", 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
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

        if(tile.getFuel(10) > 0)
        {
            i1 = this.tile.getFuel(10);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);

        }

        i1 = this.tile.getProgressBar(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        
    }
}