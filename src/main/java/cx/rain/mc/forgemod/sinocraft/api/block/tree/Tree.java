package cx.rain.mc.forgemod.sinocraft.api.block.tree;

import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class Tree extends ForgeRegistryEntry<Tree> {
    public static final IForgeRegistry<Tree> TREES = RegistryManager.ACTIVE.getRegistry(Tree.class);

    public Tree(TreeData tree) {
    }
}
