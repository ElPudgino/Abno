package pzi.abno.loading;

import pzi.abno.block.ModBlocks;

import pzi.abno.block.AbnoBlock;
import pzi.abno.Abno;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;


@EventBusSubscriber(value = Side.CLIENT, modid = Abno.MODID)
public class ModelLoading
{
    @SubscribeEvent
    public static void LoadModels(ModelRegistryEvent event)
    {
        for (int i = 0; i < ModBlocks.All_Blocks.size(); i++)
        {
            //Abno.logger.info(ModBlocks.All_Blocks.get(i)+" abnoblock addedddd - "+ ModBlocks.All_Blocks.get(i).getRegistryName());
            ModBlocks.All_Blocks.get(i).registerModel();
        }
    }
} 