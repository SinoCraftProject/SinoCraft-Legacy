package cx.rain.mc.forgemod.sinocraft.api.block.tree;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public record TreeData(String name, MaterialColor topColor, MaterialColor sideColor,
                        AbstractTreeGrower grower,
                        @Nullable RegistryObject<? extends ItemLike> fruit) implements ITreeData {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasFruit() {
        return fruit != null;
    }

    @Override
    @Nullable
    public RegistryObject<? extends ItemLike> getFruit() {
        return fruit;
    }

    @Override
    public MaterialColor getTopColor() {
        return topColor;
    }

    @Override
    public MaterialColor getSideColor() {
        return sideColor;
    }

    public AbstractTreeGrower getGrower() {
        return grower;
    }
}
