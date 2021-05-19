package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.item.IKnife;
import cx.rain.mc.forgemod.sinocraft.api.item.IShaveable;
import cx.rain.mc.forgemod.sinocraft.item.shave.BlockShave;
import cx.rain.mc.forgemod.sinocraft.item.shave.TagShave;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.tags.ITag;

import java.util.Collection;
import java.util.function.Function;

public enum APIKnife implements IKnife {

    INSTANCE;

    @Override
    public TieredItem getKnife(IItemTier tier) {
        for (ItemKnife knife : ItemKnife.KNIVES) {
            if (knife.getTier() == tier) {
                return knife;
            }
        }
        return null;
    }

    @Override
    public IShaveable newShaveable(Block block, BlockState replacedBlock, Collection<ItemStack> dropItems) {
        return new BlockShave(block, replacedBlock, dropItems);
    }

    @Override
    public IShaveable newShaveable(ITag<Block> tag, Function<BlockState, BlockState> replacedBlock, Function<BlockState, ItemStack> dropItems) {
        return new TagShave(tag, replacedBlock, dropItems);
    }

    @Override
    public void addShaveable(IShaveable shaveable, Item... knives) {
        if (knives.length == 0) {
            for (ItemKnife knife : ItemKnife.KNIVES) {
                knife.shaves.add(shaveable);
            }
        } else {
            for (Item knife : knives) {
                if (knife instanceof ItemKnife) {
                    ((ItemKnife) knife).shaves.add(shaveable);
                }
            }
        }
    }
}
