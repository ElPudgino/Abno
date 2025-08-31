package pzi.abno.loading;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import pzi.abno.Abno;
import pzi.abno.entity.TestEntity;
import pzi.abno.entity.RenderTestEntity;

@Mod.EventBusSubscriber(modid = Abno.MODID, value = Side.CLIENT)
public class EntityRenderHandler 
{
    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(TestEntity.class, RenderTestEntity::new);
    }
}
