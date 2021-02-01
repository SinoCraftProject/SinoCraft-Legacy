package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;

public class TutorialEmpty extends GuiTutorialBook.TutorialComponent {
    public TutorialEmpty(GuiTutorialBook.Page page) {
        super(page);
    }

    @Override
    public void fromJson(JsonObject j_root) {
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    }
}
