/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.venus.client.render.tile;

import micdoodle8.mods.galacticraft.core.client.model.block.ModelTreasureChest;
import micdoodle8.mods.galacticraft.planets.GalacticraftPlanets;
import micdoodle8.mods.galacticraft.planets.venus.tile.TileEntityTreasureChestVenus;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityTreasureChestRenderer extends TileEntitySpecialRenderer<TileEntityTreasureChestVenus>
{

    private static final ResourceLocation treasureChestTexture = new ResourceLocation(GalacticraftPlanets.ASSET_PREFIX, "textures/model/treasure_venus.png");

    private final ModelTreasureChest chestModel = new ModelTreasureChest();

    @Override
    public void render(TileEntityTreasureChestVenus chest, double x, double y, double z, float par7, int par8, float alpha)
    {
        int var9;

        if (!chest.hasWorld())
        {
            var9 = 0;
        } else
        {
            var9 = chest.getBlockMetadata();
        }

        this.bindTexture(TileEntityTreasureChestRenderer.treasureChestTexture);

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        short var11 = 0;

        if (var9 == 2)
        {
            var11 = 180;
        }

        if (var9 == 3)
        {
            var11 = 0;
        }

        if (var9 == 4)
        {
            var11 = 90;
        }

        if (var9 == 5)
        {
            var11 = -90;
        }

        GlStateManager.rotate(var11, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        float var12 = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * par8;

        var12 = 1.0F - var12;
        var12 = 1.0F - var12 * var12 * var12;

        this.chestModel.chestLid.rotateAngleX = -(var12 * (float) Math.PI / 4.0F);
        this.chestModel.renderAll(!chest.locked);

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
