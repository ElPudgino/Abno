package pzi.abno.rendering;

import org.lwjgl.opengl.GL11;

import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Vector3;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import pzi.abno.Abno;
import pzi.abno.entity.EntityFireballMagic;
import pzi.abno.loading.ClientProxy;
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
        Vector3 color = new Vector3(1.0, 0.11, 0.24);
        float time = (fireball.ticksExisted + partialTicks) * 0.02f;
        Matrix4 trs = AbnoRenderUtils.ToMatrix(x, y, z, MathExtra.AlignWithVelocity(fireball, new Vector3(0,1,0)), fireball.ScaleX, fireball.ScaleY, fireball.ScaleZ);

        ClientProxy.MeshesToRender.add(new RenderMeshCall("fb_inner", trs, ShaderUtils.FireballInner, ResourcesHelper.GetShaderTextureLocation("melt.png"), time, color,
                                                            GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, false));

        ClientProxy.MeshesToRender.add(new RenderMeshCall("fb_outer", trs, ShaderUtils.FireballOuter, ResourcesHelper.GetShaderTextureLocation("milky.png"), time, color,
                                                            GL11.GL_SRC_ALPHA, GL11.GL_ONE, false));

        ClientProxy.MeshesToRender.add(new RenderMeshCall("fb_side", trs, ShaderUtils.FireballSide, ResourcesHelper.GetShaderTextureLocation("melt.png"), time, color,
                                                            GL11.GL_SRC_ALPHA, GL11.GL_ONE, false));
        
        ClientProxy.MeshesToRender.add(new RenderMeshCall("fb_conetop", trs, ShaderUtils.FireballCenter, ResourcesHelper.GetShaderTextureLocation("milky.png"), time * 6, color,
                                                            GL11.GL_SRC_ALPHA, GL11.GL_ONE, false));
    }

    @Override
	protected ResourceLocation getEntityTexture(EntityFireballMagic entity) {
		return null;
	}
}
