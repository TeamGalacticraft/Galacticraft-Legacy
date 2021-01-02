package micdoodle8.mods.galacticraft.planets.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import micdoodle8.mods.galacticraft.planets.mars.blocks.MarsBlocks;
import micdoodle8.mods.galacticraft.planets.mars.tile.TileEntityTreasureChestMars;
import micdoodle8.mods.galacticraft.planets.venus.blocks.VenusBlocks;
import micdoodle8.mods.galacticraft.planets.venus.tile.TileEntityTreasureChestVenus;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ItemStackTileEntityRendererPlanets extends ItemStackTileEntityRenderer
{
    @Override
    public void render(ItemStack itemStack, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay)
    {
        Item item = itemStack.getItem();

        if (item instanceof BlockItem)
        {
            TileEntity tileentity = null;
            Block block = ((BlockItem)item).getBlock();

            if (block == MarsBlocks.TIER_2_TREASURE_CHEST)
            {
                tileentity = new TileEntityTreasureChestMars();
            }
            else if (block == VenusBlocks.TIER_3_TREASURE_CHEST)
            {
                tileentity = new TileEntityTreasureChestVenus();
            }

            if (tileentity != null)
            {
                TileEntityRendererDispatcher.instance.renderItem(tileentity, matrixStack, buffer, combinedLight, combinedOverlay);
            }
        }
    }
}