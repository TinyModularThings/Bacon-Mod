package minecraftwero.baconMod.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;


public class BaconCake<INetworkManager> extends TileEntityBacon 
{
	public int eated = 0;
	
    @Override
	public Packet getDescriptionPacket() 
    {
    	NBTTagCompound var1 = new NBTTagCompound();
    	writeToNBT(var1);
    	var1.setInteger("Eated", eated);
    	return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}
   
	public void onDataPacket(INetworkManager net, S35PacketUpdateTileEntity pkt) 
	{
		this.readFromNBT(pkt.data);
	}
	
	@Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        eated = par1NBTTagCompound.getInteger("Eated");
    }

	@Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Eated", eated);
    }
	
	public void setEatedMore()
	{
		eated++;
	}
	
	
	
    @Override
	public void updateEntity() 
    {
		super.updateEntity();
		updateBlock();
    }

	public void updateBlock()
    {
        int var1 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        markBlockDirty(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }
	
    public void markBlockDirty(World var0, int var1, int var2, int var3)
    {
        if (var0.blockExists(var1, var2, var3))
        {
            var0.getChunkFromBlockCoords(var1, var3).setChunkModified();
        }
    }
	
	public int getEatingState()
	{
		return eated;
	}
}
