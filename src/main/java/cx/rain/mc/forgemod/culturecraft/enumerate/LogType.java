package cx.rain.mc.forgemod.culturecraft.enumerate;

import net.minecraft.block.material.MaterialColor;

public enum LogType {
    Peach("peach", MaterialColor.PINK_TERRACOTTA),
    Walnut("walnut", MaterialColor.OBSIDIAN);

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
