package minecraftwero.baconMod.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class BaconLeave extends TileEntity 
{
	public int currentGroth = 0;
	
    @Override
	public Packet getDescriptionPacket() 
    {
    	NBTTagCompound var1 = new NBTTagCompound();
    	writeToNBT(var1);
    	var1.setInteger("Groth", currentGroth);
    	return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
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
        currentGroth = par1NBTTagCompound.getInteger("Groth");
    }

	@Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Groth", currentGroth);
    }
	
	public void setCurrentGroth(int i)
	{
		currentGroth = i;
	}
	
	public int getGroth()
	{
		return currentGroth;
	}

	@Override
	public void updateEntity() 
	{
		super.updateEntity();
		
		if(worldObj.rand.nextInt(20) == 0)
		{
			if(currentGroth > 10000)
			{
				return;
			}
			else
			{
				currentGroth++;			
			}

		}
		
		
	}
	
	
	
	

}
