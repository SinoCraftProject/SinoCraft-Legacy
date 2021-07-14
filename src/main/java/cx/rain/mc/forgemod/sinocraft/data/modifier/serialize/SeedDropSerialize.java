package cx.rain.mc.forgemod.sinocraft.data.modifier.serialize;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.data.modifier.GrassDropModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class SeedDropSerialize extends GlobalLootModifierSerializer<GrassDropModifier> {
    @Override
    public GrassDropModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
        ItemStack stack = new ItemStack(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(object.getAsJsonObject("item").getAsJsonPrimitive("id").getAsString())),
                object.getAsJsonObject("item").getAsJsonPrimitive("count").getAsInt());
        return new GrassDropModifier(ailootcondition, stack);
    }

    @Override
    public JsonObject write(GrassDropModifier instance) {
        JsonObject object = makeConditions(instance.getConditions());
        JsonObject stack = new JsonObject();
        stack.addProperty("id", instance.stack.getItem().getRegistryName().toString());
        stack.addProperty("count", instance.stack.getCount());
        object.add("item", stack);
        return object;
    }
}
