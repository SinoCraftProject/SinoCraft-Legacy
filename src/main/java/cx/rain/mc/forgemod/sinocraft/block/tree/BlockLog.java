package cx.rain.mc.forgemod.sinocraft.block.tree;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class BlockLog extends RotatedPillarBlock {

    protected final TreeType type;

    public BlockLog(TreeType type) {
        super(Block.Properties.create(Material.WOOD, type.getColor())
                .hardnessAndResistance(2.0F)
                .sound(SoundType.WOOD));
        this.type = type;
    }

    public TreeType getType() {
        return type;
    }
}
