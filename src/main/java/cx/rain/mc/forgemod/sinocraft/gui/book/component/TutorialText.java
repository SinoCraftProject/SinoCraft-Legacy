package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;

public class TutorialText extends TutorialComponent{
    protected IFormattableTextComponent component;
    protected int x;
    protected int y;

    public TutorialText(GuiTutorialBook gui) {
        super(gui);
    }

    @Override
    public void fromJson(JsonObject json) {
        component = ITextComponent.Serializer.getComponentFromJson(json.get("text"));
        x = json.getAsJsonPrimitive("x").getAsInt();
        y = json.getAsJsonPrimitive("y").getAsInt();
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        AbstractGui.drawString(stack, gui.getMinecraft().fontRenderer, component, x, y, component.getStyle().getColor().getColor());
    }
}
