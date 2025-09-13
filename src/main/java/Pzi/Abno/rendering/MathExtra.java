package pzi.abno.rendering;

import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Vector3;
import net.minecraft.entity.Entity;
import pzi.abno.Abno;

public class MathExtra 
{
    private static Vector3 Cross(Vector3 a, Vector3 b)
    {
        return new Vector3(a.y * b.z - a.z * b.y, 
                           a.z * b.x - a.x * b.z, 
                           a.x * b.y - a.y * b.x);
    }

    private static double Dot(Vector3 a, Vector3 b)
    {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Rotation AlignWithVelocity(Entity e, Vector3 forward)
    {   
        Vector3 velocity = new Vector3(e.motionX, e.motionY, e.motionZ);
        if (velocity.mag() < 0.000001)
        {
            return new Rotation(0,1,0,0);
        }
        velocity = velocity.normalize();
        Vector3 axis = Cross(forward, velocity);
        double angle = Math.acos(Dot(forward, velocity) / (forward.mag() * velocity.mag()));    
        return new Rotation(angle, axis);
    }
}
