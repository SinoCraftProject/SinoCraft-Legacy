package cx.rain.mc.forgemod.sinocraft.block.tree;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.block.tree.TreeData;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
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

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Tree extends ForgeRegistryEntry<Tree> {
    public static final IForgeRegistry<Tree> TREES = RegistryManager.ACTIVE.getRegistry(Tree.class);

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
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + "_log_stripped"));

            var blockLog = log(data.getTopColor(), data.getSideColor(), blockLogStripped)
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + "_log"));

            var blockBarkStripped = bark(MaterialColor.TERRACOTTA_PINK)
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + "_bark_stripped"));

            var blockBark = bark(MaterialColor.COLOR_PINK, blockBarkStripped)
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + "_bark"));

            var blockPlanks = new Block(
                    BlockBehaviour.Properties.of(Material.WOOD, data.getTopColor())
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD))
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + "_planks"));

            Block blockLeaves = null;
            if (data.hasFruit()) {
                blockLeaves = new BlockLeavesGrowable(
                        BlockBehaviour.Properties.of(Material.LEAVES)
                                .sound(SoundType.GRASS)
                                .strength(0.2F)
                                .randomTicks()
                                .noOcclusion(), data.fruit())
                        .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + "_leaves"));
            } else {
                blockLeaves = new LeavesBlock(
                        BlockBehaviour.Properties.of(Material.LEAVES)
                                .sound(SoundType.GRASS)
                                .strength(0.2F)
                                .randomTicks()
                                .noOcclusion())
                        .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + "_leaves"));
            }

            var blockSapling = new SaplingBlock(data.getGrower(), BlockBehaviour.Properties.of(Material.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GRASS));

            event.getRegistry().registerAll(blockLog, blockLogStripped, blockBark, blockBarkStripped,
                    blockLeaves, blockPlanks, blockSapling);
        }
    }

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {
        // Todo: qyl: will do. 2022.1.19.
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
}
