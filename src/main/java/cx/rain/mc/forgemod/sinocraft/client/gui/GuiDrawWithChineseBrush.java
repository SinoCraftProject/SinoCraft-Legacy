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

    protected GuiDrawWithChineseBrush(ContainerChineseBrush screenContainer, PlayerInventory playerInventory, ITextComponent titleIn) {
        super(screenContainer,playerInventory,titleIn);
        this.xSize = 176;
        this.ySize = 251;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.minecraft.getTextureManager().bindTexture(PAINT_UNIT);
        renderBackground();
        renderHoveredToolTip(mouseX, mouseY);
        RenderSystem.color4f(0, 0, 0, 1.0f);
        this.blit(this.width / 2 - 150,10,0,0,0.0f,4,4,2,2);
    }


    public static GuiDrawWithChineseBrush create(ContainerChineseBrush container, PlayerInventory inventory, ITextComponent title) {
        return new GuiDrawWithChineseBrush(container, inventory, title);
    }
}