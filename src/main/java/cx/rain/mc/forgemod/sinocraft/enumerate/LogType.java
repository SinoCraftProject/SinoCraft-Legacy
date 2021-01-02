package cx.rain.mc.forgemod.sinocraft.enumerate;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;

public enum LogType {
    PEACH("peach", MaterialColor.PINK_TERRACOTTA,
            new TypeTag(ModBlocks.LOG_PEACH, ModBlocks.LOG_PEACH_STRIPPED,
                    ModBlocks.LOG_PEACH_BARK, ModBlocks.LOG_PEACH_STRIPPED_BARK, ModBlocks.LEAVES_PEACH)),
    WALNUT("walnut", MaterialColor.OBSIDIAN,
            new TypeTag(ModBlocks.LOG_WALNUT, ModBlocks.LOG_WALNUT_STRIPPED, ModBlocks.LOG_WALNUT_BARK,
                    ModBlocks.LOG_WALNUT_STRIPPED_BARK, ModBlocks.LEAVES_WALNUT)),
    PLUM("plum", MaterialColor.PINK_TERRACOTTA,
            new TypeTag(ModBlocks.LOG_PLUM, ModBlocks.LOG_PLUM_STRIPPED, ModBlocks.LOG_PLUM_BARK,
                    ModBlocks.LOG_PLUM_STRIPPED_BARK, ModBlocks.LEAVES_PLUM)),
    MULBERRY("mulberry", MaterialColor.OBSIDIAN,
            new TypeTag(ModBlocks.LOG_MULBERRY, ModBlocks.LOG_MULBERRY_STRIPPED, ModBlocks.LOG_MULBERRY_BARK,
                    ModBlocks.LOG_MULBERRY_STRIPPED_BARK, ModBlocks.LEAVES_MULBERRY));

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
