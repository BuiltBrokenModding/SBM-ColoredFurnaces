package com.builtbroken.sbmcoloredfurnaces.client;

import com.builtbroken.sbmcoloredfurnaces.ColoredFurnaces;
import com.builtbroken.sbmcoloredfurnaces.content.ItemBlockColoredFurnace;
import com.builtbroken.sbmcoloredfurnaces.lib.FurnaceTypeRegistry;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CustomMeshDefinition implements ItemMeshDefinition
{
    public final ModelResourceLocation defaultModelResourceLocation;

    public CustomMeshDefinition()
    {
        defaultModelResourceLocation = new ModelResourceLocation(new ResourceLocation(ColoredFurnaces.MODID, EnumDyeColor.WHITE.name() + "_colored_furnace"), "inventory");
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack)
    {
        if(stack != null && stack.getItem() instanceof ItemBlockColoredFurnace && stack.hasTagCompound() && stack.getTagCompound().hasKey("FurnaceType"))
            return FurnaceTypeRegistry.TYPES.get(new ResourceLocation(stack.getTagCompound().getString("FurnaceType"))).getItemModelLocation();
        else return defaultModelResourceLocation;
    }
}
