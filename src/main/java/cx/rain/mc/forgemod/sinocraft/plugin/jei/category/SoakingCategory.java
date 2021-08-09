package cx.rain.mc.forgemod.sinocraft.plugin.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.utility.RenderHelper;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
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
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class SoakingCategory implements IRecipeCategory<ISoakingRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(SinoCraft.MODID, "jei_soaking");

    private static final ResourceLocation BG = new ResourceLocation(SinoCraft.MODID, "textures/gui/jei/soaking.png");
    private static final ITextComponent TITLE = new TranslationTextComponent("sinocraft.jei.recipe.soaking");

    private final IDrawable background;
    private final IDrawable icon;

    public SoakingCategory(IJeiHelpers helper) {
        background = helper.getGuiHelper().createDrawable(BG, 0, 0, 179, 120);
        icon = helper.getGuiHelper().createDrawableIngredient(new ItemStack(ModBlocks.VAT.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<ISoakingRecipe> getRecipeClass() {
        return ISoakingRecipe.class;
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
    public void setIngredients(ISoakingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, CategoryHelper.getInputItems(recipe.getInputItem()));
        ingredients.setInputs(VanillaTypes.FLUID, CategoryHelper.getInputFluids(recipe.getInputFluid()));
        ItemStack outputItem = recipe.getRecipeOutput();
        if (!outputItem.isEmpty()) ingredients.setOutput(VanillaTypes.ITEM, outputItem);
        FluidStack outputFluid = recipe.getFluidOutput();
        if (!outputFluid.isEmpty()) ingredients.setOutput(VanillaTypes.FLUID, outputFluid);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ISoakingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemGroups = recipeLayout.getItemStacks();
        itemGroups.init(0, true, 32, 19);
        itemGroups.set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        itemGroups.init(1, false, 129, 20);
        itemGroups.set(1, recipe.getRecipeOutput());
        IGuiFluidStackGroup fluidStacks = recipeLayout.getFluidStacks();
        List<FluidStack> fluidInputs = ingredients.getInputs(VanillaTypes.FLUID).get(0);
        if (fluidInputs.size() > 0) {
            fluidStacks.init(0, true, 11, 64, 61, 46, 1000, true, null);
            fluidStacks.set(0, fluidInputs);
        }
        FluidStack fluidOutput = recipe.getFluidOutput();
        if (!fluidOutput.isEmpty()) {
            fluidStacks.init(1, false, 107, 64, 61, 46, 1000, true, null);
            fluidStacks.set(1, fluidOutput);
        }
    }

    @Override
    public void draw(ISoakingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        FontRenderer renderer = Minecraft.getInstance().fontRenderer;
        String time = recipe.getSoakingTime() + " tick";
        RenderHelper.drawText(time, matrixStack, renderer, 79, 53, 22, 0, 0xFF000000, true);
    }
}
