package cx.rain.mc.forgemod.sinocraft.crafting.potcooking;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NmmOC7
 */
public class PotCookingSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PotCookingRecipe> {
    public static Map<ResourceLocation, PotCookingRecipe> recipes = new HashMap<>();

    public static void addRecipes(ResourceLocation id, PotCookingRecipe recipe) {
        recipes.put(id, recipe);
    }

    @Override
    public PotCookingRecipe read(ResourceLocation id, JsonObject json) {
        try {
            if (json.has("input") && json.has("output")) {
                JsonArray inputJson = json.getAsJsonArray("input");
                int inputAmount = Math.min(inputJson.size(), 6);

                ItemStack[] input = new ItemStack[inputAmount];

                for (int i = 0; i < inputAmount; i++) {
                    input[i] = ItemStack.read(
                            new JsonToNBT(
                                    new StringReader(inputJson.get(i).getAsString())
                            ).readStruct());
                }

                ItemStack result = ItemStack.read(
                        new JsonToNBT(
                                new StringReader(
                                        json.getAsJsonPrimitive("output").getAsString()
                                )
                        ).readStruct());

                addRecipes(id, new PotCookingRecipe(input, result, id));
            }
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        return recipes.get(id);
    }

    @Nullable
    @Override
    public PotCookingRecipe read(ResourceLocation id, PacketBuffer buffer) {
        CompoundNBT recipeNBT = buffer.readCompoundTag();
        ListNBT inputNBTList = (ListNBT) recipeNBT.get("input");

        ArrayList<ItemStack> input = new ArrayList<>();

        for (INBT stackNBT: inputNBTList) {
            input.add(ItemStack.read((CompoundNBT) stackNBT));
        }

        CompoundNBT outputNBT = recipeNBT.getCompound("output");

        ItemStack output = ItemStack.read(outputNBT);

        return new PotCookingRecipe(input.toArray(new ItemStack[]{}), output, id);
    }

    @Override
    public void write(PacketBuffer buffer, PotCookingRecipe recipe) {
        CompoundNBT nbt = new CompoundNBT();
        ListNBT inputListNBT = new ListNBT();

        ItemStack[] input = recipe.input;

        for (ItemStack stack: input) {
            CompoundNBT stackNBT = new CompoundNBT();
            stack.write(stackNBT);
            inputListNBT.add(stackNBT);
        }

        nbt.put("input", inputListNBT);

        CompoundNBT outputNBT = new CompoundNBT();
        recipe.output.write(outputNBT);
        nbt.put("output", outputNBT);

        buffer.writeCompoundTag(nbt);
    }
}
