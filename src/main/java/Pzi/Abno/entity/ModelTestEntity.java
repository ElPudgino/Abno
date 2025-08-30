package pzi.abno.entity;

import net.minecraft.util.ResourceLocation;
import pzi.abno.Abno;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelTestEntity extends AnimatedGeoModel<TestEntity> {
    @Override
    public ResourceLocation getModelLocation(TestEntity entityIfriti) {
        return new ResourceLocation(Abno.MODID, "geo/testentity.geo.json");
    }
    @Override
    public ResourceLocation getTextureLocation(TestEntity entityIfriti) {
        return new ResourceLocation(Abno.MODID, "textures/entities/testentity.png");
    }
    @Override
    public ResourceLocation getAnimationFileLocation(TestEntity entityIfriti) {
        return new ResourceLocation(Abno.MODID, "animations/testentity.animation.json");
    }
}
