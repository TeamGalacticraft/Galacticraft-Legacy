package micdoodle8.mods.galacticraft.planets.mars.world.gen;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.prefab.core.BlockMetaPair;
import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import micdoodle8.mods.galacticraft.api.world.DataBuilder;
import micdoodle8.mods.galacticraft.api.world.DataBuilder.BiomeData;
import micdoodle8.mods.galacticraft.planets.mars.blocks.MarsBlocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeMars extends BiomeGenBaseGC
{

    public static final Biome marsFlat = new BiomeGenFlatMars(new DataBuilder("Mars Flat").baseHeight(2.5F).heightVariation(0.4F).build());

    public static final BlockMetaPair BLOCK_TOP = new BlockMetaPair(MarsBlocks.marsBlock, (byte) 5);
    public static final BlockMetaPair BLOCK_FILL = new BlockMetaPair(MarsBlocks.marsBlock, (byte) 6);
    public static final BlockMetaPair BLOCK_LOWER = new BlockMetaPair(MarsBlocks.marsBlock, (byte) 9);

    BiomeMars(BiomeData properties)
    {
        super(properties, true);
    }

    @Override
    public float getSpawningChance()
    {
        return 0.01F;
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
        this.fillerBlock = BLOCK_LOWER.getBlockState();
        this.topBlock = BLOCK_TOP.getBlockState();
        super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }
}
