package micdoodle8.mods.galacticraft.core.network;

import net.minecraft.entity.player.EntityPlayer;

import io.netty.buffer.ByteBuf;

public interface IPacket
{

    void encodeInto(ByteBuf buffer);

    void decodeInto(ByteBuf buffer);

    void handleClientSide(EntityPlayer player);

    void handleServerSide(EntityPlayer player);

    int getDimensionID();
}
