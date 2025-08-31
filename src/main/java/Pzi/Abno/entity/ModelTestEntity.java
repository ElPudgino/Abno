package pzi.abno.entity;



public class ModelTestEntity extends ModelEntityAbno
{
    @Override
    public String GetModelLocationStr()
    {
        return "geo/testentity.geo.json";
    }
    @Override
    public String GetTextureLocationStr()
    {
        return "textures/entities/testentity.png";
    }
    @Override
    public String GetAnimationLocationStr()
    {
        return "animations/testentity.animation.json";
    }
}
