package cx.rain.mc.forgemod.culturecraft.data.gen.provider.language;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.block.BlockItems;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.entity.Entities;
import cx.rain.mc.forgemod.culturecraft.item.Items;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ProviderLanguageZHCN extends LanguageProvider {
    public ProviderLanguageZHCN(DataGenerator gen) {
        super(gen, CultureCraft.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        addItemGroups();
        addItems();
        addBlocks();
        addEntities();
    }

    private void addItemGroups() {
        add("itemGroup.culturecraft.blocks", "华夏文化 | 方块");
        add("itemGroup.culturecraft.decorate", "华夏文化 | 装饰");
        add("itemGroup.culturecraft.agriculture", "华夏文化 | 农业");
        add("itemGroup.culturecraft.foods", "华夏文化 | 食物");
        add("itemGroup.culturecraft.combat", "华夏文化 | 战斗");
        add("itemGroup.culturecraft.misc", "华夏文化 | 杂项");
    }

    private void addItems() {
        addItem(Items.PEACH, "桃子");
        addItem(Items.CHILI_PEPPER_SEED, "辣椒种子");
        addItem(Items.GREEN_PEPPER_SEED, "青椒种子");
        addItem(Items.CABBAGE_SEED, "白菜种子");
        addItem(Items.EGGPLANT_SEED, "茄子种子");
        addItem(Items.RICE_SEED, "大米种子");
        addItem(Items.MILLET_SEED, "小米种子");
        addItem(Items.SOYBEAN_SEED, "大豆种子");
        addItem(Items.SORGHUM_SEED, "高粱种子");

        addItem(BlockItems.GREEN_RADISH, "青萝卜");
        addItem(BlockItems.SUMMER_RADISH, "水萝卜");
        addItem(BlockItems.WHITE_RADISH, "白萝卜");
    }

    private void addBlocks() {
        addBlock(Blocks.LOG_PEACH, "桃树原木");
        addBlock(Blocks.LOG_PEACH_STRIPPED, "去皮桃树原木");
        addBlock(Blocks.LOG_PEACH_SKIN, "桃树树皮块");
        addBlock(Blocks.LOG_PEACH_STRIPPED_SKIN, "去皮桃树树皮块");
        addBlock(Blocks.PLANK_PEACH, "桃树木板");
        addBlock(Blocks.LEAVES_PEACH, "桃树树叶");
        addBlock(Blocks.SAPLING_PEACH, "桃树树苗");

        addBlock(Blocks.LOG_WALNUT, "核桃树原木");
        addBlock(Blocks.LOG_WALNUT_STRIPPED, "去皮核桃树原木");
        addBlock(Blocks.LOG_WALNUT_SKIN, "核桃树树皮块");
        addBlock(Blocks.LOG_WALNUT_STRIPPED_SKIN, "去皮核桃树树皮块");
        addBlock(Blocks.PLANK_WALNUT, "核桃树木板");
        addBlock(Blocks.LEAVES_WALNUT, "核桃树树叶");
        addBlock(Blocks.SAPLING_WALNUT, "核桃树树苗");

        addBlock(Blocks.LOG_PLUM, "梅花原木");
        addBlock(Blocks.LOG_PLUM_STRIPPED, "去皮梅花原木");
        addBlock(Blocks.LOG_PLUM_SKIN, "梅花树皮块");
        addBlock(Blocks.LOG_PLUM_STRIPPED_SKIN, "去皮梅花树皮块");
        addBlock(Blocks.PLANK_PLUM, "梅花木板");
        addBlock(Blocks.LEAVES_PLUM, "梅花树叶");
        //addBlock(Blocks.SAPLING_PLUM, "梅花树苗");

        addBlock(Blocks.LOG_MULBERRY, "桑树原木");
        addBlock(Blocks.LOG_MULBERRY_STRIPPED, "去皮桑树原木");
        addBlock(Blocks.LOG_MULBERRY_SKIN, "桑树树皮块");
        addBlock(Blocks.LOG_MULBERRY_STRIPPED_SKIN, "去皮桑树树皮块");
        addBlock(Blocks.PLANK_MULBERRY, "桑树木板");
        addBlock(Blocks.LEAVES_MULBERRY, "桑树树叶");
        addBlock(Blocks.SAPLING_MULBERRY, "桑树树苗");
    }

    private void addEntities() {
        addEntityType(() -> Entities.ENTITY_BUFFALO, "水牛");
    }
}
