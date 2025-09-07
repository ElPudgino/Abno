package pzi.abno.rendering;

import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Vector3;
import net.minecraft.entity.Entity;

public class MathExtra 
{
    public static Rotation AlignWithVelocity(Entity e, Vector3 forward)
    {   
        Vector3 velocity = new Vector3(e.motionX, e.motionY, e.motionZ);
        if (velocity.mag() < 0.0001)
        {
            return new Rotation(0,1,0,0);
        }
        Vector3 axis = forward.crossProduct(velocity).normalize();
        double angle = forward.angle(velocity);
        return new Rotation(angle, axis);
    }
}
