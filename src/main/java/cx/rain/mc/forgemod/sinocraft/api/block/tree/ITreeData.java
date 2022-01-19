package cx.rain.mc.forgemod.sinocraft.api.block.tree;

import cx.rain.mc.forgemod.sinocraft.api.block.IData;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

public interface ITreeData extends IData {
    boolean hasFruit();

    RegistryObject<? extends ItemLike> getFruit();

    MaterialColor getTopColor();

    MaterialColor getSideColor();

    AbstractTreeGrower getGrower();
}
