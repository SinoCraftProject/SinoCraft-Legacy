package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TutorialItem extends TutorialComponent{
    public ItemStack item;

    public TutorialItem(GuiTutorialBook.Page page) {
        super(page);
    }

    @Override
    public void fromJson(JsonObject object) {
        super.fromJson(object);
        try {
            item = ItemStack.read(JsonToNBT.getTagFromJson(object.getAsJsonObject("item").toString()));
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        stack.push();
        transformer.doTranslate(stack);
        page.getGui().getMinecraft().getItemRenderer().renderItemAndEffectIntoGUI(item, x, y);
        stack.pop();
    }
}
