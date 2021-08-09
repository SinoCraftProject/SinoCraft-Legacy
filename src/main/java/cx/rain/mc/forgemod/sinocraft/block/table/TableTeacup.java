package cx.rain.mc.forgemod.sinocraft.block.table;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.api.table.ScaleFunction;
import cx.rain.mc.forgemod.sinocraft.block.BlockTeacup;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.capability.CapabilityTeacup;
import cx.rain.mc.forgemod.sinocraft.capability.CapabilityTeapot;
import cx.rain.mc.forgemod.sinocraft.network.packet.TeapotPacket;
import cx.rain.mc.forgemod.sinocraft.utility.CapabilityHelper;
import cx.rain.mc.forgemod.sinocraft.utility.PlayerSlot;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TableTeacup extends BaseTableElement {
    private static final float SCALE_XZ = 0.33f;
    private static final float SCALE_Y = 0.7f;
    private static final VoxelShape SHAPE = VoxelShapes.create(0.1875 * SCALE_XZ, 0, 0.1875 * SCALE_XZ, 0.8125 * SCALE_XZ, 0.4375 * SCALE_Y, 0.8125 * SCALE_XZ);

    private final Map<PlayerEntity, PlayerSlot<Counter>> pouringTeapots = new HashMap<>();

    private VoxelShape shape;

    public TableTeacup(double x, double y, double z) {
        super(x - 0.3125 * SCALE_XZ, y, z - 0.3125 * SCALE_XZ);
        shape = SHAPE.withOffset(this.x, this.y, this.z);
    }

    @Override
    public BlockState makeBlock() {
        return ModBlocks.TEACUP.get().getDefaultState().with(BlockTeacup.WITH_TEA, CapabilityHelper.getTeacup(stack).getTea() > 0);
    }

    @Override
    public VoxelShape getShape() {
        return shape;
    }

    @Override
    public void scale(ScaleFunction function) {
        function.scale(SCALE_XZ, SCALE_Y, SCALE_XZ);
    }

    @Override
    public ActionResultType onActivated(BlockState state, TileEntityTeaTable table, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getHeldItem(handIn);
        CapabilityTeapot.CapTeapot teapot = CapabilityHelper.getTeapot(heldItem);
        if (teapot.isValid()) {
            CapabilityTeacup.CapTeacup teacup = CapabilityHelper.getTeacup(stack);
            int teaCount = teapot.getTea();
            int space = teacup.getCapacity() - teacup.getTea();
            if (space > 0 && teaCount > 0 && !worldIn.isRemote) {
                int transform = Math.min(Math.min(space, teaCount), 20);
                teapot.takeTea(transform);
                teacup.addTea(transform);
                table.markDirty();
                if (pouringTeapots.containsKey(player)) {
                    PlayerSlot<Counter> slot = pouringTeapots.get(player);
                    if (!slot.equals(player, handIn)) {
                        TeapotPacket.stopToClient(player, slot);
                        PlayerSlot<Counter> newSlot = new PlayerSlot<>(player, handIn, new Counter());
                        teapot.setPouring(true);
                        TeapotPacket.syncToClient(player, newSlot, teapot);
                        pouringTeapots.put(player, newSlot);
                    } else {
                        slot.extra.reset = true;
                        TeapotPacket.syncToClient(player, slot, teapot);
                    }
                } else {
                    PlayerSlot<Counter> slot = new PlayerSlot<>(player, handIn, new Counter());
                    teapot.setPouring(true);
                    TeapotPacket.syncToClient(player, slot, teapot);
                    pouringTeapots.put(player, slot);
                }
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public boolean tick(TileEntityTeaTable table) {
        updateTeapot();
        return true;
    }

    private void updateTeapot() {
        if (pouringTeapots.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<PlayerEntity, PlayerSlot<Counter>>> iterator = pouringTeapots.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<PlayerEntity, PlayerSlot<Counter>> entry = iterator.next();
            PlayerEntity player = entry.getKey();
            if (player.isAlive()) {
                if (player.world.isRemote) return;
                PlayerSlot<Counter> slot = entry.getValue();
                if (slot.extra.reset) {
                    slot.extra.count = 0;
                    slot.extra.reset = false;
                } else {
                    slot.extra.count++;
                    // 当 0.5s 未接收到继续倒茶的包 停止动画
                    if (slot.extra.count >= 10) {
                        TeapotPacket.stopToClient(player, slot);
                        iterator.remove();
                    }
                }
            } else {
                iterator.remove();
            }
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        shape = SHAPE.withOffset(x, y, z);
    }

    private static class Counter {
        int count = 0;
        boolean reset = false;
    }
}
