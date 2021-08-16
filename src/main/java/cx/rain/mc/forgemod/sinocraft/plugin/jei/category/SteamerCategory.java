package cx.rain.mc.forgemod.sinocraft.plugin.jei.category;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISteamerRecipe;
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

public class SteamerCategory implements IRecipeCategory<ISteamerRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(SinoCraft.MODID, "jei_steamer");

    private static final ResourceLocation BG = new ResourceLocation(SinoCraft.MODID, "textures/gui/jei/steamer.png");
    private static final ITextComponent TITLE = new TranslationTextComponent("sinocraft.jei.recipe.steamer");

    private final IDrawable background;
    private final IDrawable icon;

    public SteamerCategory(IJeiHelpers helper) {
        background = helper.getGuiHelper().createDrawable(BG, 0, 0, 179, 120);
        // todo 蒸笼物品对应图标
        icon = helper.getGuiHelper().createDrawableIngredient(new ItemStack(ModBlocks.STOVE.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<ISteamerRecipe> getRecipeClass() {
        return ISteamerRecipe.class;
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
    public void setIngredients(ISteamerRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutputs(VanillaTypes.ITEM, Lists.newArrayList(recipe.getRecipeOutput(), recipe.getAdustOutput()));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ISteamerRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemGroups = recipeLayout.getItemStacks();
        itemGroups.init(0, true, 23, 28);
        itemGroups.set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        itemGroups.init(1, false, 90, 66);
        itemGroups.set(1, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
        itemGroups.init(2, false, 90, 28);
        itemGroups.set(2, ingredients.getOutputs(VanillaTypes.ITEM).get(1));
    }

    @Override
    public void draw(ISteamerRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        FontRenderer renderer = Minecraft.getInstance().fontRenderer;
        String time = recipe.getCookingTime() + " tick";
        RenderHelper.drawText(time, matrixStack, renderer, 61, 70, 22, 0, 0xFF000000, true);
        RenderHelper.drawText(recipe.getMaxHeat() + " °", matrixStack, renderer, 138, 41, 30, 7, 0xFFA30101, true);
        RenderHelper.drawText(recipe.getMinHeat() + " °", matrixStack, renderer, 138, 82, 30, 7, 0xFFFF0000, true);
    }
}
