package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButtonUtils {
    public static void testClick(TutorialButton button, GuiTutorialBook gui) {
        SinoCraft.getLogger().info(button.args.getAsJsonPrimitive("msg").getAsString());
    }

    public static void jmpPage(TutorialButton button, GuiTutorialBook gui) {
        gui.jmpPage(button.args.getAsJsonPrimitive("page").getAsInt() - 1);
    }

    public static void loadNewBook(TutorialButton button, GuiTutorialBook gui) {
        gui.loadBook(new ResourceLocation(button.args.getAsJsonPrimitive("book").getAsString()));
    }
}
