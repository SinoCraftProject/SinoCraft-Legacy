package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import com.google.common.collect.Streams;
import cx.rain.mc.forgemod.sinocraft.block.table.PlacedTableElement;
import cx.rain.mc.forgemod.sinocraft.item.ItemTeaTableElement;
import cx.rain.mc.forgemod.sinocraft.utility.property.CollectionHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class TileEntityTeaTable extends TileEntityUpdatableBase {

    private final List<PlacedTableElement> elements = NonNullList.create();

    public TileEntityTeaTable() {
        super(ModTileEntities.TEA_TABLE.get());
    }

    public boolean put(double x, double z, ItemStack element) {
        Item item = element.getItem();
        if (!(item instanceof ItemTeaTableElement)) return false;
        PlacedTableElement e = ((ItemTeaTableElement) item).createElement(x, z);
        if (elements.stream().allMatch(e::isDisjoint)) {
            e.load(element);
            elements.add(e);
            markDirty();
            return true;
        }
        return false;
    }

    public ItemStack take(double x, double z, boolean simulate) {
        Iterator<PlacedTableElement> iterator = elements.iterator();
        while (iterator.hasNext()) {
            PlacedTableElement element = iterator.next();
            if (element.contains(x, z)) {
                if (!simulate) {
                    iterator.remove();
                }
                return element.make();
            }
        }
        return ItemStack.EMPTY;
    }

    public VoxelShape buildShape() {
        VoxelShape shape = VoxelShapes.empty();
        for (PlacedTableElement element : elements) {
            AxisAlignedBB range = element.getRange();
            shape = VoxelShapes.or(shape, VoxelShapes.create(range));
        }
        return shape;
    }

    public NonNullList<ItemStack> removeAll() {
        NonNullList<ItemStack> stacks = elements.stream()
                .map(PlacedTableElement::make)
                .collect(CollectionHelper.toNonnullList());
        elements.clear();
        return stacks;
    }

    public List<PlacedTableElement> getElements() {
        return elements;
    }

    @Override
    public void tick() {
        if (world != null && !world.isRemote) {
            if (elements.isEmpty()) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            } else if (!canExist(world, pos)) {
                // todo move to neighborChanged
                NonNullList<ItemStack> droppedItems = Streams.stream(elements)
                        .map(PlacedTableElement::make)
                        .collect(CollectionHelper.toNonnullList());
                InventoryHelper.dropItems(world, pos, droppedItems);
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    public static boolean canExist(@Nullable IBlockReader world, BlockPos pos) {
        if (world == null) return false;
        BlockPos down = pos.down();
        BlockState state = world.getBlockState(down);
        Block block = state.getBlock();
        if (block.isAir(state, world, down)) {
            return false;
        }
        if (!state.isSolid()) {
            return false;
        }
        boolean[] canExist = new boolean[] {false};
        VoxelShape shape = block.getCollisionShape(state, world, down);
        shape.forEachBox((x0, y0, z0, x1, y1, z1) -> {
            if (!canExist[0] && y1 == 1) {
                if (MathHelper.epsilonEquals(x0, 0)
                        && MathHelper.epsilonEquals(z0, 0)
                        && MathHelper.epsilonEquals(x1, 1)
                        && MathHelper.epsilonEquals(z1, 1)) {
                    canExist[0] = true;
                }
            }
        });
        return canExist[0];
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        elements.clear();
        ListNBT elements = nbt.getList("elements", Constants.NBT.TAG_COMPOUND);
        for (INBT eNbt : elements) {
            ItemStack stack = PlacedTableElement.deserializeItem((CompoundNBT) eNbt);
            Item item = stack.getItem();
            if (item instanceof ItemTeaTableElement) {
                PlacedTableElement element = ((ItemTeaTableElement) item).createElement(0, 0);
                element.deserializeNBT((CompoundNBT) eNbt);
                this.elements.add(element);
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound = super.write(compound);
        compound.put("elements", elements.stream()
                .map(PlacedTableElement::serializeNBT)
                .collect(CollectionHelper.toNBTList()));
        return compound;
    }
}
