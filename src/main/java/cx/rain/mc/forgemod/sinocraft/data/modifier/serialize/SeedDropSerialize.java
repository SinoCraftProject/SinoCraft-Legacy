package cx.rain.mc.forgemod.sinocraft.data.modifier.serialize;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.data.modifier.SeedDropModifier;
import cx.rain.mc.forgemod.sinocraft.utility.CraftingHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

public class SeedDropSerialize extends GlobalLootModifierSerializer<SeedDropModifier> {
    @Override
    public SeedDropModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
        ItemStack stack = CraftingHelper.deserializeItem(object.get("item"));
        return new SeedDropModifier(ailootcondition, stack);
    }

    @Override
    public JsonObject write(SeedDropModifier instance) {
        JsonObject object = makeConditions(instance.getConditions());
        object.add("item", CraftingHelper.serializeItem(instance.stack));
        return object;
    }
}
