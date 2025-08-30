package pzi.abno.loading;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import pzi.abno.Abno;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Abno.MODID, value = Side.CLIENT)
public class ClientProxy extends ServerProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        EntityRenderHandler.registerRenderers();
        super.preInit(e);
    }
}