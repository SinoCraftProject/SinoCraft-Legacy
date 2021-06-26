package cx.rain.mc.forgemod.sinocraft.block.table;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.block.BlockTeapot;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.item.ItemTeapot;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TableTeapot extends BaseTableElement {

    private float leaves;
    private int water;
    private int tea;
    private boolean hasCover = false;
    private VoxelShape shape0, shape1;

    public TableTeapot(double x, double y, double z) {
        super(x - 0.25, y, z - 0.25);
        shape0 = BlockTeapot.SHAPE0.withOffset(this.x, this.y, this.z);
        shape1 = BlockTeapot.SHAPE1.withOffset(this.x, this.y, this.z);
    }

    @Override
    public ItemStack makeItem() {
        return ItemTeapot.build(super.makeItem(), water, tea, leaves);
    }

    @Override
    public BlockState makeBlock() {
        return ModBlocks.TEAPOT.get().getDefaultState().with(BlockTeapot.FLUID, hasCover ? 0 : tea > 0 ? 2 : 1);
    }

    @Override
    public VoxelShape getShape() {
        return hasCover ? shape0 : shape1;
    }

    public float getLeaves() {
        return leaves;
    }

    public void addLeaves(float leaves) {
        this.leaves += leaves;
    }

    public float takeLeaves(float leaves) {
        if (this.leaves >= leaves) {
            this.leaves -= leaves;
            return leaves;
        } else {
            float value = this.leaves;
            this.leaves = 0;
            return value;
        }
    }

    public int getWater() {
        return water;
    }

    public void addWater(int water) {
        this.water += water;
    }

    public int takeWater(int water) {
        if (this.water >= water) {
            this.water -= water;
            return water;
        } else {
            int value = this.water;
            this.water = 0;
            return value;
        }
    }

    public int getTea() {
        return tea;
    }

    public void addTea(int tea) {
        this.tea += tea;
    }

    public int takeTea(int tea) {
        if (this.tea >= tea) {
            this.tea -= tea;
            return tea;
        } else {
            int value = this.tea;
            this.tea = 0;
            return tea;
        }
    }

    @Override
    public boolean tick(TileEntityTeaTable table) {
        if (leaves > 0 && water > 0) {
            float l = Math.min(leaves, 0.01f);
            int w = (int) (l * 1000);
            if (water >= w) {
                takeLeaves(l);
                takeWater(w);
            } else {
                takeLeaves(water / 1000f);
                w = takeWater(water);
            }
            addTea(w);
            table.markDirty();
        }
        return super.tick(table);
    }

    @Override
    public void onPlaced(TileEntityTeaTable table, ItemStack stack, ItemUseContext context, double x, double z) {
        super.onPlaced(table, stack, context, x, z);
        ItemTeapot item = ModItems.TEAPOT.get();
        if (stack.getItem() == item) {
            leaves = item.getLeaves(stack);
            water = item.getWater(stack);
            tea = item.getTea(stack);
        } else {
            leaves = 0;
            water = 0;
            tea = 0;
        }
    }

    @Override
    public ActionResultType onActivated(BlockState state, TileEntityTeaTable table, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getHeldItem(handIn);
        if (heldItem.isEmpty()) {
            hasCover = !hasCover;
            return ActionResultType.SUCCESS;
        }
        ItemTeapot teapot = ModItems.TEAPOT.get();
        Item item = heldItem.getItem();
        if (item == ModItems.TEA_LEAF.get()) {
            if (leaves < teapot.getLeavesCapacity()) {
                addTea(1);
                table.markDirty();
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.FAIL;
        }
        if (water + leaves < teapot.getWaterCapacity()) {
            heldItem.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(handler -> {
                FluidStack drain = handler.drain(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.SIMULATE);
                if (!drain.isEmpty()) {
                    addWater(drain.getAmount());
                    handler.drain(drain, IFluidHandler.FluidAction.EXECUTE);
                    table.markDirty();
                }
            });
            return ActionResultType.SUCCESS;
        }
        return super.onActivated(state, table, worldIn, pos, player, handIn, hit);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        nbt.putFloat("leaves", leaves);
        nbt.putInt("water", water);
        nbt.putInt("tea", tea);
        nbt.putBoolean("cover", hasCover);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        leaves = nbt.getFloat("leaves");
        water = nbt.getInt("water");
        tea = nbt.getInt("tea");
        hasCover = nbt.getBoolean("cover");
        shape0 = BlockTeapot.SHAPE0.withOffset(x, y, z);
        shape1 = BlockTeapot.SHAPE1.withOffset(x, y, z);
    }
}
