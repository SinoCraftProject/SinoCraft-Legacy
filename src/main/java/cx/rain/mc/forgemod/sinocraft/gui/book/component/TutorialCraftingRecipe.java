package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import cx.rain.mc.forgemod.sinocraft.network.Networks;
import cx.rain.mc.forgemod.sinocraft.network.packet.GetRecipeC2SPacket;
import cx.rain.mc.forgemod.sinocraft.network.packet.GetRecipeS2CPacket;
import net.minecraft.client.gui.recipebook.GhostRecipe;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RepairItemRecipe;
import net.minecraft.util.ResourceLocation;

public class TutorialCraftingRecipe extends TutorialComponent{
    public IRecipe<?> recipe;
    public int x;
    public int y;

    public TutorialCraftingRecipe(GuiTutorialBook.Page page) {
        super(page);
    }

    @Override
    public void fromJson(JsonObject json) {
        super.fromJson(json);
        this.recipe = new RepairItemRecipe(new ResourceLocation("sinocraft:_checker_recipe"));
        GetRecipeS2CPacket.setCallback((recipe) -> {
            this.recipe = recipe;
        });
        Networks.INSTANCE.sendToServer(new GetRecipeC2SPacket(
                new GetRecipeC2SPacket.Pack(
                        new ResourceLocation(json.getAsJsonPrimitive("recipe").getAsString()))
                )
        );
        x = json.getAsJsonPrimitive("x").getAsInt();
        y = json.getAsJsonPrimitive("y").getAsInt();
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        if (recipe.getId().toString().equals("sinocraft:_checker_recipe"))
            return;
        stack.push();
        transformer.doTranslate(stack);
        if (recipe.getType() != IRecipeType.CRAFTING)
            throw new IllegalStateException("Except Get Crafting Recipe");
        GhostRecipe gr = new GhostRecipe();
        gr.setRecipe(recipe);
        gr.func_238922_a_(stack, page.getGui().getMinecraft(), x, y, true, partialTicks);
        stack.pop();
    }
}
