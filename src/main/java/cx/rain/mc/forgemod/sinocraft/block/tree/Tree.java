package cx.rain.mc.forgemod.sinocraft.block.tree;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.block.tree.TreeData;
import cx.rain.mc.forgemod.sinocraft.item.ItemTabs;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Tree extends ForgeRegistryEntry<Tree> {
    public static final IForgeRegistry<Tree> TREES = RegistryManager.ACTIVE.getRegistry(Tree.class);

    public static final String SUFFIX_LOG = "_log";
    public static final String SUFFIX_LOG_STRIPPED = "_log_stripped";
    public static final String SUFFIX_BARK = "_log_stripped";
    public static final String SUFFIX_BARK_STRIPPED =  "_bark_stripped";
    public static final String SUFFIX_PLANKS =  "_planks";
    public static final String SUFFIX_LEAVES =  "_leaves";
    public static final String SUFFIX_SAPLING = "_sapling";

    static {
        AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);
    }

    protected TreeData treeData = null;

    public Tree(TreeData tree) {
        treeData = tree;
    }

    @SubscribeEvent
    public static void onRegisterBlock(RegistryEvent.Register<Block> event) {
        for (Map.Entry<ResourceKey<Tree>, Tree> tree : TREES.getEntries()) {
            var data = tree.getValue().treeData;

            var blockLogStripped = log(data.getTopColor(), data.getSideColor())
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_LOG_STRIPPED));

            var blockLog = log(data.getTopColor(), data.getSideColor(), blockLogStripped)
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_LOG));

            var blockBarkStripped = bark(MaterialColor.TERRACOTTA_PINK)
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_BARK_STRIPPED));

            var blockBark = bark(MaterialColor.COLOR_PINK, blockBarkStripped)
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_BARK));

            var blockPlanks = new Block(
                    BlockBehaviour.Properties.of(Material.WOOD, data.getTopColor())
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD))
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_PLANKS));

            Block blockLeaves = null;
            if (data.hasFruit()) {
                blockLeaves = new BlockLeavesGrowable(
                        BlockBehaviour.Properties.of(Material.LEAVES)
                                .sound(SoundType.GRASS)
                                .strength(0.2F)
                                .randomTicks()
                                .noOcclusion(), data.fruit())
                        .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_LEAVES));
            } else {
                blockLeaves = new LeavesBlock(
                        BlockBehaviour.Properties.of(Material.LEAVES)
                                .sound(SoundType.GRASS)
                                .strength(0.2F)
                                .randomTicks()
                                .noOcclusion())
                        .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_LEAVES));
            }

            var blockSapling = new SaplingBlock(data.getGrower(), BlockBehaviour.Properties.of(Material.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GRASS))
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_SAPLING));

            event.getRegistry().registerAll(blockLog, blockLogStripped, blockBark, blockBarkStripped,
                    blockLeaves, blockPlanks, blockSapling);
        }
    }

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {
        // Todo: qyl: Waiting for test. 2022.1.19.

        for (Map.Entry<ResourceKey<Tree>, Tree> tree : TREES.getEntries()) {
            var data = tree.getValue().treeData;

            var itemLog = blockItem(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_LOG));
            var itemLogStripped = blockItem(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_LOG_STRIPPED));
            var itemBark = blockItem(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_BARK));
            var itemBarkStripped = blockItem(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_BARK_STRIPPED));
            var itemPlanks = blockItem(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_PLANKS));
            var itemLeaves = blockItem(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_LEAVES));
            var itemSapling = blockItemSapling(new ResourceLocation(SinoCraft.MODID, data.getName() + SUFFIX_SAPLING));

            event.getRegistry().registerAll(itemLog, itemLogStripped, itemBark, itemBarkStripped,
                    itemPlanks, itemLeaves, itemSapling);
        }
    }

    private static RotatedPillarBlock log(MaterialColor topColor, MaterialColor barkColor) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD,
                (materialColor) -> materialColor.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y
                        ? topColor : barkColor).strength(2.0F).sound(SoundType.WOOD));
    }

    private static RotatedPillarBlock log(MaterialColor topColor, MaterialColor barkColor, Block afterStrip) {
        var wood = new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD,
                (materialColor) -> materialColor.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y
                        ? topColor : barkColor).strength(2.0F).sound(SoundType.WOOD));
        AxeItem.STRIPPABLES.put(wood, afterStrip);
        return wood;
    }

    private static RotatedPillarBlock bark(MaterialColor color) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, color)
                .strength(2.0F)
                .sound(SoundType.WOOD));
    }

    private static RotatedPillarBlock bark(MaterialColor color, Block afterStrip) {
        var wood = new RotatedPillarBlock(
                BlockBehaviour.Properties.of(Material.WOOD, color)
                        .strength(2.0F)
                        .sound(SoundType.WOOD));

        AxeItem.STRIPPABLES.put(wood, afterStrip);
        return wood;
    }

    private static BlockItem blockItem(ResourceLocation path) {
        var block = RegistryObject.of(path, () -> Block.class).get();
        return new BlockItem(block, new Item.Properties().tab(ItemTabs.BLOCKS));
    }

    private static BlockItem blockItemSapling(ResourceLocation path) {
        var block = RegistryObject.of(path, () -> Block.class).get();
        return new BlockItem(block, new Item.Properties().tab(ItemTabs.AGRICULTURE));
    }
}
