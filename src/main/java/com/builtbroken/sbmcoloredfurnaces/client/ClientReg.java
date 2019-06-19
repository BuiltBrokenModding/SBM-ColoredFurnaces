package com.builtbroken.sbmcoloredfurnaces.client;

import java.util.HashMap;
import java.util.Map;

import com.builtbroken.sbmcoloredfurnaces.ColoredFurnaces;
import com.builtbroken.sbmcoloredfurnaces.lib.FurnaceType;
import com.builtbroken.sbmcoloredfurnaces.lib.FurnaceTypeRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid=ColoredFurnaces.MODID, value=Side.CLIENT)
public class ClientReg
{
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        CustomMeshDefinition meshDefinition = new CustomMeshDefinition();

        ModelBakery.registerItemVariants(ColoredFurnaces.coloredFurnaceItem, meshDefinition.defaultModelResourceLocation);

        for(FurnaceType type : FurnaceTypeRegistry.TYPES.values())
        {
            ModelBakery.registerItemVariants(ColoredFurnaces.coloredFurnaceItem, type.getItemModelLocation());
        }

        ModelLoader.setCustomMeshDefinition(ColoredFurnaces.coloredFurnaceItem, meshDefinition);
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event)
    {
        for(EnumFacing facing : EnumFacing.HORIZONTALS)
        {
            event.getModelRegistry().putObject(new ModelResourceLocation(ColoredFurnaces.COLORED_FURNACE_ID, "burning=false,facing=" + facing.getName()), new ColoredFurnaceBakedModel(false));
            event.getModelRegistry().putObject(new ModelResourceLocation(ColoredFurnaces.COLORED_FURNACE_ID, "burning=true,facing=" + facing.getName()), new ColoredFurnaceBakedModel(true));
        }

        for(ResourceLocation rl : FurnaceTypeRegistry.TYPES.keySet())
        {
            try
            {
                Map<EnumFacing,IBakedModel> unLitFacingMap = new HashMap<>();
                Map<EnumFacing,IBakedModel> litFacingMap = new HashMap<>();

                for(EnumFacing facing : EnumFacing.HORIZONTALS)
                {
                    IModel unLitModel = ModelLoaderRegistry.getModel(FurnaceTypeRegistry.TYPES.get(rl).getUnLitBlockModelLocation());
                    IModel litModel = ModelLoaderRegistry.getModel(FurnaceTypeRegistry.TYPES.get(rl).getLitBlockModelLocation());

                    unLitFacingMap.put(facing, unLitModel.bake(TRSRTransformation.from(facing), DefaultVertexFormats.BLOCK, resloc -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(resloc.toString())));
                    litFacingMap.put(facing, litModel.bake(TRSRTransformation.from(facing), DefaultVertexFormats.BLOCK, resloc -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(resloc.toString())));
                }

                ColoredFurnaceBakedModel.UNLIT_TYPE_MODELS.put(rl, unLitFacingMap);
                ColoredFurnaceBakedModel.LIT_TYPE_MODELS.put(rl, litFacingMap);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public static void onTextureStitchPre(TextureStitchEvent.Pre event)
    {
        for(ResourceLocation rl : FurnaceTypeRegistry.TEXTURES_TO_STITCH)
        {
            event.getMap().registerSprite(rl);
        }
    }
}
