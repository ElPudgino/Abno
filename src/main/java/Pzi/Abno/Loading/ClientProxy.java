package pzi.abno.loading;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import codechicken.lib.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pzi.abno.Abno;
import pzi.abno.rendering.AbnoRenderUtils;
import pzi.abno.rendering.RenderMeshCall;
import pzi.abno.rendering.ShaderUtils;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Abno.MODID, value = Side.CLIENT)
public class ClientProxy extends ServerProxy
{
    public static final RegionRenderCacheBuilder bufferCache = new RegionRenderCacheBuilder();
    public static List<RenderMeshCall> MeshesToRender = new ArrayList<>();

    @Override
    public void preInit(FMLPreInitializationEvent e) 
    {
        EntityRenderHandler.registerRenderers();
        ShaderUtils.initShaders();
        pzi.abno.rendering.AbnoRenderUtils.loadModels();
        super.preInit(e);
    }

    @SubscribeEvent
	public static void onRenderWorldLastEvent(final RenderWorldLastEvent event) 
    {
        RenderCachedGeometry(event);
        
        RenderTransparents();

	}

    private static void RenderTransparents()
    {
        for (RenderMeshCall rmc : MeshesToRender)
        {
            AbnoRenderUtils.RenderMesh(rmc);
        }
        MeshesToRender.clear();
    }

    private static void RenderCachedGeometry(final RenderWorldLastEvent event)
    {
        final Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
        if (entity == null) {
            return;
        }
        final float partialTicks = event.getPartialTicks();

        // Copied from EntityRenderer. This code can be found by looking at usages of Entity.prevPosX.
        // It also appears in many other places throughout Minecraft's rendering
        double renderPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) partialTicks;
        double renderPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) partialTicks;
        double renderPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) partialTicks;

        int renderlayer = BlockRenderLayer.TRANSLUCENT.ordinal();
        final BufferBuilder bufferBuilder = bufferCache.getWorldRendererByLayerId(renderlayer);

        GlStateManager.pushMatrix();
        GlStateManager.translate(-renderPosX, -renderPosY, -renderPosZ);
        drawBuffer(bufferBuilder);
        GlStateManager.popMatrix();

    }

    private static void drawBuffer(final BufferBuilder bufferBuilder) 
    {
		if (bufferBuilder.getVertexCount() > 0) {
			VertexFormat vertexformat = bufferBuilder.getVertexFormat();
			int i = vertexformat.getSize();
			ByteBuffer bytebuffer = bufferBuilder.getByteBuffer();
			List<VertexFormatElement> list = vertexformat.getElements();

			for (int j = 0; j < list.size(); ++j) {
				VertexFormatElement vertexformatelement = list.get(j);
				bytebuffer.position(vertexformat.getOffset(j));

				// moved to VertexFormatElement.preDraw
				vertexformatelement.getUsage().preDraw(vertexformat, j, i, bytebuffer);
			}

			GlStateManager.glDrawArrays(bufferBuilder.getDrawMode(), 0, bufferBuilder.getVertexCount());
			int i1 = 0;

			for (int j1 = list.size(); i1 < j1; ++i1) {
				VertexFormatElement vertexformatelement1 = list.get(i1);		
				// moved to VertexFormatElement.postDraw
				vertexformatelement1.getUsage().postDraw(vertexformat, i1, i, bytebuffer);
			}
		}
	}
}