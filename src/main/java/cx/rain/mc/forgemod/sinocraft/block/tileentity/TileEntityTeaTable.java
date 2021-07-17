package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.block.ITileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.item.base.TableItem;
import cx.rain.mc.forgemod.sinocraft.utility.CollectionHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.common.util.Constants;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TileEntityTeaTable extends TileEntityUpdatableBase implements ITileEntityTeaTable {

    private final List<BaseTableElement> elements = NonNullList.create();

    public TileEntityTeaTable() {
        super(ModTileEntities.TEA_TABLE.get());
    }

    @Override
    public boolean put(ItemUseContext context, double x, double z, ItemStack element) {
        Item item = element.getItem();
        if (!(item instanceof TableItem)) return false;
        BaseTableElement e = ((TableItem) item).createElement(x, 0, z);
        if (elements.stream().noneMatch(e::isConflicted)) {
            elements.add(e);
            e.onPlaced(this, element, context, x, z);
            markDirty();
            return true;
        }
        return false;
    }

    @Override
    public Optional<BaseTableElement> take(double x, double y, double z) {
        Iterator<BaseTableElement> iterator = elements.iterator();
        while (iterator.hasNext()) {
            BaseTableElement element = iterator.next();
            if (element.contains(x, y, z)) {
                iterator.remove();
                return Optional.of(element);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<BaseTableElement> lookup(double x, double y, double z) {
        return elements.stream()
                .filter(e -> e.contains(x, y, z))
                .findFirst();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BaseTableElement> Optional<T> lookup(double x, double y, double z, Class<T> type) {
        return (Optional<T>) elements.stream()
                .filter(e -> e.contains(x, y, z))
                .filter(type::isInstance)
                .findFirst();
    }

    @Override
    public Stream<BaseTableElement> elements() {
        return elements.stream();
    }

    @Override
    public VoxelShape buildShape() {
        VoxelShape shape = VoxelShapes.empty();
        for (BaseTableElement element : elements) {
            shape = VoxelShapes.or(shape, element.getShape());
        }
        return shape;
    }

    @Override
    public void onTick() {
        if (world != null) {
            if (elements.isEmpty()) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            } else {
                elements().forEach(element -> element.tick(this));
            }
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        elements.clear();
        ListNBT elements = nbt.getList("elements", Constants.NBT.TAG_COMPOUND);
        for (INBT eNbt : elements) {
            ItemStack stack = ItemStack.read(((CompoundNBT) eNbt).getCompound("stack"));
            Item item = stack.getItem();
            if (item instanceof TableItem) {
                BaseTableElement element = ((TableItem) item).createElement(0, 0, 0);
                element.deserializeNBT((CompoundNBT) eNbt);
                this.elements.add(element);
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound = super.write(compound);
        compound.put("elements", elements.stream()
                .map(BaseTableElement::serializeNBT)
                .collect(CollectionHelper.toNBTList()));
        return compound;
    }
}
