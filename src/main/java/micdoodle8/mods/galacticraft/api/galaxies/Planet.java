package micdoodle8.mods.galacticraft.api.galaxies;

import micdoodle8.mods.galacticraft.annotations.ForRemoval;
import micdoodle8.mods.galacticraft.annotations.ReplaceWith;
import net.minecraft.world.biome.Biome.SpawnListEntry;

public class Planet extends CelestialBody
{

    protected SolarSystem parentSolarSystem = null;

    public Planet(String planetName)
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
        return GalaxyRegistry.getPlanetID(this.bodyName);
    }

    @Override
    public String getTranslationKeyPrefix()
    {
        return "planet";
    }

    public Planet setParentSolarSystem(SolarSystem galaxy)
    {
        this.parentSolarSystem = galaxy;
        return this;
    }

    public static void addMobToSpawn(String planetName, SpawnListEntry mobData)
    {
        GalaxyRegistry.getCelestialBodyFromUnlocalizedName("planet." + planetName).addMobInfo(mobData);
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
