package cx.rain.mc.forgemod.sinocraft.api.block.tree;

import cx.rain.mc.forgemod.sinocraft.api.block.IData;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

public interface ITreeData extends IData {
    MaterialColor getColor();

    boolean hasFruit();

    RegistryObject<? extends ItemLike> getFruit();
}
