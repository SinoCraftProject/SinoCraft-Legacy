package cx.rain.mc.forgemod.sinocraft.enumerate;

import net.minecraft.block.material.MaterialColor;

public enum LogType {
    PEACH("peach", MaterialColor.PINK_TERRACOTTA),
    WALNUT("walnut", MaterialColor.OBSIDIAN),
    PLUM("plum", MaterialColor.PINK_TERRACOTTA),
    MULBERRY("mulberry", MaterialColor.OBSIDIAN);

    private String name;
    private MaterialColor color;

    private LogType(String nameIn, MaterialColor colorIn) {
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
