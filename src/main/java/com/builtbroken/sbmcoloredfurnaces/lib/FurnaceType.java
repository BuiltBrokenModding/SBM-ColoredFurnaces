package com.builtbroken.sbmcoloredfurnaces.lib;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class FurnaceType
{
    private ResourceLocation unLitBlockModelLocation;
    private ResourceLocation litBlockModelLocation;
    private ModelResourceLocation itemModelLocation;
    private ItemStack conversionItem;

    public FurnaceType(ResourceLocation unLitBlockModelLocation, ResourceLocation litBlockModelLocation, ResourceLocation itemModelLocation, ItemStack conversionItem)
    {
        this.unLitBlockModelLocation = unLitBlockModelLocation;
        this.litBlockModelLocation = litBlockModelLocation;
        this.itemModelLocation = new ModelResourceLocation(itemModelLocation, "inventory");
        this.conversionItem = conversionItem;
    }

    public ResourceLocation getUnLitBlockModelLocation()
    {
        return unLitBlockModelLocation;
    }

    public ResourceLocation getLitBlockModelLocation()
    {
        return litBlockModelLocation;
    }

    public ModelResourceLocation getItemModelLocation()
    {
        return itemModelLocation;
    }

    public ItemStack getConversionItem()
    {
        return conversionItem;
    }
}
