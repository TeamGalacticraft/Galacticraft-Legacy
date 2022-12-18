/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.items;

import micdoodle8.mods.galacticraft.api.item.GCRarity;
import net.minecraft.item.Item;

public class ItemFuel extends Item implements GCRarity
{

    public ItemFuel(String assetName)
    {
        super();
        this.setTranslationKey(assetName);
    }

}
