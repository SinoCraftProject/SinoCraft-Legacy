package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import net.minecraftforge.items.ItemStackHandler;

public class VatItemHandler  extends ItemStackHandler {

    private final TileEntityVat te;

    public VatItemHandler(TileEntityVat te) {
        super(2);
        this.te = te;
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        te.reloadRecipe();
    }

    @Override
    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        te.reloadRecipe();
        te.markDirty();
    }
}