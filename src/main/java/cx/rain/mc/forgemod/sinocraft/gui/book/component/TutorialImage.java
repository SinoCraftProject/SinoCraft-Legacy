package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class TutorialImage extends TutorialComponent{
    public ResourceLocation image;
    public int u = 0;
    public int v = 0;
    public int width;
    public int height;
    public int image_width;
    public int image_height;
    public int x;
    public int y;

    public TutorialImage(GuiTutorialBook.Page page) {
        super(page);
    }

    private void loadImage(JsonObject json) {
        image = new ResourceLocation(json.getAsJsonPrimitive("path").getAsString() + ".png");
        image_width = json.getAsJsonPrimitive("width").getAsInt();
        image_height = json.getAsJsonPrimitive("height").getAsInt();
    }

    @Override
    public void fromJson(JsonObject json) {
        super.fromJson(json);
        loadImage(json.getAsJsonObject("image"));
        width = image_width;
        height = image_height;
        if (json.has("u"))
            u = json.getAsJsonPrimitive("u").getAsInt();
        if (json.has("v"))
            v = json.getAsJsonPrimitive("v").getAsInt();
        if (json.has("width"))
            width = json.getAsJsonPrimitive("width").getAsInt();
        if (json.has("height"))
            height = json.getAsJsonPrimitive("height").getAsInt();
        x = json.getAsJsonPrimitive("x").getAsInt();
        y = json.getAsJsonPrimitive("y").getAsInt();
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        stack.push();
        transformer.doTranslate(stack);
        page.getGui().getMinecraft().textureManager.bindTexture(image);
        AbstractGui.blit(stack, x, y, page.getGui().getBlitOffset(), u, v, width, height, image_width, image_height);
        stack.pop();
    }
}
