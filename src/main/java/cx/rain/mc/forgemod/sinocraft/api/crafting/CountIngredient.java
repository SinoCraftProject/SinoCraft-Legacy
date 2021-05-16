package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;

public class CountIngredient {

    private final Ingredient ingredient;
    private final int count;

    public CountIngredient(Ingredient ingredient, int count) {
        this.ingredient = ingredient;
        this.count = count;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getCount() {
        return count;
    }

    public boolean match(ItemStack stack) {
        return stack.getCount() >= count && ingredient.test(stack);
    }

    public void write(PacketBuffer buffer) {
        ingredient.write(buffer);
        buffer.writeVarInt(count);
    }

    public static CountIngredient read(PacketBuffer buffer) {
        Ingredient ingredient = Ingredient.read(buffer);
        return new CountIngredient(ingredient, buffer.readVarInt());
    }
}
