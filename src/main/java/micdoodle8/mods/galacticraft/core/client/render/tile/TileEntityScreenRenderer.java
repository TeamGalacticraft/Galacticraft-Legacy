/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.client.render.tile;

import java.nio.FloatBuffer;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.tile.TileEntityScreen;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityScreenRenderer extends TileEntitySpecialRenderer<TileEntityScreen>
{

    public static final ResourceLocation blockTexture = new ResourceLocation(Constants.ASSET_PREFIX, "textures/blocks/screen_side.png");
    private TextureManager renderEngine = FMLClientHandler.instance().getClient().renderEngine;
    private static FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);

    private float yPlane = 0.91F;
    float frame = 0.098F;

    @Override
    public void render(TileEntityScreen screen, double par2, double par4, double par6, float partialTickTime, int par9, float alpha)
    {
        GlStateManager.pushMatrix();
        // Texture file
        this.renderEngine.bindTexture(TileEntityScreenRenderer.blockTexture);
        GlStateManager.translate((float) par2, (float) par4, (float) par6);

        int meta = screen.getBlockMetadata();
        boolean screenData = (meta >= 8);
        meta &= 7;

        switch (meta)
        {
            case 0:
                GlStateManager.rotate(180, 1, 0, 0);
                GlStateManager.translate(0, -1.0F, -1.0F);
                break;
            case 1:
                break;
            case 2:
                GlStateManager.translate(0.0F, 0.0F, -0.87F);
                GlStateManager.rotate(90, 1.0F, 0, 0);
                GlStateManager.translate(0.0F, 0.0F, -1.0F);
                break;
            case 3:
                GlStateManager.translate(0.0F, 0.0F, 0.87F);
                GlStateManager.rotate(90, -1.0F, 0, 0);
                GlStateManager.translate(1.0F, -1.0F, 1.0F);
                GlStateManager.rotate(180, 0, -1.0F, 0);
                break;
            case 4:
                GlStateManager.translate(-0.87F, 0.0F, 0.0F);
                GlStateManager.rotate(90, 0, 0, -1.0F);
                GlStateManager.translate(-1.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90, 0, 1.0F, 0);
                break;
            case 5:
                GlStateManager.translate(0.87F, 0.0F, 0.0F);
                GlStateManager.rotate(90, 0, 0, 1.0F);
                GlStateManager.translate(1.0F, -1.0F, 0.0F);
                GlStateManager.rotate(90, 0, -1.0F, 0);
                break;
            default:
                break;
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        GlStateManager.translate(-screen.screenOffsetx, this.yPlane, -screen.screenOffsetz);
        GlStateManager.rotate(90, 1F, 0F, 0F);
        boolean cornerblock = false;
        if (screen.connectionsLeft == 0 || screen.connectionsRight == 0)
        {
            cornerblock = (screen.connectionsUp == 0 || screen.connectionsDown == 0);
        }
        int totalLR = screen.connectionsLeft + screen.connectionsRight;
        int totalUD = screen.connectionsUp + screen.connectionsDown;
        if (totalLR > 1 && totalUD > 1 && !cornerblock)
        {
            // centre block
            if (screen.connectionsLeft == screen.connectionsRight - (totalLR | 1))
            {
                if (screen.connectionsUp == screen.connectionsDown - (totalUD | 1))
                {
                    cornerblock = true;
                }
            }
        }
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.translate(-screen.screen.getScaleX(), 0.0F, 0.0F);
        screen.screen.drawScreen(screen.imageType, partialTickTime + screen.getWorld().getWorldTime(), cornerblock);

        GlStateManager.popMatrix();
    }
}
