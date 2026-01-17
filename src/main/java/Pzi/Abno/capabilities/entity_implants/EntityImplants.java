package pzi.abno.capabilities.entity_implants;

import java.util.Collection;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import pzi.abno.Abno;

public class EntityImplants implements IEntityImplants
{
    public HashMap<String,IEntityImplant> Implants = new HashMap<>();
    public void AddImplant(IEntityImplant implant, EntityPlayer player)
    {
        Abno.logger.info("Add implant called");
        Implants.put(implant.GetImplantName(), implant);
        implant.OnAdded(player);
    }

    public void RemoveImplant(IEntityImplant implant, EntityPlayer player)
    {
        implant.OnRemoved(player);
        Implants.remove(implant.GetImplantName());
    }

    public void OnTick(EntityPlayer player)
    {

    }

    public Collection<IEntityImplant> GetImplantList()
    {
        return Implants.values();
    }

    public boolean HasImplant(String name)
    {
        return Implants.containsKey(name);
    }

    public IEntityImplant GetImplant(String name)
    {
        return Implants.get(name);
    }
}
