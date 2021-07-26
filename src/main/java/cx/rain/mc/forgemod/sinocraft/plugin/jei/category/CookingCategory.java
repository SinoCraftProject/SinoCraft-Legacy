package cx.rain.mc.forgemod.sinocraft.plugin.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ICookingRecipe;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.utility.RenderHelper;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class CookingCategory implements IRecipeCategory<ICookingRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(SinoCraft.MODID, "jei_cooking");

    private static final ResourceLocation BG = new ResourceLocation(SinoCraft.MODID, "textures/gui/jei/cooking.png");
    private static final ITextComponent TITLE = new TranslationTextComponent("sinocraft.jei.recipe.cooking");

    private final IDrawable background;
    private final IDrawable icon;

    public CookingCategory(IJeiHelpers helper) {
        background = helper.getGuiHelper().createDrawable(BG, 0, 0, 179, 120);
        icon = helper.getGuiHelper().createDrawableIngredient(new ItemStack(ModBlocks.POT.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<ICookingRecipe> getRecipeClass() {
        return ICookingRecipe.class;
    }

    @Override
    public String getTitle() {
        return getTitleAsTextComponent().getString();
    }

    @Override
    public ITextComponent getTitleAsTextComponent() {
        return TITLE;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(ICookingRecipe recipe, IIngredients ingredients) {
        List<List<ItemStack>> inputs = new ArrayList<>();
        for (int i = 0; i < recipe.getInputSlotCount(); i++) {
            List<ItemStack> stacks = CategoryHelper.getInputItems(recipe.getInput(i));
            inputs.add(stacks);
        }
        ingredients.setInputLists(VanillaTypes.ITEM, inputs);
        ItemStack output = recipe.getRecipeOutput();
        ItemStack adust = recipe.getAdustOutput();
        List<ItemStack> result = new ArrayList<>();
        if (!output.isEmpty()) result.add(output);
        if (!adust.isEmpty()) result.add(adust);
        ingredients.setOutputs(VanillaTypes.ITEM, result);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ICookingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemGroups = recipeLayout.getItemStacks();
        int slot = recipe.getInputSlotCount();
        for (int i = 0; i < slot; i++) {
            itemGroups.init(i, true, i < 3 ? 11 : 38, 21 + 28 * (i % 3));
            itemGroups.set(i, ingredients.getInputs(VanillaTypes.ITEM).get(i));
        }
        itemGroups.init(slot, false, 90, 66);
        itemGroups.set(slot, recipe.getRecipeOutput());
        itemGroups.init(slot + 1, false, 90, 28);
        itemGroups.set(slot + 1, recipe.getAdustOutput());
    }

    @Override
    public void draw(ICookingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        FontRenderer renderer = Minecraft.getInstance().fontRenderer;
        String time = recipe.getCookingTime() + " tick";
        RenderHelper.drawText(time, matrixStack, renderer, 61, 70, 22, 0, 0xFF000000, false);
        RenderHelper.drawText(recipe.getMaxHeat() + " °", matrixStack, renderer, 138, 41, 30, 7, 0xFFA30101, true);
        RenderHelper.drawText(recipe.getMinHeat() + " °", matrixStack, renderer, 138, 82, 30, 7, 0xFFFF0000, true);
    }
}
