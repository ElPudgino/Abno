package pzi.abno.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pzi.abno.Abno;
import pzi.abno.capabilities.entity_implants.EntityImplantsProvider;

public class CapabilityHandler 
{
    public static final ResourceLocation IMPLANTS_CAP = new ResourceLocation(Abno.MODID, "implants");
    private static boolean renderLayerAdded = false;

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        Abno.logger.info("Attached capability");
        event.addCapability(IMPLANTS_CAP, new EntityImplantsProvider());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event)
    {
        if (!renderLayerAdded)
        {
            event.getRenderer().addLayer(null);
            renderLayerAdded = true;
        }
    }
}
