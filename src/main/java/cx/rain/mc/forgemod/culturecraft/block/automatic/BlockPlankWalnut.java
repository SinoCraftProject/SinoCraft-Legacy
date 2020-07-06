package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.ToolType;

import java.util.Collections;
import java.util.List;

@ModBlock(name = "plank_walnut")
public class BlockPlankWalnut extends Block {
    public BlockPlankWalnut() {
        super(Properties.create(Material.WOOD)
                .hardnessAndResistance(2.0F,3.0F)
                .sound(SoundType.WOOD)
                .harvestTool(ToolType.AXE));
    }
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(RegistryBlock.BLOCKS.get("plank_walnut"), (int) (1)));
    }
}
