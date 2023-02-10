/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.asteroids.items;

import java.util.List;
import javax.annotation.Nullable;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.items.ItemCanisterGeneric;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCanisterLiquidNitrogen extends ItemCanisterGeneric implements ISortableItem
{

    public ItemCanisterLiquidNitrogen(String assetName)
    {
        super(assetName);
        this.setAllowedFluid("liquidnitrogen");
    }

    @Override
    public String getTranslationKey(ItemStack itemStack)
    {
        if (itemStack.getMaxDamage() - itemStack.getItemDamage() == 0)
        {
            return "item.empty_gas_canister";
        }

        if (itemStack.getItemDamage() == 1)
        {
            return "item.canister.liquid_nitrogen.full";
        }

        return "item.canister.liquid_nitrogen.partial";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() > 0)
        {
            tooltip.add(GCCoreUtil.translate("item.canister.liquid_nitrogen.name") + ": " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()));
        }
    }

    private Block canFreeze(Block b)
    {
        if (b == Blocks.WATER)
        {
            return Blocks.ICE;
        }
        if (b == Blocks.LAVA)
        {
            return Blocks.OBSIDIAN;
        }
        return null;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack itemStack = playerIn.getHeldItem(hand);

        int damage = itemStack.getItemDamage() + 125;
        if (damage > itemStack.getMaxDamage())
        {
            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        }

        RayTraceResult movingobjectposition = this.rayTrace(worldIn, playerIn, true);

        if (movingobjectposition == null || movingobjectposition.typeOfHit == RayTraceResult.Type.MISS)
        {
            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        } else
        {
            if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos pos = movingobjectposition.getBlockPos();

                if (!worldIn.canMineBlockBody(playerIn, pos))
                {
                    return new ActionResult<>(EnumActionResult.PASS, itemStack);
                }

                if (!playerIn.canPlayerEdit(pos, movingobjectposition.sideHit, itemStack))
                {
                    return new ActionResult<>(EnumActionResult.PASS, itemStack);
                }

                IBlockState state = worldIn.getBlockState(pos);
                Block b = state.getBlock();

                Block result = this.canFreeze(b);
                if (result != null)
                {
                    this.setNewDamage(itemStack, damage);
                    worldIn.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.NEUTRAL, 1.0F, Item.itemRand.nextFloat() * 0.4F + 0.8F);
                    worldIn.setBlockState(pos, result.getDefaultState(), 3);
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
                }
            }

            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        }
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta)
    {
        return EnumSortCategoryItem.CANISTER;
    }
}
