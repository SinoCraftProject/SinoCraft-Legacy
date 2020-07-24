package cx.rain.mc.forgemod.sinocraft.enumerate;

import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.block.Block;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public enum LogType {
    PEACH("peach", MaterialColor.PINK_TERRACOTTA,
            new TypeTag(Blocks.LOG_PEACH, Blocks.LOG_PEACH_STRIPPED,
                    Blocks.LOG_PEACH_BARK,Blocks.LOG_PEACH_STRIPPED_BARK,Blocks.LEAVES_PEACH)),
    WALNUT("walnut", MaterialColor.OBSIDIAN,
            new TypeTag(Blocks.LOG_WALNUT, Blocks.LOG_WALNUT_STRIPPED,Blocks.LOG_WALNUT_BARK,
                    Blocks.LOG_WALNUT_STRIPPED_BARK,Blocks.LEAVES_WALNUT)),
    PLUM("plum", MaterialColor.PINK_TERRACOTTA,
            new TypeTag(Blocks.LOG_PLUM, Blocks.LOG_PLUM_STRIPPED,Blocks.LOG_PLUM_BARK,
                    Blocks.LOG_PLUM_STRIPPED_BARK,Blocks.LEAVES_PLUM)),
    MULBERRY("mulberry", MaterialColor.OBSIDIAN,
            new TypeTag(Blocks.LOG_MULBERRY, Blocks.LOG_MULBERRY_STRIPPED,Blocks.LOG_MULBERRY_BARK,
                    Blocks.LOG_MULBERRY_STRIPPED_BARK,Blocks.LEAVES_MULBERRY));

    private String name;
    private MaterialColor color;
    private TypeTag tag;

    LogType(String nameIn, MaterialColor colorIn,TypeTag tagIn) {
        name = nameIn;
        color = colorIn;
        tag = tagIn;
    }

    public String getName() {
        return name;
    }

    public MaterialColor getColor() {
        return color;
    }

    public TypeTag getTag(){
        return tag;
    }

    public static class TypeTag{
        public RegistryObject<Block> Log;
        public RegistryObject<Block> LogStripped;
        public RegistryObject<Block> Leaves;
        public RegistryObject<Block> LogBark;
        public RegistryObject<Block> LogStrippedBark;

        public TypeTag(RegistryObject<Block> log, RegistryObject<Block> logStripped,RegistryObject<Block> logBark,RegistryObject<Block> logStrippedBark, RegistryObject<Block> leaves) {
            Log = log;
            LogStripped = logStripped;
            Leaves = leaves;
            LogBark=logBark;
            LogStrippedBark=logStrippedBark;
        }
    }
}
