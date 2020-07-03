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
                .hardnessAndResistance(2,10)
                .sound(SoundType.WOOD)
                .harvestTool(ToolType.AXE));
    }
}
