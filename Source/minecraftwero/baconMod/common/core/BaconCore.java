package minecraftwero.baconMod.common.core;

import minecraftwero.baconMod.Bacon;
import minecraftwero.baconMod.client.gui.GuiBaconChest;
import minecraftwero.baconMod.client.gui.GuiBaconMaker;
import minecraftwero.baconMod.client.gui.GuiHeater;
import minecraftwero.baconMod.common.core.handlers.WorldGenBaconTree;
import minecraftwero.baconMod.common.entities.EntitySloth;
import minecraftwero.baconMod.common.inventory.ContainerBaconChest;
import minecraftwero.baconMod.common.inventory.ContainerBaconMaker;
import minecraftwero.baconMod.common.inventory.ContainerHeater;
import minecraftwero.baconMod.common.lib.BaconGuiIDs;
import minecraftwero.baconMod.common.tile.BaconChest;
import minecraftwero.baconMod.common.tile.BaconMaker;
import minecraftwero.baconMod.common.tile.Heater;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;



/**
 * 
 * @author MinecraftWero, Speiger
 *
 */
public class BaconCore implements IGuiHandler
{
	
	public static int startEntityId = 400;
	public WorldGenBaconTree tree = new WorldGenBaconTree();
	
	
	public void registerRenderInformation()
	{
	}
	
	
	public int getArmorTexture(String s)
	{
		return 0;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if(ID == BaconGuiIDs.baconMaker)return new ContainerBaconMaker(player.inventory, (BaconMaker)tile);
		if(ID == BaconGuiIDs.baconChest)return new ContainerBaconChest(player.inventory, (BaconChest)tile);
		if(ID == BaconGuiIDs.heater)return new ContainerHeater(player.inventory, (Heater)tile);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if(ID == BaconGuiIDs.baconMaker)return new GuiBaconMaker(player.inventory, (BaconMaker)tile);
		if(ID == BaconGuiIDs.baconChest)return new GuiBaconChest(player.inventory, (BaconChest)tile);
		if(ID == BaconGuiIDs.heater)return new GuiHeater(player.inventory, (Heater)tile);
		return null;
	}


	public void registerEntities() 
	{
		EntityRegistry.registerGlobalEntityID(EntitySloth.class, "Sloth", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntitySloth.class, "Sloth", 577, Bacon.instance, 70, 1, true);
		EntityRegistry.addSpawn(EntitySloth.class, 90, 1, 2, EnumCreatureType.creature, BiomeGenBase.plains, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.forest, BiomeGenBase.forestHills);
		registerEntityEgg(EntitySloth.class, 0xe9be6c, 0xc3ff00);
	}
	
	public void registerEverything()
	{
		GameRegistry.registerWorldGenerator(tree, 1);
	}

	
	
	public static int getUniqueEntityId() {

		do {

			startEntityId++;

		} while (EntityList.getStringFromID(startEntityId) != null);

		return startEntityId;
	}
	
	public static void registerEntityEgg(Class<? extends Entity> entity,
			int primaryColor, int secondaryColor) {

		int id = getUniqueEntityId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEgg(null, id, primaryColor,
				secondaryColor));

	}


}