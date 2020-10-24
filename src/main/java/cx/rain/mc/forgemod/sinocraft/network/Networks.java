package cx.rain.mc.forgemod.sinocraft.network;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networks {
    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID ++;
    }

    public static void setup() {
        SinoCraft.getLog().info("Registering networks.");
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(SinoCraft.MODID + ":channel1"),
                () -> SinoCraft.VERSION,
                (version) -> true,
                (version) -> true
        );
    }

    public static void registerMessages() {
        INSTANCE.registerMessage(
                nextID(),
                CDrawPaper.class,
                CDrawPaper::serialize,
                CDrawPaper::new,
                CDrawPaper::handler
        );
    }
}
