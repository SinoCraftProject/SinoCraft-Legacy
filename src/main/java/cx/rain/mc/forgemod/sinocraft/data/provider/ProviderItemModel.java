package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.DynamicBucketModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiFunction;

public class ProviderItemModel extends ItemModelProvider {
    public static final ModelFile.UncheckedModelFile GENERATED =
            new ModelFile.UncheckedModelFile("item/generated");

    public static final ModelFile.UncheckedModelFile TEMPLATE_SPAWN_EGG = new ModelFile.UncheckedModelFile("item/template_spawn_egg");

    public ProviderItemModel(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SinoCraft.MODID, exFileHelper);
    }

    private ModelFile getModel(String loc) {
        return new ModelFile.UncheckedModelFile(new ResourceLocation(modid, loc));
    }

    protected void simpleItem(Item item, ResourceLocation texture) {
        singleTexture(item.getRegistryName().getPath(), mcLoc("generated"), "layer0", texture);
    }

    protected void simpleItem(Item item) {
        simpleItem(item, modLoc("item/" + item.getRegistryName().getPath()));
    }

    protected void simpleBlockItem(BlockItem blockItem) {
        getBuilder(blockItem.getRegistryName().getPath())
                .parent(getModel("block/" + blockItem.getRegistryName().getPath()));
    }

    protected void namedBlockItem(BlockItem blockItem, String name) {
        getBuilder(blockItem.getRegistryName().getPath())
                .parent(getModel("block/" + name));
    }

    protected void axisBlockItem(BlockItem blockItem) {
        getBuilder(blockItem.getRegistryName().getPath())
                .parent(getModel("block/" + blockItem.getRegistryName().getPath() + "_horizontal"));
    }

    protected void logBlockItem(BlockItem blockItem) {
        simpleBlockItem(blockItem);
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(ModBlockItems.WHITE_MARBLE.get());
        simpleBlockItem(ModBlockItems.RED_MARBLE.get());
        simpleBlockItem(ModBlockItems.BLACK_MARBLE.get());

        getBuilder("flour").parent(GENERATED).texture("layer0", modLoc("item/flour"));
        getBuilder("dough").parent(GENERATED).texture("layer0", modLoc("item/dough"));
        getBuilder("dumpling_wrapper").parent(GENERATED).texture("layer0", modLoc("item/dumpling_wrapper"));
        getBuilder("dumpling").parent(GENERATED).texture("layer0", modLoc("item/dumpling"));
        getBuilder("cooked_dumpling").parent(GENERATED).texture("layer0", modLoc("item/cooked_dumpling"));
        getBuilder("stuffing").parent(GENERATED).texture("layer0", modLoc("item/stuffing"));
        getBuilder("bucket_wood_pulp")
                .parent(new ModelFile.UncheckedModelFile("forge:item/bucket_drip"))
                .customLoader((BiFunction<ItemModelBuilder, ExistingFileHelper, CustomLoaderBuilder<ItemModelBuilder>>) (itemModelBuilder, existingFileHelper) ->
                        DynamicBucketModelBuilder.begin(itemModelBuilder, existingFileHelper).fluid(ModFluids.WOOD_PULP.get()))
                .end();

        getBuilder("spawn_egg_buffalo").parent(TEMPLATE_SPAWN_EGG);

        addTrees();
        addCrops();
        addTools();
        addMisc();
        addMachineBlockItems();
    }

    private void addTrees() {
        logBlockItem(ModBlockItems.PEACH_LOG.get());
        logBlockItem(ModBlockItems.PEACH_LOG_STRIPPED.get());
        simpleBlockItem(ModBlockItems.PEACH_LOG_BARK.get());
        simpleBlockItem(ModBlockItems.PEACH_LOG_STRIPPED_BARK.get());
        simpleBlockItem(ModBlockItems.PEACH_PLANK.get());
        simpleBlockItem(ModBlockItems.PEACH_LEAVES.get());
        simpleItem(ModBlockItems.PEACH_SAPLING.get(), modLoc("block/peach_sapling"));

        logBlockItem(ModBlockItems.WALNUT_LOG.get());
        logBlockItem(ModBlockItems.WALNUT_LOG_STRIPPED.get());
        simpleBlockItem(ModBlockItems.WALNUT_LOG_BARK.get());
        simpleBlockItem(ModBlockItems.WALNUT_LOG_STRIPPED_BARK.get());
        simpleBlockItem(ModBlockItems.WALNUT_PLANK.get());
        simpleBlockItem(ModBlockItems.WALNUT_LEAVES.get());
        simpleItem(ModBlockItems.WALNUT_SAPLING.get(), modLoc("block/walnut_sapling"));

        logBlockItem(ModBlockItems.PLUM_LOG.get());
        logBlockItem(ModBlockItems.PLUM_LOG_STRIPPED.get());
        simpleBlockItem(ModBlockItems.PLUM_LOG_BARK.get());
        simpleBlockItem(ModBlockItems.PLUM_LOG_STRIPPED_BARK.get());
        simpleBlockItem(ModBlockItems.PLUM_PLANK.get());
        simpleBlockItem(ModBlockItems.PLUM_LEAVES.get());
        simpleItem(ModBlockItems.PLUM_SAPLING.get(), modLoc("block/plum_sapling"));

        logBlockItem(ModBlockItems.MULBERRY_LOG.get());
        logBlockItem(ModBlockItems.MULBERRY_LOG_STRIPPED.get());
        simpleBlockItem(ModBlockItems.MULBERRY_LOG_BARK.get());
        simpleBlockItem(ModBlockItems.MULBERRY_LOG_STRIPPED_BARK.get());
        simpleBlockItem(ModBlockItems.MULBERRY_PLANK.get());
        simpleBlockItem(ModBlockItems.MULBERRY_LEAVES.get());
        simpleItem(ModBlockItems.MULBERRY_SAPLING.get(), modLoc("block/mulberry_sapling"));
    }

    private void addCrops() {
        simpleItem(ModItems.CHILI_PEPPER_SEED.get());
        simpleItem(ModItems.GREEN_PEPPER_SEED.get());
        simpleItem(ModItems.EGGPLANT_SEED.get());
        simpleItem(ModItems.ADUST_FOOD.get());
        simpleItem(ModItems.CABBAGE_SEED.get());
        simpleItem(ModItems.RICE_SEED.get());
        simpleItem(ModItems.MILLET_SEED.get());
        simpleItem(ModItems.SORGHUM_SEED.get());
        simpleItem(ModItems.SOYBEAN.get());
        simpleItem(ModItems.RICE.get());
        simpleItem(ModItems.MILLET.get());
        simpleItem(ModItems.SORGHUM.get());
        simpleItem(ModItems.CHILI_PEPPER.get());
        simpleItem(ModItems.GREEN_PEPPER.get());
        simpleItem(ModItems.EGGPLANT.get());
        simpleItem(ModItems.CABBAGE.get());
        simpleItem(ModBlockItems.WHITE_RADISH.get());
        simpleItem(ModBlockItems.SUMMER_RADISH.get());
        simpleItem(ModBlockItems.GREEN_RADISH.get());
        simpleItem(ModItems.PEACH.get());
        simpleItem(ModItems.SILKWORM.get());
        simpleItem(ModItems.TEA_LEAF.get());
    }

    private void addTools() {
        simpleItem(ModItems.KNIFE_IRON.get());
        simpleItem(ModItems.KNIFE_GOLD.get());
        simpleItem(ModItems.KNIFE_DIAMOND.get());
    }

    private void addMisc() {
        simpleItem(ModItems.BARK.get(), modLoc("item/bark"));
        simpleItem(ModItems.CHARCOAL_BLACK.get(), modLoc("item/charcoal_black"));
        simpleItem(ModItems.CHINESE_BRUSH.get(), modLoc("item/chinese_brush"));
        simpleItem(ModItems.INK_STONE.get(), modLoc("item/ink_stone"));
        simpleItem(ModItems.CHINESE_INK.get(), modLoc("item/chinese_ink"));
        simpleItem(ModItems.EMPTY_XUAN_PAPER.get(), modLoc("item/empty_xuan_paper"));
        // Todo: Replace empty_xuan_paper to xuan_paper.
        simpleItem(ModItems.XUAN_PAPER.get(), modLoc("item/empty_xuan_paper"));

        simpleItem(ModItems.DISH.get());
        simpleItem(ModItems.HEROES_ASSEMBLE.get());

        simpleItem(ModItems.TEACUP.get());
    }

    private void addMachineBlockItems() {
        simpleBlockItem(ModBlockItems.VAT.get());
        namedBlockItem(ModBlockItems.STOVE.get(), "stove_off");
        simpleBlockItem(ModBlockItems.POT.get());
        namedBlockItem(ModBlockItems.BELLOWS.get(), "bellows0");
    }
}
