package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import cx.rain.mc.forgemod.sinocraft.network.Networks;
import cx.rain.mc.forgemod.sinocraft.network.packet.GetRecipeC2SPacket;
import cx.rain.mc.forgemod.sinocraft.network.packet.GetRecipeS2CPacket;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//TODO
@OnlyIn(Dist.CLIENT)
public class TutorialCraftingRecipe extends TutorialComponent {
    private static ResourceLocation BACKGROUND = new ResourceLocation("sinocraft:textures/gui/book/craft_back.png");
    public RecipeMatcher recipe;
    public ResourceLocation recipe_id;
    public float time;

    public TutorialCraftingRecipe(GuiTutorialBook.Page page) {
        super(page);
    }

    @Override
    public void fromJson(JsonObject json) {
        super.fromJson(json);
        recipe_id = new ResourceLocation(json.getAsJsonPrimitive("recipe").getAsString());
        this.recipe = null;
    }

    @OnlyIn(Dist.CLIENT)
    public static class RecipeMatcher {
        private List<List<ItemStack>> items = new ArrayList();
        public IRecipe<?> recipe;

        public RecipeMatcher(IRecipe<?> recipe) {
            this.recipe = recipe;
            items.add(Collections.singletonList(recipe.getRecipeOutput()));
            NonNullList<Ingredient> ingredients = recipe.getIngredients();
            for (Ingredient ingredient : ingredients) {
                if (ingredient.hasNoMatchingItems()) {
                    items.add(Collections.singletonList(ItemStack.EMPTY));
                }
                else {
                    items.add(Arrays.asList(ingredient.getMatchingStacks()));
                }
            }
        }

        public ItemStack getMatchItem(int index, float time) {
            return items.get(index).get((int)Math.floor(time) % items.get(index).size());
        }

        public List<ItemStack> getMatchItems(float time) {
            List<ItemStack> result = new ArrayList<>();
            for (List<ItemStack> item : items) {
                result.add(item.get((int)Math.floor(time / 10) % item.size()));
            }
            return result;
        }
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        time += partialTicks;
        if (recipe == null)
            return;
        if (recipe.recipe.getType() != IRecipeType.CRAFTING)
            throw new IllegalStateException("Except Get Crafting Recipe");
        stack.push();
        transformer.doTranslate(stack);
        this.page.getGui().getMinecraft().textureManager.bindTexture(BACKGROUND);
        AbstractGui.blit(stack, x - 2, y - 2, 0, 0, 93, 56, 256, 256);
        RenderSystem.pushMatrix();
        RenderSystem.multMatrix(stack.getLast().getMatrix());
        this.page.getGui().getMinecraft().getItemRenderer().renderItemAndEffectIntoGUI(recipe.getMatchItem(0, time), 75 + x,  18 + y);
        int pos = -1;
        for (ItemStack itemStack : recipe.getMatchItems(time)) {
            pos ++;
            if (pos == 0) {
                continue;
            }
            this.page.getGui().getMinecraft().getItemRenderer().renderItemAndEffectIntoGUI(itemStack, ((pos - 1) % 3) * 18 + x, ((pos - 1) / 3) * 18 + y);
        }
        RenderSystem.popMatrix();
        stack.pop();
    }

    @Override
    public void init() {
        GetRecipeS2CPacket.setCallback((recipe) -> this.recipe = new RecipeMatcher(recipe));
        Networks.INSTANCE.sendToServer(new GetRecipeC2SPacket(new GetRecipeC2SPacket.Pack(recipe_id)));
    }
}
