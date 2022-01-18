package cx.rain.mc.forgemod.sinocraft.api.block.tree;

import cx.rain.mc.forgemod.sinocraft.api.block.tree.ITreeData;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public record TreeData(String name, MaterialColor color, @Nullable RegistryObject<? extends ItemLike> fruit) implements ITreeData {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public MaterialColor getColor() {
        return color;
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
}
