package cx.rain.mc.forgemod.sinocraft.plugin.waila;

import cx.rain.mc.forgemod.sinocraft.api.crafting.ICookingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.capability.IHeat;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityPot;
import cx.rain.mc.forgemod.sinocraft.capability.ModCapabilities;
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

import java.awt.*;
import java.util.List;

public enum PotProvider implements IWailaProvider {

    INSTANCE;

    public static final ResourceLocation RENDERER = new ResourceLocation("sinocraft", "pot_tooltips");

    @Override
    public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityPot) {
            TileEntityPot pot = (TileEntityPot) tileEntity;
            IHeat heat = ModCapabilities.getHeat(pot);
            data.putInt("heat", heat.getHeat());
            ICookingRecipe recipe = pot.getRecipe();
            if (recipe != null) {
                data.putInt("maxHeat", heat.getMaxHeat());
                data.putInt("process", pot.getProgress());
                data.putString("recipe", recipe.getId().toString());
            }
        }
    }

    @Override
    public void appendTail(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        if (!config.get(WailaSupport.CONFIG_ENABLED, true)) {
            return;
        }

        TileEntity tileEntity = accessor.getTileEntity();
        if (!(tileEntity instanceof TileEntityPot)) {
            return;
        }

        TileEntityPot pot = (TileEntityPot) tileEntity;
        for (ItemStack item : pot.getInput()) {
            if (!item.isEmpty()) {
                StringTextComponent input = new StringTextComponent(" -> ");
                ProviderHelper.appendItemStack(input, item);
                tooltip.add(input);
            }
        }
        ItemStack outputItem = pot.getOutput();
        if (!outputItem.isEmpty()) {
            StringTextComponent output = new StringTextComponent(" <- ");
            ProviderHelper.appendItemStack(output, outputItem);
            tooltip.add(output);
        }
        CompoundNBT serverData = accessor.getServerData();
        int heat = serverData.getInt("heat");
        if (serverData.contains("recipe", Constants.NBT.TAG_STRING)) {
            int maxHeat = serverData.getInt("maxHeat");
            int process = serverData.getInt("process");
            ResourceLocation recipeId = new ResourceLocation(serverData.getString("recipe"));
            ICookingRecipe recipe = null;
            World world = pot.getWorld();
            if (world != null) {
                recipe = (ICookingRecipe) world.getRecipeManager().getRecipe(recipeId)
                        .filter(r -> r instanceof ICookingRecipe)
                        .orElse(null);
            }
            tooltip.add(new StringTextComponent("Heat: " + heat + "/" + maxHeat));
            if (recipe == null) {
                tooltip.add(new StringTextComponent("Error recipe: " + recipeId));
            } else {
                tooltip.add(new StringTextComponent("Process: " + process + "/" + recipe.getCookingTime()));
                ItemStack stack = maxHeat <= recipe.getMaxHeat() ? recipe.getRecipeOutput() : recipe.getAdustOutput();
                if (!stack.isEmpty()) {
                    StringTextComponent result = new StringTextComponent("Result: ");
                    ProviderHelper.appendItemStack(result, stack);
                    tooltip.add(result);
                }
            }
        } else {
            tooltip.add(new StringTextComponent("Heat: " + heat));
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
