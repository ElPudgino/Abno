package pzi.abno.loading;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import pzi.abno.Abno;
import pzi.abno.entity.TestEntity;
import pzi.abno.rendering.RenderFireballMagic;
import pzi.abno.rendering.RenderTestEntity;
import pzi.abno.entity.EntityFireballMagic;

@Mod.EventBusSubscriber(modid = Abno.MODID, value = Side.CLIENT)
public class EntityRenderHandler 
{
    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(TestEntity.class, RenderTestEntity::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFireballMagic.class, RenderFireballMagic::new);
    }
}
