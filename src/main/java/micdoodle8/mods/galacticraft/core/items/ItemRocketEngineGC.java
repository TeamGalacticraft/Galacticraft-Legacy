/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.items;

import micdoodle8.mods.galacticraft.api.item.GCRarity;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemRocketEngineGC extends Item implements ISortableItem, GCRarity
{

    public static final String[] names =
    {"tier1engine", // 0
            "tier1booster"}; // 1

    public ItemRocketEngineGC(String assetName)
    {
        super();
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setTranslationKey(assetName);
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return GalacticraftCore.galacticraftItemsTab;
    }

    @Override
    public String getTranslationKey(ItemStack itemStack)
    {
        return this.getTranslationKey() + "." + ItemRocketEngineGC.names[itemStack.getItemDamage()];
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        if (tab == GalacticraftCore.galacticraftItemsTab || tab == CreativeTabs.SEARCH)
        {
            for (int i = 0; i < ItemRocketEngineGC.names.length; i++)
            {
                list.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getMetadata(int par1)
    {
        return par1;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta)
    {
        return EnumSortCategoryItem.GENERAL;
    }
}
