package cx.rain.mc.forgemod.sinocraft.api_impl;

import cx.rain.mc.forgemod.sinocraft.api.block.IModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraftforge.fml.RegistryObject;

public enum APIModBlocks implements IModBlocks {

    INSTANCE;

    @Override
    public RegistryObject<? extends CropsBlock> getWhiteRadishPlant() {
        return ModBlocks.WHITE_RADISH_PLANT;
    }
    @Override
    public RegistryObject<? extends CropsBlock> getSummerRadishPlant() {
        return ModBlocks.SUMMER_RADISH_PLANT;
    }
    @Override
    public RegistryObject<? extends CropsBlock> getGreenRadishPlant() {
        return ModBlocks.GREEN_RADISH_PLANT;
    }
    @Override
    public RegistryObject<? extends CropsBlock> getChiliPepperPlant() {
        return ModBlocks.CHILI_PEPPER_PLANT;
    }
    @Override
    public RegistryObject<? extends CropsBlock> getGreenPepperPlant() {
        return ModBlocks.GREEN_PEPPER_PLANT;
    }
    @Override
    public RegistryObject<? extends CropsBlock> getEggplantPlant() {
        return ModBlocks.EGGPLANT_PLANT;
    }
    @Override
    public RegistryObject<? extends CropsBlock> getCabbagePlant() {
        return ModBlocks.CABBAGE_PLANT;
    }
    @Override
    public RegistryObject<? extends CropsBlock> getRicePlant() {
        return ModBlocks.RICE_PLANT;
    }
    @Override
    public RegistryObject<? extends CropsBlock> getMilletPlant() {
        return ModBlocks.MILLET_PLANT;
    }
    @Override
    public RegistryObject<? extends CropsBlock> getSoybeanPlant() {
        return ModBlocks.SOYBEAN_PLANT;
    }
    @Override
    public RegistryObject<? extends CropsBlock> getSorghumPlant() {
        return ModBlocks.SORGHUM_PLANT;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getPeachLog() {
        return ModBlocks.PEACH_LOG;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getPeachLogBark() {
        return ModBlocks.PEACH_LOG_BARK;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getPeachLogStripped() {
        return ModBlocks.PEACH_LOG_STRIPPED;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getPeachLogStrippedBark() {
        return ModBlocks.PEACH_LOG_STRIPPED_BARK;
    }
    @Override
    public RegistryObject<? extends Block> getPeachPlank() {
        return ModBlocks.PEACH_PLANK;
    }
    @Override
    public RegistryObject<? extends LeavesBlock> getPeachLeaves() {
        return ModBlocks.PEACH_LEAVES;
    }
    @Override
    public RegistryObject<? extends SaplingBlock> getPeachSapling() {
        return ModBlocks.PEACH_SAPLING;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getWalnutLog() {
        return ModBlocks.WALNUT_LOG;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getWalnutLogBark() {
        return ModBlocks.WALNUT_LOG_BARK;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getWalnutLogStripped() {
        return ModBlocks.WALNUT_LOG_STRIPPED;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getWalnutLogStrippedBark() {
        return ModBlocks.WALNUT_LOG_STRIPPED_BARK;
    }
    @Override
    public RegistryObject<? extends Block> getWalnutPlank() {
        return ModBlocks.WALNUT_PLANK;
    }
    @Override
    public RegistryObject<? extends LeavesBlock> getWalnutLeaves() {
        return ModBlocks.WALNUT_LEAVES;
    }
    @Override
    public RegistryObject<? extends SaplingBlock> getWalnutSapling() {
        return ModBlocks.WALNUT_SAPLING;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getPlumLog() {
        return ModBlocks.PLUM_LOG;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getPlumLogBark() {
        return ModBlocks.PLUM_LOG_BARK;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getPlumLogStripped() {
        return ModBlocks.PLUM_LOG_STRIPPED;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getPlumLogStrippedBark() {
        return ModBlocks.PLUM_LOG_STRIPPED_BARK;
    }
    @Override
    public RegistryObject<? extends Block> getPlumPlank() {
        return ModBlocks.PLUM_PLANK;
    }
    @Override
    public RegistryObject<? extends LeavesBlock> getPlumLeaves() {
        return ModBlocks.PLUM_LEAVES;
    }
    @Override
    public RegistryObject<? extends SaplingBlock> getPlumSapling() {
        return ModBlocks.PLUM_SAPLING;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getMulberryLog() {
        return ModBlocks.MULBERRY_LOG;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getMulberryLogBark() {
        return ModBlocks.MULBERRY_LOG_BARK;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getMulberryLogStripped() {
        return ModBlocks.MULBERRY_LOG_STRIPPED;
    }
    @Override
    public RegistryObject<? extends RotatedPillarBlock> getMulberryLogStrippedBark() {
        return ModBlocks.MULBERRY_LOG_STRIPPED_BARK;
    }
    @Override
    public RegistryObject<? extends Block> getMulberryPlank() {
        return ModBlocks.MULBERRY_PLANK;
    }
    @Override
    public RegistryObject<? extends LeavesBlock> getMulberryLeaves() {
        return ModBlocks.MULBERRY_LEAVES;
    }
    @Override
    public RegistryObject<? extends SaplingBlock> getMulberrySapling() {
        return ModBlocks.MULBERRY_SAPLING;
    }
    @Override
    public RegistryObject<? extends Block> getWhiteMarble() {
        return ModBlocks.WHITE_MARBLE;
    }
    @Override
    public RegistryObject<? extends Block> getRedMarble() {
        return ModBlocks.RED_MARBLE;
    }
    @Override
    public RegistryObject<? extends Block> getBlackMarble() {
        return ModBlocks.BLACK_MARBLE;
    }
    @Override
    public RegistryObject<? extends HorizontalBlock> getStove() {
        return ModBlocks.STOVE;
    }
    @Override
    public RegistryObject<? extends HorizontalBlock> getVat() {
        return ModBlocks.VAT;
    }
    @Override
    public RegistryObject<? extends HorizontalBlock> getStoneMill() {
        return ModBlocks.STONE_MILL;
    }
    @Override
    public RegistryObject<? extends HorizontalBlock> getPaperDryingRack() {
        return ModBlocks.PAPER_DRYING_RACK;
    }
    @Override
    public RegistryObject<? extends Block> getPot() {
        return ModBlocks.POT;
    }
    @Override
    public RegistryObject<? extends FlowingFluidBlock> getWoodPulpBlock() {
        return ModBlocks.WOOD_PULP_BLOCK;
    }
    @Override
    public RegistryObject<? extends Block> getTeaTable() {
        return ModBlocks.TEA_TABLE;
    }
    @Override
    public RegistryObject<? extends Block> getTeacup() {
        return ModBlocks.TEACUP;
    }
    @Override
    public RegistryObject<? extends Block> getTeapot() {
        return ModBlocks.TEAPOT;
    }

}
