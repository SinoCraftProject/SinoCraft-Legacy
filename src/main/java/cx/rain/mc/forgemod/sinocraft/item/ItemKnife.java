package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.advanement.RegistryTrigger;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IFactory;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IShave;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.defaultImpl.ShaveBase;
import cx.rain.mc.forgemod.sinocraft.block.base.BlockLog;
import cx.rain.mc.forgemod.sinocraft.group.Groups;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemKnife extends SwordItem {
	public static List<IFactory<IShave,ItemUseContext>> shaveManagers = new ArrayList();

	public static void dropItem(World worldIn, BlockPos pos, ItemStack stack){
        worldIn.addEntity(new ItemEntity(worldIn.getWorld(),
                pos.getX(),pos.getY(),pos.getZ(),stack));
    }

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
            if(type.getWorld().getBlockState(type.getPos()).getBlock() instanceof BlockLog){
                BlockLog b = (BlockLog) type.getWorld().getBlockState(type.getPos()).getBlock();
                if(b.kind!= BlockLog.KIND.LOG){
                    return null;
                }
                return (context) -> {
                    BlockLog block = (BlockLog)context.getWorld().getBlockState(context.getPos()).getBlock();
                    context.getWorld().setBlockState(context.getPos(),
                            block.type.getTag().LogStripped.get().getDefaultState().with(LogBlock.AXIS,context.getWorld().getBlockState(context.getPos()).get(LogBlock.AXIS)));
                    dropItem(context.getWorld(), context.getPos(),
                            new ItemStack(Items.BARK.get(), context.getWorld().getRandom().nextInt(2)));
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
	    if(context.getWorld().isRemote){
	        return ActionResultType.PASS;
        }
	    for(IFactory<IShave,ItemUseContext> shaveManager : shaveManagers){
            try {
                IShave shave=shaveManager.get(context,null);
                if(shave!=null){
                    shave.Shave(context);
                    RegistryTrigger.SHAVE_WITH_KNIFE.test((ServerPlayerEntity) context.getPlayer(),
                            context.getPos(),context.getItem());
                    return ActionResultType.SUCCESS;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return ActionResultType.FAIL;
    }
}
