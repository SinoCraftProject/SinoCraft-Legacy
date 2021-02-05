package cx.rain.mc.forgemod.sinocraft.gui.book;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.book.component.TutorialComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.item.BookItem;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.resources.SimpleReloadableResourceManager;
import net.minecraft.resources.SimpleResource;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.packs.ModFileResourcePack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GuiTutorialBook extends Screen {
    private static final ResourceLocation GUI = new ResourceLocation(SinoCraft.MODID, "textures/gui/tutorial_book.png");

    protected static final int xSize = 128;
    protected static final int ySize = 182;
    protected int guiLeft;
    protected int guiTop;

    protected int nowPage = 0;

    public class Page implements IRenderable {
        protected List<TutorialComponent> components = new ArrayList();
        public GuiTutorialBook gui;
        public ResourceLocation background;
        public boolean isLeft;

        public Page(JsonObject j_root, GuiTutorialBook gui, boolean isLeft) {
            this.gui = gui;
            this.isLeft = isLeft;
            background = new ResourceLocation(j_root.getAsJsonPrimitive("background").getAsString());
            background = new ResourceLocation(background.getNamespace(), "textures/gui/" + background.getPath() + ".png");
            JsonArray j_arr = j_root.getAsJsonArray("components");
        }

        @Override
        public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
            this.gui.getMinecraft().getTextureManager().bindTexture(background);
            if (isLeft) {
                blit(stack, -64, 0, 0, 0, 128, 182);
            }
            else {
                blit(stack, 64, 0, 128, 0, 128, 182);
            }
        }
    }
    protected List<Page> pages = new ArrayList();

    public static SimpleReloadableResourceManager manager = new SimpleReloadableResourceManager(ResourcePackType.CLIENT_RESOURCES);
    static {
        manager.addResourcePack(new ModFileResourcePack(ModList.get().getModFileById(SinoCraft.MODID).getFile()));
    }

    @Override
    protected void init() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        super.init();
    }

    protected GuiTutorialBook(ITextComponent title, ResourceLocation path) {
        super(title);
        List<ResourceLocation> l_pages = new ArrayList();
        path = new ResourceLocation(path.getNamespace(), "book/" + path.getPath() + ".json");
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
                l_pages.add(new ResourceLocation(rl.getNamespace(), "book/pages/" + rl.getPath() + ".json"));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        boolean isLeft = false;
        for (ResourceLocation rl : l_pages) {
            try {
                JsonObject j_root = JSONUtils.fromJson(
                        new InputStreamReader(
                                manager.getResource(rl).getInputStream()
                                , StandardCharsets.UTF_8
                        )
                );
                pages.add(new Page(j_root, this, isLeft));
                isLeft =! isLeft;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.fillGradient(stack, 0, 0, this.width, this.height, -1072689136, -804253680);
        stack.push();
        stack.translate((float)guiLeft, (float)guiTop, 0.0F);
        if (nowPage >= 0) {
            pages.get(nowPage).render(stack, mouseX, mouseY, partialTicks);
        }
        if (nowPage + 1 < pages.size()) {
            pages.get(nowPage + 1).render(stack, mouseX, mouseY, partialTicks);
        }
        stack.pop();
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    public void jmpPage(int index) {
        this.nowPage = index;
    }

    public void nextPage() {
        this.nowPage ++;
    }

    public void lastPage() {
        this.nowPage --;
    }

    public static GuiTutorialBook create(ResourceLocation path) {
        return new GuiTutorialBook(new StringTextComponent("null"), path);
    }
}
