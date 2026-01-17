package pzi.abno;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;

import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import pzi.abno.loading.ServerProxy;
import software.bernie.geckolib3.GeckoLib;
import pzi.abno.capabilities.CapabilityHandler;
import pzi.abno.capabilities.entity_implants.EntityImplants;
import pzi.abno.capabilities.entity_implants.EntityImplantsStorage;
import pzi.abno.capabilities.entity_implants.IEntityImplants;
import pzi.abno.commands.AddImplantCommand;
import pzi.abno.commands.GetImplantsCommand;
import pzi.abno.gui.AbnosCreativeTab;

@Mod(modid = Abno.MODID, name = Abno.MODNAME, version = Abno.MODVERSION, dependencies = "required-after:forge@[11.16.0.1865,)", useMetadata = true)
public class Abno {

    public static final String MODID = "abno";
    public static final String MODNAME = "Abno";
    public static final String MODVERSION= "0.1.0";

    public static CreativeTabs tab = new AbnosCreativeTab("Abnos");

    @SidedProxy(clientSide = "pzi.abno.loading.ClientProxy", serverSide = "pzi.abno.loading.ServerProxy")
    public static ServerProxy proxy;

    @Mod.Instance(MODID)
    public static Abno instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Hello world abno");
        GeckoLib.initialize();
        proxy.preInit(event);       
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        RegisterCapabilities();
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        // Register server-side commands
        event.registerServerCommand(new AddImplantCommand());
        event.registerServerCommand(new GetImplantsCommand());
    }
    
    private static void RegisterCapabilities()
    {
        Abno.logger.info("Registered capabilities");
        CapabilityManager.INSTANCE.register(IEntityImplants.class, new EntityImplantsStorage(), EntityImplants::new);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    } 

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}