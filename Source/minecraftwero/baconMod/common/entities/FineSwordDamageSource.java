package minecraftwero.baconMod.common.entities;

import net.minecraft.util.DamageSource;

public class FineSwordDamageSource extends DamageSource 
{

	public FineSwordDamageSource(String par1Str) 
	{
		super(par1Str);
		this.setDamageBypassesArmor();
	}

	
}
