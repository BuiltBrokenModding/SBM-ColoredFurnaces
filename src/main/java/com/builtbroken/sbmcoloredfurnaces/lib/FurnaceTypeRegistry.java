package com.builtbroken.sbmcoloredfurnaces.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.builtbroken.sbmcoloredfurnaces.ColoredFurnaces;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;

public class FurnaceTypeRegistry
{
    public static final Map<ResourceLocation,FurnaceType> TYPES = new TreeMap<>((s1, s2) -> s1.compareTo(s2));
    public static final List<ResourceLocation> TEXTURES_TO_STITCH = new ArrayList<>();

    public static void registerFurnaceType(ResourceLocation typeName, FurnaceType type, ResourceLocation... texturesToStitch)
    {
        if(Loader.instance().isInState(LoaderState.PREINITIALIZATION))
        {
            if(TYPES.containsKey(typeName))
                ColoredFurnaces.LOGGER.warn("Furnace type " + typeName + " already exists!");
            else
                TYPES.put(typeName, type);

            TEXTURES_TO_STITCH.addAll(Arrays.asList(texturesToStitch));
        }
        else
            ColoredFurnaces.LOGGER.warn("Furnace type registry is frozen before/after pre-init!");
    }
}
