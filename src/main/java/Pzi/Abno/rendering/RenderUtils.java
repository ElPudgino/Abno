package pzi.abno.rendering;

import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderUtils {
    public static List<Double> QuadVerts = Arrays.asList(-0.5, 0.5, 0.5, 0.5, 0.5, -0.5, -0.5, -0.5);

    

    public static void RenderQuad(double x, double y, double z, double size, ResourceLocation texLocation, float shader_time)
    {
        GlStateManager.pushMatrix();
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableRescaleNormal();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);  

        Tessellator tessellator = Tessellator.getInstance();
        ShaderUtils.EnableShaderProg(ShaderUtils.TestShader, shader_time);
        BufferBuilder builder = tessellator.getBuffer();

        // disable depth write for transparent objects or draw them later 

		builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);

        if (texLocation != null)
        {
            Minecraft.getMinecraft().renderEngine.bindTexture(texLocation);
        }
        

        for (int i = 0; i < 4; i++)
        {
            builder.pos(size * QuadVerts.get(2*i) + x, size * QuadVerts.get(2*i+1) + y, 0+z ).tex(QuadVerts.get(2*i)+0.5, QuadVerts.get(2*i+1)+0.5).normal(0.0F, 1.0F, 0.0F).endVertex();
        }
        
        tessellator.draw();

        ShaderUtils.DisableShaderProg();

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableCull();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
    }

   
    
}
