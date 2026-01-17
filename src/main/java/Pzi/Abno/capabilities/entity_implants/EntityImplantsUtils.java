package pzi.abno.capabilities.entity_implants;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import java.lang.reflect.Constructor;
import org.apache.logging.log4j.Level;

import pzi.abno.Abno;

public class EntityImplantsUtils {
    private static final Marker CREATE_INSTANCE_FAIL = MarkerManager.getMarker("CREATE_INSTANCE_FAIL");
    public static IEntityImplant CreateImplantInstance(String Name)
    {
        Abno.logger.info("Started creating implant instance");
        IEntityImplant result = null;
        try 
        {
            Class<?> cl = Class.forName("pzi.abno.capabilities.entity_implants.implants."+Name);
            if (cl == null)
            {
                Abno.logger.log(Level.WARN, CREATE_INSTANCE_FAIL,"Failed to find implant class");
            }
            Constructor<?> ctor = cl.getConstructor();
            if (ctor == null)
            {
                Abno.logger.log(Level.WARN, CREATE_INSTANCE_FAIL,"Failed to find implant constructor");
            }
            result = (IEntityImplant)ctor.newInstance();
        }
        catch (ClassNotFoundException e)
        {
            Abno.logger.log(Level.WARN, CREATE_INSTANCE_FAIL,"Failed to find implant class exception");
        }
        catch (NoSuchMethodException e)
        {
            Abno.logger.log(Level.WARN, CREATE_INSTANCE_FAIL,"Failed to find implant constructor exception");
        }
        catch (Exception e)
        {
            Abno.logger.log(Level.WARN, CREATE_INSTANCE_FAIL,"Failed to create implant instance");
        }
        return result;
    }
}
