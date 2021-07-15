package cx.rain.mc.forgemod.sinocraft.api_impl;

import cx.rain.mc.forgemod.sinocraft.api.item.IModItems;
import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableItem;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;

public enum APIModItems implements IModItems {

    INSTANCE;

    @Override
    public RegistryObject<? extends BlockItem> getChiliPepperSeed() {
        return ModItems.CHILI_PEPPER_SEED;
    }
    @Override
    public RegistryObject<? extends BlockItem> getGreenPepperSeed() {
        return ModItems.GREEN_PEPPER_SEED;
    }
    @Override
    public RegistryObject<? extends BlockItem> getEggplantSeed() {
        return ModItems.EGGPLANT_SEED;
    }
    @Override
    public RegistryObject<? extends BlockItem> getCabbageSeed() {
        return ModItems.CABBAGE_SEED;
    }
    @Override
    public RegistryObject<? extends BlockItem> getRiceSeed() {
        return ModItems.RICE_SEED;
    }
    @Override
    public RegistryObject<? extends BlockItem> getMilletSeed() {
        return ModItems.MILLET_SEED;
    }
    @Override
    public RegistryObject<? extends BlockItem> getSorghumSeed() {
        return ModItems.SORGHUM_SEED;
    }
    @Override
    public RegistryObject<? extends BlockItem> getSoybean() {
        return ModItems.SOYBEAN;
    }
    @Override
    public RegistryObject<? extends Item> getRice() {
        return ModItems.RICE;
    }
    @Override
    public RegistryObject<? extends Item> getMillet() {
        return ModItems.MILLET;
    }
    @Override
    public RegistryObject<? extends Item> getSorghum() {
        return ModItems.SORGHUM;
    }
    @Override
    public RegistryObject<? extends Item> getSilkworm() {
        return ModItems.SILKWORM;
    }
    @Override
    public RegistryObject<? extends Item> getPeach() {
        return ModItems.PEACH;
    }
    @Override
    public RegistryObject<? extends Item> getChiliPepper() {
        return ModItems.CHILI_PEPPER;
    }
    @Override
    public RegistryObject<? extends Item> getGreenPepper() {
        return ModItems.GREEN_PEPPER;
    }
    @Override
    public RegistryObject<? extends Item> getEggplant() {
        return ModItems.EGGPLANT;
    }
    @Override
    public RegistryObject<? extends Item> getCabbage() {
        return ModItems.CABBAGE;
    }
    @Override
    public RegistryObject<? extends Item> getAdustFood() {
        return ModItems.ADUST_FOOD;
    }
    @Override
    public RegistryObject<? extends Item> getHeroesAssemble() {
        return ModItems.HEROES_ASSEMBLE;
    }
    @Override
    public RegistryObject<? extends Item> getFlour() {
        return ModItems.FLOUR;
    }
    @Override
    public RegistryObject<? extends Item> getDough() {
        return ModItems.DOUGH;
    }
    @Override
    public RegistryObject<? extends Item> getDumplingWrapper() {
        return ModItems.DUMPLING_WRAPPER;
    }
    @Override
    public RegistryObject<? extends Item> getStuffing() {
        return ModItems.STUFFING;
    }
    @Override
    public RegistryObject<? extends Item> getDumpling() {
        return ModItems.DUMPLING;
    }
    @Override
    public RegistryObject<? extends Item> getCookedDumpling() {
        return ModItems.COOKED_DUMPLING;
    }
    @Override
    public RegistryObject<? extends Item> getBark() {
        return ModItems.BARK;
    }
    @Override
    public RegistryObject<? extends BucketItem> getBucketWoodPulp() {
        return ModItems.BUCKET_WOOD_PULP;
    }
    @Override
    public RegistryObject<? extends Item> getEmptyXuanPaper() {
        return ModItems.EMPTY_XUAN_PAPER;
    }
    @Override
    public RegistryObject<? extends Item> getXuanPaper() {
        return ModItems.XUAN_PAPER;
    }
    @Override
    public RegistryObject<? extends Item> getChineseInk() {
        return ModItems.CHINESE_INK;
    }
    @Override
    public RegistryObject<? extends Item> getCharcoalBlack() {
        return ModItems.CHARCOAL_BLACK;
    }
    @Override
    public RegistryObject<? extends Item> getDish() {
        return ModItems.DISH;
    }
    @Override
    public RegistryObject<? extends SwordItem> getKnifeIron() {
        return ModItems.KNIFE_IRON;
    }
    @Override
    public RegistryObject<? extends SwordItem> getKnifeGold() {
        return ModItems.KNIFE_GOLD;
    }
    @Override
    public RegistryObject<? extends SwordItem> getKnifeDiamond() {
        return ModItems.KNIFE_DIAMOND;
    }
    @Override
    public RegistryObject<? extends Item> getChineseBrush() {
        return ModItems.CHINESE_BRUSH;
    }
    @Override
    public RegistryObject<? extends Item> getInkStone() {
        return ModItems.INK_STONE;
    }
    @Override
    public RegistryObject<? extends Item> getTeaLeaf() {
        return ModItems.TEA_LEAF;
    }
    @Override
    public RegistryObject<? extends BaseTableItem> getTeacup() {
        return ModItems.TEACUP;
    }
    @Override
    public RegistryObject<? extends BaseTableItem> getTeapot() {
        return ModItems.TEAPOT;
    }

}
