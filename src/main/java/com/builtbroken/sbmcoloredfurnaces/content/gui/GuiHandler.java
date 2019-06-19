package com.builtbroken.sbmcoloredfurnaces.content.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

import com.builtbroken.sbmcoloredfurnaces.content.TileEntityColoredFurnace;

/**
 * Created by Dark(DarkGuardsman, Robert) on 6/9/2019.
 */
public class GuiHandler implements IGuiHandler
{
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if(tileEntity instanceof TileEntityColoredFurnace)
        {
            return new ContainerColoredFurnace(player.inventory, (TileEntityColoredFurnace) tileEntity);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if(tileEntity instanceof TileEntityColoredFurnace)
        {
            return new GuiColoredFurnace(player.inventory, (TileEntityColoredFurnace) tileEntity);
        }
        return null;
    }
}
