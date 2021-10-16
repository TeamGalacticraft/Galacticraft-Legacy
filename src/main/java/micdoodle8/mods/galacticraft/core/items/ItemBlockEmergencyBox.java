package micdoodle8.mods.galacticraft.core.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockEmergencyBox extends ItemBlockDesc
{

    public ItemBlockEmergencyBox(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getTranslationKey(ItemStack par1ItemStack)
    {
        if (par1ItemStack.getItemDamage() == 1)
        {
            return this.getBlock().getTranslationKey() + ".filled";
        }

        return this.getBlock().getTranslationKey();
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
}
