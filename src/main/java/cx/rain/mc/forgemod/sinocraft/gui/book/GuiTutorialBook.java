package cx.rain.mc.forgemod.sinocraft.gui.book;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.gui.book.component.TutorialComponent;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GuiTutorialBook extends Screen {
    protected static class Page implements IRenderable {
        protected List<TutorialComponent> components = new ArrayList();
        protected GuiTutorialBook gui;

        @Override
        public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
            drawString(stack, gui.font, "test", 0xAAEEFF, 9, 5);
        }

        public Page(GuiTutorialBook gui, JsonObject j_root) {
            this.gui = gui;
        }
    }
    protected List<Page> pages =  new ArrayList();

    protected GuiTutorialBook(ITextComponent title, ResourceLocation path) {
        super(title);
        path = new ResourceLocation(path.getNamespace(), "book/" + path.getPath() + ".json");
        List<ResourceLocation> l_pages = new ArrayList();
        try {
            JsonObject j_root = JSONUtils.fromJson(
                    new InputStreamReader(
                            this.minecraft.getResourceManager().getResource(path).getInputStream()
                            , StandardCharsets.UTF_8
                    )
            );
            JsonArray j_pages = j_root.getAsJsonArray("pages");
            for (JsonElement j_ele : j_pages) {
                ResourceLocation rl = new ResourceLocation(j_ele.getAsString());
                l_pages.add(new ResourceLocation(rl.getNamespace(), "book/pages/" + path.getPath() + ".json"));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (ResourceLocation rl : l_pages) {
            try {
                JsonObject j_root = JSONUtils.fromJson(
                        new InputStreamReader(
                                this.minecraft.getResourceManager().getResource(rl).getInputStream()
                                , StandardCharsets.UTF_8
                        )
                );
                pages.add(new Page(this, j_root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        Page page = new Page(this, null);
        page.render(stack, mouseX, mouseY, partialTicks);
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    public static GuiTutorialBook create(ResourceLocation path) {
        return new GuiTutorialBook(null, path);
    }
}
