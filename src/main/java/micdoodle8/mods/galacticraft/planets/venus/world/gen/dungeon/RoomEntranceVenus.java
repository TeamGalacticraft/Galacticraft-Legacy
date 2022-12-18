/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.venus.world.gen.dungeon;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class RoomEntranceVenus extends SizedPieceVenus
{

    public RoomEntranceVenus()
    {
    }

    public RoomEntranceVenus(DungeonConfigurationVenus configuration, Random rand, int blockPosX, int blockPosZ)
    {
        super(configuration, rand.nextInt(4) + 6, 12, rand.nextInt(4) + 6, EnumFacing.Plane.HORIZONTAL.random(rand));
        this.setCoordBaseMode(EnumFacing.SOUTH);
        int sX = this.sizeX / 2;
        int sZ = this.sizeZ / 2;

        this.boundingBox =
            new StructureBoundingBox(blockPosX - sX, configuration.getYPosition(), blockPosZ - sZ, blockPosX - sX + this.sizeX, configuration.getYPosition() + this.sizeY, blockPosZ - sZ + this.sizeZ);
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
    {
        for (int i = 0; i <= this.sizeX; i++)
        {
            for (int j = 0; j <= this.sizeY; j++)
            {
                for (int k = 0; k <= this.sizeZ; k++)
                {
                    if (i == 0 || i == this.sizeX
                        || j == 0 /*
                                   * || j == this.sizeY
                                   */ || k == 0 || k == this.sizeZ)
                    {
                        this.setBlockState(worldIn, this.configuration.getBrickBlock(), i, j, k, boundingBox);
                    } else
                    {
                        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), i, j, k, boundingBox);
                    }
                }
            }
        }

        return true;
    }

    @Override
    public PieceVenus getNextPiece(DungeonStartVenus startPiece, Random rand)
    {
        return getCorridor(rand, startPiece, 10, false);
    }
}
