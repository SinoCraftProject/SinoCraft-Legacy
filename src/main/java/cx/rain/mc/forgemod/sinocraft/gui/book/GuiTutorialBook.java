package cx.rain.mc.forgemod.sinocraft.gui.book;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.book.component.ComponentType;
import cx.rain.mc.forgemod.sinocraft.gui.book.component.TutorialComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.resources.SimpleReloadableResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.packs.ModFileResourcePack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class GuiTutorialBook extends Screen {
    protected static final int xSize = 128;
    protected static final int ySize = 182;
    protected int guiLeft;
    protected int guiTop;

    protected int nowPage = -1;
    protected List<Widget> wfa = new ArrayList<>();
    protected List<Widget> wfd = new ArrayList<>();

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

        public <T extends Widget> void addTutorialButton(T widget) {
            if (isLeft) {
                widget.x = widget.x + guiLeft - 64;
            }
            else {
                widget.x = widget.x + guiLeft + 64;
            }
            widget.y = guiTop + widget.y;
            gui.wfa.add(widget);
        }

        public <T extends Widget> void removeTutorialButton(T widget) {
            wfd.add(widget);
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
            if (j_arr != null) {
                for (JsonElement ele : j_arr) {
                    ComponentType<? extends TutorialComponent> t_component = ComponentType.TUTORIAL_COMPONENT.getValue(new ResourceLocation(
                            ele.getAsJsonObject().getAsJsonPrimitive("id").getAsString())
                    );
                    if (t_component == null) {
                        throw new IllegalStateException("Invalid Component " + ele.getAsJsonObject().getAsJsonPrimitive("id").getAsString());
                    }
                    TutorialComponent component = t_component.getComponent(this);
                    component.fromJson(ele.getAsJsonObject());
                    components.add(component);
                }
            }
        }

        @Override
        public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
            this.gui.getMinecraft().getTextureManager().bindTexture(background);
            stack.push();
            if (isLeft) {
                blit(stack, -64, 0, 0, 0, 128, 181);
                stack.translate(-64, 0, 0);
            }
            else {
                blit(stack, 64, 0, 128, 0, 128, 181);
                stack.translate(64, 0, 0);
            }
            for (TutorialComponent component : components) {
                component.render(stack, mouseX, mouseY, partialTicks);
            }
            stack.pop();
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
            super(x, y, 17, 11, new StringTextComponent("null"), (button)->{
                gui.do_terminate();
                if (isNextPage) {
                    gui.nextPage();
                } else {
                    gui.lastPage();
                }
                gui.do_init();
            });
            this.isNextPage = isNextPage;
            this.gui = gui;
        }

        @Override
        public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            if (!((gui.nowPage == -1 && ! isNextPage) || (gui.nowPage + 1 >= gui.pages.size() && isNextPage))) {
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
            handler.play(SimpleSound.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
        }
    }

    @Override
    protected void init() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.buttons.add(new ChangePageButton(this.guiLeft - 64, this.guiTop + 192, this, false));
        this.buttons.add(new ChangePageButton(this.guiLeft + 192, this.guiTop + 192, this, true));
        SinoCraft.getLogger().info(buttons.size());
        super.init();
    }

    protected void do_terminate() {
        if (nowPage >= 0) {
            for (TutorialComponent component : pages.get(nowPage).components) {
                component.onTerminate();
            }
        }
        if (nowPage + 1 < pages.size()) {
            for (TutorialComponent component : pages.get(nowPage + 1).components) {
                component.onTerminate();
            }
        }
    }

    protected void do_init() {
        if (nowPage >= 0) {
            for (TutorialComponent component : pages.get(nowPage).components) {
                component.init();
            }
        }
        if (nowPage + 1 < pages.size()) {
            for (TutorialComponent component : pages.get(nowPage + 1).components) {
                component.init();
            }
        }
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
        for (Widget w : wfd) {
            if (buttons.contains(w)) {
                buttons.remove(w);
            }
        }
        for (Widget w : wfa) {
            buttons.add(w);
        }
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
    }

    public void nextPage() {
        this.nowPage += 2;
    }

    public void lastPage() {
        this.nowPage -= 2;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Widget w : buttons) {
            if (w.isMouseOver(mouseX, mouseY)) {
                w.onClick(mouseX, mouseY);
            }
            SinoCraft.getLogger().info(w.x + ":" + w.y + "#" + mouseX + ":" + mouseY);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public  <T extends IGuiEventListener> T addListener(T listener) {
        return super.addListener(listener);
    }

    public static GuiTutorialBook create(ResourceLocation path) {
        return new GuiTutorialBook(new StringTextComponent("null"), path);
    }
}
