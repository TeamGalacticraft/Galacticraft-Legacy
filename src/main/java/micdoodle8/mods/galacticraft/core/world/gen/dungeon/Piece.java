package micdoodle8.mods.galacticraft.core.world.gen.dungeon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.Random;

public abstract class Piece extends StructureComponent
{

    protected DungeonConfiguration configuration;
    private static Field mirror;
    private static Field rotation;

    public Piece()
    {
    }

    public Piece(DungeonConfiguration configuration)
    {
        this.configuration = configuration;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
        this.configuration.writeToNBT(tagCompound);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager manager)
    {
        if (this.configuration == null)
        {
            this.configuration = new DungeonConfiguration();
            this.configuration.readFromNBT(tagCompound);
        }
    }

    protected StructureBoundingBox getExtension(EnumFacing direction, int length, int width)
    {
        int blockX, blockZ, sizeX, sizeZ;
        switch (direction)
        {
            case NORTH:
                sizeX = width;
                sizeZ = length;
                blockX = this.boundingBox.minX + (this.boundingBox.maxX - this.boundingBox.minX) / 2 - sizeX / 2;
                blockZ = this.boundingBox.minZ - sizeZ;
                break;
            case EAST:
                sizeX = length;
                sizeZ = width;
                blockX = this.boundingBox.maxX;
                blockZ = this.boundingBox.minZ + (this.boundingBox.maxZ - this.boundingBox.minZ) / 2 - sizeZ / 2;
                break;
            case SOUTH:
                sizeX = width;
                sizeZ = length;
                blockX = this.boundingBox.minX + (this.boundingBox.maxX - this.boundingBox.minX) / 2 - sizeX / 2;
                blockZ = this.boundingBox.maxZ;
                break;
            case WEST:
            default:
                sizeX = length;
                sizeZ = width;
                blockX = this.boundingBox.minX - sizeX;
                blockZ = this.boundingBox.minZ + (this.boundingBox.maxZ - this.boundingBox.minZ) / 2 - sizeZ / 2;
                break;
        }
        return new StructureBoundingBox(blockX, this.configuration.getYPosition(), blockZ, blockX + sizeX, this.configuration.getYPosition() + this.configuration.getHallwayHeight(), blockZ + sizeZ);
    }

    public Piece getNextPiece(DungeonStart startPiece, Random rand)
    {
        return null;
    }
}
