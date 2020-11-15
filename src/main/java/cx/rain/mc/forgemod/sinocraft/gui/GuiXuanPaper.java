package cx.rain.mc.forgemod.sinocraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class GuiXuanPaper extends Screen {
    private static final ResourceLocation GUI = new ResourceLocation(SinoCraft.MODID, "textures/gui/chinese_brush.png");
    byte[] pixels;
    int guiLeft;
    int guiTop;

    protected GuiXuanPaper(ITextComponent title, byte[] pixelsIn) {
        super(title);
        pixels = pixelsIn;
    }

    @Override
    protected void init() {
        this.guiLeft = (this.width - 212) / 2;
        this.guiTop = (this.height - 236) / 2;
        super.init();
    }

    protected void draw() {
        this.minecraft.getTextureManager().bindTexture(GUI);

        int sx = 61, sy = 14, unit = 4;
        for (int i = 0 ; i < 32 ; i ++) {
            for (int j = 0 ; j < 32 ; j ++) {
                float color = 0.0625f * (16 - pixels[i * 32 + j]);
                if (color == 0.0625f) {
                    color = 0.0f;
                }
                RenderSystem.color3f(color, color, color);
                blit(sx + i * unit, sy + j * unit, 0, 22, 236, unit, unit, 256, 256);
            }
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)guiLeft, (float)guiTop, 0.0F);
        draw();
        RenderSystem.popMatrix();
        super.render(mouseX, mouseY, partialTicks);
    }

    public static GuiXuanPaper create(byte[] pixels) {
        if (pixels.length == 0) {
            pixels = null;
        }
        return new GuiXuanPaper(new StringTextComponent("null"), pixels != null ? pixels : new byte[32 * 32]);
    }
}
