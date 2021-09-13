package cx.rain.mc.forgemod.sinocraft.utility.enumerate;

import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;

public enum TreeType {
    PEACH("peach", MaterialColor.PINK_TERRACOTTA, ModItems.PEACH),
    WALNUT("walnut", MaterialColor.OBSIDIAN, null),
    PLUM("plum", MaterialColor.PINK_TERRACOTTA, null),
    MULBERRY("mulberry", MaterialColor.OBSIDIAN, null);

    private String name;
    private MaterialColor color;

    /**
     * @param fruit 用于生成树叶方块，只能引用来自 ModItems, ModBlockItems, Items 的物品
     */
    TreeType(String nameIn, MaterialColor colorIn, @Nullable RegistryObject<? extends Item> fruit) {
        name = nameIn;
        color = colorIn;
    }

    public String getName() {
        return name;
    }

    public MaterialColor getColor() {
        return color;
    }
}
