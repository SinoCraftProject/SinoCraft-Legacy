package cx.rain.mc.forgemod.sinocraft.utility.enumerate;

import net.minecraft.block.material.MaterialColor;

public enum LogType {
    PEACH("peach", MaterialColor.PINK_TERRACOTTA),
    WALNUT("walnut", MaterialColor.OBSIDIAN),
    PLUM("plum", MaterialColor.PINK_TERRACOTTA),
    MULBERRY("mulberry", MaterialColor.OBSIDIAN);

    private String name;
    private MaterialColor color;

    LogType(String nameIn, MaterialColor colorIn) {
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
