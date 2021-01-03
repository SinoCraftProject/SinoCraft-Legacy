package cx.rain.mc.forgemod.sinocraft.enumerate;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;

public enum LogType {
    PEACH("peach", MaterialColor.PINK_TERRACOTTA,
            new TypeTag(ModBlocks.PEACH_LOG, ModBlocks.PEACH_LOG_STRIPPED,
                    ModBlocks.PEACH_LOG_BARK, ModBlocks.PEACH_LOG_STRIPPED_BARK, ModBlocks.PEACH_LEAVES)),
    WALNUT("walnut", MaterialColor.OBSIDIAN,
            new TypeTag(ModBlocks.WALNUT_LOG, ModBlocks.WALNUT_LOG_STRIPPED, ModBlocks.WALNUT_LOG_BARK,
                    ModBlocks.WALNUT_LOG_STRIPPED_BARK, ModBlocks.WALNUT_LEAVES)),
    PLUM("plum", MaterialColor.PINK_TERRACOTTA,
            new TypeTag(ModBlocks.PLUM_LOG, ModBlocks.PLUM_LOG_STRIPPED, ModBlocks.PLUM_LOG_BARK,
                    ModBlocks.PLUM_LOG_STRIPPED_BARK, ModBlocks.PLUM_LEAVES)),
    MULBERRY("mulberry", MaterialColor.OBSIDIAN,
            new TypeTag(ModBlocks.MULBERRY_LOG, ModBlocks.MULBERRY_LOG_STRIPPED, ModBlocks.MULBERRY_LOG_BARK,
                    ModBlocks.MULBERRY_LOG_STRIPPED_BARK, ModBlocks.MULBERRY_LEAVES));

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
