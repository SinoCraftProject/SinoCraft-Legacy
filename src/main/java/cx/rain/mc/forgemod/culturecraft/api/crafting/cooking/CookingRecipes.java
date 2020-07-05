package cx.rain.mc.forgemod.culturecraft.crafting.cooking;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.ICookingRecpie;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.ICookingTool;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.IThermal;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * Normal recipe
 * You only need to create such an instance for normal recipe
 * {@link IThermal}
 *
 * @author Infinity_rain
 */
public class CookingRecipes extends ForgeRegistryEntry<ICookingRecpie> implements ICookingRecpie {
    private int toolLevel;
    private String toolType;
    private int minThermal;
    private int maxThermal;
    private NonNullList<ItemStack> stacks;
    private ItemStack recipeResult;

    /**
     * @param registryName registry name
     * @param toolLevel    the lowest level of cooking tool
     * @param toolType     the type of tool
     * @param minThermal   the min thermal of recipe
     * @param maxThermal   the max thermal of recipe
     * @param recipeResult the result of recipe
     * @param stacks       item set
     */
    public CookingRecipes(String registryName, int toolLevel, String toolType, int minThermal, int maxThermal, ItemStack recipeResult, ItemStack... stacks) {
        this.setRegistryName(registryName);
        this.toolLevel = toolLevel;
        this.toolType = toolType;
        this.minThermal = minThermal;
        this.maxThermal = maxThermal;
        this.recipeResult = recipeResult;
        this.stacks = NonNullList.create();
        for (ItemStack stack : stacks) {
            this.stacks.add(stack);
        }
    }

    @Override
    public boolean matches(NonNullList<ItemStack> items, ICookingTool tool, PlayerEntity player, World worldIn, BlockPos pos) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof IThermal) {
            int t = ((IThermal) te).getThermal();
            if (t < minThermal) {
                return false;
            } else if (items.equals(stacks)) {
                if (tool.getCookingToolType() == toolType && tool.getCookingToolLevel() >= toolLevel) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public ItemStack getCookingResult(NonNullList<ItemStack> items, ICookingTool tool, PlayerEntity player, World worldIn, BlockPos pos) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof IThermal) {
            int t = ((IThermal) te).getThermal();
            if (t > maxThermal) {
                return new ItemStack(RegistryItem.ITEMS.get("charred_food"), 1);
            } else {
                return recipeResult;
            }
        }
        return ItemStack.EMPTY;
    }
}
