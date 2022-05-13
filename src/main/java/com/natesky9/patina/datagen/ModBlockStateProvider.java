package com.natesky9.patina.datagen;

import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper)
    {
        super(gen, Patina.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        //add blocks here
        //simpleBlock(ModBlocks.TEST_BLOCK.get());
        //stairsBlock((StairBlock) ModBlocks.TEST_STAIRS.get(),blockTexture(ModBlocks.TEST_BLOCK.get()));
        //slabBlock((SlabBlock) ModBlocks.TEST_SLAB.get(),blockTexture(ModBlocks.TEST_BLOCK.get()), blockTexture(ModBlocks.TEST_BLOCK.get()));
        //wallBlock((WallBlock) ModBlocks.TEST_WALL.get(),blockTexture(ModBlocks.TEST_BLOCK.get()));
        //simpleBlock(ModBlocks.HONEY_PUDDLE.get());//change to thin block
        //simpleBlock(ModBlocks.TELECHORUS.get());
        //pipeBlock((PipeBlock) ModBlocks.CHORUS_CABLE.get(),blockTexture(Blocks.CHORUS_FLOWER),blockTexture(Blocks.CHORUS_PLANT));
        //simpleBlock(ModBlocks.MACHINE_BLAST_CAULDRON.get());
        //simpleBlock(ModBlocks.MACHINE_CAULDRON_BREWING.get());
        //simpleBlock(ModBlocks.MACHINE_SMOKER_GRINDSTONE.get());
        //simpleBlock(ModBlocks.CUSTOM_BLOCK.get());


        //done adding blocks
    }

    public void pipeBlock(PipeBlock block, ResourceLocation nodeTexture, ResourceLocation pipeTexture)
    {
        String baseName = block.getRegistryName().toString();
        String texture1 = "node_texture";
        String texture2 = "pipe_texture";

        BlockModelBuilder node = models().getBuilder(baseName+"_node").texture(texture1,nodeTexture);
        BlockModelBuilder pipe = models().getBuilder(baseName+"_pipe").texture(texture2,pipeTexture);
        BlockModelBuilder inventory = models().cubeAll(baseName+"_inventory",blockTexture(block));
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);

        formNode(node,texture1);
        formPipe(pipe,texture2);

        addNode(builder,node);
        addPipe(builder,pipe);
        builder.part().modelFile(inventory).addModel();

    }
    private void formNode(BlockModelBuilder builder, String texture)
    {
        box(builder,texture,5,5,5,11,11,11);
    }
    private void formPipe(BlockModelBuilder builder, String texture)
    {
        box(builder,texture,6,6,0,10,10,5);
    }

    public MultiPartBlockStateBuilder addNode(MultiPartBlockStateBuilder builder, ModelFile node)
    {
        //center node
        return builder.part().modelFile(node).addModel().end();
    }
    public MultiPartBlockStateBuilder addPipe(MultiPartBlockStateBuilder builder, ModelFile pipe) {
        //6 pipes out
        builder.part().modelFile(pipe).rotationX(90).addModel().condition(BlockStateProperties.DOWN,true);
        builder.part().modelFile(pipe).rotationX(270).addModel().condition(BlockStateProperties.UP,true);
        builder.part().modelFile(pipe).rotationY(0).addModel().condition(BlockStateProperties.NORTH,true);
        builder.part().modelFile(pipe).rotationY(90).addModel().condition(BlockStateProperties.EAST,true);
        builder.part().modelFile(pipe).rotationY(180).addModel().condition(BlockStateProperties.SOUTH,true);
        builder.part().modelFile(pipe).rotationY(270).addModel().condition(BlockStateProperties.WEST,true);
        return builder;
    }
    //
    public void box(BlockModelBuilder builder, String texture, int x, int y, int z, int xx, int yy, int zz)
    {
        builder.element().from(x,y,z).to(xx,yy,zz).allFaces((direction, faceBuilder) -> faceBuilder.texture("#"+texture));

    }
}
