package com.natesky9.patina.datagen;

import com.natesky9.patina.Patina;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput packOutput, ExistingFileHelper exFileHelper)
    {
        super(packOutput, Patina.MODID, exFileHelper);
    }

    @Override
    public void registerStatesAndModels()
    {
        //add blocks here

        //done adding blocks
    }
    private void basicBlock(Block block)
    {
        String name = block.getName().toString();
        ResourceLocation texture = blockTexture(block);
        ModelFile modelFile = models().cubeAll(name,texture);
        models().getBuilder(name).texture("all",texture);

        getVariantBuilder(block).partialState().addModels(new ConfiguredModel(modelFile));
        //itemModels().getBuilder(block.getRegistryName().getPath()).parent(modelFile);
    }
    private void thinBlock(Block block, ResourceLocation texture)
    {
        String name = block.getName().toString();
        BlockModelBuilder builder = models().getBuilder(name);

        ModelFile modelFile = models().cubeAll(name,texture);
        builder.texture("texture",texture);
        builder.element().from(0,0,0).to(16,1,16).texture("texture");


        getVariantBuilder(block).partialState().addModels(new ConfiguredModel(modelFile));
    }
    public void makeCrop(CropBlock block, String name) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, name);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String name) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(name + state.getValue(block.getAgeProperty()),
                new ResourceLocation(Patina.MODID, "block/plants/" + name + state.getValue(block.getAgeProperty()))));

        return models;
    }

    public void pipeBlock(PipeBlock block, ResourceLocation nodeTexture, ResourceLocation pipeTexture)
    {
        String baseName = block.getName().toString();
        String texture1 = "node_texture";
        String texture2 = "pipe_texture";

        MultiPartBlockStateBuilder multipartBuilder = getMultipartBuilder(block);
        MultiPartBlockStateBuilder inventoryBuilder = getMultipartBuilder(block);

        BlockModelBuilder nodeModel = models().getBuilder(baseName+"_node").texture(texture1,nodeTexture);
        BlockModelBuilder pipeModel = models().getBuilder(baseName+"_pipe").texture(texture2,pipeTexture);


        formNode(nodeModel);
        formPipe(pipeModel);
        nodeModel.texture("node",nodeTexture);
        pipeModel.texture("pipe",pipeTexture);

        blockPipe(multipartBuilder,nodeModel,pipeModel);

    }
    private void formNode(BlockModelBuilder builder)
    {
        box(builder,5,5,5,11,11,11);
    }
    private void formPipe(BlockModelBuilder builder)
    {
        box(builder,6,6,0,10,10,5);
    }

    public void blockPipe(MultiPartBlockStateBuilder builder, ModelFile node, ModelFile pipe) {
        builder.part().modelFile(node).addModel();
        //6 pipes out
        builder.part().modelFile(pipe).rotationX(90).addModel().condition(BlockStateProperties.DOWN,true);
        builder.part().modelFile(pipe).rotationX(270).addModel().condition(BlockStateProperties.UP,true);
        builder.part().modelFile(pipe).rotationY(0).addModel().condition(BlockStateProperties.NORTH,true);
        builder.part().modelFile(pipe).rotationY(90).addModel().condition(BlockStateProperties.EAST,true);
        builder.part().modelFile(pipe).rotationY(180).addModel().condition(BlockStateProperties.SOUTH,true);
        builder.part().modelFile(pipe).rotationY(270).addModel().condition(BlockStateProperties.WEST,true);
    }
    //
    public void box(BlockModelBuilder builder, int x, int y, int z, int xx, int yy, int zz)
    {
        builder.element().from(x,y,z).to(xx,yy,zz);
    }
}
