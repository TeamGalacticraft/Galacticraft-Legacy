package micdoodle8.mods.galacticraft.api.world;

import net.minecraft.world.biome.Biome.BiomeProperties;

/**
 * Builder class for BiomeData that provides easier BiomeProperties creation
 * <br><br> Since planets in Galacticraft have no rain: <br> disableRain is
 * false by default <br> rainfall is 0.0F by default <br>
 *
 * @author ROMVoid95
 */
public class DataBuilder
{

    private final String biomeName;
    private float baseHeight = 0.1F;
    private float heightVariation = 0.2F;
    private float temperature = 0.5F;
    private float rainfall = 0.0F;
    private int waterColor = 16777215;
    private boolean enableSnow = false;
    private boolean enableRain = false;
    private String baseBiomeRegName;

    public DataBuilder(String name)
    {
        this.biomeName = name;
    }

    public DataBuilder temp(Float temperature)
    {
        if (temperature != null)
            this.temperature = temperature;
        return this;
    }

    public DataBuilder rainfall(Float rainfall)
    {
        if (rainfall != null)
            this.rainfall = rainfall;
        return this;
    }

    public DataBuilder baseHeight(Float baseHeight)
    {
        if (baseHeight != null)
            this.baseHeight = baseHeight;
        return this;
    }

    public DataBuilder heightVariation(Float heightVariation)
    {
        if (heightVariation != null)
            this.heightVariation = heightVariation;
        return this;
    }

    public DataBuilder enableRain()
    {
        this.enableRain = true;
        return this;
    }

    public DataBuilder snowEnabled()
    {
        this.enableSnow = true;
        return this;
    }

    public DataBuilder waterColor(Integer waterColor)
    {
        if (waterColor != null)
            this.waterColor = waterColor;
        return this;
    }

    public DataBuilder baseBiome(String name)
    {
        if (name != null)
            this.baseBiomeRegName = name;
        return this;
    }

    public BiomeData build()
    {
        return new BiomeData(this);
    }

    /**
     * BiomeData extends BiomeProperties
     *
     * @author ROMVoid95
     */
    public class BiomeData extends BiomeProperties
    {

        public final String biomeName;

        public BiomeData(DataBuilder builder)
        {
            super(builder.biomeName);
            this.setTemperature(builder.temperature);
            this.setRainfall(builder.rainfall);
            this.setBaseHeight(builder.baseHeight);
            this.setHeightVariation(builder.heightVariation);
            if (!builder.enableRain)
                this.setRainDisabled();
            if (builder.enableSnow)
                this.setSnowEnabled();
            this.setWaterColor(builder.waterColor);
            this.setBaseBiome(builder.baseBiomeRegName);

            this.biomeName = builder.biomeName;
        }

        public String getBiomeName()
        {
            return biomeName;
        }
    }
}
