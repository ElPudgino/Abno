package pzi.abno.capabilities.entity_implants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;

public interface IEntityImplant {
    public String GetImplantName();
    public String GetInGameName();
    public void OnTick(EntityPlayer player);
    public void OnAdded(EntityPlayer player);
    public void OnRemoved(EntityPlayer player);
    public NBTBase writeToNBT();
    public void readFromNBT(NBTBase nbt);
    public void OnRender(EntityPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale);
}
