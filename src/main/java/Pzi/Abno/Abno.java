package pzi.abno;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraft.creativetab.CreativeTabs;
import pzi.abno.loading.ServerProxy;
import software.bernie.geckolib3.GeckoLib;
import pzi.abno.gui.AbnosCreativeTab;

@Mod(modid = Abno.MODID, name = Abno.MODNAME, version = Abno.MODVERSION, dependencies = "required-after:forge@[11.16.0.1865,)", useMetadata = true)
public class Abno {

    public static final String MODID = "abno";
    public static final String MODNAME = "Abno";
    public static final String MODVERSION= "0.0.2";

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

    //@Mod.EventHandler
    //public void init(FMLInitializationEvent e) {
    //    proxy.init(e);
    //}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}