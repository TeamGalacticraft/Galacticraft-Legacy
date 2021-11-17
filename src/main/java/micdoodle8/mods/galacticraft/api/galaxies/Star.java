package micdoodle8.mods.galacticraft.api.galaxies;

import micdoodle8.mods.galacticraft.annotations.ForRemoval;
import micdoodle8.mods.galacticraft.annotations.ReplaceWith;

public class Star extends CelestialBody
{

    protected SolarSystem parentSolarSystem = null;

    public Star(String planetName)
    {
        super(planetName);
    }

    public SolarSystem getParentSolarSystem()
    {
        return this.parentSolarSystem;
    }

    @Override
    public int getID()
    {
        return this.parentSolarSystem.getID();
    }

    @Override
    public String getTranslationKeyPrefix()
    {
        return "star";
    }

    public Star setParentSolarSystem(SolarSystem galaxy)
    {
        this.parentSolarSystem = galaxy;
        return this;
    }
    
    @Override
    @Deprecated
    @ForRemoval(deadline = "4.1.0")
    @ReplaceWith("getTranslationKeyPrefix()")
    public String getUnlocalizedNamePrefix()
    {
        return getTranslationKeyPrefix();
    }
}
