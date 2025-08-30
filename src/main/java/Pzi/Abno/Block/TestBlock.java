package pzi.abno.block;

import pzi.abno.block.ModBlocks;

import pzi.abno.*;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import pzi.abno.*;

public class TestBlock extends AbnoBlock
{
    public TestBlock()
    {
        super(Material.PLANTS, "testblock");
        ModBlocks.testblock = this;
        Abno.logger.info("testblock contructed");
    }

    
}