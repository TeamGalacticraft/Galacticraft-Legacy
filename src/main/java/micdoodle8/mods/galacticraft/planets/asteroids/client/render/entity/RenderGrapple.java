/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.asteroids.client.render.entity;

import com.google.common.base.Function;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.planets.GalacticraftPlanets;
import micdoodle8.mods.galacticraft.planets.asteroids.client.render.item.ItemModelGrapple;
import micdoodle8.mods.galacticraft.planets.asteroids.entities.EntityGrapple;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

public class RenderGrapple extends Render<EntityGrapple>
{

    private ItemModelGrapple grappleModel;

    public RenderGrapple(RenderManager manager)
    {
        super(manager);
    }

    private void updateModel()
    {
        if (grappleModel == null)
        {
            Function<ResourceLocation, TextureAtlasSprite> TEXTUREGETTER = input -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(input.toString());

            ModelResourceLocation modelResourceLocation = new ModelResourceLocation(GalacticraftPlanets.TEXTURE_PREFIX + "grapple", "inventory");
            grappleModel = (ItemModelGrapple) FMLClientHandler.instance().getClient().getRenderItem().getItemModelMesher().getModelManager().getModel(modelResourceLocation);
        }
    }

    @Override
    public void doRender(EntityGrapple grapple, double x, double y, double z, float par8, float partialTicks)
    {
        GlStateManager.disableRescaleNormal();
        GlStateManager.pushMatrix();

        Vec3d vec3 = new Vec3d(0.0D, -0.2D, 0.0D);
        EntityPlayer shootingEntity = grapple.getShootingEntity();

        if (shootingEntity != null && grapple.getPullingEntity())
        {
            double d3 = shootingEntity.prevPosX + (shootingEntity.posX - shootingEntity.prevPosX) * partialTicks + vec3.x;
            double d4 = shootingEntity.prevPosY + (shootingEntity.posY - shootingEntity.prevPosY) * partialTicks + vec3.y;
            double d5 = shootingEntity.prevPosZ + (shootingEntity.posZ - shootingEntity.prevPosZ) * partialTicks + vec3.z;

            Tessellator tessellator = Tessellator.getInstance();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            tessellator.getBuffer().begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
            byte b2 = 16;

            double d14 = grapple.prevPosX + (grapple.posX - grapple.prevPosX) * partialTicks;
            double d8 = grapple.prevPosY + (grapple.posY - grapple.prevPosY) * partialTicks + 0.25D;
            double d10 = grapple.prevPosZ + (grapple.posZ - grapple.prevPosZ) * partialTicks;
            double d11 = (float) (d3 - d14);
            double d12 = (float) (d4 - d8);
            double d13 = (float) (d5 - d10);
            tessellator.getBuffer().setTranslation(0, -0.2F, 0);

            for (int i = 0; i <= b2; ++i)
            {
                float f12 = (float) i / (float) b2;
                tessellator.getBuffer().pos(x + d11 * f12, y + d12 * (f12 * f12 + f12) * 0.5D + 0.15D, z + d13 * f12).color(203.0F / 255.0F, 203.0F / 255.0F, 192.0F / 255.0F, 1.0F).endVertex();
            }

            tessellator.draw();
            tessellator.getBuffer().setTranslation(0, 0, 0);
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
        }

        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.rotate(grapple.prevRotationYaw + (grapple.rotationYaw - grapple.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(grapple.prevRotationPitch + (grapple.rotationPitch - grapple.prevRotationPitch) * partialTicks - 180, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(grapple.prevRotationRoll + (grapple.rotationRoll - grapple.prevRotationRoll) * partialTicks, 1.0F, 0.0F, 0.0F);

        updateModel();

        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        if (Minecraft.isAmbientOcclusionEnabled())
        {
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
        } else
        {
            GlStateManager.shadeModel(GL11.GL_FLAT);
        }

        ClientUtil.drawBakedModel(grappleModel);

//        this.bindEntityTexture(grapple);
//        ItemRendererGrappleHook.modelGrapple.renderAll(); TODO

        GlStateManager.popMatrix();
    }

//    protected ResourceLocation getEntityTexture(EntityGrapple grapple)
//    {
////        return ItemRendererGrappleHook.grappleTexture;
//    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGrapple entity)
    {
        return new ResourceLocation("missing");
    }

    @Override
    public boolean shouldRender(EntityGrapple entity, ICamera camera, double camX, double camY, double camZ)
    {
        return entity.isInRangeToRender3d(camX, camY, camZ);
    }
}
