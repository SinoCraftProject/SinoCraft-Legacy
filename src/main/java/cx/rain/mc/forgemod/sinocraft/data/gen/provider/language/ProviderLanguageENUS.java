package cx.rain.mc.forgemod.sinocraft.data.gen.provider.language;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.BlockItems;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.entity.Entities;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ProviderLanguageENUS extends LanguageProvider {
    public ProviderLanguageENUS(DataGenerator gen) {
        super(gen, SinoCraft.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItemGroups();
        addItems();
        addBlocks();
        addEntities();
        addAdvancements();
    }

    private void addAdvancements(){
        add("advancement.sinocraft.basic.root.title","Welcome to SinoCraft!");
        add("advancement.sinocraft.basic.root.description","Welcome to SinoCraft!");

        add("advancement.sinocraft.basic.knife.title","Knife");
        add("advancement.sinocraft.basic.knife.description","Get a knife.");

        add("advancement.sinocraft.basic.knife_killed.title","Knife can kill too");
        add("advancement.sinocraft.basic.knife_killed.description","Kill a entity with a knife.");

        add("advancement.sinocraft.basic.get_bark.title","Strip the tree!");
        add("advancement.sinocraft.basic.get_bark.description","Shave a tree with knife and get the bark.");

        add("advancement.minecraft.adventure.kill_all_entities.title","Slaughter!");
        add("advancement.minecraft.adventure.kill_all_entities.description","Kill one of every living entities.");

        add("advancement.sinocraft.basic.kill_all_mobs_with_knife.title","Mob Master");
        add("advancement.sinocraft.basic.kill_all_mobs_with_knife.description","Kill one of every mobs with knife.");

        add("advancement.sinocraft.basic.kill_all_entities_with_knife.title","Real slaughter!");
        add("advancement.sinocraft.basic.kill_all_entities_with_knife.description","Kill one of every living entities with knife.");
    }

    private void addItemGroups() {
        add("itemGroup.sinocraft.blocks", "SinoCraft | Blocks");
        add("itemGroup.sinocraft.decorate", "SinoCraft | Decorate");
        add("itemGroup.sinocraft.agriculture", "SinoCraft | Agriculture");
        add("itemGroup.sinocraft.foods", "SinoCraft | Foods");
        add("itemGroup.sinocraft.combat", "SinoCraft | Combat");
        add("itemGroup.sinocraft.misc", "SinoCraft | Miscellaneous");
    }

    private void addItems() {
        addItem(Items.PEACH, "Peach");
        addItem(Items.CHILI_PEPPER_SEED, "Chilipepper Seeds");
        addItem(Items.GREEN_PEPPER_SEED, "Green Pepper Seeds");
        addItem(Items.CABBAGE_SEED, "Chinese Cabbage Seeds");
        addItem(Items.EGGPLANT_SEED, "Eggplant Seeds");
        addItem(Items.RICE_SEED, "Rice Seeds");
        addItem(Items.MILLET_SEED, "Millet Seeds");
        addItem(Items.SORGHUM_SEED, "Sorghum Seeds");
        addItem(Items.SOYBEAN, "Soybean");
        addItem(Items.RICE, "Rice");
        addItem(Items.MILLET, "Millet");
        addItem(Items.SORGHUM, "Sorghum");

        addItem(BlockItems.GREEN_RADISH, "Green Radish");
        addItem(BlockItems.SUMMER_RADISH, "Summer Radish");
        addItem(BlockItems.WHITE_RADISH, "White Radish");
        addItem(Items.CHILI_PEPPER, "Chilipepper");
        addItem(Items.GREEN_PEPPER, "Green Pepper");
        addItem(Items.EGGPLANT, "Eggplant");
        addItem(Items.CABBAGE, "Chinese Cabbage");
        addItem(Items.BARK,"Bark");
        addItem(Items.KNIFE_IRON,"Knife");
    }

    private void addBlocks() {
        addBlock(Blocks.LOG_PEACH, "Peach Log");
        addBlock(Blocks.LOG_PEACH_STRIPPED, "Stripped Peach Log");
        addBlock(Blocks.LOG_PEACH_BARK, "Peach Wood");
        addBlock(Blocks.LOG_PEACH_STRIPPED_BARK, "Stripped Peach Wood");
        addBlock(Blocks.PLANK_PEACH, "Peach Plank");
        addBlock(Blocks.LEAVES_PEACH, "Peach Leaves");
        addBlock(Blocks.SAPLING_PEACH, "Peach Sapling");

        addBlock(Blocks.LOG_WALNUT, "Walnut Log");
        addBlock(Blocks.LOG_WALNUT_STRIPPED, "Stripped Walnut Log");
        addBlock(Blocks.LOG_WALNUT_BARK, "Walnut Wood");
        addBlock(Blocks.LOG_WALNUT_STRIPPED_BARK, "Stripped Walnut Wood");
        addBlock(Blocks.PLANK_WALNUT, "Walnut Plank");
        addBlock(Blocks.LEAVES_WALNUT, "Walnut Leaves");
        addBlock(Blocks.SAPLING_WALNUT, "Walnut Sapling");

        addBlock(Blocks.LOG_PLUM, "Plum Log");
        addBlock(Blocks.LOG_PLUM_STRIPPED, "Stripped Plum Log");
        addBlock(Blocks.LOG_PLUM_BARK, "Plum Wood");
        addBlock(Blocks.LOG_PLUM_STRIPPED_BARK, "Stripped Plum Wood");
        addBlock(Blocks.PLANK_PLUM, "Plum Plank");
        addBlock(Blocks.LEAVES_PLUM, "Plum Leaves");
        addBlock(Blocks.SAPLING_PLUM, "Plum Sapling");

        addBlock(Blocks.LOG_MULBERRY, "Mulberry Log");
        addBlock(Blocks.LOG_MULBERRY_STRIPPED, "Stripped Mulberry Log");
        addBlock(Blocks.LOG_MULBERRY_BARK, "Mulberry Wood");
        addBlock(Blocks.LOG_MULBERRY_STRIPPED_BARK, "Stripped Mulberry Wood");
        addBlock(Blocks.PLANK_MULBERRY, "Mulberry Plank");
        addBlock(Blocks.LEAVES_MULBERRY, "Mulberry Leaves");
        addBlock(Blocks.SAPLING_MULBERRY, "Mulberry Sapling");

        addBlock(Blocks.WHITE_MARBLE, "White Marble");
        addBlock(Blocks.RED_MARBLE, "Red Marble");
        addBlock(Blocks.BLACK_MARBLE, "Black Marble");
    }

    private void addEntities() {
        addEntityType(() -> Entities.ENTITY_BUFFALO, "Buffalo");
    }
}
