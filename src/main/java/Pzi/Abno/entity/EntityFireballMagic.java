package pzi.abno.entity;


import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import pzi.abno.Abno;

public class EntityFireballMagic extends Entity implements IProjectile
{
    public static final DamageSource MAGIC_EXP = (new DamageSource("explosionMagic")).setExplosion().setMagicDamage();
    public static final DamageSource MAGIC_PIERCE = (new DamageSource("pierceMagic")).setMagicDamage().setProjectile();
    
    public double ScaleX = 1;
    public double ScaleY = 1;
    public double ScaleZ = 1;
    public double Power = 20.0;
    public boolean CollideEntities = true;
    public double Durability = 16.0;
    public Entity Owner = null;

    private final AxisAlignedBB bb = new AxisAlignedBB(0,0,0,0,0,0).grow(Math.max(ScaleX, ScaleY)/2);

    public EntityFireballMagic(World worldIn) 
    {
        super(worldIn);
        setSize((float)Math.max(ScaleX, ScaleY), (float)Math.max(ScaleX, ScaleY)/2f);
    }

    @Override
    protected void entityInit() 
    {        
        return;
    }

    @Override
    public void onUpdate()
    {
        motionX = 0.1f;
        motionY = -0.2f;
        //motionY = 0f;
      
        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        
        setEntityBoundingBox(bb.offset(posX,posY,posZ));
        
        if (!world.isRemote)
        {
            CheckCollisions();
        }

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        //super.onUpdate();      
    }

    private void CheckCollisions()
    {
        Abno.logger.info(Power);
        Abno.logger.info("----");
        Abno.logger.info(Durability);
        for (EntityLivingBase e : world.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox(),null))
        {
            if (e == null || e.getUniqueID() == this.getUniqueID() || (Owner != null && e.getUniqueID() == Owner.getUniqueID()))
            {
                continue;
            } 
            //Abno.logger.info("collided with " + e.getName());
            if (CollideEntities && Durability < 20.0)
            {
                Detonate();
            }  
            DamageCollidedEntity(e); 
            
        }
        if (world.collidesWithAnyBlock(getEntityBoundingBox()))
        {
            //Abno.logger.info("breaking blocks");
            BreakObstacles();
        }
    }

    @SuppressWarnings("deprecation")
    private void BreakObstacles()
    {
        AxisAlignedBB aabb = getEntityBoundingBox();
        for (int x = (int)Math.floor(aabb.minX); x <= (int)Math.floor(aabb.maxX); x++)
        {
            for (int y = (int)Math.floor(aabb.minY); y <= (int)Math.floor(aabb.maxY); y++)
            {
                for (int z = (int)Math.floor(aabb.minZ); z <= (int)Math.floor(aabb.maxZ); z++)
                {
                    BlockPos bp = new BlockPos(x, y, z);
                    if (!world.isAirBlock(bp))
                    {
                        IBlockState bs = world.getBlockState(bp); 
                       
                        //Abno.logger.info("break b");
                        float blast_r = bs.getBlock().getExplosionResistance(this);
                        Durability -= blast_r;
                        if (Durability < 0)
                        {
                            Detonate();
                            return;
                        }
                        else
                        {
                            world.destroyBlock(bp, false);
                        }
                    }
                }
            }
        }
    }

    private void Detonate()
    {
        for (EntityLivingBase e : world.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().grow(Math.sqrt(Power)/1.5), null))
        {
            if (e == null || e.getUniqueID() == this.getUniqueID() || (Owner != null && e.getUniqueID() == Owner.getUniqueID()))
            {
                continue;
            }
            double dist = e.getPositionVector().subtract(getPositionVector()).length();
            float dmg = (float)(Power*Math.sqrt(Power)*4 / ((Math.sqrt(Power)/1.5) + dist));
            if (e.isImmuneToExplosions())
            {
                dmg = dmg / 5.0f;
            }
            e.attackEntityFrom(MAGIC_EXP, dmg);
            e.hurtResistantTime = 0;
        }
        world.newExplosion(this, posX, posY, posZ, (float)(Math.sqrt(Power)/1.5), true, true);
        this.setDead();
    }

    private void DamageCollidedEntity(EntityLivingBase e)
    {
        if (e.isDead)
        {
            return;
        }
        Vec3d dir = e.getPositionVector().subtract(getPositionVector());
        double l = dir.length() * 20.0;
        dir = dir.normalize();
        double dmg = Power / 5.0;
        if (e.isImmuneToFire())
        {
            dmg = dmg / 2.0;
        }
        e.attackEntityFrom(MAGIC_PIERCE, (float)dmg);
        e.hurtResistantTime = 4;
        e.addVelocity(Power * dir.x / l,Power * dir.y / l, Power * dir.z / l);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) 
    {
       
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) 
    {
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
