package cx.rain.mc.forgemod.sinocraft.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.ContainerChineseBrush;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GuiDrawWithChineseBrush extends ContainerScreen<ContainerChineseBrush> {
    private static final ResourceLocation PAINT_UNIT = new ResourceLocation(SinoCraft.MODID, "textures/misc/paint_unit.png");
    private static final ResourceLocation BACKGROUND = new ResourceLocation(SinoCraft.MODID, "textures/gui/chinese_brush.png");

    protected GuiDrawWithChineseBrush(ContainerChineseBrush screenContainer, PlayerInventory playerInventory, ITextComponent titleIn) {
        super(screenContainer,playerInventory,titleIn);
        this.xSize = 212;
        this.ySize = 236;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int keyCode) {
        SinoCraft.getInstance().getLog().info(mouseX+":"+mouseY);
        return super.mouseClicked(mouseX, mouseY, keyCode);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        //ItemStack paper = this.container.getSlot(0).getStack();

        this.minecraft.getTextureManager().bindTexture(BACKGROUND);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.blit(guiLeft, guiTop, 0, 0, 0, xSize, ySize, 256, 256);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        //this.minecraft.getTextureManager().bindTexture(PAINT_UNIT);
        //RenderSystem.color4f(0, 0, 0, 1.0f);
        //this.blit(this.width / 2 - 150,10,0,0,0.0f,4,4,2,2);
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    public static GuiDrawWithChineseBrush create(ContainerChineseBrush container, PlayerInventory inventory, ITextComponent title) {
        return new GuiDrawWithChineseBrush(container, inventory, title);
    }
}