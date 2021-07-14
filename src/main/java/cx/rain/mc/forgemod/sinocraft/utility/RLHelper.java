package cx.rain.mc.forgemod.sinocraft.utility;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class RLHelper {
    public static ResourceLocation modLoc(String name) {
        return new ResourceLocation(SinoCraft.MODID, name);
    }

    public static ResourceLocation dropLoc(String name) {
        return modLoc("drops/" + name);
    }
}
