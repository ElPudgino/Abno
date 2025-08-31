package pzi.abno.entity;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;

public abstract class EntityAbno extends EntityMob implements IAnimatable 
{
    public double lookPosX;
    public double lookPosY;
    public double lookPosZ; 
    public boolean looking = false;

    public List<EntityLivingBase> ActiveTargets = new ArrayList<EntityLivingBase>();

    public EntityAbno(World world)
    {
        super(world);
    }

    protected void GeneralModifyVisuals()
    {
        EntityLivingBase elb = this.getAttackTarget();
        if (elb != null)
        {      
            lookPosX = elb.posX;
            lookPosY = elb.posY;
            lookPosZ = elb.posZ;
            looking = false;
        }
    }

    protected void GeneralAI()
    {
        if (this.getAttackTarget() == null && ActiveTargets.size() > 0)
        {
            this.setAttackTarget(ActiveTargets.get(0));
        }
    }

        
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (this.world.isRemote)
        {
            GeneralModifyVisuals();
        }   
        GeneralAI();
    }
        
}
