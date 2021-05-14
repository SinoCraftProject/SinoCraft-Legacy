package cx.rain.mc.forgemod.sinocraft.api.crafting;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;

public interface IModRecipeSerializer<T extends IRecipe<?>> extends IRecipeSerializer<T> {

    void write(JsonObject json, T recipe);
}
