package pzi.abno.rendering;

import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import codechicken.lib.render.CCModel;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.OBJParser;
import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Vector3;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import pzi.abno.Abno;
import pzi.abno.loading.ResourcesHelper;

public class RenderUtils {
    public static Map<String, CCModel> Models = new HashMap<String, CCModel>();

    public static List<Double> QuadVerts = Arrays.asList(-0.5, 0.5, 0.5, 0.5, 0.5, -0.5, -0.5, -0.5);

    public static void loadModels()
    {
        Models.putIfAbsent("Cone1", CCModel.combine(OBJParser.parseModels(ResourcesHelper.GetModelLocation("cone1.obj")).values()));
    }

    public static Matrix4 ToMatrix(double x, double y, double z, Rotation rot, double size)
    {
        return codechicken.lib.render.RenderUtils.getMatrix(new Vector3(x, y, z), rot, size);
    }

    public static void RenderMesh(String meshname, Matrix4 trs, ResourceLocation texLocation, float shader_time, Vector3 color)
    {
        GlStateManager.pushMatrix();
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableRescaleNormal();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);  

        
        ShaderUtils.EnableShaderProg(ShaderUtils.TestShader, shader_time, color);

        CCRenderState ccrs = CCRenderState.instance();
      
        CCModel cm = Models.get(meshname);
        
        if (texLocation != null)
        {
            Minecraft.getMinecraft().renderEngine.bindTexture(texLocation);
        }

        ccrs.startDrawing(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX);
        cm.render(ccrs, trs);
        ccrs.draw();

        ShaderUtils.DisableShaderProg();

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.enableCull();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
    }

   
    
}
