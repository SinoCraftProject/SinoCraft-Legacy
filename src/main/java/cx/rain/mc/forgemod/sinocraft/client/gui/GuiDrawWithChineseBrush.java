package cx.rain.mc.forgemod.sinocraft.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.ContainerChineseBrush;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
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
        buttonUp = new Button(16, 112,11,7,"null",(button)->{this.container.incColor();}) {
            @Override
            public void render(int mouseX, int mouseY, float partialTicks) {
                Minecraft.getInstance().getTextureManager().bindTexture(GUI);
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0f);
                RenderSystem.enableAlphaTest();
                RenderSystem.enableBlend();
                if (mouseX >= this.x && mouseY >= this.y && mouseX <= this.x + this.width && mouseY <= this.y + this.height) {
                    this.blit(this.x, this.y, 11, 243, this.width, this.height, 256, 256);
                }
                else {
                    this.blit(this.x, this.y, 11, 236, this.width, this.height, 256, 256);
                }
                RenderSystem.disableAlphaTest();
                RenderSystem.disableBlend();
            }
        };
        buttonDown = new Button(16, 166,11,7,"null",(button)->{this.container.decColor();}) {
            @Override
            public void render(int mouseX, int mouseY, float partialTicks) {
                Minecraft.getInstance().getTextureManager().bindTexture(GUI);
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0f);
                RenderSystem.enableAlphaTest();
                RenderSystem.enableBlend();
                if (mouseX >= this.x && mouseY >= this.y && mouseX <= this.x + this.width && mouseY <= this.y + this.height) {
                    this.blit(this.x, this.y, 0, 243, this.width, this.height, 256, 256);
                }
                else {
                    this.blit(this.x, this.y, 0, 236, this.width, this.height, 256, 256);
                }
                RenderSystem.disableAlphaTest();
                RenderSystem.disableBlend();
            }
        };
        super.init();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    //@Override
    //public boolean mouseClicked(double mouseX, double mouseY, int keyCode) {
    //    return super.mouseClicked(mouseX, mouseY, keyCode);
    //}

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        //ItemStack paper = this.container.getSlot(0).getStack();

        this.minecraft.getTextureManager().bindTexture(GUI);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.blit(guiLeft, guiTop, 0, 0, 0, xSize, ySize, 256, 256);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        //this.minecraft.getTextureManager().bindTexture(PAINT_UNIT);
        //RenderSystem.color4f(0, 0, 0, 1.0f);
        //this.blit(this.width / 2 - 150,10,0,0,0.0f,4,4,2,2);
        buttonUp.render(mouseX, mouseY, 20);
        buttonDown.render(mouseX, mouseY, 20);
        this.drawString(this.font, Integer.toString(this.container.color), 14, 139, 0x000000);
    }

    public static GuiDrawWithChineseBrush create(ContainerChineseBrush container, PlayerInventory inventory, ITextComponent title) {
        return new GuiDrawWithChineseBrush(container, inventory, title);
    }
}