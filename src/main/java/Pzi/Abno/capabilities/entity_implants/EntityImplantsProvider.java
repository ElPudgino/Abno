package pzi.abno.capabilities.entity_implants;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.Mod;
import pzi.abno.Abno;

@Mod.EventBusSubscriber
public class EntityImplantsProvider implements ICapabilitySerializable<NBTTagCompound>
{
    @CapabilityInject(IEntityImplants.class)
    public static final Capability<IEntityImplants> IMPLANT_CAP = null;

    private IEntityImplants instance = IMPLANT_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == IMPLANT_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        Abno.logger.info("--trying to get capability");
        return capability == IMPLANT_CAP ? IMPLANT_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return (NBTTagCompound)IMPLANT_CAP.getStorage().writeNBT(IMPLANT_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        IMPLANT_CAP.getStorage().readNBT(IMPLANT_CAP, this.instance, null, nbt);
    }
}
