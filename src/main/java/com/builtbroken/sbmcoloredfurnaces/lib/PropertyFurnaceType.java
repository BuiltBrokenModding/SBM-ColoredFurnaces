package com.builtbroken.sbmcoloredfurnaces.lib;

import net.minecraftforge.common.property.IUnlistedProperty;

public class PropertyFurnaceType implements IUnlistedProperty<String>
{
    @Override
    public String getName()
    {
        return "furnace_type";
    }

    @Override
    public boolean isValid(String value)
    {
        return true;
    }

    @Override
    public Class<String> getType()
    {
        return String.class;
    }

    @Override
    public String valueToString(String value)
    {
        return value;
    }
}
