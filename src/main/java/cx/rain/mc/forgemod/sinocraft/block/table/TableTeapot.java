package cx.rain.mc.forgemod.sinocraft.block.table;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.block.BlockTeapot;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.capability.CapabilityTeapot;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.utility.CapabilityHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

    private boolean hasCover = false;
    private VoxelShape shape0, shape1;

    public TableTeapot(double x, double y, double z) {
        super(x - 0.25, y, z - 0.25);
        shape0 = BlockTeapot.SHAPE0.withOffset(this.x, this.y, this.z);
        shape1 = BlockTeapot.SHAPE1.withOffset(this.x, this.y, this.z);
    }

    @Override
    public BlockState makeBlock() {
        return ModBlocks.TEAPOT.get().getDefaultState().with(BlockTeapot.FLUID, hasCover ? 0 : CapabilityHelper.getTeapot(stack).getTea() > 0 ? 2 : 1);
    }

    @Override
    public VoxelShape getShape() {
        return hasCover ? shape0 : shape1;
    }

    @Override
    public boolean tick(TileEntityTeaTable table) {
        CapabilityTeapot.CapTeapot teapot = CapabilityHelper.getTeapot(stack);
        float leaves = teapot.getLeaves();
        int water = teapot.getWater();
        World world = table.getWorld();
        if (world == null) return super.tick(table);
        if (leaves > 0 && water > 0 && !world.isRemote) {
            float l = Math.min(leaves, 0.01f);
            int w = (int) (l * 1000);
            if (water >= w) {
                teapot.takeLeaves(l);
                w = teapot.takeWater(w);
            } else {
                teapot.takeLeaves(water / 1000f);
                w = teapot.takeWater(water);
            }
            teapot.addTea(w);
            table.markDirty();
        }
        return super.tick(table);
    }

    @Override
    public ActionResultType onActivated(BlockState state, TileEntityTeaTable table, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getHeldItem(handIn);
        if (heldItem.isEmpty()) {
            hasCover = !hasCover;
            return ActionResultType.SUCCESS;
        }
        Item item = heldItem.getItem();
        if (item == ModItems.TEA_LEAF.get()) {
            CapabilityTeapot.CapTeapot teapot = CapabilityHelper.getTeapot(stack);
            if (teapot.addLeaves(1) > 0) {
                table.markDirty();
                heldItem.shrink(1);
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.FAIL;
        } else {
            heldItem.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(handler -> {
                FluidStack drain = handler.drain(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.SIMULATE);
                if (!drain.isEmpty()) {
                    CapabilityTeapot.CapTeapot teapot = CapabilityHelper.getTeapot(stack);
                    int add = teapot.addWater(drain.getAmount());
                    if (add > 0) {
                        FluidStack drain1 = handler.drain(new FluidStack(Fluids.WATER, add), IFluidHandler.FluidAction.EXECUTE);
                        if (drain1.getAmount() == 0) {
                            handler.drain(drain, IFluidHandler.FluidAction.EXECUTE);
                        }
                    }
                    table.markDirty();
                    player.setHeldItem(handIn, handler.getContainer());
                }
            });
            return ActionResultType.SUCCESS;
        }
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        super.onTakeItem(player, stack);
//        if (!player.world.isRemote) {
//            int slot = getSlotFor(player.inventory, stack);
//            if (slot >= 0) {
//                CapabilityTeapot.CapTeapot teapot = CapabilityHelper.getTeapot(stack);
//                if (teapot.isValid()) {
//                    TeapotQueryS2CPacket packet = new TeapotQueryS2CPacket(slot, teapot);
//                    Networks.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), packet);
//                }
//            }
//        }
    }

    // copy from PlayerInventory#getSlotFor
    private int getSlotFor(PlayerInventory inventory, ItemStack stack) {
        for(int i = 0; i < inventory.mainInventory.size(); ++i) {
            if (!inventory.mainInventory.get(i).isEmpty() && stackEqualExact(stack, inventory.mainInventory.get(i))) {
                return i;
            }
        }

        return -1;
    }

    // copy from PlayerInventory#stackEqualExact
    private boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == stack2.getItem() && ItemStack.areItemStackTagsEqual(stack1, stack2);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        nbt.putBoolean("cover", hasCover);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        hasCover = nbt.getBoolean("cover");
        shape0 = BlockTeapot.SHAPE0.withOffset(x, y, z);
        shape1 = BlockTeapot.SHAPE1.withOffset(x, y, z);
    }
}
