package pzi.abno.loading;

import javax.annotation.Nonnull;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import pzi.abno.Abno;
import pzi.abno.entity.TestEntity;

@Mod.EventBusSubscriber(modid = Abno.MODID)
public class EntitiesInit 
{
    private static int entityID = 0;
    
    @SubscribeEvent
    public static void RegisterEntities(RegistryEvent.Register<EntityEntry> e) {
        final EntityEntry[] entityEntries = {
                regEntity_egg("testentity", TestEntity.class, 30, 1, true, 15231488, 10289152)
        };
        e.getRegistry().registerAll(entityEntries);
    }

    private static EntityEntry regEntity_egg(String name, @Nonnull Class<? extends Entity> entity, int trackingRange, int trackerUpdate, boolean sendVelocity, int color1, int color2) {
        return EntityEntryBuilder.create().entity(entity).id(new ResourceLocation(Abno.MODID, name), entityID++).name(name).egg(color1, color2).tracker(trackingRange,trackerUpdate, sendVelocity).build();
    }
    //private static EntityEntry regEntity_no_egg(String name, @Nonnull Class<? extends Entity> entity, int trackingRange, int trackerUpdate, boolean sendVelocity) {
    //    return EntityEntryBuilder.create().entity(entity).id(new ResourceLocation(Abno.MODID, name), entityID++).name(name).tracker(trackingRange,trackerUpdate, sendVelocity).build();
    //}
}
