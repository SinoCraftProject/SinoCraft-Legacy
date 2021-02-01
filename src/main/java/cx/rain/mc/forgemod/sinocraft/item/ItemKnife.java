package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IFactory;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IShave;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.defaultImpl.ShaveBase;
import cx.rain.mc.forgemod.sinocraft.group.ModGroups;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.AxeItem;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemKnife extends SwordItem {
	public static List<IFactory<IShave,ItemUseContext>> shaveManagers = new ArrayList();

	public static class DefaultManager implements IFactory<IShave, ItemUseContext> {
        private static Map<Block, IShave> recipes = new HashMap();

        public DefaultManager(){
            initIShave();
        }

        private void initIShave(){

        }

        public static void addShaveRecipe(Block block,IShave shave){
            recipes.put(block,shave);
        }

        public static void addShaveRecipe(Block block, BlockState replace, ItemStack... stacks){
            addShaveRecipe(block,new ShaveBase(replace,stacks));
        }

        @Override
        public IShave get(ItemUseContext type, @Nullable Object[] args){
            if(recipes.containsKey(type.getWorld().getBlockState(type.getPos()).getBlock())){
                return recipes.get(type.getWorld().getBlockState(type.getPos()).getBlock());
            }
            return null;
        }
    }

    private static class ShaveBarkManager implements IFactory<IShave, ItemUseContext> {
        public ShaveBarkManager(){

        }

        @Override
        public IShave get(ItemUseContext type, @Nullable Object[] args){
            BlockState strip_state = AxeItem.getAxeStrippingState(type.getWorld().getBlockState(type.getPos()));
            if(strip_state != null){
                return (context) -> {
                    context.getWorld().setBlockState(context.getPos(), strip_state);
                    CriteriaTriggers.RIGHT_CLICK_BLOCK_WITH_ITEM.test((ServerPlayerEntity) context.getPlayer(),
                            context.getPos(),context.getItem());
                };
            }
            return null;
        }
    }

    public ItemKnife(IItemTier tier) {
        super(tier,2,-3.0f,new Item.Properties().group(ModGroups.TOOLS));
        initManager();
    }

    void initManager(){
	    shaveManagers.add(new DefaultManager());
	    shaveManagers.add(new ShaveBarkManager());
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
	    for(IFactory<IShave,ItemUseContext> shaveManager : shaveManagers){
            try {
                IShave shave=shaveManager.get(context,null);
                if(shave!=null){
                    if(!context.getWorld().isRemote){
                        shave.Shave(context);
                        CriteriaTriggers.RIGHT_CLICK_BLOCK_WITH_ITEM.test((ServerPlayerEntity) context.getPlayer(),
                                        context.getPos(),context.getItem());

                        context.getWorld().notifyBlockUpdate(context.getPos(), context.getWorld().getBlockState(context.getPos()), context.getWorld().getBlockState(context.getPos()), 2);
                    }
                    context.getWorld().playSound(context.getPlayer(),context.getPos(), SoundEvents.ITEM_AXE_STRIP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    return ActionResultType.SUCCESS;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (context.getWorld().isRemote) {
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
}
