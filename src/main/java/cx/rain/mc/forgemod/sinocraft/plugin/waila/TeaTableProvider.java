package cx.rain.mc.forgemod.sinocraft.plugin.waila;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import mcp.mobius.waila.api.ICommonAccessor;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public enum TeaTableProvider implements IWailaProvider {

    INSTANCE;

    public static ResourceLocation RENDERER = new ResourceLocation("sinocraft", "tea_table_tooltips");

    @Override
    public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity tileEntity) {
    }

    @Override
    public void appendTail(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        TileEntity te = accessor.getTileEntity();
        World world = te.getWorld();
        if (te instanceof TileEntityTeaTable && world != null && world.isRemote) {
            TileEntityTeaTable table = (TileEntityTeaTable) te;
            RayTraceResult hitResult = accessor.getHitResult();
            Vector3d hitVec = hitResult.getHitVec();
            BlockPos pos = table.getPos();
            double x = hitVec.x - pos.getX();
            double y = hitVec.y - pos.getY();
            double z = hitVec.z - pos.getZ();
            Optional<BaseTableElement> lookup = table.lookup(x, y, z);
            if (lookup.isPresent()) {
                ItemStack stack = lookup.get().getStack();
                appendTailOnClient(stack, accessor.getWorld(), tooltip);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void appendTailOnClient(ItemStack stack, World world, List<ITextComponent> tooltip) {
        stack.getItem().addInformation(stack, world, tooltip, net.minecraft.client.util.ITooltipFlag.TooltipFlags.NORMAL);
    }

    @Override
    public ItemStack getStack(IDataAccessor accessor, IPluginConfig config) {
        TileEntity te = accessor.getTileEntity();
        if (te instanceof TileEntityTeaTable) {
            TileEntityTeaTable table = (TileEntityTeaTable) te;
            RayTraceResult hitResult = accessor.getHitResult();
            Vector3d hitVec = hitResult.getHitVec();
            BlockPos pos = table.getPos();
            return table.lookup(hitVec.x - pos.getX(), hitVec.y - pos.getY(), hitVec.z - pos.getZ())
                    .map(BaseTableElement::getStack)
                    .orElse(ItemStack.EMPTY);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public Dimension getSize(CompoundNBT data, ICommonAccessor accessor) {
        return new Dimension(0, 0);
    }

    @Override
    public void draw(CompoundNBT data, ICommonAccessor accessor, int x, int y) {
    }
}
