package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SinoCraft.MODID);

//    public static RegistryObject<ItemSeed> CHILI_PEPPER_SEED = ITEMS.register("chili_pepper_seed", () -> new ItemSeed(PlantType.CHILI_PEPPER));
//    public static RegistryObject<ItemSeed> GREEN_PEPPER_SEED = ITEMS.register("green_pepper_seed", () -> new ItemSeed(PlantType.GREEN_PEPPER));
//    public static RegistryObject<ItemSeed> EGGPLANT_SEED = ITEMS.register("eggplant_seed", () -> new ItemSeed(PlantType.EGGPLANT));
//    public static RegistryObject<ItemSeed> CABBAGE_SEED = ITEMS.register("cabbage_seed", () -> new ItemSeed(PlantType.CABBAGE));
//    public static RegistryObject<ItemSeed> RICE_SEED = ITEMS.register("rice_seed", () -> new ItemSeed(PlantType.RICE));
//    public static RegistryObject<ItemSeed> MILLET_SEED = ITEMS.register("millet_seed", () -> new ItemSeed(PlantType.MILLET));
//    public static RegistryObject<ItemSeed> SORGHUM_SEED = ITEMS.register("sorghum_seed", () -> new ItemSeed(PlantType.SORGHUM));
//    public static RegistryObject<ItemSeed> SOYBEAN = ITEMS.register("soybean", () -> new ItemSeed(PlantType.SOYBEAN));
//    public static RegistryObject<ItemFood> PEACH = ITEMS.register("peach", () -> new ItemFood(EnumFoods.PEACH));
//    public static RegistryObject<ItemFood> CHILI_PEPPER = ITEMS.register("chili_pepper", () -> new ItemFood(EnumFoods.CHILI_PEPPER));
//    public static RegistryObject<ItemFood> GREEN_PEPPER = ITEMS.register("green_pepper", () -> new ItemFood(EnumFoods.GREEN_PEPPER));
//    public static RegistryObject<ItemFood> EGGPLANT = ITEMS.register("eggplant", () -> new ItemFood(EnumFoods.EGGPLANT));
//    public static RegistryObject<ItemFood> CABBAGE = ITEMS.register("cabbage", () -> new ItemFood(EnumFoods.CABBAGE));
//    public static RegistryObject<ItemFood> ADUST_FOOD = ITEMS.register("adust_food", () -> new ItemFood(EnumFoods.ADUST_FOOD));
//    public static RegistryObject<ItemFood> COOKED_DUMPLING = ITEMS.register("cooked_dumpling", () -> new ItemFood(EnumFoods.COOKED_DUMPLING));
//    public static RegistryObject<ItemFood> BOWL_WITH_RICE = ITEMS.register("bowl_with_rice", () -> new ItemFood(EnumFoods.BOWL_WITH_RICE));
//    public static RegistryObject<ItemFood> BOWL_WITH_WATER = ITEMS.register("bowl_with_water", () -> new ItemFood(EnumFoods.BOWL_WITH_WATER));
//    public static RegistryObject<ItemFood> BOWL_WITH_PORRIDGE = ITEMS.register("bowl_with_porridge", () -> new ItemFood(EnumFoods.BOWL_WITH_PORRIDGE));
//    public static RegistryObject<ItemKnife> KNIFE_IRON = ITEMS.register("knife_iron", () -> new ItemKnife(ItemTier.IRON));
//    public static RegistryObject<ItemKnife> KNIFE_GOLD = ITEMS.register("knife_gold", () -> new ItemKnife(ItemTier.GOLD));
//    public static RegistryObject<ItemKnife> KNIFE_DIAMOND = ITEMS.register("knife_diamond", () -> new ItemKnife(ItemTier.DIAMOND));
//    public static RegistryObject<ItemBucket> BUCKET_WOOD_PULP = ITEMS.register("bucket_wood_pulp", () -> new ItemBucket(ModFluids.WOOD_PULP));
//    public static RegistryObject<Item> STUFFING = ITEMS.register("stuffing", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
//    public static RegistryObject<Item> MILLET = ITEMS.register("millet", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
//    public static RegistryObject<Item> DUMPLING = ITEMS.register("dumpling", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
//    public static RegistryObject<Item> SORGHUM = ITEMS.register("sorghum", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
//    public static RegistryObject<Item> SILKWORM = ITEMS.register("silkworm", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
//    public static RegistryObject<Item> FLOUR = ITEMS.register("flour", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
//    public static RegistryObject<Item> RICE = ITEMS.register("rice", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
//    public static RegistryObject<Item> TEA_LEAF = ITEMS.register("tea_leaf", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
//    public static RegistryObject<Item> DUMPLING_WRAPPER = ITEMS.register("dumpling_wrapper", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
//    public static RegistryObject<Item> DOUGH = ITEMS.register("dough", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
//    public static RegistryObject<Item> EMPTY_XUAN_PAPER = ITEMS.register("empty_xuan_paper", () -> new Item(new Item.Properties().group(ModGroups.MISC)));
//    public static RegistryObject<Item> DISH = ITEMS.register("dish", () -> new Item(new Item.Properties().group(ModGroups.MISC).maxStackSize(16).setNoRepair()));
//    public static RegistryObject<Item> BARK = ITEMS.register("bark", () -> new Item(new Item.Properties().group(ModGroups.MISC)));
//    public static RegistryObject<Item> INK_STONE = ITEMS.register("ink_stone", () -> new Item(new Item.Properties().group(ModGroups.MISC)));
//    public static RegistryObject<Item> CHINESE_INK = ITEMS.register("chinese_ink", () -> new Item(new Item.Properties().group(ModGroups.MISC)));
//    public static RegistryObject<Item> CHARCOAL_BLACK = ITEMS.register("charcoal_black", () -> new Item(new Item.Properties().group(ModGroups.MISC)));
//    public static RegistryObject<ItemChineseBrush> CHINESE_BRUSH = ITEMS.register("chinese_brush", ItemChineseBrush::new);
//    public static RegistryObject<ItemClothHelmet> CLOTH_HELMET = ITEMS.register("cloth_helmet", ItemClothHelmet::new);
//    public static RegistryObject<ItemHeroesAssemble> HEROES_ASSEMBLE = ITEMS.register("heroes_assemble", ItemHeroesAssemble::new);
//    public static RegistryObject<ItemTeacup> TEACUP = ITEMS.register("teacup", ItemTeacup::new);
//    public static RegistryObject<ItemTeapot> TEAPOT = ITEMS.register("teapot", ItemTeapot::new);
//    public static RegistryObject<ItemXuanPaper> XUAN_PAPER = ITEMS.register("xuan_paper", ItemXuanPaper::new);

    public ModItems(IEventBus bus) {
        SinoCraft.getInstance().getLogger().info("Registering items.");
        ITEMS.register(bus);
    }
}
