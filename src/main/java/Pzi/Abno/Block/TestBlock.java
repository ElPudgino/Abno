package pzi.abno.block;

import pzi.abno.*;
import net.minecraft.block.material.Material;

public class TestBlock extends AbnoBlock
{
    public TestBlock()
    {
        super(Material.PLANTS, "testblock");
        ModBlocks.testblock = this;
        Abno.logger.info("testblock contructed");
    }

    
}