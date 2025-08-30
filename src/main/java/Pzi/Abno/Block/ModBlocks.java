package pzi.abno.block;

import net.minecraftforge.fml.common.registry.GameRegistry;
import java.util.List;
import java.util.ArrayList;

public class ModBlocks
{
    @GameRegistry.ObjectHolder("abno:testblock")
    public static TestBlock testblock;

    public static List<AbnoBlock> All_Blocks = new ArrayList<>();
}

