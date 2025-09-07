package pzi.abno.loading;

import net.minecraft.util.ResourceLocation;
import pzi.abno.Abno;

public class ResourcesHelper {

    public static String GetShadersPath()
    {
        return "/assets/abno/shaders/";
    }   

    public static String GetModelsPath()
    {
        return "obj_models/";
    }

    public static String GetShaderTexturesPath()
    {
        return "textures/shaders/";
    }

    public static ResourceLocation GetModelLocation(String name)
    {
        return GetLocation(GetModelsPath()+name);
    }

    public static ResourceLocation GetShaderTextureLocation(String name)
    {
        return GetLocation(GetShaderTexturesPath()+name);
    }

    public static ResourceLocation GetLocation(String path)
    {
        return new ResourceLocation(Abno.MODID, path);
    }
    
}
