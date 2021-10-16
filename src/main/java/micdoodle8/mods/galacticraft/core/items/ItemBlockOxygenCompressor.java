package micdoodle8.mods.galacticraft.core.items;

import micdoodle8.mods.galacticraft.core.blocks.BlockOxygenCompressor;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockOxygenCompressor extends ItemBlockDesc
{

    public ItemBlockOxygenCompressor(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getTranslationKey(ItemStack itemstack)
    {
        int metadata = 0;

        if (itemstack.getItemDamage() >= BlockOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA)
        {
            metadata = 1;
        } else if (itemstack.getItemDamage() >= BlockOxygenCompressor.OXYGEN_COMPRESSOR_METADATA)
        {
            metadata = 0;
        }

        return this.getBlock().getTranslationKey() + "." + metadata;
    }

    @Override
    public String getTranslationKey()
    {
        return this.getBlock().getTranslationKey() + ".0";
    }
}
