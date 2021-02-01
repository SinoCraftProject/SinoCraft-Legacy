package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

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

    protected void simpleBlockItem(Item blockItem) {
        getBuilder(blockItem.getRegistryName().getPath()).parent(getModel("block/" + blockItem.getRegistryName().getPath()));
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(ModBlockItems.PEACH_LOG.get());
        simpleBlockItem(ModBlockItems.PEACH_LOG_STRIPPED.get());
        simpleBlockItem(ModBlockItems.PEACH_LOG_BARK.get());
        simpleBlockItem(ModBlockItems.PEACH_LOG_STRIPPED_BARK.get());
        simpleBlockItem(ModBlockItems.PEACH_PLANK.get());
        simpleBlockItem(ModBlockItems.PEACH_LEAVES.get());
        simpleItem(ModBlockItems.PEACH_SAPLING.get(), modLoc("block/peach_sapling"));

        simpleBlockItem(ModBlockItems.WALNUT_LOG.get());
        simpleBlockItem(ModBlockItems.WALNUT_LOG_STRIPPED.get());
        simpleBlockItem(ModBlockItems.WALNUT_LOG_BARK.get());
        simpleBlockItem(ModBlockItems.WALNUT_LOG_STRIPPED_BARK.get());
        simpleBlockItem(ModBlockItems.WALNUT_PLANK.get());
        simpleBlockItem(ModBlockItems.WALNUT_LEAVES.get());
        simpleItem(ModBlockItems.WALNUT_SAPLING.get(), modLoc("block/walnut_sapling"));

        simpleBlockItem(ModBlockItems.PLUM_LOG.get());
        simpleBlockItem(ModBlockItems.PLUM_LOG_STRIPPED.get());
        simpleBlockItem(ModBlockItems.PLUM_LOG_BARK.get());
        simpleBlockItem(ModBlockItems.PLUM_LOG_STRIPPED_BARK.get());
        simpleBlockItem(ModBlockItems.PLUM_PLANK.get());
        simpleBlockItem(ModBlockItems.PLUM_LEAVES.get());
        simpleItem(ModBlockItems.PLUM_SAPLING.get(), modLoc("block/plum_sapling"));

        simpleBlockItem(ModBlockItems.MULBERRY_LOG.get());
        simpleBlockItem(ModBlockItems.MULBERRY_LOG_STRIPPED.get());
        simpleBlockItem(ModBlockItems.MULBERRY_LOG_BARK.get());
        simpleBlockItem(ModBlockItems.MULBERRY_LOG_STRIPPED_BARK.get());
        simpleBlockItem(ModBlockItems.MULBERRY_PLANK.get());
        simpleBlockItem(ModBlockItems.MULBERRY_LEAVES.get());
        simpleItem(ModBlockItems.MULBERRY_SAPLING.get(), modLoc("block/mulberry_sapling"));

        simpleBlockItem(ModBlockItems.WHITE_MARBLE.get());
        simpleBlockItem(ModBlockItems.RED_MARBLE.get());
        simpleBlockItem(ModBlockItems.BLACK_MARBLE.get());

        getBuilder("peach").parent(GENERATED).texture("layer0", modLoc("item/peach"));
        getBuilder("chili_pepper_seed").parent(GENERATED).texture("layer0", modLoc("item/chili_pepper_seed"));
        getBuilder("green_pepper_seed").parent(GENERATED).texture("layer0", modLoc("item/green_pepper_seed"));
        getBuilder("eggplant_seed").parent(GENERATED).texture("layer0", modLoc("item/eggplant_seed"));
        getBuilder("cabbage_seed").parent(GENERATED).texture("layer0", modLoc("item/cabbage_seed"));
        getBuilder("rice_seed").parent(GENERATED).texture("layer0", modLoc("item/rice_seed"));
        getBuilder("millet_seed").parent(GENERATED).texture("layer0", modLoc("item/millet_seed"));
        getBuilder("sorghum_seed").parent(GENERATED).texture("layer0", modLoc("item/sorghum_seed"));
        getBuilder("soybean").parent(GENERATED).texture("layer0", modLoc("item/soybean"));
        getBuilder("rice").parent(GENERATED).texture("layer0", modLoc("item/rice"));
        getBuilder("millet").parent(GENERATED).texture("layer0", modLoc("item/millet"));
        getBuilder("sorghum").parent(GENERATED).texture("layer0", modLoc("item/sorghum"));

        getBuilder("chili_pepper").parent(GENERATED).texture("layer0", modLoc("item/chili_pepper"));
        getBuilder("green_pepper").parent(GENERATED).texture("layer0", modLoc("item/green_pepper"));
        getBuilder("eggplant").parent(GENERATED).texture("layer0", modLoc("item/eggplant"));
        getBuilder("cabbage").parent(GENERATED).texture("layer0", modLoc("item/cabbage"));
        getBuilder("white_radish").parent(GENERATED).texture("layer0", modLoc("item/white_radish"));
        getBuilder("summer_radish").parent(GENERATED).texture("layer0", modLoc("item/summer_radish"));
        getBuilder("green_radish").parent(GENERATED).texture("layer0", modLoc("item/green_radish"));

        getBuilder("flour").parent(GENERATED).texture("layer0", modLoc("item/flour"));
        getBuilder("dough").parent(GENERATED).texture("layer0", modLoc("item/dough"));
        getBuilder("dumpling_wrapper").parent(GENERATED).texture("layer0", modLoc("item/dumpling_wrapper"));
        getBuilder("dumpling").parent(GENERATED).texture("layer0", modLoc("item/dumpling"));
        getBuilder("cooked_dumpling").parent(GENERATED).texture("layer0", modLoc("item/cooked_dumpling"));
        getBuilder("stuffing").parent(GENERATED).texture("layer0", modLoc("item/stuffing"));

        getBuilder("bark").parent(GENERATED).texture("layer0",modLoc("item/bark"));
        getBuilder("china_ink").parent(GENERATED).texture("layer0",modLoc("item/china_ink"));
        getBuilder("bucket_wood_pulp").parent(GENERATED).texture("layer0",modLoc("item/bucket_wood_pulp"));
        getBuilder("xuan_paper").parent(GENERATED).texture("layer0",modLoc("item/xuan_paper"));
        getBuilder("charcoal_black").parent(GENERATED).texture("layer0", modLoc("item/charcoal_black"));
        getBuilder("ink_stone").parent(GENERATED).texture("layer0", modLoc("item/ink_stone"));

        getBuilder("knife_iron").parent(GENERATED).texture("layer0",modLoc("item/knife_iron"));
        getBuilder("knife_gold").parent(GENERATED).texture("layer0",modLoc("item/knife_gold"));
        getBuilder("knife_diamond").parent(GENERATED).texture("layer0",modLoc("item/knife_diamond"));
        getBuilder("chinese_brush").parent(GENERATED).texture("layer0",modLoc("item/chinese_brush"));

        getBuilder("adust_food").parent(GENERATED).texture("layer0",modLoc("item/adust_food"));

        getBuilder("spawn_egg_buffalo").parent(TEMPLATE_SPAWN_EGG);
    }
}
