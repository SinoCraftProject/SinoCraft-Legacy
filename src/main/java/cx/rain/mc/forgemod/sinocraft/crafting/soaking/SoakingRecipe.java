package cx.rain.mc.forgemod.sinocraft.crafting.soaking;

import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public final class SoakingRecipe implements ISoakingRecipe, IFinishedRecipe {
    private ItemStack item;
    private ItemStack result;
    private FluidStack fluid_result;
    private ResourceLocation id;

    public SoakingRecipe(ItemStack item, ItemStack result, ResourceLocation id) {
        this.item = item;
        this.result = result;
        this.id = id;
    }

    public SoakingRecipe(ItemStack item, FluidStack fluid_result, ResourceLocation id) {
        this.item = item;
        this.fluid_result = fluid_result;
        this.id = id;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        if (inv.getStackInSlot(1).getItem().getRegistryName().equals(item.getItem().getRegistryName()) && inv.getStackInSlot(1).getCount() >= item.getCount()) {
            return true;
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return result.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public void serialize(JsonObject json) {
        CompoundNBT nbt = new CompoundNBT();
        item.write(nbt);
        json.addProperty("item", nbt.toString());
        nbt = new CompoundNBT();
        if (result != null) {
            result.write(nbt);
            json.addProperty("result", nbt.toString());
        }
        else {
            fluid_result.writeToNBT(nbt);
            json.addProperty("fluid_result", nbt.toString());
        }
    }

    @Override
    public ResourceLocation getID() {
        return getId();
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return new SoakingRecipeSerializer();
    }

    @Nullable
    @Override
    public JsonObject getAdvancementJson() {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getAdvancementID() {
        return null;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public FluidStack getFluidResult() {
        return fluid_result.copy();
    }

    @Override
    public JsonObject getRecipeJson() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("type", "sinocraft:soak");
        this.serialize(jsonobject);
        return jsonobject;
    }
}
