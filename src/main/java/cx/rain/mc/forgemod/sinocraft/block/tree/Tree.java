package cx.rain.mc.forgemod.sinocraft.block.tree;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.block.tree.TreeData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

import java.util.Map;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Tree extends ForgeRegistryEntry<Tree> {
    public static final IForgeRegistry<Tree> TREES = RegistryManager.ACTIVE.getRegistry(Tree.class);

    protected TreeData treeData = null;

    public Tree(TreeData tree) {
        treeData = tree;
    }

    @SubscribeEvent
    public static void onRegisterBlock(RegistryEvent.Register<Block> event) {
        for (Map.Entry<ResourceKey<Tree>, Tree> tree : TREES.getEntries()) {
            var data = tree.getValue().treeData;

            var blockLog = new RotatedPillarBlock(
                    Block.Properties.of(Material.WOOD, data.getColor())
                            .strength(2.0F)
                            .sound(SoundType.WOOD))
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + "_log"));

            var blockLogStripped = new RotatedPillarBlock(
                    Block.Properties.of(Material.WOOD, tree.getValue().treeData.getColor())
                            .strength(2.0F)
                            .sound(SoundType.WOOD))
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + "_log_stripped"));

            var blockPlanks = new Block(
                    BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                            .strength(2.0F, 3.0F)
                            .sound(SoundType.WOOD))
                    .setRegistryName(new ResourceLocation(SinoCraft.MODID, data.getName() + "_planks"));
        }

        event.getRegistry().register();
    }

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {

    }
}
