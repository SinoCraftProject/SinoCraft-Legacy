package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TutorialEmpty extends TutorialComponent{
    public TutorialEmpty(GuiTutorialBook.Page page) {
        super(page);
    }

    @Override
    public void fromJson(JsonObject object) {
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    }
}
