package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//TODO
@OnlyIn(Dist.CLIENT)
public class TutorialButton extends TutorialComponent {
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
    private int width;
    private int height;
    private Button button;
    public JsonObject args;

    public TutorialButton(GuiTutorialBook.Page page) {
        super(page);
    }

    private void loadImage(JsonObject object, Image image) {
        image.image = new ResourceLocation(object.getAsJsonPrimitive("image").getAsString() + ".png");
        image.image_width = object.getAsJsonPrimitive("image_width").getAsInt();
        image.image_height = object.getAsJsonPrimitive("image_height").getAsInt();
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
            String clazz = object.getAsJsonPrimitive("class_in").getAsString();
            String method = object.getAsJsonPrimitive("on_click").getAsString();
            onClick = Class.forName(clazz).getDeclaredMethod(method, TutorialButton.class, GuiTutorialBook.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        args = object.getAsJsonObject("args");
        width = object.getAsJsonPrimitive("width").getAsInt();
        height = object.getAsJsonPrimitive("height").getAsInt();
    }

    @Override
    public void init() {
        button = new Button(x, y, width, height, new StringTextComponent(""), button -> {
            try {
                onClick.invoke(null, this, this.page.getGui());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }) {
            @Override
            public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            }
        };
        this.page.addTutorialButton(button);
        super.init();
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        stack.push();
        transformer.doTranslate(stack);
        if (! button.isHovered()) {
            page.getGui().getMinecraft().textureManager.bindTexture(general.image);
            AbstractGui.blit(stack, x, y, page.getGui().getBlitOffset(), general.u, general.v, general.width, general.height, general.image_width, general.image_height);
        }
        else {
            page.getGui().getMinecraft().textureManager.bindTexture(onHover.image);
            AbstractGui.blit(stack, x, y, page.getGui().getBlitOffset(), onHover.u, onHover.v, onHover.width, onHover.height, onHover.image_width, onHover.image_height);
        }
        stack.pop();
    }

    @Override
    public void onTerminate() {
        this.page.removeTutorialButton(button);
    }
}
