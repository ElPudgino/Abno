package pzi.abno.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import pzi.abno.loading.ResourcesHelper;
import pzi.abno.rendering.RenderEntityAbno;
import pzi.abno.rendering.RenderUtils;

public class RenderTestEntity extends RenderEntityAbno
{
    public RenderTestEntity(RenderManager renderManager)
    {
        super(renderManager, new ModelTestEntity());
    }

    @Override
    public void PostRender(EntityAbno entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        RenderUtils.RenderQuad(x, y + 1 + entity.getEyeHeight(), z, 0.6, ResourcesHelper.GetShaderTextureLocation("milky.png"), 0.04f*((float)entity.ticksExisted + partialTicks));
    }
}
