/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.api.vector;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockTuple
{

    public Block block;
    public int meta;

    public BlockTuple(Block b, int m)
    {
        this.block = b;
        this.meta = m;
    }

    @Override
    public String toString()
    {
        Item item = Item.getItemFromBlock(this.block);
        if (item == null || item == Item.getItemFromBlock(Blocks.AIR))
            return this.block.getTranslationKey() + ".name";
        return new ItemStack(item, 1, this.meta).getTranslationKey() + ".name";
    }
}
