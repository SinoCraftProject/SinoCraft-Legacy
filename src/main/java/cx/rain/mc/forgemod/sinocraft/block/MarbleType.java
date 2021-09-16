package cx.rain.mc.forgemod.sinocraft.block;

import net.minecraft.block.material.MaterialColor;

public enum MarbleType {
    WHITE("white", MaterialColor.WHITE_TERRACOTTA),
    RED("red", MaterialColor.RED_TERRACOTTA),
    BLACK("black", MaterialColor.BLACK_TERRACOTTA);

    private String name;
    private MaterialColor color;

    MarbleType(String nameIn, MaterialColor colorIn) {
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
