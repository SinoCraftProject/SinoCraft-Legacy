package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.advancement.RegistryTrigger;
import cx.rain.mc.forgemod.sinocraft.api.base.BlockActivatable;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IFactory;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IShave;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.defaultImpl.ShaveBase;
import cx.rain.mc.forgemod.sinocraft.group.Groups;
import cx.rain.mc.forgemod.sinocraft.utility.ProtectedHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

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
            Map<Block, Block> STRIPPING_MAP = ProtectedHelper.getStaticField(AxeItem.class, "field_203176_a");
            if(STRIPPING_MAP.get(type.getWorld().getBlockState(type.getPos()).getBlock()) != null){
                return (context) -> {
                    LogBlock block = (LogBlock)context.getWorld().getBlockState(context.getPos()).getBlock();
                    context.getWorld().setBlockState(
                            context.getPos(), (STRIPPING_MAP.get(block).getDefaultState().with(LogBlock.AXIS,context.getWorld().getBlockState(context.getPos()).get(LogBlock.AXIS))));
                    InventoryHelper.spawnItemStack(context.getWorld(), context.getPos().getX(),context.getPos().getY(),context.getPos().getZ(), new ItemStack(Items.BARK.get(), context.getWorld().getRandom().nextInt(2)));
                    RegistryTrigger.SHAVE_BARK_WITH_KNIFE.test((ServerPlayerEntity) context.getPlayer(),
                            context.getPos(),context.getItem());
                };
            }
            return null;
        }
    }

    public ItemKnife(IItemTier tier) {
        super(tier,2,-3.0f,new Item.Properties().group(Groups.TOOLS));
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
                        RegistryTrigger.SHAVE_WITH_KNIFE.test((ServerPlayerEntity) context.getPlayer(),
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
