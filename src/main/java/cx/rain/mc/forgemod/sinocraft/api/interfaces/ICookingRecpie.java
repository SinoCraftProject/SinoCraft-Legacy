package cx.rain.mc.forgemod.sinocraft.api.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Recipe interface
 * The advanced recipe must implements it
 * @author Infinity_rain
 */
public interface ICookingRecpie extends IForgeRegistryEntry<ICookingRecpie> {
    /**
     * Judge item set is meeting the rules of this recipe
     * @param items Item set
     * @param tool Cooking tool
     * @param player player
     * @param worldIn the world of player in
     * @param pos the pos
     * @return Is item set meeting the rules of this recipe
     */
    boolean matches(@Nonnull NonNullList<ItemStack> items, ICookingTool tool, PlayerEntity player, World worldIn, BlockPos pos);

    /**
     * Get the result of this recipe
     * @param items Item set
     * @param tool Cooking tool
     * @param player player
     * @param worldIn the world of player in
     * @param pos the pos
     * @return the result of this recipe
     */
    ItemStack getCookingResult(@Nonnull NonNullList<ItemStack> items, ICookingTool tool, PlayerEntity player, World worldIn, BlockPos pos);

    /**
     * Get remaining items after cooked
     * @param items Item set
     * @return Remaining items
     */
    default NonNullList<ItemStack> getRemainingItems(@Nonnull NonNullList<ItemStack> items){
        return NonNullList.create();
    }
}
