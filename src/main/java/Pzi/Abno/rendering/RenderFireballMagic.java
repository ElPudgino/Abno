package pzi.abno.rendering;

import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Vector3;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import pzi.abno.entity.EntityFireballMagic;
import pzi.abno.loading.ResourcesHelper;

public class RenderFireballMagic extends Render<EntityFireballMagic> 
{
    public RenderFireballMagic(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Override
	public void doRender(EntityFireballMagic fireball, double x, double y, double z, float entityYaw, float partialTicks) 
    {
        float time = (fireball.ticksExisted + partialTicks) * 0.1f;
        Matrix4 trs = RenderUtils.ToMatrix(x, y, z, MathExtra.AlignWithVelocity(fireball, new Vector3(0,-1,0)), fireball.ScaleX);
        RenderUtils.RenderMesh("Cone1", trs, ResourcesHelper.GetShaderTextureLocation("milky.png"), time, new Vector3(1.0, 0.11, 0.24));
    }

    @Override
	protected ResourceLocation getEntityTexture(EntityFireballMagic entity) {
		return null;
	}
}
