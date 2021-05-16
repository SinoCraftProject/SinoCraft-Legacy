package cx.rain.mc.forgemod.sinocraft.utility;

import com.google.gson.*;
import cx.rain.mc.forgemod.sinocraft.api.crafting.CountIngredient;
import cx.rain.mc.forgemod.sinocraft.api.crafting.FluidIngredient;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class CraftingHelper {

    public static JsonElement serializeItem(ItemStack stack) {
        if (stack.isEmpty()) {
            return JsonNull.INSTANCE;
        }
        int count = stack.getCount();
        if (count == 1) {
            return new JsonPrimitive(stack.getItem().delegate.name().toString());
        } else {
            JsonObject object = new JsonObject();
            object.addProperty("item", stack.getItem().delegate.name().toString());
            object.addProperty("count", count);
            return object;
        }
    }

    public static ItemStack deserializeItem(JsonElement json) {
        if (json.isJsonNull()) {
            return ItemStack.EMPTY;
        } else if (json.isJsonPrimitive()) {
            String id = json.getAsJsonPrimitive().getAsString();
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
            if (item == null) {
                throw new JsonSyntaxException("Unknown item '" + id + "'");
            }
            return new ItemStack(item);
        } else if (json.isJsonObject()) {
            return net.minecraftforge.common.crafting.CraftingHelper.getItemStack(json.getAsJsonObject(), true);
        }
        throw new JsonSyntaxException("Json " + json + " is not a string or object");
    }

    public static JsonElement serializeFluid(FluidStack stack) {
        if (stack.isEmpty()) {
            return JsonNull.INSTANCE;
        } else if (stack.getAmount() == 1000) {
            return new JsonPrimitive(stack.getFluid().delegate.name().toString());
        } else {
            JsonObject object = new JsonObject();
            object.addProperty("fluid", stack.getFluid().delegate.name().toString());
            object.addProperty("amount", stack.getAmount());
            return object;
        }
    }

    public static FluidStack deserializeFluid(JsonElement json) {
        if (json.isJsonNull()) {
            return FluidStack.EMPTY;
        } else if (json.isJsonPrimitive()) {
            String id = json.getAsJsonPrimitive().getAsString();
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(id));
            if (fluid == null) {
                throw new JsonSyntaxException("Unknown fluid '" + id + "'");
            }
            return new FluidStack(fluid, 1000);
        } else if (json.isJsonObject()) {
            JsonObject fluidObject = json.getAsJsonObject();
            String id = fluidObject.get("fluid").getAsString();
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(id));
            if (fluid == null) {
                throw new JsonSyntaxException("Unknown fluid '" + id + "'");
            }
            if (fluidObject.has("amount")) {
                return new FluidStack(fluid, fluidObject.get("amount").getAsInt());
            } else {
                return new FluidStack(fluid, 1000);
            }
        }
        throw new JsonSyntaxException("Json " + json + " is not a string or object");
    }

    public static JsonElement serializeIngredient(CountIngredient ingredient) {
        JsonElement ingredientJson = ingredient.getIngredient().serialize();
        int count = ingredient.getCount();
        if (count == 1) {
            return ingredientJson;
        }
        if (ingredientJson.isJsonObject()) {
            ingredientJson.getAsJsonObject().addProperty("count", count);
            return ingredientJson;
        } else {
            JsonObject object = new JsonObject();
            object.add("items", ingredientJson);
            object.addProperty("count", count);
            return object;
        }
    }

    public static CountIngredient deserializeIngredient(JsonElement json) {
        if (json.isJsonObject()) {
            JsonObject object = json.getAsJsonObject();
            Ingredient ingredient = object.has("items")
                    ? Ingredient.deserialize(object.get("items"))
                    : Ingredient.deserialize(json);
            int count = object.has("count") ? object.get("count").getAsInt() : 1;
            return new CountIngredient(ingredient, count);
        } else {
            return new CountIngredient(Ingredient.deserialize(json), 1);
        }
    }

    public static JsonElement serializeFluidIngredient(FluidIngredient ingredient) {
        if (ingredient.getType() == 0) {
            if (ingredient.getAmount() == 1000) {
                return new JsonPrimitive(ingredient.loc.toString());
            } else {
                JsonObject object = new JsonObject();
                object.addProperty("fluid", ingredient.loc.toString());
                object.addProperty("amount", ingredient.getAmount());
                return object;
            }
        } else {
            JsonObject object = new JsonObject();
            object.addProperty("tag", ingredient.loc.toString());
            if (ingredient.getAmount() != 1000) {
                object.addProperty("amount", ingredient.getAmount());
            }
            return object;
        }
    }

    public static FluidIngredient deserializeFluidIngredient(JsonElement json) {
        if (json.isJsonPrimitive()) {
            String id = json.getAsJsonPrimitive().getAsString();
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(id));
            if (fluid == null) {
                throw new JsonSyntaxException("Unknown fluid '" + id + "'");
            }
            return new FluidIngredient(fluid, 1000);
        } else if (json.isJsonObject()) {
            JsonObject fluidObject = json.getAsJsonObject();
            if (fluidObject.has("tag")) {
                ResourceLocation tag = new ResourceLocation(fluidObject.get("tag").getAsString());
                if (fluidObject.has("amount")) {
                    return new FluidIngredient(tag, fluidObject.get("amount").getAsInt());
                } else {
                    return new FluidIngredient(tag, 1000);
                }
            } else if (fluidObject.has("fluid")) {
                String id = fluidObject.get("fluid").getAsString();
                Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(id));
                if (fluid == null) {
                    throw new JsonSyntaxException("Unknown fluid '" + id + "'");
                }
                if (fluidObject.has("amount")) {
                    return new FluidIngredient(fluid, fluidObject.get("amount").getAsInt());
                } else {
                    return new FluidIngredient(fluid, 1000);
                }
            }
        }
        throw new JsonSyntaxException("Json " + json + " is not a string or object");
    }

    public static Ingredient deserializeVanillaIngredient(JsonElement json) {
        if (json.isJsonNull()) {
            return Ingredient.EMPTY;
        } else if (json.isJsonPrimitive()) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(json.getAsString()));
            if (item != null) {
                return Ingredient.fromItems(item);
            } else {
                throw new JsonSyntaxException("Json " + json + " is not a string or object");
            }
        } else {
            return Ingredient.deserialize(json);
        }
    }
}
