package pzi.abno.loading;

import pzi.abno.*;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.Item;
import pzi.abno.block.*;


@Mod.EventBusSubscriber(modid = Abno.MODID)
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent e) {}

    //public void init(FMLPostInitializationEvent e) {}

    public void postInit(FMLPostInitializationEvent e) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().register(new TestBlock());
        //Abno.logger.info(" cmnproxy");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new ItemBlock(ModBlocks.testblock).setRegistryName(ModBlocks.testblock.getRegistryName()));
        //Abno.logger.info(" cmnproxy");
    }
}