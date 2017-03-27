package micdoodle8.mods.galacticraft.core.tile;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.blocks.BlockMulti;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntitySpaceStationBase extends TileEntityMulti implements IMultiBlock
{
    public TileEntitySpaceStationBase()
    {
        super(null);
    }

    public String ownerUsername = "bobby";

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.ownerUsername = nbt.getString("ownerUsername");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setString("ownerUsername", this.ownerUsername);
        return nbt;
    }

    public void setOwner(String username)
    {
        this.ownerUsername = username;
    }

    public String getOwner()
    {
        return this.ownerUsername;
    }

    @Override
    public boolean onActivated(EntityPlayer entityPlayer)
    {
        return false;
    }

    @Override
    public void onCreate(World world, BlockPos placedPosition)
    {
        this.mainBlockPosition = placedPosition;
        this.markDirty();

        for (int y = 1; y < 3; y++)
        {
            final BlockPos vecToAdd = new BlockPos(placedPosition.getX(), placedPosition.getY() + y, placedPosition.getZ());

            if (!vecToAdd.equals(placedPosition))
            {
                ((BlockMulti) GCBlocks.fakeBlock).makeFakeBlock(world, vecToAdd, placedPosition, 1);
            }
        }

    }

    @Override
    public void onDestroy(TileEntity callingBlock)
    {
        for (int y = 0; y < 3; y++)
        {
            this.world.destroyBlock(this.getPos().add(0, y, 0), false);
        }
    }
}
