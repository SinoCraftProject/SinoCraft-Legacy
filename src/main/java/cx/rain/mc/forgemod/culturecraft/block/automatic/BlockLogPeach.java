package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.world.storage.loot.LootContext;

import java.util.Collections;
import java.util.List;

@ModBlock(name = "log_peach")
public class BlockLogPeach extends LogBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public BlockLogPeach() {
        super(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN)
                        .hardnessAndResistance(2.0F)
                        .sound(SoundType.WOOD));
    }
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(RegistryBlock.BLOCKS.get("log_peach"), (int) (1)));
    }
}
