package cx.rain.mc.forgemod.sinocraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockStove extends Block {
    public BlockStove() {
        super(Properties.create(Material.GOURD, MaterialColor.GRAY));
    }

    //@Nullable
    //@Override
    //public TileEntity createNewTileEntity(IBlockReader worldIn) {
    //    return new TileEntityStove();
    //}
}
