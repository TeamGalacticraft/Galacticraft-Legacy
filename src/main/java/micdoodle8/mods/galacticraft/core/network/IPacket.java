/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public interface IPacket
{

    void encodeInto(ByteBuf buffer);

    void decodeInto(ByteBuf buffer);

    void handleClientSide(EntityPlayer player);

    void handleServerSide(EntityPlayer player);

    int getDimensionID();
}
