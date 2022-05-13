package com.natesky9.patina.datagen;

import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Patina.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        folderItem(ModItems.BEE_FRAGMENT_1.get(),"fragment");
        folderItem(ModItems.BEE_FRAGMENT_2.get(),"fragment");
        folderItem(ModItems.BEE_FRAGMENT_3.get(),"fragment");
        folderItem(ModItems.BEE_FRAGMENT_4.get(),"fragment");
        folderItem(ModItems.BEE_FRAGMENT_A.get(),"fragment");
        folderItem(ModItems.BEE_FRAGMENT_B.get(),"fragment");
        handheldItem(ModItems.BEE_WEAPON.get());

        folderItem(ModItems.PIG_FRAGMENT_1.get(),"fragment");
        folderItem(ModItems.PIG_FRAGMENT_2.get(),"fragment");
        folderItem(ModItems.PIG_FRAGMENT_3.get(),"fragment");
        folderItem(ModItems.PIG_FRAGMENT_4.get(),"fragment");
        folderItem(ModItems.PIG_FRAGMENT_A.get(),"fragment");
        folderItem(ModItems.PIG_FRAGMENT_B.get(),"fragment");
        handheldItem(ModItems.PIG_WEAPON.get());

        handheldItem(ModItems.PIGLIN_BALLISTA.get());
        handheldItem(ModItems.PIGLIN_BALLISTA.get());

        simpleItem(ModItems.CANDY_WARTS.get());
        simpleItem(ModItems.BLINK_BROWNIE.get());
        simpleItem(ModItems.COAL_COKE.get());
        //simpleItem(ModItems.CALCITE_DUST.get());
        //simpleItem(ModItems.CRUSHED_SHELLS.get());
        //simpleItem(ModItems.FERTILIZER.get());
        simpleItem(ModItems.INERT_ROD.get());
        //simpleItem(ModItems.KELP_MEAL.get());
        simpleItem(ModItems.TEST_FOOD.get());
        simpleItem(ModItems.ROYAL_JELLY.get());
        simpleItem(ModItems.SPIDER_NEST.get());

        copperItem(ModItems.COPPER_HELMET.get());
        copperItem(ModItems.COPPER_CHESTPLATE.get());
        copperItem(ModItems.COPPER_LEGGINGS.get());
        copperItem(ModItems.COPPER_BOOTS.get());
        folderItem(ModItems.CRYSTAL_HELMET.get(),"crystal");
        folderItem(ModItems.CRYSTAL_CHESTPLATE.get(),"crystal");
        folderItem(ModItems.CRYSTAL_LEGGINGS.get(),"crystal");
        folderItem(ModItems.CRYSTAL_BOOTS.get(),"crystal");

        simpleItem(ModItems.CUSTOM_NUGGET.get());
        simpleItem(ModItems.CUSTOM_INGOT.get());
        simpleItem(ModItems.CUSTOM_AXE.get());
        simpleItem(ModItems.CUSTOM_SWORD.get());
        simpleItem(ModItems.CUSTOM_SHOVEL.get());
        simpleItem(ModItems.CUSTOM_HOE.get());
        simpleItem(ModItems.CUSTOM_PICK.get());

        simpleItem(ModItems.VENOM_SWORD.get());

        simpleItem(ModItems.LUXOMETER.get());
        simpleItem(ModItems.TEST.get());

    }

    private ItemModelBuilder simpleItem(Item item)
    {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Patina.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder copperItem(Item item)
    {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Patina.MOD_ID,"item/copper/" + item.getRegistryName().getPath() + "_clean"))
                .override().predicate(ModItems.rustlevel,.25F).model(withExistingParent(item.getRegistryName().getPath(),
                        new ResourceLocation("item/generated")).texture("layer0",
                        new ResourceLocation(Patina.MOD_ID,"item/copper/" + item.getRegistryName().getPath() + "_exposed"))).end()
                .override().predicate(ModItems.rustlevel,.5F).model(withExistingParent(item.getRegistryName().getPath(),
                        new ResourceLocation("item/generated")).texture("layer0",
                        new ResourceLocation(Patina.MOD_ID,"item/copper/" + item.getRegistryName().getPath() + "_weathered"))).end()
                .override().predicate(ModItems.rustlevel,.75F).model(withExistingParent(item.getRegistryName().getPath(),
                        new ResourceLocation("item/generated")).texture("layer0",
                        new ResourceLocation(Patina.MOD_ID,"item/copper/" + item.getRegistryName().getPath() + "_oxidized"))).end();
    }
    private ItemModelBuilder handheldItem(Item item)
    {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Patina.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }
    private ItemModelBuilder folderItem(Item item, String folder)
    {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Patina.MOD_ID,"item/" + folder + "/" + item.getRegistryName().getPath()));

    }

}
