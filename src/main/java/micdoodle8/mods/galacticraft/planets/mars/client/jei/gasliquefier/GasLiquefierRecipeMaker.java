/*
 * Copyright (c) 2022 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.mars.client.jei.gasliquefier;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import net.minecraft.item.ItemStack;

public class GasLiquefierRecipeMaker
{

    public static List<GasLiquefierRecipeWrapper> getRecipesList()
    {
        List<GasLiquefierRecipeWrapper> recipes = new ArrayList<>();

        recipes.add(new GasLiquefierRecipeWrapper(new ItemStack(AsteroidsItems.methaneCanister, 1, 1), new ItemStack(GCItems.fuelCanister, 1, 1)));
        recipes.add(new GasLiquefierRecipeWrapper(new ItemStack(AsteroidsItems.atmosphericValve), new ItemStack(AsteroidsItems.canisterLN2, 1, 1)));
        recipes.add(new GasLiquefierRecipeWrapper(new ItemStack(AsteroidsItems.atmosphericValve), new ItemStack(AsteroidsItems.canisterLOX, 1, 1)));
        recipes.add(new GasLiquefierRecipeWrapper(new ItemStack(AsteroidsItems.canisterLN2, 1, 501), new ItemStack(AsteroidsItems.canisterLN2, 1, 1)));
        recipes.add(new GasLiquefierRecipeWrapper(new ItemStack(AsteroidsItems.canisterLOX, 1, 501), new ItemStack(AsteroidsItems.canisterLOX, 1, 1)));

        return recipes;
    }
}
