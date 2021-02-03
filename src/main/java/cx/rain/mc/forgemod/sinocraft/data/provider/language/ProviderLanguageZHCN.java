package cx.rain.mc.forgemod.sinocraft.data.provider.language;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.entity.ModEntities;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ProviderLanguageZHCN extends LanguageProvider {
    public ProviderLanguageZHCN(DataGenerator gen) {
        super(gen, SinoCraft.MODID, "zh_cn");
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
        add("advancement.sinocraft.basic.root.title","感谢使用华夏工艺模组！");
        add("advancement.sinocraft.basic.root.description","感谢使用华夏工艺模组！");

        add("advancement.sinocraft.basic.knife.title","刀");
        add("advancement.sinocraft.basic.knife.description","获得一把小刀");

        add("advancement.sinocraft.basic.knife_killed.title","小刀也能毙命");
        add("advancement.sinocraft.basic.knife_killed.description","用小刀杀死一个生物");

        add("advancement.sinocraft.basic.get_bark.title","给树脱衣！");
        add("advancement.sinocraft.basic.get_bark.description","用小刀剥下并捡起一棵树的树皮");

        add("advancement.minecraft.adventure.kill_all_entities.title","大屠杀!");
        add("advancement.minecraft.adventure.kill_all_entities.description","杀死每一种生物.");

        add("advancement.sinocraft.basic.kill_all_mobs_with_knife.title","怪物大师");
        add("advancement.sinocraft.basic.kill_all_mobs_with_knife.description","用小刀杀死每一种怪物.");

        add("advancement.sinocraft.basic.kill_all_entities_with_knife.title","真正的屠杀!");
        add("advancement.sinocraft.basic.kill_all_entities_with_knife.description","用小刀杀死每一种生物.");

        add("advancement.sinocraft.basic.get_china_ink.title","墨香扑鼻!");
        add("advancement.sinocraft.basic.get_china_ink.description","获得墨.");

        add("advancement.sinocraft.basic.get_stone_mill.title","磨坊主");
        add("advancement.sinocraft.basic.get_stone_mill.description","获得石磨.");
    }

    private void addItemGroups() {
        add("itemGroup.sinocraft.blocks", "华夏工艺 | 方块");
        add("itemGroup.sinocraft.decorate", "华夏工艺 | 装饰");
        add("itemGroup.sinocraft.agriculture", "华夏工艺 | 农业");
        add("itemGroup.sinocraft.foods", "华夏工艺 | 食物");
        add("itemGroup.sinocraft.combat", "华夏工艺 | 战斗");
        add("itemGroup.sinocraft.tools", "华夏工艺 | 工具");
        add("itemGroup.sinocraft.misc", "华夏工艺 | 杂项");
    }

    private void addItems() {
        addItem(ModItems.PEACH, "桃子");
        addItem(ModItems.CHILI_PEPPER_SEED, "辣椒种子");
        addItem(ModItems.GREEN_PEPPER_SEED, "青椒种子");
        addItem(ModItems.CABBAGE_SEED, "白菜种子");
        addItem(ModItems.EGGPLANT_SEED, "茄子种子");
        addItem(ModItems.RICE_SEED, "稻谷");
        addItem(ModItems.MILLET_SEED, "谷子");
        addItem(ModItems.SORGHUM_SEED, "高粱");
        addItem(ModItems.SOYBEAN, "大豆");
        addItem(ModItems.RICE, "大米");
        addItem(ModItems.MILLET, "小米");
        addItem(ModItems.SORGHUM, "高粱米");

        addItem(ModItems.FLOUR, "面粉");
        addItem(ModItems.DOUGH, "面团");
        addItem(ModItems.DUMPLING, "饺子");
        addItem(ModItems.DUMPLING_WRAPPER, "饺子皮");
        addItem(ModItems.COOKED_DUMPLING, "熟饺子");
        addItem(ModItems.STUFFING, "馅");

        addItem(ModBlockItems.GREEN_RADISH, "青萝卜");
        addItem(ModBlockItems.SUMMER_RADISH, "水萝卜");
        addItem(ModBlockItems.WHITE_RADISH, "白萝卜");
        addItem(ModItems.CHILI_PEPPER, "辣椒");
        addItem(ModItems.GREEN_PEPPER, "青椒");
        addItem(ModItems.EGGPLANT, "茄子");
        addItem(ModItems.CABBAGE, "白菜");
        addItem(ModItems.BARK,"树皮");
        addItem(ModItems.KNIFE_IRON,"铁小刀");
        addItem(ModItems.KNIFE_GOLD,"金小刀");
        addItem(ModItems.KNIFE_DIAMOND,"钻石小刀");
        addItem(ModItems.CHINESE_BRUSH,"毛笔");
        addItem(ModItems.CHINA_INK,"墨");
        addItem(ModItems.CHARCOAL_BLACK,"炭黑");
        addItem(ModItems.INK_STONE, "砚");
        addItem(ModItems.XUAN_PAPER, "宣纸");
        addItem(ModItems.BUCKET_WOOD_PULP, "木浆桶");
        addItem(ModItems.TUTORIAL_BOOK, "《华夏文明》");
    }

    private void addBlocks() {
        addBlock(ModBlocks.PEACH_LOG, "桃树原木");
        addBlock(ModBlocks.PEACH_LOG_STRIPPED, "去皮桃树原木");
        addBlock(ModBlocks.PEACH_LOG_BARK, "桃树树皮块");
        addBlock(ModBlocks.PEACH_LOG_STRIPPED_BARK, "去皮桃树树皮块");
        addBlock(ModBlocks.PEACH_PLANK, "桃树木板");
        addBlock(ModBlocks.PEACH_LEAVES, "桃树树叶");
        addBlock(ModBlocks.PEACH_SAPLING, "桃树树苗");

        addBlock(ModBlocks.WALNUT_LOG, "核桃树原木");
        addBlock(ModBlocks.WALNUT_LOG_STRIPPED, "去皮核桃树原木");
        addBlock(ModBlocks.WALNUT_LOG_BARK, "核桃树树皮块");
        addBlock(ModBlocks.WALNUT_LOG_STRIPPED_BARK, "去皮核桃树树皮块");
        addBlock(ModBlocks.WALNUT_PLANK, "核桃树木板");
        addBlock(ModBlocks.WALNUT_LEAVES, "核桃树树叶");
        addBlock(ModBlocks.WALNUT_SAPLING, "核桃树树苗");

        addBlock(ModBlocks.PLUM_LOG, "梅花原木");
        addBlock(ModBlocks.PLUM_LOG_STRIPPED, "去皮梅花原木");
        addBlock(ModBlocks.PLUM_LOG_BARK, "梅花树皮块");
        addBlock(ModBlocks.PLUM_LOG_STRIPPED_BARK, "去皮梅花树皮块");
        addBlock(ModBlocks.PLUM_PLANK, "梅花木板");
        addBlock(ModBlocks.PLUM_LEAVES, "梅花树叶");
        addBlock(ModBlocks.PLUM_SAPLING, "梅花树苗");

        addBlock(ModBlocks.MULBERRY_LOG, "桑树原木");
        addBlock(ModBlocks.MULBERRY_LOG_STRIPPED, "去皮桑树原木");
        addBlock(ModBlocks.MULBERRY_LOG_BARK, "桑树树皮块");
        addBlock(ModBlocks.MULBERRY_LOG_STRIPPED_BARK, "去皮桑树树皮块");
        addBlock(ModBlocks.MULBERRY_PLANK, "桑树木板");
        addBlock(ModBlocks.MULBERRY_LEAVES, "桑树树叶");
        addBlock(ModBlocks.MULBERRY_SAPLING, "桑树树苗");

        addBlock(ModBlocks.WHITE_MARBLE, "汉白玉大理石");
        addBlock(ModBlocks.RED_MARBLE, "南江红大理石");
        addBlock(ModBlocks.BLACK_MARBLE, "晶墨玉大理石");

        addBlock(ModBlocks.STOVE, "炉灶");
        addBlock(ModBlocks.VAT, "木缸");
        addBlock(ModBlocks.PAPER_DRYING_RACK, "晾纸架");
        addBlock(ModBlocks.STONE_MILL, "石磨");
    }

    private void addEntities() {
        addEntityType(() -> ModEntities.ENTITY_BUFFALO.get(), "水牛");
    }
}
