package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.client.gui.IRenderable;

public abstract class TutorialComponent implements IRenderable {
    protected GuiTutorialBook.Page page;

    public TutorialComponent(GuiTutorialBook.Page page) {
        this.page = page;
    }

    public boolean canClick() {
        return false;
    }

    public void onClick() {
    }

    public void onHover() {
    }

    public abstract void fromJson(JsonObject object);
}
