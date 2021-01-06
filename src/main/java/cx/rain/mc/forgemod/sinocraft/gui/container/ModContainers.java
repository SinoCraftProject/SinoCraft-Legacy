package cx.rain.mc.forgemod.sinocraft.gui.container;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static final DeferredRegister<ContainerType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.CONTAINERS, SinoCraft.MODID);
    public static RegistryObject<ContainerType<ContainerChineseBrush>> CHINESE_BRUSH = REGISTRY.register("chinese_brush", () ->
            IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) ->
                    new ContainerChineseBrush(windowId, new Inventory(3), inv)
            )
    );

    public ModContainers(IEventBus bus) {
        SinoCraft.getInstance().getLogger().info("Registering containers.");
        REGISTRY.register(bus);
    }
}
