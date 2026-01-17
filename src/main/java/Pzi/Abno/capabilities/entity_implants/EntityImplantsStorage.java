package pzi.abno.capabilities.entity_implants;

import java.util.Collection;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class EntityImplantsStorage implements IStorage<IEntityImplants>
{
    @Override
    public NBTTagCompound writeNBT(Capability<IEntityImplants> capability, IEntityImplants instance, EnumFacing side)
    {
        NBTTagCompound compound = new NBTTagCompound();
        for (IEntityImplant im : instance.GetImplantList())
        {
            NBTBase nbt = im.writeToNBT();
            if (nbt != null)
            {
                compound.setTag(im.GetImplantName(), nbt);
            }         
        }
        return compound;
    }
    @Override
    public void readNBT(Capability<IEntityImplants> capability, IEntityImplants instance, EnumFacing side, NBTBase nbt)
    {
        Collection<IEntityImplant> implants = instance.GetImplantList();
        NBTTagCompound compound = (NBTTagCompound)nbt;
        for (String name : compound.getKeySet())
        {
            IEntityImplant im = null;
            if (!instance.HasImplant(name))
            {
                im = EntityImplantsUtils.CreateImplantInstance(name);
                implants.add(im);
            }
            else
            {
                im = instance.GetImplant(name);
            }
            if (compound.hasKey(name))
            {
                im.readFromNBT(compound.getTag(name));
            }        
        }
    }
}
