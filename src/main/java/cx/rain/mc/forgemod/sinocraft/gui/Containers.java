package cx.rain.mc.forgemod.sinocraft.gui;

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

public class Containers {
    public static final DeferredRegister<ContainerType<?>> REGISTRY = new DeferredRegister<>(ForgeRegistries.CONTAINERS, SinoCraft.MODID);
    public static RegistryObject<ContainerType<ContainerChineseBrush>> CHINESE_BRUSH = REGISTRY.register("chinese_brush", () ->
            IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) ->
                    new ContainerChineseBrush(windowId, new Inventory(2), inv)
            )
    );

    public Containers(IEventBus bus) {
        SinoCraft.getInstance().getLog().info("Registering items.");
        REGISTRY.register(bus);
    }
}
