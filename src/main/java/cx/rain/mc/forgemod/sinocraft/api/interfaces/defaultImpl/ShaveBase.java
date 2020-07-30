package cx.rain.mc.forgemod.sinocraft.api.interfaces.defaultImpl;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IShave;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ShaveBase implements IShave {
    protected BlockState block;
    protected List<ItemStack> stacks;

    public ShaveBase(BlockState replaceBlockIn,@Nullable ItemStack... dropStacks){
        block=replaceBlockIn;
        if(dropStacks==null){
            stacks=null;
        }
        else if(dropStacks.length==0){
            stacks=null;
        }
        else{
            this.stacks = new ArrayList();
            for(ItemStack stack : dropStacks){
                stacks.add(stack);
            }
        }
    }

    @Override
    public void Shave(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        world.setBlockState(pos,block);
        for(ItemStack stack : stacks){
            InventoryHelper.spawnItemStack(world, pos.getX(),pos.getY(),pos.getZ(), stack);
        }
    }
}
