package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TutorialText extends TutorialComponent{
    protected IFormattableTextComponent component;

    public TutorialText(GuiTutorialBook.Page page) {
        super(page);
    }

    @Override
    public void fromJson(JsonObject object) {
        super.fromJson(object);
        component = ITextComponent.Serializer.getComponentFromJson(object.get("text"));
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        stack.push();
        transformer.doTranslate(stack);
        if (component.getStyle().getColor() == null) {
            AbstractGui.drawString(stack, Minecraft.getInstance().fontRenderer, component, x, y, 0x000000);
        }
        else {
            AbstractGui.drawString(stack, Minecraft.getInstance().fontRenderer, component, x, y, component.getStyle().getColor().getColor());
        }
        stack.pop();
    }
}
