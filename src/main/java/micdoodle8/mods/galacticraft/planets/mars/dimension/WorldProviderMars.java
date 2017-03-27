package micdoodle8.mods.galacticraft.planets.mars.dimension;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.GCPlanetDimensions;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import micdoodle8.mods.galacticraft.planets.mars.world.gen.BiomeProviderMars;
import micdoodle8.mods.galacticraft.planets.mars.world.gen.ChunkProviderMars;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderMars extends WorldProviderSpace implements IGalacticraftWorldProvider, ISolarLevel
{
    private double solarMultiplier = -1D;

    @Override
    public Vector3 getFogColor()
    {
        float f = 1.0F - this.getStarBrightness(1.0F);
        return new Vector3(210F / 255F * f, 120F / 255F * f, 59F / 255F * f);
    }

    @Override
    public Vector3 getSkyColor()
    {
        float f = 1.0F - this.getStarBrightness(1.0F);
        return new Vector3(154 / 255.0F * f, 114 / 255.0F * f, 66 / 255.0F * f);
    }

    @Override
    public boolean hasSunset()
    {
        return false;
    }

    @Override
    public long getDayLength()
    {
        return 24000L;
    }

    @Override
    public boolean shouldForceRespawn()
    {
        return !ConfigManagerCore.forceOverworldRespawn;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass()
    {
        return ChunkProviderMars.class;
    }

    @Override
    public Class<? extends BiomeProvider> getBiomeProviderClass()
    {
        return BiomeProviderMars.class;
    }

//    @Override
//	public void setDimension(int var1)
//	{
//		this.getDimensionId() = var1;
//		super.setDimension(var1);
//	}
//
//	@Override
//	protected void generateLightBrightnessTable()
//	{
//		final float var1 = 0.0F;
//
//		for (int var2 = 0; var2 <= 15; ++var2)
//		{
//			final float var3 = 1.0F - var2 / 15.0F;
//			this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
//		}
//	}

//	@Override
//	public float[] calcSunriseSunsetColors(float var1, float var2)
//	{
//		return null;
//	}

//	@Override
//	public void registerWorldChunkManager()
//	{
//		this.worldChunkMgr = new WorldChunkManagerMars();
//	}

//	@SideOnly(Side.CLIENT)
//	@Override
//	public Vec3d getFogColor(float var1, float var2)
//	{
//		return Vec3d.createVectorHelper((double) 210F / 255F, (double) 120F / 255F, (double) 59F / 255F);
//	}
//
//	@Override
//	public Vec3d getSkyColor(Entity cameraEntity, float partialTicks)
//	{
//		return Vec3d.createVectorHelper(154 / 255.0F, 114 / 255.0F, 66 / 255.0F);
//	}

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
        float f1 = this.world.getCelestialAngle(par1);
        float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        return f2 * f2 * 0.75F;
    }

//	@Override
//	public float calculateCelestialAngle(long par1, float par3)
//	{
//		return super.calculateCelestialAngle(par1, par3);
//	}
//
//	public float calculatePhobosAngle(long par1, float par3)
//	{
//		return this.calculateCelestialAngle(par1, par3) * 3000;
//	}
//
//	public float calculateDeimosAngle(long par1, float par3)
//	{
//		return this.calculatePhobosAngle(par1, par3) * 0.0000000001F;
//	}
//
//	@Override
//	public IChunkProvider createChunkGenerator()
//	{
//		return new ChunkProviderMars(this.world, this.world.getSeed(), this.world.getWorldInfo().isMapFeaturesEnabled());
//	}
//
//	@Override
//	public boolean isSkyColored()
//	{
//		return true;
//	}

    @Override
    public double getHorizon()
    {
        return 44.0D;
    }

    @Override
    public int getAverageGroundLevel()
    {
        return 76;
    }

    @Override
    public boolean canCoordinateBeSpawn(int var1, int var2)
    {
        return true;
    }

    //Overriding only in case the Galacticraft API is not up-to-date
    //(with up-to-date API this makes zero difference)
    @Override
    public boolean isSurfaceWorld()
    {
        return (this.world == null) ? false : this.world.isRemote;
    }

    //Overriding so that beds do not explode on Mars
    @Override
    public boolean canRespawnHere()
    {
        if (EventHandlerGC.bedActivated)
        {
            EventHandlerGC.bedActivated = false;
            return true;
        }
        return false;
    }

    //Overriding only in case the Galacticraft API is not up-to-date
    //(with up-to-date API this makes zero difference)
    @Override
    public int getRespawnDimension(EntityPlayerMP player)
    {
        return this.shouldForceRespawn() ? this.getDimension() : 0;
    }

//	@Override
//	public String getSaveFolder()
//	{
//		return "DIM" + ConfigManagerMars.dimensionIDMars;
//	}
//
//	@Override
//	public String getWelcomeMessage()
//	{
//		return "Entering Mars";
//	}
//
//	@Override
//	public String getDepartMessage()
//	{
//		return "Leaving Mars";
//	}
//
//	@Override
//	public String getDimensionName()
//	{
//		return "Mars";
//	}

    //	@Override
    //	public boolean canSnowAt(int x, int y, int z)
    //	{
    //		return false;
    //	}

//	@Override
//	public boolean canBlockFreeze(int x, int y, int z, boolean byWater)
//	{
//		return false;
//	}
//
//	@Override
//	public boolean canDoLightning(Chunk chunk)
//	{
//		return false;
//	}
//
//	@Override
//	public boolean canDoRainSnowIce(Chunk chunk)
//	{
//		return false;
//	}

    @Override
    public float getGravity()
    {
        return 0.058F;
    }

    @Override
    public double getMeteorFrequency()
    {
        return 10.0D;
    }

    @Override
    public double getFuelUsageMultiplier()
    {
        return 0.9D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= 2;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.38F;
    }

    @Override
    public float getSoundVolReductionAmount()
    {
        return 10.0F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return MarsModule.planetMars;
    }

    @Override
    public boolean hasBreathableAtmosphere()
    {
        return false;
    }

    @Override
    public float getThermalLevelModifier()
    {
        return -1;
    }

    @Override
    public float getWindLevel()
    {
        return 0.3F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        if (this.solarMultiplier < 0D)
        {
            double s = this.getSolarSize();
            this.solarMultiplier = s * s * s;
        }
        return this.solarMultiplier;
    }

    @Override
    public boolean shouldDisablePrecipitation()
    {
        return true;
    }

    @Override
    public boolean shouldCorrodeArmor()
    {
        return false;
    }

    @Override
    public DimensionType getDimensionType()
    {
        return GCPlanetDimensions.MARS;
    }
}
