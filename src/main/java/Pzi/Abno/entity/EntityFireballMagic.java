package pzi.abno.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFireballMagic extends Entity implements IProjectile
{
    public double ScaleX = 1;
    public double ScaleY = 1;
    public double ScaleZ = 1;
    public double Power;
    public boolean CollideEntities;
    public double Durability;

    public EntityFireballMagic(World worldIn) 
    {
        super(worldIn);
    }

    @Override
    protected void entityInit() 
    {
        return;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        motionX += 0.01;
        motionY += 0.01;
        motionZ -= 0.01;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) 
    {
        ScaleX = compound.getDouble("ScaleX");
        ScaleY = compound.getDouble("ScaleY");
        ScaleZ = compound.getDouble("ScaleZ");
        Power = compound.getDouble("Power");
        Durability = compound.getDouble("Dur");
        CollideEntities = compound.getBoolean("CollideEntities");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) 
    {
        compound.setDouble("ScaleX", ScaleX);
        compound.setDouble("ScaleY", ScaleY);
        compound.setDouble("ScaleY", ScaleZ);
        compound.setDouble("Power", Power);
        compound.setDouble("Dur", Durability);
        compound.setBoolean("CollideEntities", CollideEntities);
    }
    
    @Override
	public boolean canBeAttackedWithItem() {
		return false;
	}

    @Override
	protected boolean canTriggerWalking() {
		return false;
	}

    
    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) 
    {
        float f2 = MathHelper.sqrt(x * x + y * y + z * z);
		x /= f2;
		y /= f2;
		z /= f2;
		x += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
		y += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
		z += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
		x *= velocity;
		y *= velocity;
		z *= velocity;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
    }
    
}
