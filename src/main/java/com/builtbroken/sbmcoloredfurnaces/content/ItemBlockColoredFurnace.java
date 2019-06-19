package com.builtbroken.sbmcoloredfurnaces.content;

import com.builtbroken.sbmcoloredfurnaces.ColoredFurnaces;
import com.builtbroken.sbmcoloredfurnaces.lib.FurnaceTypeRegistry;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemBlockColoredFurnace extends ItemBlock
{
    public ItemBlockColoredFurnace(Block block)
    {
        super(block);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(tab == CreativeTabs.DECORATIONS)
        {
            for(ResourceLocation key : FurnaceTypeRegistry.TYPES.keySet())
            {
                ItemStack stack = new ItemStack(this);
                NBTTagCompound tag = new NBTTagCompound();

                tag.setString("FurnaceType", key.toString());
                stack.setTagCompound(tag);
                items.add(stack);
            }
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack)
    {
        if(stack != null && stack.hasTagCompound() && stack.getTagCompound().hasKey("FurnaceType"))
            return "tile." + ColoredFurnaces.COLORED_FURNACE_ID + "_" + stack.getTagCompound().getString("FurnaceType");
        else return super.getTranslationKey(stack);
    }
}