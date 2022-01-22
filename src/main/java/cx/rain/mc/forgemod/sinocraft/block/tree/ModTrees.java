package cx.rain.mc.forgemod.sinocraft.block.tree;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.block.tree.TreeData;
import net.minecraft.world.level.block.grower.BirchTreeGrower;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.grower.SpruceTreeGrower;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Supplier;

public class ModTrees {
//    public static final DeferredRegister<Tree> TREES = DeferredRegister.create(Tree.TREES, SinoCraft.MODID);

    // Todo: qyl: Need more test. 2022.1.19.
    // Todo: qyl: Replace it by our own Registry Manager. 2022.1.22.
//    public static final RegistryObject<Tree> PEACH_TREE = TREES.register("peach_tree", () -> new Tree(new TreeData("peach_tree",  MaterialColor.COLOR_PINK, MaterialColor.TERRACOTTA_PINK, new OakTreeGrower(), ModItems.PEACH)));
//    public static final RegistryObject<Tree> WALNUT_TREE = TREES.register("walnut_tree", () -> new Tree(new TreeData("walnut_tree", MaterialColor.COLOR_PURPLE, MaterialColor.COLOR_PURPLE, new BirchTreeGrower(), null)));
//    public static final RegistryObject<Tree> PLUM_TREE = TREES.register("plum_tree", () -> new Tree(new TreeData("plum_tree", MaterialColor.TERRACOTTA_PINK, MaterialColor.TERRACOTTA_PINK, new SpruceTreeGrower(), null)));
//    public static final RegistryObject<Tree> MULBERRY_TREE = TREES.register("mulberry_tree", () -> new Tree(new TreeData("mulberry_tree", MaterialColor.COLOR_PURPLE, MaterialColor.COLOR_PURPLE, new OakTreeGrower(), null)));

//    public static final RegistryObject<Tree> PEACH_TREE = Tree.TREES.put("peach_tree", () -> new Tree(new TreeData("peach_tree",  MaterialColor.COLOR_PINK, MaterialColor.TERRACOTTA_PINK, new OakTreeGrower(), ModItems.PEACH)));
    public static final Supplier<Tree> WALNUT_TREE = Tree.TREES.put("walnut_tree", () -> new Tree(new TreeData("walnut_tree", MaterialColor.COLOR_PURPLE, MaterialColor.COLOR_PURPLE, new BirchTreeGrower(), null)));
    public static final Supplier<Tree> PLUM_TREE = Tree.TREES.put("plum_tree", () -> new Tree(new TreeData("plum_tree", MaterialColor.TERRACOTTA_PINK, MaterialColor.TERRACOTTA_PINK, new SpruceTreeGrower(), null)));
    public static final Supplier<Tree> MULBERRY_TREE = Tree.TREES.put("mulberry_tree", () -> new Tree(new TreeData("mulberry_tree", MaterialColor.COLOR_PURPLE, MaterialColor.COLOR_PURPLE, new OakTreeGrower(), null)));


    public ModTrees(IEventBus bus) {
        SinoCraft.getInstance().getLogger().info("Let me see how many trees we have grown chunk by chunk, TiKang.");
//        TREES.register(bus);
    }
}
