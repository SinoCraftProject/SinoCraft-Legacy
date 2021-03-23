package cx.rain.mc.forgemod.sinocraft.network;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.network.packet.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networks {
    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void setup() {
        SinoCraft.getLogger().info("Registering networks.");
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(SinoCraft.MODID + "paper_drawing"),
                () -> SinoCraft.VERSION,
                SinoCraft.VERSION::equals,
                SinoCraft.VERSION::equals
        );
    }

    public static void registerMessages() {
        INSTANCE.registerMessage(
                nextID(),
                DrawPaperC2SPacket.class,
                DrawPaperC2SPacket::serialize,
                DrawPaperC2SPacket::new,
                DrawPaperC2SPacket::handler
        );
        INSTANCE.registerMessage(
                nextID(),
                GetRecipeC2SPacket.class,
                GetRecipeC2SPacket::serialize,
                GetRecipeC2SPacket::new,
                GetRecipeC2SPacket::handler
        );
        INSTANCE.registerMessage(
                nextID(),
                GetRecipeS2CPacket.class,
                GetRecipeS2CPacket::serialize,
                GetRecipeS2CPacket::new,
                GetRecipeS2CPacket::handler
        );
    }
}
