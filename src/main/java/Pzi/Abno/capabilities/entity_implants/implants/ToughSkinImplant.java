package pzi.abno.capabilities.entity_implants.implants;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import pzi.abno.capabilities.entity_implants.IEntityImplant;

public class ToughSkinImplant implements IEntityImplant 
{
    private static UUID armorMod = UUID.fromString("482ff699-ffa0-4def-a857-ea2d4a1bbd2e");
    private static UUID speedMod = UUID.fromString("e4378b74-6d45-44d7-a82e-cd142dc05867");

    public ToughSkinImplant() {}

    public String GetImplantName()
    {
        return "ToughSkinImplant";
    }
    public String GetInGameName()
    {
        return "Tough Skin";
    }
    public void OnAdded(EntityPlayer player)
    {
        if (player.world.isRemote)
            return;
        player.getEntityAttribute(SharedMonsterAttributes.ARMOR)
            .applyModifier(new AttributeModifier(armorMod, "ToughSkinImplantArmor", 4, 0));
        player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
            .applyModifier(new AttributeModifier(speedMod,"ToughSkinImplantSpeed", -0.15, 1));
    }
    public void OnRemoved(EntityPlayer player)
    {
        if (player.world.isRemote)
            return;
        player.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(armorMod);
        player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(speedMod);
    
    }
    public void OnTick(EntityPlayer player)
    {}
    public void readFromNBT(NBTBase nbt)
    {}
    public NBTBase writeToNBT()
    {
        return null;
    }
    public void OnRender(EntityPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
            float ageInTicks, float netHeadYaw, float headPitch, float scale) {}
}
