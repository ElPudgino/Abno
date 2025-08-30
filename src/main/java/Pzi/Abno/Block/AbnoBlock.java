package pzi.abno.block;

import pzi.abno.block.ModBlocks;

import pzi.abno.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraft.block.material.Material;

public class AbnoBlock extends Block
{ 
    public AbnoBlock(Material mat, String name)
    {
        super(mat);
        InitBlock(name); 
        ModBlocks.All_Blocks.add(this);        
        //Abno.logger.info(this.getRegistryName()+" abnoblock add");
        
    }

    protected void InitBlock(String name)
    {
        setRegistryName(name);        // The unique name (within your mod) that identifies this block
        setTranslationKey(name);
        this.setCreativeTab(Abno.tab);
    }

    public void registerModel() {
        //Abno.logger.info(this.getRegistryName()+" register model");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));    
    }
}