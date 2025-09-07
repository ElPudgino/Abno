package pzi.abno.rendering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;

import codechicken.lib.vec.Vector3;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import pzi.abno.Abno;
import pzi.abno.loading.ResourcesHelper;

public class ShaderUtils {
    public static int DefaultShader = 0;
    public static int TestShader = 0;

    private static boolean DefaultLighting;

    public static int timeLoc = 0;
    public static int colorLoc = 0;

    public static void initShaders() {
		if (Minecraft.getMinecraft().getResourceManager() instanceof SimpleReloadableResourceManager) {
			((SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(manager -> {
				DeleteShaderProg(TestShader); TestShader = 0;

				LoadShaderProgs();
			});
		}

	}

    public static void EnableShaderProg(int prog_id)
    {
        if (prog_id == 0)
        {
            Abno.logger.error("Enabled shaderprog 0 with EnableShaderProg");
        }
        DefaultLighting = GL11.glGetBoolean(GL11.GL_LIGHTING);
        GlStateManager.disableLighting();
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);

        ARBShaderObjects.glUseProgramObjectARB(prog_id);
    }

    public static void EnableShaderProg(int prog_id, float shader_time)
    {
        EnableShaderProg(prog_id);
        if (timeLoc == 0)
        {
            timeLoc = ARBShaderObjects.glGetUniformLocationARB(prog_id, "time");
        }     
        ARBShaderObjects.glUniform1fARB(timeLoc, shader_time);
    }

    public static void EnableShaderProg(int prog_id, float shader_time, Vector3 color)
    {
        EnableShaderProg(prog_id, shader_time);
        if (colorLoc == 0)
        {
            colorLoc = ARBShaderObjects.glGetUniformLocationARB(prog_id, "color");
        }     
        ARBShaderObjects.glUniform3fARB(colorLoc, (float)color.x, (float)color.y, (float)color.z);
    }


    public static void DisableShaderProg()
    {
        if(DefaultLighting)
			GlStateManager.enableLighting();
		
        ARBShaderObjects.glUseProgramObjectARB(0);
    }

    private static void DeleteShaderProg(int id) {
		if (id != 0) {
			ARBShaderObjects.glDeleteObjectARB(id);
		}
	}

    private static void LoadShaderProgs()
    {
        TestShader = LoadShaderProg("testfrag", "testvert");
    }

    protected static int LoadShaderProg(String fragname, String vertname)
    {
        int shaderprog = ARBShaderObjects.glCreateProgramObjectARB();

        if (shaderprog == 0)
        {
            Abno.logger.warn("shaderprog is Zero");
            return 0;
        }
        
        if (fragname != null)
        {
            ARBShaderObjects.glAttachObjectARB(shaderprog, LoadShader(fragname, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB));
        }
      

        if (vertname != null)
        {
            ARBShaderObjects.glAttachObjectARB(shaderprog, LoadShader(vertname, ARBVertexShader.GL_VERTEX_SHADER_ARB));
        }
      
           
        ARBShaderObjects.glLinkProgramARB(shaderprog);

        if (ARBShaderObjects.glGetObjectParameteriARB(shaderprog, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE)
        {
            Abno.logger.error("Couldnt link shaderprogram: "+fragname +" :frag | vert: " + vertname);
            return 0;
        }

        ARBShaderObjects.glValidateProgramARB(shaderprog);

        if (ARBShaderObjects.glGetObjectParameteriARB(shaderprog, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE)
        {
            Abno.logger.error("Couldnt validate shaderprogram: "+fragname +" :frag | vert: " + vertname);
            return 0;
        }

        return shaderprog;
    }

    protected static int LoadShader(String name, int type)
    {
        int Shader = ARBShaderObjects.glCreateShaderObjectARB(type);
        String sh_source = "";
        try
        {
            sh_source = ReadFile(name+".glsl");
        }
        catch (Exception ex)
        {
            Abno.logger.error("Couldnt read shader file: " + ResourcesHelper.GetShadersPath() + name + ".glsl");
            return 0;
        }
        Abno.logger.info("Read shader: "+sh_source);
        ARBShaderObjects.glShaderSourceARB(Shader, sh_source);
        ARBShaderObjects.glCompileShaderARB(Shader);

        if (ARBShaderObjects.glGetObjectParameteriARB(Shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE) {
            Abno.logger.error("Couldnt compile shader: "+name);
        }
        else
        {
            Abno.logger.info("Shader compiled: "+name);
        }
        return Shader;
    }

    private static String ReadFile(String filename) throws IOException 
    {
        InputStream in = Abno.instance.getClass().getResourceAsStream(ResourcesHelper.GetShadersPath() + filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = "", res = "";
		while ((line=br.readLine()) != null) res += line + "\n";
		return res;

	}
	

}
