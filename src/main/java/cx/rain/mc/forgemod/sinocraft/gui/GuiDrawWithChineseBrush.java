package cx.rain.mc.forgemod.sinocraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.ContainerChineseBrush;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GuiDrawWithChineseBrush extends ContainerScreen<ContainerChineseBrush> {
    private static final ResourceLocation GUI = new ResourceLocation(SinoCraft.MODID, "textures/gui/chinese_brush.png");
    private Button buttonUp;
    private Button buttonDown;

    protected GuiDrawWithChineseBrush(ContainerChineseBrush screenContainer, PlayerInventory playerInventory, ITextComponent titleIn) {
        super(screenContainer,playerInventory,titleIn);
        this.xSize = 212;
        this.ySize = 236;
    }

    @Override
    protected void init() {
        super.init();
        buttonUp = new Button(guiLeft + 16, guiTop + 112,11,7,"null",(button)->{this.container.incColor();SinoCraft.getInstance().getLog().info("test");}) {
            @Override
            public void render(int mouseX, int mouseY, float partialTicks) {
                this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                Minecraft.getInstance().getTextureManager().bindTexture(GUI);
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0f);
                RenderSystem.enableAlphaTest();
                RenderSystem.enableBlend();
                if (this.isHovered()) {
                    this.blit(this.x, this.y, 11, 243, this.width, this.height, 256, 256);
                }
                else {
                    this.blit(this.x, this.y, 11, 236, this.width, this.height, 256, 256);
                }
                RenderSystem.disableAlphaTest();
                RenderSystem.disableBlend();
            }
        };
        buttonDown = new Button(guiLeft + 16, guiTop + 166,11,7,"null",(button)->{this.container.decColor();SinoCraft.getInstance().getLog().info("test");}) {
            @Override
            public void render(int mouseX, int mouseY, float partialTicks) {
                this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                Minecraft.getInstance().getTextureManager().bindTexture(GUI);
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0f);
                RenderSystem.enableAlphaTest();
                RenderSystem.enableBlend();
                if (this.isHovered()) {
                    this.blit(this.x, this.y, 0, 243, this.width, this.height, 256, 256);
                }
                else {
                    this.blit(this.x, this.y, 0, 236, this.width, this.height, 256, 256);
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
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    public void tick() {
        if (container.inventory.getStackInSlot(2).equals(ItemStack.EMPTY) && (! container.inventory.getStackInSlot(0).equals(ItemStack.EMPTY))) {
            container.inventory.setInventorySlotContents(2, new ItemStack(Items.XUAN_PAPER.get()));
            container.inventory.getStackInSlot(0).shrink(1);
        }
    }

    protected void draw() {

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int keyCode) {
        if (super.mouseClicked(mouseX, mouseY, keyCode)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int keyCode, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        if (super.mouseDragged(mouseX, mouseY, keyCode, p_mouseDragged_6_, p_mouseDragged_8_)) {
            return true;
        }
        return false;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.minecraft.getTextureManager().bindTexture(GUI);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.blit(guiLeft, guiTop, 0, 0, 0, xSize, ySize, 256, 256);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.drawString(this.font, Integer.toString(this.container.color), 18, 139, 0xffffff);
    }

    public static GuiDrawWithChineseBrush create(ContainerChineseBrush container, PlayerInventory inventory, ITextComponent title) {
        return new GuiDrawWithChineseBrush(container, inventory, title);
    }
}