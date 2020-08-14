package cx.rain.mc.forgemod.sinocraft.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

public class ContainerChineseBrushProvider implements INamedContainerProvider {
    private Inventory inventory = new Inventory(2);

    public ContainerChineseBrushProvider() {

    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Chinese Brush");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ContainerChineseBrush(id, inventory, playerInventory);
    }
}
