package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TutorialImage extends TutorialComponent{
    public ResourceLocation image;
    public int u = 0;
    public int v = 0;
    public int width;
    public int height;
    public int image_width;
    public int image_height;

    public TutorialImage(GuiTutorialBook.Page page) {
        super(page);
    }

    private void loadImage(JsonObject object) {
        image = new ResourceLocation(object.getAsJsonPrimitive("path").getAsString() + ".png");
        image_width = object.getAsJsonPrimitive("width").getAsInt();
        image_height = object.getAsJsonPrimitive("height").getAsInt();
    }

    @Override
    public void fromJson(JsonObject object) {
        super.fromJson(object);
        loadImage(object.getAsJsonObject("image"));
        width = image_width;
        height = image_height;
        if (object.has("u"))
            u = object.getAsJsonPrimitive("u").getAsInt();
        if (object.has("v"))
            v = object.getAsJsonPrimitive("v").getAsInt();
        if (object.has("width"))
            width = object.getAsJsonPrimitive("width").getAsInt();
        if (object.has("height"))
            height = object.getAsJsonPrimitive("height").getAsInt();
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
