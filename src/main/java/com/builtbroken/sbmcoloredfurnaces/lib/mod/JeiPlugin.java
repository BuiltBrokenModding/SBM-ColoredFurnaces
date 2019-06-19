package com.builtbroken.sbmcoloredfurnaces.lib.mod;

import com.builtbroken.sbmcoloredfurnaces.ColoredFurnaces;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JeiPlugin implements IModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        registry.addRecipeCatalyst(new ItemStack(ColoredFurnaces.coloredFurnace), VanillaRecipeCategoryUid.SMELTING);
    }
}