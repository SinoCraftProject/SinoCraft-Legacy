package cx.rain.mc.forgemod.culturecraft.data.gen.provider.language;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.block.BlockItems;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.entity.Entities;
import cx.rain.mc.forgemod.culturecraft.item.Items;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ProviderLanguageENUS extends LanguageProvider {
    public ProviderLanguageENUS(DataGenerator gen) {
        super(gen, CultureCraft.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItemGroups();
        addItems();
        addBlocks();
        addEntities();
    }

    private void addItemGroups() {
        add("itemGroup.culturecraft.blocks", "Culture Craft | Blocks");
        add("itemGroup.culturecraft.decorate", "Culture Craft | Decorate");
        add("itemGroup.culturecraft.agriculture", "Culture Craft | Agriculture");
        add("itemGroup.culturecraft.foods", "Culture Craft | Foods");
        add("itemGroup.culturecraft.combat", "Culture Craft | Combat");
        add("itemGroup.culturecraft.misc", "Culture Craft | Miscellaneous");
    }

    private void addItems() {
        addItem(Items.PEACH, "Peach");
        addItem(Items.CHILI_PEPPER_SEED, "Chilipepper Seeds");
        addItem(Items.GREEN_PEPPER_SEED, "Green Pepper Seeds");
        addItem(Items.CABBAGE_SEED, "Chinese Cabbage Seeds");
        addItem(Items.EGGPLANT_SEED, "Eggplant Seeds");
        addItem(Items.RICE_SEED, "Rice Seeds");
        addItem(Items.MILLET_SEED, "Millet Seeds");
        addItem(Items.SOYBEAN_SEED, "Soybean Seeds");

        addItem(BlockItems.GREEN_RADISH, "Green Radish");
        addItem(BlockItems.SUMMER_RADISH, "Summer Radish");
        addItem(BlockItems.WHITE_RADISH, "White Radish");
    }

    private void addBlocks() {
        addBlock(Blocks.LOG_PEACH, "Peach Log");
        addBlock(Blocks.LOG_PEACH_STRIPPED, "Stripped Peach Log");
        addBlock(Blocks.LOG_PEACH_SKIN, "Peach Wood");
        addBlock(Blocks.LOG_PEACH_STRIPPED_SKIN, "Stripped Peach Wood");
        addBlock(Blocks.PLANK_PEACH, "Peach Plank");
        addBlock(Blocks.LEAVES_PEACH, "Peach Leaves");
        addBlock(Blocks.SAPLING_PEACH, "Peach Sapling");

        addBlock(Blocks.LOG_WALNUT, "Walnut Log");
        addBlock(Blocks.LOG_WALNUT_STRIPPED, "Stripped Walnut Log");
        addBlock(Blocks.LOG_WALNUT_SKIN, "Walnut Wood");
        addBlock(Blocks.LOG_WALNUT_STRIPPED_SKIN, "Stripped Walnut Wood");
        addBlock(Blocks.PLANK_WALNUT, "Walnut Plank");
        addBlock(Blocks.LEAVES_WALNUT, "Walnut Leaves");
        addBlock(Blocks.SAPLING_WALNUT, "Walnut Sapling");

        addBlock(Blocks.LOG_PLUM, "Plum Log");
        addBlock(Blocks.LOG_PLUM_STRIPPED, "Stripped Plum Log");
        addBlock(Blocks.LOG_PLUM_SKIN, "Plum Wood");
        addBlock(Blocks.LOG_PLUM_STRIPPED_SKIN, "Stripped Plum Wood");
        addBlock(Blocks.PLANK_PLUM, "Plum Plank");
        addBlock(Blocks.LEAVES_PLUM, "Plum Leaves");
        //addBlock(Blocks.SAPLING_PLUM, "Plum Sapling");

        addBlock(Blocks.LOG_MULBERRY, "Mulberry Log");
        addBlock(Blocks.LOG_MULBERRY_STRIPPED, "Stripped Mulberry Log");
        addBlock(Blocks.LOG_MULBERRY_SKIN, "Mulberry Wood");
        addBlock(Blocks.LOG_MULBERRY_STRIPPED_SKIN, "Stripped Mulberry Wood");
        addBlock(Blocks.PLANK_MULBERRY, "Mulberry Plank");
        addBlock(Blocks.LEAVES_MULBERRY, "Mulberry Leaves");
        addBlock(Blocks.SAPLING_MULBERRY, "Mulberry Sapling");
    }

    private void addEntities() {
        addEntityType(() -> Entities.ENTITY_BUFFALO, "Buffalo");
    }
}
