package pzi.abno.gui;

import pzi.abno.block.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AbnosCreativeTab extends CreativeTabs
{
    public AbnosCreativeTab(String name)
    {
        super(CreativeTabs.getNextID(), name);
    }

    @Override
	public ItemStack createIcon() {
		if(ModBlocks.testblock != null){
			return new ItemStack(Item.getItemFromBlock(ModBlocks.testblock));
		}
		return new ItemStack(Items.IRON_PICKAXE);
	}
}