package pzi.abno.rendering;

import net.minecraft.client.renderer.entity.RenderManager;
import pzi.abno.entity.ModelTestEntity;

public class RenderTestEntity extends RenderEntityAbno
{
    public RenderTestEntity(RenderManager renderManager)
    {
        super(renderManager, new ModelTestEntity());
    }

}
