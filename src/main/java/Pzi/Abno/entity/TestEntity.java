package pzi.abno.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class TestEntity extends EntityAbno {
        protected static final AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.testentity.test_idle", true);
        protected static final AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.testentity.test_walk", true);

        private final AnimationFactory factory = new AnimationFactory(this);

        public TestEntity(World world) {
            super(world);
        }

        @Override
        protected void initEntityAI()
        {
            this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
            this.tasks.addTask(1, new EntityAISwimming(this));
            this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
            this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
            this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
            this.tasks.addTask(6, new EntityAILookIdle(this));
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
            
        }
    
        @Override
        protected void applyEntityAttributes() {
            super.applyEntityAttributes();
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0f);
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25f);
        }

        @Override
        public void registerControllers(final AnimationData data) {
            data.addAnimationController(new AnimationController<>(this, "controller", 5, this::MainController));
        }
        
        protected <E extends TestEntity> PlayState MainController(final AnimationEvent<E> event) {
            if (event.isMoving()) {
                event.getController().setAnimation(WALK_ANIM);
                return PlayState.CONTINUE;
            }
            else
            {
                event.getController().setAnimation(IDLE_ANIM);
                

                return PlayState.CONTINUE;
            }

            //return PlayState.STOP;
        }
    
        @Override
        public AnimationFactory getFactory() {
            return this.factory;
        }
}