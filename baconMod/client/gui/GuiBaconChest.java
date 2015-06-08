package minecraftwero.baconMod.client.gui;

import minecraftwero.baconMod.common.inventory.ContainerBaconChest;
import minecraftwero.baconMod.common.tile.BaconChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GuiBaconChest extends GuiContainer
{
	private BaconChest tile;
	public GuiBaconChest(InventoryPlayer par1, BaconChest par2) 
	{
		super(new ContainerBaconChest(par1, par2));
		tile = par2;
		ySize = 221;
	}
	
    private static final ResourceLocation gui = new ResourceLocation("minecraftwero:textures/gui/BaconChest.png");
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString("Bacon Chest", 2, 6, 4210752);
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
    }
}
