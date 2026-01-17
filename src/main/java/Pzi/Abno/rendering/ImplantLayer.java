package pzi.abno.rendering;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import pzi.abno.capabilities.entity_implants.EntityImplantsProvider;
import pzi.abno.capabilities.entity_implants.IEntityImplant;
import pzi.abno.capabilities.entity_implants.IEntityImplants;

public class ImplantLayer<T extends EntityPlayer> implements LayerRenderer<T>{
    @Override
    public void doRenderLayer(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        IEntityImplants implants = entitylivingbaseIn.getCapability(EntityImplantsProvider.IMPLANT_CAP, null);
        for (IEntityImplant implant : implants.GetImplantList())
        {
            implant.OnRender(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
