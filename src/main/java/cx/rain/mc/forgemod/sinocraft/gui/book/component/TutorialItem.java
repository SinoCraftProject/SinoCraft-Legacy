package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;

public class TutorialItem extends TutorialComponent{
    public ItemStack item;
    public int x;
    public int y;

    public TutorialItem(GuiTutorialBook.Page page) {
        super(page);
    }

    @Override
    public void fromJson(JsonObject json) {
        super.fromJson(json);
        try {
            item = ItemStack.read(JsonToNBT.getTagFromJson(json.getAsJsonObject("item").toString()));
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        x = json.getAsJsonPrimitive("x").getAsInt();
        y = json.getAsJsonPrimitive("y").getAsInt();
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        stack.push();
        transformer.doTranslate(stack);
        page.getGui().getMinecraft().getItemRenderer().renderItemAndEffectIntoGUI(item, x, y);
        stack.pop();
    }
}
