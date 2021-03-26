package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//TODO
@OnlyIn(Dist.CLIENT)
public class TutorialButton extends TutorialComponent{
    private static class Image {
        public ResourceLocation image;
        public int u = 0;
        public int v = 0;
        public int width;
        public int height;
        public int image_width;
        public int image_height;
    }

    private Method onClick;
    private Image general = new Image();
    private Image onHover = new Image();
    private boolean hovered;
    private int width;
    private int height;

    public TutorialButton(GuiTutorialBook.Page page) {
        super(page);
    }

    private void loadImage(JsonObject object, Image image) {
        image.image = new ResourceLocation(object.getAsJsonPrimitive("path").getAsString() + ".png");
        image.image_width = object.getAsJsonPrimitive("width").getAsInt();
        image.image_height = object.getAsJsonPrimitive("height").getAsInt();
        image.width = image.image_width;
        image.height = image.image_height;
        if (object.has("u"))
            image.u = object.getAsJsonPrimitive("u").getAsInt();
        if (object.has("v"))
            image.v = object.getAsJsonPrimitive("v").getAsInt();
        if (object.has("width"))
            image.width = object.getAsJsonPrimitive("width").getAsInt();
        if (object.has("height"))
            image.height = object.getAsJsonPrimitive("height").getAsInt();
    }

    @Override
    public void fromJson(JsonObject object) {
        super.fromJson(object);
        loadImage(object.getAsJsonObject("general"), general);
        loadImage(object.getAsJsonObject("on_hover"), onHover);
        try {
            String clazz = object.getAsJsonPrimitive("classIn").getAsString();
            String method = object.getAsJsonPrimitive("onClick").getAsString();
            onClick = Class.forName(clazz).getDeclaredMethod(method, int.class, int.class, TutorialButton.class, GuiTutorialBook.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        width = object.getAsJsonPrimitive("width").getAsInt();
        height = object.getAsJsonPrimitive("height").getAsInt();
    }

    @Override
    public boolean canClick() {
        return true;
    }

    @Override
    public void onClick(int mouseX, int mouseY, int key) {
        try {
            onClick.invoke(null, mouseX, mouseY, this, this.page.getGui());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX < x + width && mouseY >= y - height && mouseY < y;
    }

    @Override
    public void onHover(int mouseX, int mouseY) {
        hovered = true;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        stack.push();
        transformer.doTranslate(stack);
        if (hovered) {
            page.getGui().getMinecraft().textureManager.bindTexture(general.image);
            AbstractGui.blit(stack, x, y, page.getGui().getBlitOffset(), general.u, general.v, general.width, general.height, general.image_width, general.image_height);
        }
        else {
            page.getGui().getMinecraft().textureManager.bindTexture(onHover.image);
            AbstractGui.blit(stack, x, y, page.getGui().getBlitOffset(), onHover.u, onHover.v, onHover.width, onHover.height, onHover.image_width, onHover.image_height);
        }
        hovered = false;
        stack.pop();
    }
}
