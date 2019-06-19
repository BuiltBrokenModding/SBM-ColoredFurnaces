package com.builtbroken.sbmcoloredfurnaces.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.builtbroken.sbmcoloredfurnaces.content.BlockColoredFurnace;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.property.IExtendedBlockState;

public class ColoredFurnaceBakedModel implements IBakedModel
{
    public static final Map<ResourceLocation,Map<EnumFacing,IBakedModel>> UNLIT_TYPE_MODELS = new HashMap<>();
    public static final Map<ResourceLocation,Map<EnumFacing,IBakedModel>> LIT_TYPE_MODELS = new HashMap<>();
    private ResourceLocation type = null;
    private EnumFacing facing = null;
    private boolean isLit;

    public ColoredFurnaceBakedModel(boolean isLit)
    {
        this.isLit = isLit;
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand)
    {
        if(state instanceof IExtendedBlockState)
        {
            String saved = ((IExtendedBlockState)state).getValue(BlockColoredFurnace.TYPE);

            if(saved != null)
            {
                type = new ResourceLocation(saved);
                facing = state.getValue(BlockColoredFurnace.FACING);

                if(isLit && LIT_TYPE_MODELS.containsKey(type))
                {
                    Map<EnumFacing,IBakedModel> facingMap = LIT_TYPE_MODELS.get(type);

                    if(facingMap != null)
                        return facingMap.getOrDefault(facing, Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getMissingModel()).getQuads(state, side, rand);
                }
                else if(!isLit && UNLIT_TYPE_MODELS.containsKey(type))
                {
                    Map<EnumFacing,IBakedModel> facingMap = UNLIT_TYPE_MODELS.get(type);

                    if(facingMap != null)
                        return facingMap.getOrDefault(facing, Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getMissingModel()).getQuads(state, side, rand);
                }
            }
        }

        return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getMissingModel().getQuads(state, side, rand);
    }

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        if(isLit && LIT_TYPE_MODELS.containsKey(type))
        {
            Map<EnumFacing,IBakedModel> facingMap = LIT_TYPE_MODELS.get(type);

            if(facingMap != null)
                return facingMap.getOrDefault(facing, Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getMissingModel()).getParticleTexture();
        }
        else if(!isLit && UNLIT_TYPE_MODELS.containsKey(type))
        {
            Map<EnumFacing,IBakedModel> facingMap = UNLIT_TYPE_MODELS.get(type);

            if(facingMap != null)
                return facingMap.getOrDefault(facing, Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getMissingModel()).getParticleTexture();
        }

        return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getMissingModel().getParticleTexture();
    }

    @Override
    public boolean isAmbientOcclusion()
    {
        return true;
    }

    @Override
    public boolean isGui3d()
    {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return false;
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        if(isLit && LIT_TYPE_MODELS.containsKey(type))
        {
            Map<EnumFacing,IBakedModel> facingMap = LIT_TYPE_MODELS.get(type);

            if(facingMap != null)
                return facingMap.getOrDefault(facing, Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getMissingModel()).getOverrides();
        }
        else if(!isLit && UNLIT_TYPE_MODELS.containsKey(type))
        {
            Map<EnumFacing,IBakedModel> facingMap = UNLIT_TYPE_MODELS.get(type);

            if(facingMap != null)
                return facingMap.getOrDefault(facing, Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getMissingModel()).getOverrides();
        }

        return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getMissingModel().getOverrides();
    }
}
