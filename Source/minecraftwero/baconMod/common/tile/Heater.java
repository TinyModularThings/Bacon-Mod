package minecraftwero.baconMod.common.tile;

import java.util.Random;

import minecraftwero.baconMod.common.core.helper.HeaterHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.minecraftwero.bacon.common.tile.TileEntityBacon;

public class Heater extends TileEntityBacon implements IInventory, IFluidHandler
{

	public ItemStack[] inventory = new ItemStack[5];
	public boolean update = true;

	//Lava
	public FluidTank lava = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*16);
	public int heatLiquid = 0;
	
	//Solid Fuel
	public int heatSolid = 0;
	
	//Heat
	public int heaterHeat = 0;
	
	public int getHeat()
	{
		return heaterHeat;
	}
	
	public Heater()
	{
		lava.setFluid(new FluidStack(FluidRegistry.LAVA.getID(), 0));

	}
	
	public void setHeat(int x, int z)
	{
		BiomeGenBase biome = worldObj.getBiomeGenForCoords(x, z);
		heaterHeat = HeaterHelper.getTemperatureFromBiome(biome);
	}
	
	public int getLava(int par1)
	{
		return lava.getInfo().fluid.amount / (28 * par1);
	}
	
	public int getTemperatur(int i)
	{
		int b = 2;
		return b + (this.heaterHeat / (5*i));
	}
	
	public int getSolidFuel(int par1)
	{
		int i = heatSolid / (10 * par1);
		if(i <= 10)
		{
			return i;
		}
		return 10;
	}
	
	public boolean hasSolidHeat()
	{
		return heatSolid > 0;
	}
	
	public boolean hasLavaHeat()
	{
		return heatLiquid > 0;
	}

	public int getLavaHeat(int i)
	{
		return heatLiquid / (10 * i);
	}
	
	public boolean hasLava()
	{
		return lava.getFluidAmount() > 0;
	}
	
	@Override
	public int getSizeInventory() 
	{
        return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) 
	{
        return this.inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if (this.inventory[i] != null)
        {
            ItemStack itemstack;

            if (this.inventory[i].stackSize <= j)
            {
                itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inventory[i].splitStack(j);

                if (this.inventory[i].stackSize == 0)
                {
                    this.inventory[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }


	@Override
	public ItemStack getStackInSlotOnClosing(int i) 
	{
		if (this.inventory[i] != null)
        {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }


	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) 
	{
		   this.inventory[i] = itemstack;

	        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	        {
	        	itemstack.stackSize = this.getInventoryStackLimit();
	        }
	    }


	@Override
	public String getInvName() {

		return "Heater";
	}

	@Override
	public boolean isInvNameLocalized() {
		
		return false;
	}

	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		
		return true;
	}

	@Override
	public void openChest() 
	{
	}

	@Override
	public void closeChest() 
	{
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
		return true;
	}

    public void updateBlock()
    {
        int var1 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        this.worldObj.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
        markBlockDirty(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }
	
    public void markBlockDirty(World var0, int var1, int var2, int var3)
    {
        if (var0.blockExists(var1, var2, var3))
        {
            var0.getChunkFromBlockCoords(var1, var3).setChunkModified();
        }
    }
	
    @Override
	public Packet getDescriptionPacket() 
    {
    	NBTTagCompound var1 = new NBTTagCompound();
    	writeToNBT(var1);
    	lava.writeToNBT(var1);
    	return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}
   
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) 
	{
		this.readFromNBT(pkt.data);
	}
	
	@Override
    public void readFromNBT(NBTTagCompound par1)
    {
        super.readFromNBT(par1);
        facing = par1.getInteger("rotation");
        heaterHeat = par1.getInteger("Heat");
        heatLiquid = par1.getInteger("LiquidHeat");
        this.heatSolid = par1.getInteger("SolidFuel");
        lava.readFromNBT(par1);
        
        NBTTagList nbttaglist = par1.getTagList("Items");
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

	@Override
    public void writeToNBT(NBTTagCompound par1)
    {
        super.writeToNBT(par1);
        par1.setInteger("rotation", facing);
        par1.setInteger("Heat", heaterHeat);
        par1.setInteger("LiquidHeat", heatLiquid);
        par1.setInteger("SolidFuel", this.heatSolid);
        lava.writeToNBT(par1);
        
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1.setTag("Items", nbttaglist);
    }


	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if(resource.getFluid() == FluidRegistry.LAVA)
		{
			update = true;
			return lava.fill(resource, doFill);
			
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) 
	{
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) 
	{
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) 
	{
		if(fluid == FluidRegistry.LAVA)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) 
	{
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) 
	{
		return new FluidTankInfo[]{lava.getInfo()};
	}
	
	public Random rand = new Random();
	private int fuelleft;
	private int consumeLava;
	
	@Override
	public void updateEntity() 
	{
		super.updateEntity();
		updateBlock();
		if(!worldObj.isRemote)
		{
			fillLava();
			createSolidFuel();
			heatHeater();
			consumeLava();
			sort();
			if(lava.getFluidAmount() <= 0 && heatLiquid <= 0 && heatSolid <= 0)
			{
				coolDown();
			}
		}


		HeatHeaterSolid();
		if(update)
		{
			update = false;
			this.onInventoryChanged();
		}
	}
	
	public void sort()
	{
		if(!worldObj.isRemote)
		{
			sortInventory(4, 3);
			sortInventory(3, 2);
			sortInventory(2, 1);
		}
	}
	
	public void sortInventory(int slot0, int slot1)
	{
		if(inventory[slot0] != null)
		{
			if(inventory[slot1] == null)
			{
				inventory[slot1] = inventory[slot0].copy();
				inventory[slot0] = null;
				update = true;
			}
			else if(inventory[slot1] != null && inventory[slot1].stackSize < 64 && inventory[slot0].getItem() == inventory[slot1].getItem() && inventory[slot0].getItemDamage() == inventory[slot1].getItemDamage())
			{
				int uberfluss = inventory[slot0].stackSize - (64 - inventory[slot1].stackSize);
				if(uberfluss < 0)
				{
					uberfluss = 0;
				}
				
				inventory[slot1].stackSize = inventory[slot1].stackSize + inventory[slot0].stackSize - uberfluss;
				if(uberfluss == 0)
				{
					inventory[slot0] = null;
					update = true;
				}
				else
				{
					inventory[slot0].stackSize = uberfluss;
					update = true;
				}
			}

		}
	}
	
	public void fillLava()
	{
		
		if(lava.getFluid() != null && lava.getInfo().fluid.amount < 15000)
		{
			if(inventory[0] != null && !worldObj.isRemote)
			{
				FluidStack current = FluidContainerRegistry.getFluidForFilledItem(inventory[0]);
				if(current != null && current.getFluid() == FluidRegistry.LAVA)
				{
					lava.fill(current, true);
					inventory[0].stackSize--;
					if(inventory[0].stackSize<=0)
					{
						inventory[0] = inventory[0].getItem().getContainerItemStack(inventory[0]);
					}
				}
			}
		}
	}

	public void coolDown()
	{
		int randome = rand.nextInt(100);
		if(randome == 0)
		{
			int biometemp = HeaterHelper.getTemperatureFromBiome(worldObj.getBiomeGenForCoords(xCoord, zCoord));
			if(heaterHeat > biometemp)
			{
				heaterHeat--;
			}
			else if(heaterHeat < biometemp)
			{
				heaterHeat++;
			}
		}
	}
	
	public void heatHeater()
	{
		if(heatLiquid > 0)
		{
			heatLiquid = HeaterHelper.addHeat(heaterHeat, lava.getFluid() != null ? lava.getInfo().fluid.amount : 0, heatLiquid);
			if(rand.nextInt(35) == 0 && heaterHeat < 2000)
			{
				heaterHeat+=1;
			}
			
		}
	}
	
	public void consumeLava()
	{
		if(heatLiquid <= 0 && lava.getFluidAmount() > 0)
		{
			lava.drain(1, true);
			heatLiquid += 1000;
		}

	}

	public void HeatHeaterSolid()
	{
		if(heatSolid > 0)
		{
			heatSolid = HeaterHelper.consumeSolidHeat(heatSolid, heaterHeat);
			if(rand.nextInt(50) == 0 && heaterHeat < 1000)
			{
				heaterHeat+=1;
			}
		}
	}
	
	public void createSolidFuel()
	{
		if(heaterHeat < 999 && heatSolid <= 0)
		{
			
			if(inventory[1] != null && hasItemFuel(inventory[1]))
			{
				int fuelbetween = TileEntityFurnace.getItemBurnTime(inventory[1]);
				if(fuelbetween > 0)
				{
					heatSolid += fuelbetween;
					inventory[1].stackSize--;
					update = true;
					if(inventory[1].stackSize <= 0)
					{
						inventory[1] = inventory[1].getItem().getContainerItemStack(inventory[1]);
						update = true;
					}
				}
				

			}
		}
		update = true;
	}
	
	public boolean hasItemFuel(ItemStack par1)
	{
		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(par1);
		if(fluid != null && fluid.getFluid() == FluidRegistry.LAVA)
		{
			return false;
		}
		
		return TileEntityFurnace.isItemFuel(par1);
	}



	

}
