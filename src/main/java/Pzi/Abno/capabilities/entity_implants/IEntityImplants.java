package pzi.abno.capabilities.entity_implants;

import java.util.Collection;

import net.minecraft.entity.player.EntityPlayer;


public interface IEntityImplants 
{
    public void AddImplant(IEntityImplant implant, EntityPlayer player);
    public void RemoveImplant(IEntityImplant implant, EntityPlayer player);
    public boolean HasImplant(String name);
    public Collection<IEntityImplant> GetImplantList();
    public IEntityImplant GetImplant(String name);
    public void OnTick(EntityPlayer player);
}