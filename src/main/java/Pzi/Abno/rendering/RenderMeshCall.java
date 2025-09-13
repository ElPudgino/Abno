package pzi.abno.rendering;


import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Vector3;
import net.minecraft.util.ResourceLocation;

public class RenderMeshCall 
{
    public RenderMeshCall(String Meshname, Matrix4 Trs, int Shader, ResourceLocation Texture,  float Shader_time, Vector3 Color, int Blendmode_src, int Blendmode_dest, boolean Depth_mask)
    {
        meshname = Meshname;
        trs = Trs;
        texture = Texture;
        shader_time = Shader_time;
        color = Color;
        blendmode_src = Blendmode_src;
        blendmode_dest = Blendmode_dest;
        depth_mask = Depth_mask;
        shader = Shader;
    }

    public String meshname;
    public Matrix4 trs;
    public ResourceLocation texture;
    public float shader_time;
    public Vector3 color;

    public int shader;
    public int blendmode_src;
    public int blendmode_dest;
    public boolean depth_mask;
    
}
