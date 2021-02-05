package cx.rain.mc.forgemod.sinocraft.gui.book;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.book.component.TutorialComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ChangePageButton;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.item.BookItem;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.resources.SimpleReloadableResourceManager;
import net.minecraft.resources.SimpleResource;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.packs.ModFileResourcePack;

import javax.annotation.Nullable;
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

    protected int nowPage = -1;

    public class Page implements IRenderable {
        protected List<TutorialComponent> components = new ArrayList();
        protected GuiTutorialBook gui;
        protected ResourceLocation background;
        protected boolean isLeft;

        public ResourceLocation getBackground() {
            return background;
        }

        public GuiTutorialBook getGui() {
            return gui;
        }

        public boolean isLeft() {
            return isLeft;
        }

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

    private static class ChangePageButton extends Button {
        private GuiTutorialBook gui;
        private boolean isNextPage;

        public ChangePageButton(int x, int y, GuiTutorialBook gui, boolean isNextPage) {
            super(x, y, 34, 22, new StringTextComponent("null"), (button)->{
                System.out.println("click1");
                if (isNextPage) {
                    gui.nextPage();
                } else {
                    gui.lastPage();
                }
            });
            this.isNextPage = isNextPage;
            this.gui = gui;
        }

        @Override
        public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            if (!((gui.nowPage == -1 && ! isNextPage) || (gui.nowPage == gui.pages.size() && isNextPage))) {
                this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                int i = isHovered ? 11: 0;
                int j = isNextPage ?  0: 17;
                ResourceLocation GUI;
                if (isNextPage) {
                    GUI = gui.pages.get(gui.nowPage + 1).background;
                }
                else {
                    GUI = gui.pages.get(gui.nowPage).background;
                }
                Minecraft.getInstance().getTextureManager().bindTexture(GUI);
                this.blit(matrixStack, this.x , this.y, j, i + 182, 17, 11);
            }
        }

        public void playDownSound(SoundHandler handler) {
            System.out.println("click2");
            handler.play(SimpleSound.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
        }
    }

    @Override
    protected void init() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.buttons.add(new ChangePageButton(this.guiLeft - 64, this.guiTop + 192, this, false));
        this.buttons.add(new ChangePageButton(this.guiLeft + 192, this.guiTop + 192, this, true));
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
        super.render(stack, mouseX, mouseY, partialTicks);
        stack.push();
        stack.translate((float)guiLeft, (float)guiTop, 0.0F);
        if (nowPage >= 0) {
            pages.get(nowPage).render(stack, mouseX, mouseY, partialTicks);
        }
        if (nowPage + 1 < pages.size()) {
            pages.get(nowPage + 1).render(stack, mouseX, mouseY, partialTicks);
        }
        stack.pop();
    }

    public void jmpPage(int index) {
        this.nowPage = index;
        System.out.println("j:" + nowPage);
    }

    public void nextPage() {
        this.nowPage += 2;
        System.out.println("n:" + nowPage);
    }

    public void lastPage() {
        this.nowPage -= 2;
        System.out.println("l:" + nowPage);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        System.out.println("click2");
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public static GuiTutorialBook create(ResourceLocation path) {
        return new GuiTutorialBook(new StringTextComponent("null"), path);
    }
}
