package cx.rain.mc.forgemod.sinocraft.gui.book;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.resources.SimpleReloadableResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.minecraftforge.fml.packs.ModFileResourcePack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GuiTutorialBook extends Screen {
    public static class Page implements IRenderable {
        protected List<TutorialComponent> components = new ArrayList();
        protected GuiTutorialBook gui;
        protected int currentX = 0;
        protected int currentY = 0;

        @Override
        public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
            drawString(stack, gui.font, "test", 0xAAEEFF, 9, 5);
        }

        public Page(GuiTutorialBook gui, JsonObject j_root) {
            this.gui = gui;
            //System.out.println(j_root.toString());
        }

        public int getCurrentX() {
            return currentX;
        }

        public int getCurrentY() {
            return currentY;
        }

        public GuiTutorialBook getGUI() {
            return gui;
        }
    }

    public static abstract class TutorialComponent implements IRenderable {
        protected Page page;

        public TutorialComponent(Page page) {
            this.page = page;
        }

        public boolean canClick() {
            return false;
        }

        public void onClick() {
        }

        public void onHover() {
        }

        public abstract void fromJson(JsonObject j_root);
    }

    protected List<Page> pages =  new ArrayList();
    protected SimpleReloadableResourceManager manager = new SimpleReloadableResourceManager(ResourcePackType.CLIENT_RESOURCES);

    protected GuiTutorialBook(ITextComponent title, ResourceLocation path) {
        super(title);
        manager.addResourcePack(new ModFileResourcePack(ModList.get().getModFileById(path.getNamespace()).getFile()));
        path = new ResourceLocation(path.getNamespace(), "book/" + path.getPath() + ".json");
        List<ResourceLocation> l_pages = new ArrayList();
        try {
            JsonObject j_root = JSONUtils.fromJson(
                    new InputStreamReader(
                            manager.getResource(path).getInputStream()
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
                                manager.getResource(rl).getInputStream()
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
        return new GuiTutorialBook(new StringTextComponent("tutorial"), path);
    }

    public static GuiTutorialBook create(String path) {
        return create(new ResourceLocation(path));
    }
}
