package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.client.gui.IRenderable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class TutorialComponent implements IRenderable {
    protected GuiTutorialBook.Page page;
    protected ComponentHelper.Transformer transformer;
    public int x;
    public int y;

    public TutorialComponent(GuiTutorialBook.Page page) {
        this.page = page;
    }

    public boolean canClick() {
        return false;
    }

    public void onClick(int mouseX, int mouseY, int key) {
    }

    public void onHover(int mouseX, int mouseY) {
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return false;
    }

    public void fromJson(JsonObject object) {
        transformer = new ComponentHelper.Transformer(object.getAsJsonObject("transform"));
        x = object.getAsJsonPrimitive("x").getAsInt();
        y = object.getAsJsonPrimitive("y").getAsInt();
    }
}
