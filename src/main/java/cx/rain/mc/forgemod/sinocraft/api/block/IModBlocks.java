package cx.rain.mc.forgemod.sinocraft.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraftforge.fml.RegistryObject;

public interface IModBlocks {

    RegistryObject<? extends CropsBlock> getWhiteRadishPlant();
    RegistryObject<? extends CropsBlock> getSummerRadishPlant();
    RegistryObject<? extends CropsBlock> getGreenRadishPlant();
    RegistryObject<? extends CropsBlock> getChiliPepperPlant();
    RegistryObject<? extends CropsBlock> getGreenPepperPlant();
    RegistryObject<? extends CropsBlock> getEggplantPlant();
    RegistryObject<? extends CropsBlock> getCabbagePlant();
    RegistryObject<? extends CropsBlock> getRicePlant();
    RegistryObject<? extends CropsBlock> getMilletPlant();
    RegistryObject<? extends CropsBlock> getSoybeanPlant();
    RegistryObject<? extends CropsBlock> getSorghumPlant();
    RegistryObject<? extends RotatedPillarBlock> getPeachLog();
    RegistryObject<? extends RotatedPillarBlock> getPeachLogBark();
    RegistryObject<? extends RotatedPillarBlock> getPeachLogStripped();
    RegistryObject<? extends RotatedPillarBlock> getPeachLogStrippedBark();
    RegistryObject<? extends Block> getPeachPlank();
    RegistryObject<? extends LeavesBlock> getPeachLeaves();
    RegistryObject<? extends SaplingBlock> getPeachSapling();
    RegistryObject<? extends RotatedPillarBlock> getWalnutLog();
    RegistryObject<? extends RotatedPillarBlock> getWalnutLogBark();
    RegistryObject<? extends RotatedPillarBlock> getWalnutLogStripped();
    RegistryObject<? extends RotatedPillarBlock> getWalnutLogStrippedBark();
    RegistryObject<? extends Block> getWalnutPlank();
    RegistryObject<? extends LeavesBlock> getWalnutLeaves();
    RegistryObject<? extends SaplingBlock> getWalnutSapling();
    RegistryObject<? extends RotatedPillarBlock> getPlumLog();
    RegistryObject<? extends RotatedPillarBlock> getPlumLogBark();
    RegistryObject<? extends RotatedPillarBlock> getPlumLogStripped();
    RegistryObject<? extends RotatedPillarBlock> getPlumLogStrippedBark();
    RegistryObject<? extends Block> getPlumPlank();
    RegistryObject<? extends LeavesBlock> getPlumLeaves();
    RegistryObject<? extends SaplingBlock> getPlumSapling();
    RegistryObject<? extends RotatedPillarBlock> getMulberryLog();
    RegistryObject<? extends RotatedPillarBlock> getMulberryLogBark();
    RegistryObject<? extends RotatedPillarBlock> getMulberryLogStripped();
    RegistryObject<? extends RotatedPillarBlock> getMulberryLogStrippedBark();
    RegistryObject<? extends Block> getMulberryPlank();
    RegistryObject<? extends LeavesBlock> getMulberryLeaves();
    RegistryObject<? extends SaplingBlock> getMulberrySapling();
    RegistryObject<? extends Block> getWhiteMarble();
    RegistryObject<? extends Block> getRedMarble();
    RegistryObject<? extends Block> getBlackMarble();
    RegistryObject<? extends HorizontalBlock> getStove();
    RegistryObject<? extends HorizontalBlock> getVat();
    RegistryObject<? extends HorizontalBlock> getStoneMill();
    RegistryObject<? extends HorizontalBlock> getPaperDryingRack();
    RegistryObject<? extends Block> getPot();
    RegistryObject<? extends HorizontalBlock> getBellows();
    RegistryObject<? extends FlowingFluidBlock> getWoodPulpBlock();
    RegistryObject<? extends Block> getTeaTable();
    RegistryObject<? extends Block> getTeacup();
    RegistryObject<? extends Block> getTeapot();

}
