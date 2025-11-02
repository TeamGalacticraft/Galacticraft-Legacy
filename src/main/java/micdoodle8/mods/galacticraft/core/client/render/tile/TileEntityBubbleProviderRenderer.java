/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.client.render.tile;

import com.google.common.collect.ImmutableList;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.entities.IBubbleProvider;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@Deprecated
public class TileEntityBubbleProviderRenderer<E extends TileEntity & IBubbleProvider> extends TileEntitySpecialRenderer<E>
{

    private static IBakedModel sphere;

    private final float colorRed;
    private final float colorGreen;
    private final float colorBlue;

    public TileEntityBubbleProviderRenderer(float colorRed, float colorGreen, float colorBlue)
    {
        this.colorRed = colorRed;
        this.colorGreen = colorGreen;
        this.colorBlue = colorBlue;
    }

    private static void updateModels()
    {
        if (sphere == null)
        {
            try
            {
                sphere = ClientUtil.modelFromOBJ(new ResourceLocation(Constants.ASSET_PREFIX, "sphere.obj"), ImmutableList.of("Sphere"));
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void render(E provider, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        if (!provider.getBubbleVisible())
        {
            return;
        }

        updateModels();

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float) x + 0.5F, (float) y + 1.0F, (float) z + 0.5F);

        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();

        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(this.colorRed / 2.0F, this.colorGreen / 2.0F, this.colorBlue / 2.0F, 1.0F);
        GlStateManager.matrixMode(GL11.GL_TEXTURE);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.depthMask(false);
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.scale(provider.getBubbleSize(), provider.getBubbleSize(), provider.getBubbleSize());

        int color = ColorUtil.to32BitColor(30, (int) (this.colorBlue / 2.0F * 255), (int) (this.colorGreen / 2.0F * 255), (int) (this.colorRed / 2.0F * 255));
        ClientUtil.drawBakedModelColored(sphere, color);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.matrixMode(GL11.GL_TEXTURE);
        GlStateManager.depthMask(true);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.depthFunc(GL11.GL_LEQUAL);
        GlStateManager.enableCull();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
