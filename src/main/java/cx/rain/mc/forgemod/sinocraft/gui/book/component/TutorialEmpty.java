package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;

public class TutorialEmpty extends TutorialComponent{
    public TutorialEmpty(GuiTutorialBook gui) {
        super(gui);
    }

    @Override
    public void fromJson(JsonObject object) {
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    }
}
