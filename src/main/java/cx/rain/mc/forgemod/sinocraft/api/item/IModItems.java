package cx.rain.mc.forgemod.sinocraft.api.item;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;

public interface IModItems {

    RegistryObject<? extends BlockItem> getChiliPepperSeed();
    RegistryObject<? extends BlockItem> getGreenPepperSeed();
    RegistryObject<? extends BlockItem> getEggplantSeed();
    RegistryObject<? extends BlockItem> getCabbageSeed();
    RegistryObject<? extends BlockItem> getRiceSeed();
    RegistryObject<? extends BlockItem> getMilletSeed();
    RegistryObject<? extends BlockItem> getSorghumSeed();
    RegistryObject<? extends BlockItem> getSoybean();
    RegistryObject<? extends Item> getRice();
    RegistryObject<? extends Item> getMillet();
    RegistryObject<? extends Item> getSorghum();
    RegistryObject<? extends Item> getSilkworm();
    RegistryObject<? extends Item> getPeach();
    RegistryObject<? extends Item> getChiliPepper();
    RegistryObject<? extends Item> getGreenPepper();
    RegistryObject<? extends Item> getEggplant();
    RegistryObject<? extends Item> getCabbage();
    RegistryObject<? extends Item> getAdustFood();
    RegistryObject<? extends Item> getHeroesAssemble();
    RegistryObject<? extends Item> getFlour();
    RegistryObject<? extends Item> getDough();
    RegistryObject<? extends Item> getDumplingWrapper();
    RegistryObject<? extends Item> getStuffing();
    RegistryObject<? extends Item> getDumpling();
    RegistryObject<? extends Item> getCookedDumpling();
    RegistryObject<? extends Item> getBark();
    RegistryObject<? extends BucketItem> getBucketWoodPulp();
    RegistryObject<? extends Item> getEmptyXuanPaper();
    RegistryObject<? extends Item> getXuanPaper();
    RegistryObject<? extends Item> getChineseInk();
    RegistryObject<? extends Item> getCharcoalBlack();
    RegistryObject<? extends Item> getDish();
    RegistryObject<? extends SwordItem> getKnifeIron();
    RegistryObject<? extends SwordItem> getKnifeGold();
    RegistryObject<? extends SwordItem> getKnifeDiamond();
    RegistryObject<? extends Item> getChineseBrush();
    RegistryObject<? extends Item> getInkStone();
    RegistryObject<? extends Item> getTeaLeaf();
    RegistryObject<? extends BaseTableItem> getTeacup();
    RegistryObject<? extends BaseTableItem> getTeapot();

}
