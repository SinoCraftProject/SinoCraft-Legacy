package cx.rain.mc.forgemod.sinocraft.block.tree;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.block.tree.Tree;
import cx.rain.mc.forgemod.sinocraft.api.block.tree.TreeData;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrees {
    public static final DeferredRegister<Tree> TREES = DeferredRegister.create(Tree.TREES, SinoCraft.MODID);

    public static final RegistryObject<Tree> PEACH_TREE = TREES.register("peach", () -> new Tree(new TreeData("peach", MaterialColor.TERRACOTTA_PINK, ModItems.PEACH)));

    public ModTrees(IEventBus bus) {
        SinoCraft.getInstance().getLogger().info("Let me see how many trees we have grown chunk by chunk, TiKang.");
        TREES.register(bus);
    }
}
