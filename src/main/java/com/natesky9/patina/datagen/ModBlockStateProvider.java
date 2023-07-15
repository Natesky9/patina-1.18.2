package com.natesky9.patina.datagen;

import com.natesky9.patina.Block.ChorusCableBlock;
import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

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
        horizontalBlock(ModBlocks.MACHINE_EVAPORATOR.get(),
                new ResourceLocation(Patina.MODID,"block/evaporator_side"),
                new ResourceLocation(Patina.MODID,"block/evaporator_front"),
                new ResourceLocation(Patina.MODID,"block/evaporator_top"));
        simpleBlock(ModBlocks.PRISMATIC_ORE.get());
        simpleBlock(ModBlocks.BISMUTH_ORE.get());
        simpleBlock(ModBlocks.APPLIANCE_ICEBOX.get());
        simpleBlock(ModBlocks.APPLIANCE_WARDROBE.get());
        simpleBlock(ModBlocks.MACHINE_FOUNDRY.get());
        simpleBlock(ModBlocks.MACHINE_ENCHANTER.get());
        simpleBlock(ModBlocks.MACHINE_ALEMBIC.get());

        models().cubeAll(ModBlocks.TEST_GENERATOR.getId().getPath(),new ResourceLocation("block/iron_block"));
        models().withExistingParent(ModBlocks.CHORUS_TELEPORTER.getId().getPath(),"block/cube_bottom_top")
                .texture("side", modLoc("block/chorus_teleporter_side"))
                .texture("bottom", new ResourceLocation("block/end_stone_bricks"))
                .texture("top", modLoc("block/chorus_teleporter_top"));
        pipeBlock(ModBlocks.CHORUS_CABLE,
                new ResourceLocation("block/chorus_plant"),
                new ResourceLocation("block/white_wool"));
        pipeBlock(ModBlocks.CHARGE_CABLE,
                new ResourceLocation("block/yellow_wool"),
                new ResourceLocation("block/copper_block"));
        //done adding blocks
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


    public void pipeBlock(RegistryObject<Block> block, ResourceLocation nodeTexture, ResourceLocation pipeTexture)
    {
        String baseName = block.getId().getPath();
        String node_string = "node_texture";
        String pipe_string = "pipe_texture";

        MultiPartBlockStateBuilder builder = getMultipartBuilder(block.get());

        BlockModelBuilder nodeModel = models().cubeAll(baseName,new ResourceLocation("block/chorus_plant"))
                .texture(node_string,nodeTexture)
                .texture("particle",nodeTexture);
        builder.part().modelFile(nodeModel);

        BlockModelBuilder pipeModel = models().withExistingParent(baseName+"_pipe","block/block")
                .texture(pipe_string,pipeTexture)
                        .texture("particle",pipeTexture);
                //.getBuilder(baseName+"_pipe").texture(pipe_string,pipeTexture);
        pipeModel.texture("particle",nodeTexture);

        box(nodeModel,5,5,5,11,11,11,node_string);
        box(pipeModel,6,6,0,10,10,5,pipe_string);

        builder.part().modelFile(nodeModel).addModel();
        //6 pipes out
        builder.part().modelFile(pipeModel).rotationX(90).addModel().condition(BlockStateProperties.DOWN,true);
        builder.part().modelFile(pipeModel).rotationX(270).addModel().condition(BlockStateProperties.UP,true);
        builder.part().modelFile(pipeModel).rotationY(0).addModel().condition(BlockStateProperties.NORTH,true);
        builder.part().modelFile(pipeModel).rotationY(90).addModel().condition(BlockStateProperties.EAST,true);
        builder.part().modelFile(pipeModel).rotationY(180).addModel().condition(BlockStateProperties.SOUTH,true);
        builder.part().modelFile(pipeModel).rotationY(270).addModel().condition(BlockStateProperties.WEST,true);

        //itemBuilder.part().modelFile(nodeModel).addModel().end()
        //        .part().modelFile(pipeModel).addModel().end()
        //        .part().modelFile(pipeModel).rotationX(180).addModel();
        BlockModelBuilder item = models().withExistingParent(baseName + "_inventory",modLoc(baseName));
        box(item,5,5,5,11,11,11,node_string);
        box(item,0,6,6,5,10,10,pipe_string);
        box(item,6,6,0,10,10,5,pipe_string);
        box(item,11,6,6,16,10,10,pipe_string);
        box(item,6,6,11,10,10,16,pipe_string);
        item.texture(pipe_string,pipeTexture).texture(node_string,nodeTexture);
    }

    //
    public void box(BlockModelBuilder builder, int x, int y, int z, int xx, int yy, int zz,String string)
    {
        builder.element().from(x,y,z).to(xx,yy,zz).textureAll("#" + string);
    }
}
