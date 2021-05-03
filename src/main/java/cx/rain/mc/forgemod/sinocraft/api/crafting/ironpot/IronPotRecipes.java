package cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityPot;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * @author NmmOC7
 */
public class IronPotRecipes implements IRecipe<TileEntityPot>, IFinishedRecipe {
    public ResourceLocation id;
    public ItemStack[] input;
    public ItemStack output;

    public IronPotRecipes(ItemStack[] input, ItemStack output, ResourceLocation id) {
        this.id = id;
        this.input = input;
        this.output = output;

        ModIronPotRecipes.IRON_POT_RECIPES.add(this);
    }

    @Override
    public boolean matches(TileEntityPot inv, World worldIn) {
        boolean result = true;

        for (ItemStack stack: this.input) {
            if (!inv.hasItemStack(stack)) {
                result = false;
                break;
            }
        }

        return result;
    }

    @Override
    public ItemStack getCraftingResult(TileEntityPot inv) {
        return this.output.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public ResourceLocation getID() {
        return this.id;
    }

    @Override
    public void serialize(JsonObject json) {
        ListNBT inputNBT = new ListNBT();

        for (ItemStack stack: this.input) {
            CompoundNBT stackNBT = new CompoundNBT();
            stack.write(stackNBT);
            inputNBT.add(stackNBT);
        }

        json.addProperty("input", inputNBT.toString());

        CompoundNBT outputNBT = new CompoundNBT();
        this.output.write(outputNBT);
        json.addProperty("output", outputNBT.toString());
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return new IronPotSerializer();
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
    public IRecipeType<?> getType() {
        return IRecipeType.register(SinoCraft.MODID + "iron_pot");
    }
}
