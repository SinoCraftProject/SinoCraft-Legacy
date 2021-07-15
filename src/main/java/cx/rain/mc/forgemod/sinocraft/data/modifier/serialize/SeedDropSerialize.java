package cx.rain.mc.forgemod.sinocraft.data.modifier.serialize;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.data.modifier.GrassDropModifier;
import cx.rain.mc.forgemod.sinocraft.utility.CraftingHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

public class SeedDropSerialize extends GlobalLootModifierSerializer<GrassDropModifier> {
    @Override
    public GrassDropModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
        ItemStack stack = CraftingHelper.deserializeItem(object.get("item"));
        return new GrassDropModifier(ailootcondition, stack);
    }

    @Override
    public JsonObject write(GrassDropModifier instance) {
        JsonObject object = makeConditions(instance.getConditions());
        object.add("item", CraftingHelper.serializeItem(instance.stack));
        return object;
    }
}
