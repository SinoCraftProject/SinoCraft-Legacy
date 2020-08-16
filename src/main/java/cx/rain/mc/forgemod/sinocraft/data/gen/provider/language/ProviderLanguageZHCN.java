package cx.rain.mc.forgemod.sinocraft.data.gen.provider.language;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.BlockItems;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.entity.Entities;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ProviderLanguageZHCN extends LanguageProvider {
    public ProviderLanguageZHCN(DataGenerator gen) {
        super(gen, SinoCraft.MODID, "zh_cn");
    }
/*
§0 黑色 black
§1 深蓝色 dark_blue
§2 深绿色 dark_green
§3 湖蓝色 dark_aqua
§4 深红色 dark_red
§5 紫色 dark_purple
§6 金色 gold
§7 灰色 gray
§8 深灰色 dark_gray
§9 靛蓝色 blue
§a 鲜绿色 green
§b 水蓝色 aqua
§c 红色 red
§d 粉色 light_purple
§e 黄色 yellow
§f 白色 white
§r 默认字体
§l 加粗
§o 倾斜
§n 下划线
§m 删除线
*/
    @Override
    protected void addTranslations() {
        addItemGroups();
        addItems();
        addBlocks();
        addEntities();
        addAdvancements();
    }

    private void addAdvancements(){
        add("advancement.sinocraft.basic.root.title","感谢使用华夏文明模组！");
        add("advancement.sinocraft.basic.root.description","感谢使用华夏文明模组！");

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
        addItem(Items.PEACH, "桃子");
        addItem(Items.CHILI_PEPPER_SEED, "辣椒种子");
        addItem(Items.GREEN_PEPPER_SEED, "青椒种子");
        addItem(Items.CABBAGE_SEED, "白菜种子");
        addItem(Items.EGGPLANT_SEED, "茄子种子");
        addItem(Items.RICE_SEED, "稻谷");
        addItem(Items.MILLET_SEED, "谷子");
        addItem(Items.SORGHUM_SEED, "高粱");
        addItem(Items.SOYBEAN, "大豆");
        addItem(Items.RICE, "大米");
        addItem(Items.MILLET, "小米");
        addItem(Items.SORGHUM, "高粱米");

        addItem(Items.FLOUR, "面粉");
        addItem(Items.DOUGH, "面团");
        addItem(Items.DUMPLING, "饺子");
        addItem(Items.DUMPLING_WRAPPER, "饺子皮");
        addItem(Items.COOKED_DUMPLING, "熟饺子");
        addItem(Items.STUFFING, "馅");

        addItem(BlockItems.GREEN_RADISH, "青萝卜");
        addItem(BlockItems.SUMMER_RADISH, "水萝卜");
        addItem(BlockItems.WHITE_RADISH, "白萝卜");
        addItem(Items.CHILI_PEPPER, "辣椒");
        addItem(Items.GREEN_PEPPER, "青椒");
        addItem(Items.EGGPLANT, "茄子");
        addItem(Items.CABBAGE, "白菜");
        addItem(Items.BARK,"树皮");
        addItem(Items.KNIFE_IRON,"铁小刀");
        addItem(Items.KNIFE_GOLD,"金小刀");
        addItem(Items.KNIFE_DIAMOND,"钻石小刀");
        addItem(Items.CHINESE_BRUSH,"毛笔");
        addItem(Items.CHINA_INK,"墨");
        addItem(Items.CHARCOAL_BLACK,"炭黑");
        addItem(Items.INK_STONE, "砚");
        addItem(Items.XUAN_PAPER, "宣纸");
        addItem(Items.BUCKET_WOOD_PULP, "木浆桶");
    }

    private void addBlocks() {
        addBlock(Blocks.LOG_PEACH, "桃树原木");
        addBlock(Blocks.LOG_PEACH_STRIPPED, "去皮桃树原木");
        addBlock(Blocks.LOG_PEACH_BARK, "桃树树皮块");
        addBlock(Blocks.LOG_PEACH_STRIPPED_BARK, "去皮桃树树皮块");
        addBlock(Blocks.PLANK_PEACH, "桃树木板");
        addBlock(Blocks.LEAVES_PEACH, "桃树树叶");
        addBlock(Blocks.SAPLING_PEACH, "桃树树苗");

        addBlock(Blocks.LOG_WALNUT, "核桃树原木");
        addBlock(Blocks.LOG_WALNUT_STRIPPED, "去皮核桃树原木");
        addBlock(Blocks.LOG_WALNUT_BARK, "核桃树树皮块");
        addBlock(Blocks.LOG_WALNUT_STRIPPED_BARK, "去皮核桃树树皮块");
        addBlock(Blocks.PLANK_WALNUT, "核桃树木板");
        addBlock(Blocks.LEAVES_WALNUT, "核桃树树叶");
        addBlock(Blocks.SAPLING_WALNUT, "核桃树树苗");

        addBlock(Blocks.LOG_PLUM, "梅花原木");
        addBlock(Blocks.LOG_PLUM_STRIPPED, "去皮梅花原木");
        addBlock(Blocks.LOG_PLUM_BARK, "梅花树皮块");
        addBlock(Blocks.LOG_PLUM_STRIPPED_BARK, "去皮梅花树皮块");
        addBlock(Blocks.PLANK_PLUM, "梅花木板");
        addBlock(Blocks.LEAVES_PLUM, "梅花树叶");
        addBlock(Blocks.SAPLING_PLUM, "梅花树苗");

        addBlock(Blocks.LOG_MULBERRY, "桑树原木");
        addBlock(Blocks.LOG_MULBERRY_STRIPPED, "去皮桑树原木");
        addBlock(Blocks.LOG_MULBERRY_BARK, "桑树树皮块");
        addBlock(Blocks.LOG_MULBERRY_STRIPPED_BARK, "去皮桑树树皮块");
        addBlock(Blocks.PLANK_MULBERRY, "桑树木板");
        addBlock(Blocks.LEAVES_MULBERRY, "桑树树叶");
        addBlock(Blocks.SAPLING_MULBERRY, "桑树树苗");

        addBlock(Blocks.WHITE_MARBLE, "汉白玉大理石");
        addBlock(Blocks.RED_MARBLE, "南江红大理石");
        addBlock(Blocks.BLACK_MARBLE, "晶墨玉大理石");

        addBlock(Blocks.STOVE, "炉灶");
        addBlock(Blocks.VAT, "桶");
        addBlock(Blocks.PAPER_DRYING_RACK, "晾纸架");
        addBlock(Blocks.STONE_MILL, "石磨");
    }

    private void addEntities() {
        addEntityType(() -> Entities.ENTITY_BUFFALO, "水牛");
    }
}
