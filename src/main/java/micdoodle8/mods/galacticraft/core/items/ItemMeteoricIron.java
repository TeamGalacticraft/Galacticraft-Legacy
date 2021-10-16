package micdoodle8.mods.galacticraft.core.items;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMeteoricIron extends Item implements ISortableItem
{
//    private final String iconName;

    public ItemMeteoricIron(String assetName)
    {
        super();
//        this.iconName = assetName;
        this.setTranslationKey(assetName);
    }

    /*
     * @Override
     * @SideOnly(Side.CLIENT) public void registerIcons(IIconRegister
     * iconRegister) { this.itemIcon =
     * iconRegister.registerIcon("galacticraftmoon:" + this.iconName); }
     */

    @Override
    public CreativeTabs getCreativeTab()
    {
        return GalacticraftCore.galacticraftItemsTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta)
    {
        return EnumSortCategoryItem.GENERAL;
    }
}
