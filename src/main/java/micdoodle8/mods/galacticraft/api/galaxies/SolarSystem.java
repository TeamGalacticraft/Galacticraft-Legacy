package micdoodle8.mods.galacticraft.api.galaxies;

import java.util.Locale;

import micdoodle8.mods.galacticraft.annotations.ForRemoval;
import micdoodle8.mods.galacticraft.annotations.ReplaceWith;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.util.text.translation.I18n;

public class SolarSystem
{

    protected final String systemName;
    protected String translationKey;
    protected Vector3 mapPosition = null;
    protected Star mainStar = null;
    protected String galaxyName;

    public SolarSystem(String solarSystem, String parentGalaxy)
    {
        this.systemName = solarSystem.toLowerCase(Locale.ENGLISH);
        this.translationKey = solarSystem;
        this.galaxyName = parentGalaxy;
    }

    public String getName()
    {
        return this.systemName;
    }

    public final int getID()
    {
        return GalaxyRegistry.getSolarSystemID(this.systemName);
    }

    public String getTranslatedName()
    {
        String s = this.getTranslationKey();
        return s == null ? "" : I18n.translateToLocal(s);
    }

    public String getTranslationKey()
    {
        return "solarsystem." + this.translationKey;
    }

    public Vector3 getMapPosition()
    {
        return this.mapPosition;
    }

    public SolarSystem setMapPosition(Vector3 mapPosition)
    {
        mapPosition.scale(500D);
        this.mapPosition = mapPosition;
        return this;
    }

    public Star getMainStar()
    {
        return this.mainStar;
    }

    public SolarSystem setMainStar(Star star)
    {
        this.mainStar = star;
        return this;
    }

    public String getTranslatedParentGalaxyName()
    {
        String s = this.getParentGalaxyTranslationKey();
        return s == null ? "" : I18n.translateToLocal(s);
    }

    public String getParentGalaxyTranslationKey()
    {
        return "galaxy." + this.galaxyName;
    }
    
    // DEPRECATED METHODS
    
    @Deprecated
    @ForRemoval(deadline = "4.1.0")
    @ReplaceWith("getTranslationKey()")
    public String getUnlocalizedName()
    {
        return getTranslationKey();
    }
    
    @Deprecated
    @ForRemoval(deadline = "4.1.0")
    @ReplaceWith("getTranslatedName()")
    public String getLocalizedName()
    {
        return getTranslatedName();
    }
    
    @Deprecated
    @ForRemoval(deadline = "4.1.0")
    @ReplaceWith("getTranslatedParentGalaxyName()")
    public String getLocalizedParentGalaxyName()
    {
        return getTranslatedParentGalaxyName();
    }

    @Deprecated
    @ForRemoval(deadline = "4.1.0")
    @ReplaceWith("getParentGalaxyTranslationKey()")
    public String getUnlocalizedParentGalaxyName()
    {
        return getParentGalaxyTranslationKey();
    }
}
