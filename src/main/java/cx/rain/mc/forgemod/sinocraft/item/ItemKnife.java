package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.item.IShaveable;
import cx.rain.mc.forgemod.sinocraft.api.item.ShaveResult;
import cx.rain.mc.forgemod.sinocraft.item.shave.TagShave;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ItemKnife extends SwordItem {

    public final static List<ItemKnife> KNIVES = new ArrayList<>();

    public final List<IShaveable> shaves = new ArrayList<>();

    public ItemKnife(IItemTier tier) {
        super(tier, 2, -3.0f, new Item.Properties().group(ModGroups.MISC));
        Function<BlockState, ItemStack> dropItem = state -> new ItemStack(ModItems.BARK.get(), random.nextInt(2) + 1);
        shaves.add(0, new TagShave(BlockTags.LOGS, AxeItem::getAxeStrippingState, dropItem));
        KNIVES.add(this);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state = world.getBlockState(pos);
        for (IShaveable shave : shaves) {
            ShaveResult result = shave.onShave(state, context);
            if (result.isSkip()) continue;
            BlockState block = result.getReplacedBlock();
            if (!world.isRemote) {
                if (block != state) {
                    world.setBlockState(pos, block);
                    world.notifyBlockUpdate(pos, state, block, 2);
                }
                world.playSound(context.getPlayer(), pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            for (ItemStack item : result.getDropItems()) {
                InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), item);
            }
            return ActionResultType.SUCCESS;
        }
        return world.isRemote ? ActionResultType.SUCCESS : ActionResultType.FAIL;
    }
}
