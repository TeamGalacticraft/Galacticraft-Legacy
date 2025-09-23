/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.api.recipe;

import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public interface IRecipeUpdatable extends IRecipe
{

    /**
     * Replace all inputs which match ItemStack inputA with a List&lt;ItemStack&gt;
     * (probably representing OreDict output).
     *
     * @param ingredient the current simple ItemStack ingredient
     * @param replacement the List&lt;ItemStack&gt; to replace it with
     */
    public void replaceInput(ItemStack ingredient, List<ItemStack> replacement);

    /**
     * Replace all inputs which are lists containing ItemStack ingredient with
     * simple ItemStack of ingredient.
     *
     * @param ingredient
     */
    public void replaceInput(ItemStack ingredient);
}
