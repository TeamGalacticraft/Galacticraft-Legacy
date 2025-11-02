/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.core.client.render.tile;

import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.tile.TileEntityDish;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class TileEntityDishRenderer extends TileEntitySpecialRenderer<TileEntityDish>
{

    private static final ResourceLocation textureSupport = new ResourceLocation(Constants.ASSET_PREFIX, "textures/model/telesupport.png");
    private static final ResourceLocation textureFork = new ResourceLocation(Constants.ASSET_PREFIX, "textures/model/telefork.png");
    private static final ResourceLocation textureDish = new ResourceLocation(Constants.ASSET_PREFIX, "textures/model/teledish.png");
    private static IBakedModel modelSupport;
    private static IBakedModel modelFork;
    private static IBakedModel modelDish;
    private TextureManager renderEngine = FMLClientHandler.instance().getClient().renderEngine;

    private void updateModels()
    {
        if (modelDish == null)
        {
            try
            {
                modelDish = ClientUtil.modelFromOBJ(new ResourceLocation(Constants.ASSET_PREFIX, "teledish.obj"));
                modelFork = ClientUtil.modelFromOBJ(new ResourceLocation(Constants.ASSET_PREFIX, "telefork.obj"));
                modelSupport = ClientUtil.modelFromOBJ(new ResourceLocation(Constants.ASSET_PREFIX, "telesupport.obj"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void render(TileEntityDish tile, double par2, double par4, double par6, float partialTickTime, int par9, float alpha)
    {
        this.updateModels();
        TileEntityDish dish = (TileEntityDish) tile;
        float hour = dish.rotation(partialTickTime) % 360F;
        float declination = dish.elevation(partialTickTime) % 360F;

        final EntityPlayer player = FMLClientHandler.instance().getClient().player;

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate((float) par2, (float) par4, (float) par6);
        GlStateManager.translate(0.5F, 1.0F, 0.5F);
        GlStateManager.scale(1.6F, 1.25F, 1.6F);

        this.renderEngine.bindTexture(textureSupport);
        ClientUtil.drawBakedModel(modelSupport);
        GlStateManager.scale(1.25F, 1.6F, 1.25F);
        GlStateManager.translate(0F, 2.88F * 0.15F / 1.6F, 0F);
        GlStateManager.scale(0.85F, 0.85F, 0.85F);
        GlStateManager.rotate(hour, 0, -1, 0);
        this.renderEngine.bindTexture(textureFork);
        ClientUtil.drawBakedModel(modelFork);

        GlStateManager.translate(0.0F, 2.3F, 0.0F);
        GlStateManager.rotate(declination, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0.0F, -2.3F, 0.0F);

        this.renderEngine.bindTexture(textureDish);
        ClientUtil.drawBakedModel(modelDish);

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }
}
