package cx.rain.mc.forgemod.culturecraft.item;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {
    public static final DeferredRegister<Item> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.ITEMS, CultureCraft.MODID);

    public static RegistryObject<Item> PEPPER_CHILI_SEED =
            REGISTRY.register("pepper_chili_seed", () -> new BlockNamedItem(Blocks.PEPPER_CHILI_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.MISC)
                            .setNoRepair()));

    public static RegistryObject<Item> PEPPER_GREEN_SEED =
            REGISTRY.register("pepper_green_seed", () -> new BlockNamedItem(Blocks.PEPPER_GREEN_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.MISC)
                            .setNoRepair()));

    public static RegistryObject<Item> EGGPLANT_SEED =
            REGISTRY.register("eggplant_seed", () -> new BlockNamedItem(RegistryBlock.BLOCKS.get("eggplant_plant"),
                            new Item.Properties()
                                    .group(Groups.MISC)
                                    .setNoRepair()));

    public static RegistryObject<Item> SEED_RICE = REGISTRY.register("seed_rice", () ->
            new BlockNamedItem(Blocks.RICE_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.MISC)
                            .setNoRepair()));

    public static RegistryObject<Item> SEED_SOYBEAN = REGISTRY.register("seed_soybean", () ->
            new BlockNamedItem(Blocks.SOYBEAN_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.MISC)
                            .setNoRepair()));

    public Items(IEventBus bus) {
        CultureCraft.getInstance().getLog().info("Registering items.");
        REGISTRY.register(bus);
    }
}
