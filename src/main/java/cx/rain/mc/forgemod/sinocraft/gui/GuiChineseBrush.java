package cx.rain.mc.forgemod.sinocraft.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.container.ContainerChineseBrush;
import cx.rain.mc.forgemod.sinocraft.network.CDrawPaper;
import cx.rain.mc.forgemod.sinocraft.network.Networks;
import cx.rain.mc.forgemod.sinocraft.utility.math.Vec2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class GuiChineseBrush extends ContainerScreen<ContainerChineseBrush> {
    private static final ResourceLocation GUI = new ResourceLocation(SinoCraft.MODID, "textures/gui/chinese_brush.png");
    private Button buttonUp;
    private Button buttonDown;

    protected GuiChineseBrush(ContainerChineseBrush screenContainer, PlayerInventory playerInventory, ITextComponent titleIn) {
        super(screenContainer, playerInventory, titleIn);
        this.xSize = 212;
        this.ySize = 236;
    }

    @Override
    protected void init() {
        super.init();
        buttonUp = new Button(guiLeft + 16, guiTop + 112, 11, 7, new StringTextComponent("null"), (button) -> {
            this.container.incColor();
        }) {
            @Override
            public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
                this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                Minecraft.getInstance().getTextureManager().bindTexture(GUI);
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0f);
                RenderSystem.enableAlphaTest();
                RenderSystem.enableBlend();
                if (this.isHovered()) {
                    this.blit(matrixStack, this.x, this.y, 11, 243, this.width, this.height, 256, 256);
                } else {
                    this.blit(matrixStack, this.x, this.y, 11, 236, this.width, this.height, 256, 256);
                }
                RenderSystem.disableAlphaTest();
                RenderSystem.disableBlend();
            }
        };

        buttonDown = new Button(guiLeft + 16, guiTop + 166, 11, 7, new StringTextComponent("null"), (button) -> {
            this.container.decColor();
        }) {
            @Override
            public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
                this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                Minecraft.getInstance().getTextureManager().bindTexture(GUI);
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0f);
                RenderSystem.enableAlphaTest();
                RenderSystem.enableBlend();
                if (this.isHovered()) {
                    this.blit(matrixStack, this.x, this.y, 0, 243, this.width, this.height, 256, 256);
                } else {
                    this.blit(matrixStack, this.x, this.y, 0, 236, this.width, this.height, 256, 256);
                }
                RenderSystem.disableAlphaTest();
                RenderSystem.disableBlend();
            }
        };
        this.addButton(buttonUp);
        this.addButton(buttonDown);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderHoveredTooltip(matrixStack, mouseX, mouseY);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    protected void draw(MatrixStack matrixStack) {
        if (container.inventory.getStackInSlot(0).copy() != ItemStack.EMPTY) {
            ItemStack paper = this.container.inventory.getStackInSlot(0).copy();

            this.minecraft.getTextureManager().bindTexture(GUI);

            byte[] pixels = null;

            if (paper.getOrCreateTag().contains("pixels")) {
                pixels = paper.getTag().getByteArray("pixels");
            }

            if (pixels == null) {
                pixels = new byte[32 * 32];
            }

            int sx = 61, sy = 14, unit = 4;

            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    float color = 0.0625f * (16 - pixels[i * 32 + j]);
                    if (color == 0.0625f) {
                        color = 0.0f;
                    }
                    RenderSystem.color3f(color, color, color);
                    blit(matrixStack, sx + i * unit, sy + j * unit, 0, 22, 236, unit, unit, 256, 256);
                }
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int keyCode) {
        super.mouseClicked(mouseX, mouseY, keyCode);
        if ((mouseX >= guiLeft + 61) && (mouseX < guiLeft + 61 + 128) && (mouseY >= guiTop + 14) && (mouseY < guiTop + 14 + 128)) {
            ItemStack paper = this.container.inventory.getStackInSlot(0);

            if (paper == ItemStack.EMPTY) {
                return false;
            }

            byte[] pixels = null;

            if (paper.getOrCreateTag().contains("pixels")) {
                pixels = paper.getTag().getByteArray("pixels");
            } else {
                pixels = new byte[32 * 32];
            }

            //this.container.inventory.getStackInSlot(1).damageItem(this.container.color, this.minecraft.player, (e)->{});

            int x = (int) (Math.round(mouseX) - guiLeft - 61) / 4;
            int y = (int) (Math.round(mouseY) - guiTop - 14) / 4;

            Networks.INSTANCE.sendToServer(new CDrawPaper(
                    new CDrawPaper.Pack(
                            new Vec2(x, y), this.container.color
                    )
            ));
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int keyCode, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        super.mouseDragged(mouseX, mouseY, keyCode, p_mouseDragged_6_, p_mouseDragged_8_);
        if ((mouseX >= guiLeft + 61) && (mouseX < guiLeft + 61 + 128) && (mouseY >= guiTop + 14) && (mouseY < guiTop + 14 + 128)) {
            ItemStack paper = this.container.inventory.getStackInSlot(0);

            if (paper == ItemStack.EMPTY) {
                return false;
            }

            byte[] pixels = null;

            if (paper.getOrCreateTag().contains("pixels")) {
                pixels = paper.getTag().getByteArray("pixels");
            }

            if (pixels == null) {
                pixels = new byte[32 * 32];
            }

            int x = (int) (Math.round(mouseX) - guiLeft - 61) / 4;
            int y = (int) (Math.round(mouseY) - guiTop - 14) / 4;

            Networks.INSTANCE.sendToServer(new CDrawPaper(
                    new CDrawPaper.Pack(
                            new Vec2(x, y), this.container.color
                    )
            ));
            return true;
        }
        return false;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.minecraft.getTextureManager().bindTexture(GUI);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.blit(matrixStack, guiLeft, guiTop, 0, 0, 0, xSize, ySize, 256, 256);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.drawString(matrixStack, this.font, Integer.toString(this.container.color), 18, 139, 0xffffff);
        draw(matrixStack);
    }

    public static GuiChineseBrush create(ContainerChineseBrush container, PlayerInventory inventory, ITextComponent title) {
        return new GuiChineseBrush(container, inventory, title);
    }
}