package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.enumerate.LogType;
import cx.rain.mc.forgemod.sinocraft.enumerate.PlantType;
import cx.rain.mc.forgemod.sinocraft.fluid.Fluids;
import cx.rain.mc.forgemod.sinocraft.group.Groups;
import cx.rain.mc.forgemod.sinocraft.item.base.ItemFood;
import cx.rain.mc.forgemod.sinocraft.item.base.ItemSeed;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {
    public static final DeferredRegister<Item> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.ITEMS, SinoCraft.MODID);

    public static RegistryObject<Item> CHILI_PEPPER_SEED = REGISTRY.register("chili_pepper_seed", () -> new ItemSeed(Blocks.CHILI_PEPPER_PLANT.get(), PlantType.CHILI_PEPPER));
    public static RegistryObject<Item> GREEN_PEPPER_SEED = REGISTRY.register("green_pepper_seed", () -> new ItemSeed(Blocks.GREEN_PEPPER_PLANT.get(), PlantType.GREEN_PEPPER));
    public static RegistryObject<Item> EGGPLANT_SEED = REGISTRY.register("eggplant_seed", () -> new ItemSeed(Blocks.EGGPLANT_PLANT.get(), PlantType.EGGPLANT));
    public static RegistryObject<Item> CABBAGE_SEED = REGISTRY.register("cabbage_seed", () -> new ItemSeed(Blocks.CABBAGE_PLANT.get(), PlantType.CABBAGE));
    public static RegistryObject<Item> RICE_SEED = REGISTRY.register("rice_seed", () -> new ItemSeed(Blocks.RICE_PLANT.get(), PlantType.RICE));
    public static RegistryObject<Item> MILLET_SEED = REGISTRY.register("millet_seed", () -> new ItemSeed(Blocks.MILLET_PLANT.get(), PlantType.MILLET));
    public static RegistryObject<Item> SORGHUM_SEED = REGISTRY.register("sorghum_seed", () -> new ItemSeed(Blocks.SORGHUM_PLANT.get(), PlantType.SORGHUM));

    public static RegistryObject<Item> SOYBEAN = REGISTRY.register("soybean", () -> new ItemSeed(Blocks.SOYBEAN_PLANT.get(), PlantType.SOYBEAN));
    public static RegistryObject<Item> RICE = REGISTRY.register("rice", () -> new Item(new Item.Properties().group(Groups.AGRICULTURE)));
    public static RegistryObject<Item> MILLET = REGISTRY.register("millet", () -> new Item(new Item.Properties().group(Groups.AGRICULTURE)));
    public static RegistryObject<Item> SORGHUM = REGISTRY.register("sorghum", () -> new Item(new Item.Properties().group(Groups.AGRICULTURE)));
    public static RegistryObject<Item> SILKWORM = REGISTRY.register("silkworm", () -> new Item(new Item.Properties().group(Groups.AGRICULTURE)));

    public static RegistryObject<Item> PEACH = REGISTRY.register("peach", () -> new ItemFood(new Food.Builder().hunger(2).saturation(3).fastToEat().build()));
    public static RegistryObject<Item> CHILI_PEPPER = REGISTRY.register("chili_pepper", () -> new ItemFood(new Food.Builder().hunger(1).saturation(2).fastToEat().build()));
    public static RegistryObject<Item> GREEN_PEPPER = REGISTRY.register("green_pepper", () -> new ItemFood(new Food.Builder().hunger(2).saturation(3).fastToEat().build()));
    public static RegistryObject<Item> EGGPLANT = REGISTRY.register("eggplant", () -> new ItemFood(new Food.Builder().hunger(3).saturation(4).fastToEat().build()));
    public static RegistryObject<Item> CABBAGE = REGISTRY.register("cabbage", () -> new ItemFood(new Food.Builder().hunger(3).saturation(4).fastToEat().build()));

    public static RegistryObject<Item> BARK = REGISTRY.register("bark",()->new Item(new Item.Properties().group(Groups.MISC)));
    public static RegistryObject<Item> BUCKET_WOOD_PULP = REGISTRY.register("bucket_wood_pulp", () -> new BucketItem(Fluids.WOOD_PULP, new Item.Properties().group(Groups.MISC).containerItem(net.minecraft.item.Items.BUCKET)));
    public static RegistryObject<Item> XUAN_PAPER = REGISTRY.register("xuan_paper",()->new Item(new Item.Properties().group(Groups.MISC)));
    public static RegistryObject<Item> CHINA_INK = REGISTRY.register("china_ink",()->new Item(new Item.Properties().group(Groups.MISC).defaultMaxDamage(136)));

    public static RegistryObject<Item> KNIFE_IRON = REGISTRY.register("knife_iron",()->new ItemKnife(ItemTier.IRON));
    public static RegistryObject<Item> KNIFE_GOLD = REGISTRY.register("knife_gold",()->new ItemKnife(ItemTier.GOLD));
    public static RegistryObject<Item> KNIFE_DIAMOND = REGISTRY.register("knife_diamond",()->new ItemKnife(ItemTier.DIAMOND));
    public static RegistryObject<Item> CHINESE_BRUSH = REGISTRY.register("chinese_brush",()->new ItemChineseBrush());

    public Items(IEventBus bus) {
        SinoCraft.getInstance().getLog().info("Registering items.");
        REGISTRY.register(bus);
    }
}
