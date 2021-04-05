package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.entity.ModEntities;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.PlantType;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.group.ModGroups;
import cx.rain.mc.forgemod.sinocraft.gui.GuiXuanPaper;
import cx.rain.mc.forgemod.sinocraft.item.base.ItemFood;
import cx.rain.mc.forgemod.sinocraft.item.base.ItemSeed;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class ModItems {
    public static final DeferredRegister<Item> REGISTRY =
            DeferredRegister.create(ForgeRegistries.ITEMS, SinoCraft.MODID);

    public static RegistryObject<Item> CHILI_PEPPER_SEED = REGISTRY.register("chili_pepper_seed", () -> new ItemSeed(ModBlocks.CHILI_PEPPER_PLANT.get(), PlantType.CHILI_PEPPER));
    public static RegistryObject<Item> GREEN_PEPPER_SEED = REGISTRY.register("green_pepper_seed", () -> new ItemSeed(ModBlocks.GREEN_PEPPER_PLANT.get(), PlantType.GREEN_PEPPER));
    public static RegistryObject<Item> EGGPLANT_SEED = REGISTRY.register("eggplant_seed", () -> new ItemSeed(ModBlocks.EGGPLANT_PLANT.get(), PlantType.EGGPLANT));
    public static RegistryObject<Item> CABBAGE_SEED = REGISTRY.register("cabbage_seed", () -> new ItemSeed(ModBlocks.CABBAGE_PLANT.get(), PlantType.CABBAGE));
    public static RegistryObject<Item> RICE_SEED = REGISTRY.register("rice_seed", () -> new ItemSeed(ModBlocks.RICE_PLANT.get(), PlantType.RICE));
    public static RegistryObject<Item> MILLET_SEED = REGISTRY.register("millet_seed", () -> new ItemSeed(ModBlocks.MILLET_PLANT.get(), PlantType.MILLET));
    public static RegistryObject<Item> SORGHUM_SEED = REGISTRY.register("sorghum_seed", () -> new ItemSeed(ModBlocks.SORGHUM_PLANT.get(), PlantType.SORGHUM));

    public static RegistryObject<Item> SOYBEAN = REGISTRY.register("soybean", () -> new ItemSeed(ModBlocks.SOYBEAN_PLANT.get(), PlantType.SOYBEAN));
    public static RegistryObject<Item> RICE = REGISTRY.register("rice", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
    public static RegistryObject<Item> MILLET = REGISTRY.register("millet", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
    public static RegistryObject<Item> SORGHUM = REGISTRY.register("sorghum", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));
    public static RegistryObject<Item> SILKWORM = REGISTRY.register("silkworm", () -> new Item(new Item.Properties().group(ModGroups.AGRICULTURE)));

    public static RegistryObject<Item> FLOUR = REGISTRY.register("flour", () -> new Item(new Item.Properties().group(ModGroups.FOODS)));
    public static RegistryObject<Item> DOUGH = REGISTRY.register("dough", () -> new Item(new Item.Properties().group(ModGroups.FOODS)));
    public static RegistryObject<Item> DUMPLING_WRAPPER = REGISTRY.register("dumpling_wrapper", () -> new Item(new Item.Properties().group(ModGroups.FOODS)));
    public static RegistryObject<Item> STUFFING = REGISTRY.register("stuffing", () -> new Item(new Item.Properties().group(ModGroups.FOODS)));
    public static RegistryObject<Item> DUMPLING = REGISTRY.register("dumpling", () -> new Item(new Item.Properties().group(ModGroups.FOODS)));
    public static RegistryObject<Item> COOKED_DUMPLING = REGISTRY.register("cooked_dumpling", () -> new ItemFood(new Food.Builder().hunger(6).saturation(0.2f).build()));
    public static RegistryObject<Item> PEACH = REGISTRY.register("peach", () -> new ItemFood(new Food.Builder().hunger(2).saturation(3).build()));
    public static RegistryObject<Item> CHILI_PEPPER = REGISTRY.register("chili_pepper", () -> new ItemFood(new Food.Builder().hunger(1).saturation(2).build()));
    public static RegistryObject<Item> GREEN_PEPPER = REGISTRY.register("green_pepper", () -> new ItemFood(new Food.Builder().hunger(2).saturation(3).build()));
    public static RegistryObject<Item> EGGPLANT = REGISTRY.register("eggplant", () -> new ItemFood(new Food.Builder().hunger(3).saturation(4).build()));
    public static RegistryObject<Item> CABBAGE = REGISTRY.register("cabbage", () -> new ItemFood(new Food.Builder().hunger(3).saturation(4).build()));
    public static RegistryObject<Item> ADUST_FOOD = REGISTRY.register("adust_food", () -> new ItemFood(new Food.Builder().hunger(1).saturation(0).build()));

    public static RegistryObject<Item> BARK = REGISTRY.register("bark", () -> new Item(new Item.Properties().group(ModGroups.MISC)));
    public static RegistryObject<Item> BUCKET_WOOD_PULP = REGISTRY.register("bucket_wood_pulp", () -> new BucketItem(ModFluids.WOOD_PULP, new Item.Properties().group(ModGroups.MISC).containerItem(net.minecraft.item.Items.BUCKET)));
    public static RegistryObject<Item> EMPTY_XUAN_PAPER = REGISTRY.register("empty_xuan_paper", () -> new Item(new Item.Properties().group(ModGroups.MISC)));
    public static RegistryObject<Item> XUAN_PAPER = REGISTRY.register("xuan_paper", () -> new XuanPaper());
    public static RegistryObject<Item> TUTORIAL_BOOK = REGISTRY.register("tutorial_book", () -> new Item(new Item.Properties().group(ModGroups.MISC).maxStackSize(1)) {
        @Override
        public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
            if (world.isRemote) {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                    Minecraft.getInstance().displayGuiScreen(
                            GuiTutorialBook.create(new ResourceLocation("sinocraft:tutorial"))
                    );
                });
            }
            return ActionResult.resultSuccess(player.getHeldItem(hand));
        }
    });
    public static RegistryObject<Item> CHINA_INK = REGISTRY.register("china_ink", () -> new Item(new Item.Properties().group(ModGroups.MISC).defaultMaxDamage(186)) {
        @Override
        public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
            return 0;
        }
    });
    public static RegistryObject<Item> CHARCOAL_BLACK = REGISTRY.register("charcoal_black", () -> new Item(new Item.Properties().group(ModGroups.MISC)));

    public static RegistryObject<Item> KNIFE_IRON = REGISTRY.register("knife_iron", () -> new ItemKnife(ItemTier.IRON));
    public static RegistryObject<Item> KNIFE_GOLD = REGISTRY.register("knife_gold", () -> new ItemKnife(ItemTier.GOLD));
    public static RegistryObject<Item> KNIFE_DIAMOND = REGISTRY.register("knife_diamond", () -> new ItemKnife(ItemTier.DIAMOND));
    public static RegistryObject<Item> CHINESE_BRUSH = REGISTRY.register("chinese_brush", () -> new ItemChineseBrush());
    public static RegistryObject<Item> INK_STONE = REGISTRY.register("ink_stone", () -> new Item(new Item.Properties().group(ModGroups.TOOLS)));

    public static RegistryObject<Item> BUFFALO_SPAWN_EGG = REGISTRY.register("spawn_egg_buffalo", () -> new ModSpawnEggItem(ModEntities.ENTITY_BUFFALO, 0xae782d, 0xc6c6c6, new Item.Properties().group(ItemGroup.MISC)));

    public ModItems(IEventBus bus) {
        SinoCraft.getLogger().info("Registering items.");
        REGISTRY.register(bus);
    }
}
