package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;

public class TutorialText extends GuiTutorialBook.TutorialComponent {
    protected IFormattableTextComponent component;
    protected int x;
    protected int y;

    public TutorialText(GuiTutorialBook.Page page) {
        super(page);
    }

    @Override
    public void fromJson(JsonObject j_root) {
        component = ITextComponent.Serializer.getComponentFromJson(j_root.get("text"));
        x = j_root.getAsJsonPrimitive("x").getAsInt();
        y = j_root.getAsJsonPrimitive("y").getAsInt();
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        AbstractGui.drawString(stack, page.getGUI().getMinecraft().fontRenderer, component, x, y, component.getStyle().getColor().getColor());
    }
}
