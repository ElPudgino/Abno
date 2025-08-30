package pzi.abno.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import pzi.abno.Abno;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class TestEntity extends EntityMob implements IAnimatable {
        protected static final AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("animation.testentity.test_idle", true);
        protected static final AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("animation.testentity.test_walk", true);

        private final AnimationFactory factory = new AnimationFactory(this);

        public TestEntity(World world) {
            super(world);
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