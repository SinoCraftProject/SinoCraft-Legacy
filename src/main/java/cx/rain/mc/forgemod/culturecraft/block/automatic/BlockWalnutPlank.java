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

@ModBlock(name = "walnut_tree_plank")
public class BlockWalnutPlank extends Block {
    public BlockWalnutPlank() {
        super(Properties.create(Material.WOOD).hardnessAndResistance(2,10).sound(SoundType.WOOD)
        .harvestLevel(0).harvestTool(ToolType.AXE));
    }
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(RegistryBlock.BLOCKS.get("walnut_tree_plank"), (int) (1)));
    }
}
