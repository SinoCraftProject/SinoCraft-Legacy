package cx.rain.mc.forgemod.sinocraft.crafting.vat;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

public class SoakRecipes extends ForgeRegistryEntry<SoakRecipes> {
    public static IForgeRegistry<SoakRecipes> REGISTRY = new RegistryBuilder<SoakRecipes>()
            .setType(SoakRecipes.class)
            .setName(new ResourceLocation(SinoCraft.MODID, "vat_recipes"))
            .allowModification()
            .create();
}
