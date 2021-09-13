package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.item.base.ItemBucket;
import cx.rain.mc.forgemod.sinocraft.item.base.ItemSeed;
import cx.rain.mc.forgemod.sinocraft.item.food.EnumFoods;
import cx.rain.mc.forgemod.sinocraft.item.food.ItemFood2;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.PlantType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

class ModItems2 {
  public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, SinoCraft.MODID);

  public static RegistryObject<ItemSeed> CHILI_PEPPER_SEED = REGISTRY.register("chili_pepper_seed", () -> new ItemSeed(PlantType.CHILI_PEPPER));

  public static RegistryObject<ItemSeed> GREEN_PEPPER_SEED = REGISTRY.register("green_pepper_seed", () -> new ItemSeed(PlantType.GREEN_PEPPER));

  public static RegistryObject<ItemSeed> EGGPLANT_SEED = REGISTRY.register("eggplant_seed", () -> new ItemSeed(PlantType.EGGPLANT));

  public static RegistryObject<ItemSeed> CABBAGE_SEED = REGISTRY.register("cabbage_seed", () -> new ItemSeed(PlantType.CABBAGE));

  public static RegistryObject<ItemSeed> RICE_SEED = REGISTRY.register("rice_seed", () -> new ItemSeed(PlantType.RICE));

  public static RegistryObject<ItemSeed> MILLET_SEED = REGISTRY.register("millet_seed", () -> new ItemSeed(PlantType.MILLET));

  public static RegistryObject<ItemSeed> SORGHUM_SEED = REGISTRY.register("sorghum_seed", () -> new ItemSeed(PlantType.SORGHUM));

  public static RegistryObject<ItemSeed> SOYBEAN = REGISTRY.register("soybean", () -> new ItemSeed(PlantType.SOYBEAN));

  public static RegistryObject<ItemFood2> PEACH = REGISTRY.register("peach", () -> new ItemFood2(EnumFoods.PEACH));

  public static RegistryObject<ItemFood2> CHILI_PEPPER = REGISTRY.register("chili_pepper", () -> new ItemFood2(EnumFoods.CHILI_PEPPER));

  public static RegistryObject<ItemFood2> GREEN_PEPPER = REGISTRY.register("green_pepper", () -> new ItemFood2(EnumFoods.GREEN_PEPPER));

  public static RegistryObject<ItemFood2> EGGPLANT = REGISTRY.register("eggplant", () -> new ItemFood2(EnumFoods.EGGPLANT));

  public static RegistryObject<ItemFood2> CABBAGE = REGISTRY.register("cabbage", () -> new ItemFood2(EnumFoods.CABBAGE));

  public static RegistryObject<ItemFood2> ADUST_FOOD = REGISTRY.register("adust_food", () -> new ItemFood2(EnumFoods.ADUST_FOOD));

  public static RegistryObject<ItemFood2> COOKED_DUMPLING = REGISTRY.register("cooked_dumpling", () -> new ItemFood2(EnumFoods.COOKED_DUMPLING));

  public static RegistryObject<ItemFood2> BOWL_WITH_RICE = REGISTRY.register("bowl_with_rice", () -> new ItemFood2(EnumFoods.BOWL_WITH_RICE));

  public static RegistryObject<ItemFood2> BOWL_WITH_WATER = REGISTRY.register("bowl_with_water", () -> new ItemFood2(EnumFoods.BOWL_WITH_WATER));

  public static RegistryObject<ItemFood2> BOWL_WITH_PORRIDGE = REGISTRY.register("bowl_with_porridge", () -> new ItemFood2(EnumFoods.BOWL_WITH_PORRIDGE));

  public static RegistryObject<ItemKnife> KNIFE_IRON = REGISTRY.register("knife_iron", () -> new ItemKnife(ItemTier.IRON));

  public static RegistryObject<ItemKnife> KNIFE_GOLD = REGISTRY.register("knife_gold", () -> new ItemKnife(ItemTier.GOLD));

  public static RegistryObject<ItemKnife> KNIFE_DIAMOND = REGISTRY.register("knife_diamond", () -> new ItemKnife(ItemTier.DIAMOND));

  public static RegistryObject<ItemBucket> BUCKET_WOOD_PULP = REGISTRY.register("bucket_wood_pulp", () -> new ItemBucket(ModFluids.WOOD_PULP));

  public static RegistryObject<Item> STUFFING = REGISTRY.register("stuffing", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

  public static RegistryObject<Item> MILLET = REGISTRY.register("millet", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

  public static RegistryObject<Item> DUMPLING = REGISTRY.register("dumpling", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

  public static RegistryObject<Item> SORGHUM = REGISTRY.register("sorghum", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

  public static RegistryObject<Item> SILKWORM = REGISTRY.register("silkworm", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

  public static RegistryObject<Item> FLOUR = REGISTRY.register("flour", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

  public static RegistryObject<Item> RICE = REGISTRY.register("rice", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

  public static RegistryObject<Item> TEA_LEAF = REGISTRY.register("tea_leaf", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

  public static RegistryObject<Item> DUMPLING_WRAPPER = REGISTRY.register("dumpling_wrapper", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

  public static RegistryObject<Item> DOUGH = REGISTRY.register("dough", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

  public static RegistryObject<Item> EMPTY_XUAN_PAPER = REGISTRY.register("empty_xuan_paper", () -> new Item(new Item.Properties().group(ModGroups.MISC)));

  public static RegistryObject<Item> DISH = REGISTRY.register("dish", () -> new Item(new Item.Properties().group(ModGroups.MISC).maxStackSize(16).setNoRepair()));

  public static RegistryObject<Item> BARK = REGISTRY.register("bark", () -> new Item(new Item.Properties().group(ModGroups.MISC)));

  public static RegistryObject<Item> INK_STONE = REGISTRY.register("ink_stone", () -> new Item(new Item.Properties().group(ModGroups.MISC)));

  public static RegistryObject<Item> CHINESE_INK = REGISTRY.register("chinese_ink", () -> new Item(new Item.Properties().group(ModGroups.MISC)));

  public static RegistryObject<Item> CHARCOAL_BLACK = REGISTRY.register("charcoal_black", () -> new Item(new Item.Properties().group(ModGroups.MISC)));

  public static RegistryObject<ItemChineseBrush> CHINESE_BRUSH = REGISTRY.register("chinese_brush", ItemChineseBrush::new);

  public static RegistryObject<ItemHeroesAssemble> HEROES_ASSEMBLE = REGISTRY.register("heroes_assemble", ItemHeroesAssemble::new);

  public static RegistryObject<ItemTeacup> TEACUP = REGISTRY.register("teacup", ItemTeacup::new);

  public static RegistryObject<ItemTeapot> TEAPOT = REGISTRY.register("teapot", ItemTeapot::new);

  public static RegistryObject<ItemXuanPaper> XUAN_PAPER = REGISTRY.register("xuan_paper", ItemXuanPaper::new);

  public ModItems2(IEventBus bus) {
    SinoCraft.getLogger().info("Registering items.");
    REGISTRY.register(bus);
  }
}
