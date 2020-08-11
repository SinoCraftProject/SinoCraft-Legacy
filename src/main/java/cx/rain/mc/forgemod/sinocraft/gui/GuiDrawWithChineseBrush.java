package cx.rain.mc.forgemod.sinocraft.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class GuiDrawWithChineseBrush extends Screen {
    protected GuiDrawWithChineseBrush(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {}

    @Override
    public void render(int mouseX, int mouseY, float particleTick) {
        this.renderBackground();
        //this.drawString(this.font,"GUI", this.width / 2 - 10, 30, 0xffffff);
        //RenderSystem.color3f(1.0f, 1.0f, 1.0f);
        super.render(mouseX, mouseY, particleTick);
    }

    public static GuiDrawWithChineseBrush create(ItemStack stack) {
        return new GuiDrawWithChineseBrush(stack.getDisplayName());
    }
}