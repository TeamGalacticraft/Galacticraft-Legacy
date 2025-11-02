/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.client.render.tile;

import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.client.model.block.ModelSolarPanel;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderSpaceStation;
import micdoodle8.mods.galacticraft.core.tile.TileEntitySolar;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileEntitySolarPanelRenderer extends TileEntitySpecialRenderer<TileEntitySolar>
{

    private static final ResourceLocation solarPanelTexture = new ResourceLocation(Constants.ASSET_PREFIX, "textures/model/solar_panel_basic.png");
    private static final ResourceLocation solarPanelAdvTexture = new ResourceLocation(Constants.ASSET_PREFIX, "textures/model/solar_panel_advanced.png");
    public ModelSolarPanel model = new ModelSolarPanel();

    @Override
    public void render(TileEntitySolar panel, double par2, double par4, double par6, float partialTickTime, int par9, float alpha)
    {
        boolean doSkyRotation = false;
        if (panel.tierGC == 2)
        {
            this.bindTexture(TileEntitySolarPanelRenderer.solarPanelAdvTexture);
            doSkyRotation = panel.getWorld().provider instanceof WorldProviderSpaceStation;
        } else
        {
            this.bindTexture(TileEntitySolarPanelRenderer.solarPanelTexture);
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate((float) par2, (float) par4, (float) par6);

        GlStateManager.translate(0.5F, 1.0F, 0.5F);
        if (doSkyRotation)
        {
            GlStateManager.pushMatrix();
            GlStateManager.rotate(((WorldProviderSpaceStation) panel.getWorld().provider).getSkyRotation(), 0.0F, 1.0F, 0.0F);
            this.model.renderPole();
            GlStateManager.popMatrix();
        } else
            this.model.renderPole();

        GlStateManager.translate(0.0F, 1.5F, 0.0F);

        GlStateManager.rotate(180.0F, 0, 0, 1);
        GlStateManager.rotate(-90.0F, 0, 1, 0);

        float celestialAngle = (panel.getWorld().getCelestialAngle(1.0F) - 0.784690560F) * 360.0F;
        float celestialAngle2 = panel.getWorld().getCelestialAngle(1.0F) * 360.0F;

        if (doSkyRotation)
        {
            GlStateManager.rotate(((WorldProviderSpaceStation) panel.getWorld().provider).getSkyRotation(), 0.0F, -1.0F, 0.0F);
        }

        GlStateManager.rotate(panel.currentAngle - (celestialAngle - celestialAngle2), 1.0F, 0.0F, 0.0F);

        this.model.renderPanel();

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
