package cx.rain.mc.forgemod.sinocraft.plugin.waila;

import cx.rain.mc.forgemod.sinocraft.api.crafting.soaking.ISoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityVat;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.VatFluidHandler;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.VatItemHandler;
import mcp.mobius.waila.api.ICommonAccessor;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;
import java.util.List;

public enum VatProvider implements IWailaProvider {

    INSTANCE;

    public static ResourceLocation RENDERER = new ResourceLocation("sinocraft", "vat_tooltips");

    @Override
    public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity tileEntity) {
        if (!(tileEntity instanceof TileEntityVat)) {
            return;
        }
        TileEntityVat vat = (TileEntityVat) tileEntity;
        ISoakingRecipe recipe = vat.getCurrentRecipe();
        if (recipe != null) {
            data.putString("recipe", recipe.getId().toString());
            data.putInt("process", vat.getProgress());
        }
    }

    @Override
    public void appendTail(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        TileEntity tileEntity = accessor.getTileEntity();
        if (!(tileEntity instanceof TileEntityVat)) {
            return;
        }
        TileEntityVat vat = (TileEntityVat) tileEntity;
        VatItemHandler itemHandler = vat.getItemHandler();
        VatFluidHandler fluidHandler = vat.getFluidHandler();
        ItemStack inputItem = itemHandler.getStackInSlot(1);
        if (!inputItem.isEmpty()) {
            StringTextComponent input = new StringTextComponent(" -> ");
            ProviderHelper.appendItemStack(input, inputItem);
            tooltip.add(input);
        }
        ItemStack outputItem = itemHandler.getStackInSlot(0);
        if (!outputItem.isEmpty()) {
            StringTextComponent output = new StringTextComponent(" -> ");
            ProviderHelper.appendItemStack(output, outputItem);
            tooltip.add(output);
        }
        FluidStack fluidStack = fluidHandler.getFluid();
        if (!fluidStack.isEmpty()) {
            StringTextComponent fluid = new StringTextComponent("Fluid: ");
            ProviderHelper.appendFluidStack(fluid, fluidStack);
            tooltip.add(fluid);
        }
        CompoundNBT serverData = accessor.getServerData();
        if (serverData.contains("recipe", Constants.NBT.TAG_STRING)) {
            ResourceLocation recipeId = new ResourceLocation(serverData.getString("recipe"));
            int process = serverData.getInt("process");
            ISoakingRecipe recipe = null;
            World world = vat.getWorld();
            if (world != null) {
                recipe = (ISoakingRecipe) world.getRecipeManager().getRecipe(recipeId)
                        .filter(r -> r instanceof ISoakingRecipe)
                        .orElse(null);
            }
            if (recipe == null) {
                tooltip.add(new StringTextComponent("Error recipe: " + recipeId));
            } else {
                tooltip.add(new StringTextComponent("Process: " + process + "/" + recipe.getSoakingTime()));
                ItemStack oi = recipe.getRecipeOutput();
                if (!oi.isEmpty()) {
                    StringTextComponent item = new StringTextComponent("Result: ");
                    ProviderHelper.appendItemStack(item, oi);
                    tooltip.add(item);
                }
                FluidStack of = recipe.getFluidOutput();
                if (!of.isEmpty()) {
                    StringTextComponent fluid = new StringTextComponent("Result: ");
                    ProviderHelper.appendFluidStack(fluid, of);
                    tooltip.add(fluid);
                }
            }
        }
    }

    @Override
    public Dimension getSize(CompoundNBT data, ICommonAccessor accessor) {
        return new Dimension(0, 0);
    }

    @Override
    public void draw(CompoundNBT data, ICommonAccessor accessor, int x, int y) {

    }
}
