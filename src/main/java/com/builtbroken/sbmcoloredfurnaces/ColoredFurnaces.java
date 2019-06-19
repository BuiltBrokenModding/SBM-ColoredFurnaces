package com.builtbroken.sbmcoloredfurnaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.builtbroken.sbmcoloredfurnaces.content.BlockColoredFurnace;
import com.builtbroken.sbmcoloredfurnaces.content.ItemBlockColoredFurnace;
import com.builtbroken.sbmcoloredfurnaces.content.TileEntityColoredFurnace;
import com.builtbroken.sbmcoloredfurnaces.content.gui.GuiHandler;
import com.builtbroken.sbmcoloredfurnaces.lib.FurnaceType;
import com.builtbroken.sbmcoloredfurnaces.lib.FurnaceTypeRegistry;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ColoredFurnaces.MODID, name = ColoredFurnaces.MOD_NAME, version = ColoredFurnaces.VERSION)
@Mod.EventBusSubscriber(modid = ColoredFurnaces.MODID)
public class ColoredFurnaces
{
    public static final String MOD_NAME = "[SBM] Colored Furnaces";
    public static final String MODID = "sbmcoloredfurnaces";
    public static final String COLORED_FURNACE_ID = MODID + ":colored_furnace";
    public static final String LIT_COLORED_FURNACE_ID = MODID + ":lit_colored_furnace";
    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String MC_VERSION = "@MC@";
    public static final String VERSION = MC_VERSION + "-" + MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;
    @Mod.Instance(MODID)
    public static ColoredFurnaces INSTANCE;
    @GameRegistry.ObjectHolder(COLORED_FURNACE_ID)
    public static BlockColoredFurnace coloredFurnace;
    @GameRegistry.ObjectHolder(COLORED_FURNACE_ID)
    public static ItemBlockColoredFurnace coloredFurnaceItem;
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());

        for(EnumDyeColor color : EnumDyeColor.values())
        {
            String fileName = color.getName() + "_colored_furnace";

            FurnaceTypeRegistry.registerFurnaceType(new ResourceLocation(MODID, color.getName()),
                    new FurnaceType(new ResourceLocation(MODID, "block/" + fileName),
                            new ResourceLocation(MODID, "block/" + color.getName() + "_colored_lit_furnace"),
                            new ResourceLocation(MODID, fileName),
                            new ItemStack(Items.DYE, 1, color.getDyeDamage())),
                    new ResourceLocation(ColoredFurnaces.MODID, "blocks/" + color.getName() + "_colored_furnace_on"),
                    new ResourceLocation(ColoredFurnaces.MODID, "blocks/" + color.getName() + "_colored_furnace_side"),
                    new ResourceLocation(ColoredFurnaces.MODID, "blocks/" + color.getName() + "_colored_furnace_top"),
                    new ResourceLocation(ColoredFurnaces.MODID, "blocks/" + color.getName() + "_colored_furnace"));
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().register(new BlockColoredFurnace().setRegistryName(COLORED_FURNACE_ID).setTranslationKey(COLORED_FURNACE_ID));
        GameRegistry.registerTileEntity(TileEntityColoredFurnace.class, new ResourceLocation(ColoredFurnaces.MODID, "colored_furnace"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new ItemBlockColoredFurnace(coloredFurnace).setRegistryName(coloredFurnace.getRegistryName()));
    }
}
