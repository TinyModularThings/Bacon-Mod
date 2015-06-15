package minecraftwero.baconMod.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityBacon extends TileEntity 
{
	public int facing = 0;
	
    @Override
	public Packet getDescriptionPacket() 
    {
    	NBTTagCompound var1 = new NBTTagCompound();
    	writeToNBT(var1);
    	var1.setInteger("rotation", facing);
    	return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}
   
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) 
	{
		this.readFromNBT(pkt.data);
	}
	
	@Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        facing = par1NBTTagCompound.getInteger("rotation");
    }

	@Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("rotation", facing);
    }
	
	public void setFacing(int i)
	{
		facing = i;
	}
	
	public int getFacing()
	{
		return facing;
	}
}
