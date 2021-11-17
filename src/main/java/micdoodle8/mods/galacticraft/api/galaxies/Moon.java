package micdoodle8.mods.galacticraft.api.galaxies;

import micdoodle8.mods.galacticraft.annotations.ForRemoval;
import micdoodle8.mods.galacticraft.annotations.ReplaceWith;

public class Moon extends CelestialBody implements IChildBody
{

    protected Planet parentPlanet = null;

    public Moon(String moonName)
    {
        super(moonName);
    }

    public Moon setParentPlanet(Planet planet)
    {
        this.parentPlanet = planet;
        return this;
    }

    @Override
    public int getID()
    {
        return GalaxyRegistry.getMoonID(this.bodyName);
    }

    @Override
    public String getTranslationKeyPrefix()
    {
        return "moon";
    }

    @Override
    public Planet getParentPlanet()
    {
        return this.parentPlanet;
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
