package pzi.abno.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderTestEntity extends GeoEntityRenderer<TestEntity> {
    public RenderTestEntity(RenderManager renderManager) {
        super(renderManager, new ModelTestEntity());
    }
}