package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;

import cx.rain.mc.forgemod.culturecraft.api.enumerate.*;
import cx.rain.mc.forgemod.culturecraft.block.automatic.BlockMahoganyPlank;
import net.minecraft.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks {
    public static final DeferredRegister<Block> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.BLOCKS, CultureCraft.MODID);

    public static RegistryObject<Block> RADISH_WHITE_PLANT =
            REGISTRY.register("radish_white_plant", () -> new BlockRadish(RadishType.WHITE));
    public static RegistryObject<Block> RADISH_SUMMER_PLANT =
            REGISTRY.register("radish_summer_plant", () -> new BlockRadish(RadishType.SUMMER));
    public static RegistryObject<Block> RADISH_GREEN_PLANT =
            REGISTRY.register("radish_green_plant", () -> new BlockRadish(RadishType.GREEN));

    public static RegistryObject<Block> PEPPER_CHILI_PLANT =
            REGISTRY.register("pepper_chili_plant",() -> new BlockPepper(PepperType.CHILI));
    public static RegistryObject<Block> PEPPER_GREEN_PLANT =
            REGISTRY.register("pepper_green_plant",() -> new BlockPepper(PepperType.GREEN));

    public static RegistryObject<Block> CELERY_CABBAGE_PLANT =
            REGISTRY.register("celery_cabbage_plant",() -> new BlockCeleryCabbage(CeleryCabbageType.CELERY_CABBAGE));

    public static RegistryObject<Block> EGGPLANT_PLANT =
            REGISTRY.register("eggplant_plant",() -> new BlockEggplant(EggplantType.EGGPLANT));

    public static RegistryObject<Block> RICE =
            REGISTRY.register("rice_plant",() -> new BlockRice(RiceType.RICE));


    public Blocks(IEventBus bus) {
        CultureCraft.getInstance().getLog().info("Registering blocks.");
        REGISTRY.register(bus);
    }
}
