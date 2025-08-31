package pzi.abno.entity;

import net.minecraft.util.ResourceLocation;
import pzi.abno.Abno;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelEntityAbno extends AnimatedGeoModel<EntityAbno> {
    @Override
    public ResourceLocation getModelLocation(EntityAbno entity) {
        return new ResourceLocation(Abno.MODID, GetModelLocationStr());
    }
    @Override
    public ResourceLocation getTextureLocation(EntityAbno entity) {
        return new ResourceLocation(Abno.MODID, GetTextureLocationStr());
    }
    @Override
    public ResourceLocation getAnimationFileLocation(EntityAbno entity) {
        return new ResourceLocation(Abno.MODID, GetAnimationLocationStr());
    }

    public String GetModelLocationStr()
    {
        Abno.logger.error("Model location was not set");
        return "";
    }
    public String GetTextureLocationStr()
    {
        Abno.logger.error("Texture location was not set");
        return "";
    }
    public String GetAnimationLocationStr()
    {
        Abno.logger.error("Animation location was not set");
        return "";
    }
}
