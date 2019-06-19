package com.builtbroken.sbmcoloredfurnaces;

import com.builtbroken.sbmcoloredfurnaces.content.BlockColoredFurnace;
import com.builtbroken.sbmcoloredfurnaces.content.TileEntityColoredFurnace;
import com.builtbroken.sbmcoloredfurnaces.lib.FurnaceType;
import com.builtbroken.sbmcoloredfurnaces.lib.FurnaceTypeRegistry;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapelessOreRecipe;

@EventBusSubscriber
public class FurnaceEventHandler
{
    @SubscribeEvent
    public static void onBlockRightClick(RightClickBlock event)
    {
        IBlockState state = event.getWorld().getBlockState(event.getPos());

        if(state.getBlock() == Blocks.FURNACE)
        {
            ItemStack stack = event.getEntityPlayer().getHeldItem(event.getHand());

            if(!stack.isEmpty())
            {
                for(ResourceLocation rl : FurnaceTypeRegistry.TYPES.keySet())
                {
                    FurnaceType type = FurnaceTypeRegistry.TYPES.get(rl);

                    if(stack.getItem() == type.getConversionItem().getItem() && stack.getMetadata() == type.getConversionItem().getMetadata())
                    {
                        event.getWorld().setBlockState(event.getPos(), ColoredFurnaces.coloredFurnace.getDefaultState().withProperty(BlockColoredFurnace.FACING, state.getValue(BlockFurnace.FACING)));

                        TileEntity te = event.getWorld().getTileEntity(event.getPos());

                        if(te instanceof TileEntityColoredFurnace)
                            ((TileEntityColoredFurnace)te).setFurnaceType(stack);

                        if(!event.getEntityPlayer().isCreative())
                            stack.shrink(1);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        for(ResourceLocation rl : FurnaceTypeRegistry.TYPES.keySet())
        {
            FurnaceType type = FurnaceTypeRegistry.TYPES.get(rl);
            NBTTagCompound tag = new NBTTagCompound();
            ItemStack stack = new ItemStack(ColoredFurnaces.coloredFurnace);

            tag.setString("FurnaceType", rl.toString());
            stack.setTagCompound(tag);
            event.getRegistry().register(new ShapelessOreRecipe(null, stack, new ItemStack(Blocks.FURNACE), type.getConversionItem()).setRegistryName(new ResourceLocation(ColoredFurnaces.MODID, "vanilla_to_" + rl.toString().replace(":", "_"))));
            event.getRegistry().register(new ShapelessOreRecipe(null, stack, new ItemStack(ColoredFurnaces.coloredFurnace), type.getConversionItem()).setRegistryName(new ResourceLocation(ColoredFurnaces.MODID, "mod_to_" + rl.toString().replace(":", "_"))));
        }
    }
}
