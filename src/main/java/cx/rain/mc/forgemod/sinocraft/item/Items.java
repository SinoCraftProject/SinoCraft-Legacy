package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.entity.Entities;
import cx.rain.mc.forgemod.sinocraft.enumerate.PlantType;
import cx.rain.mc.forgemod.sinocraft.fluid.Fluids;
import cx.rain.mc.forgemod.sinocraft.group.Groups;
import cx.rain.mc.forgemod.sinocraft.gui.GuiXuanPaper;
import cx.rain.mc.forgemod.sinocraft.gui.container.ContainerChineseBrushProvider;
import cx.rain.mc.forgemod.sinocraft.item.base.ItemFood;
import cx.rain.mc.forgemod.sinocraft.item.base.ItemSeed;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class Items {
    public static final DeferredRegister<Item> REGISTRY =
            DeferredRegister.create(ForgeRegistries.ITEMS, SinoCraft.MODID);

    public static RegistryObject<Item> CHILI_PEPPER_SEED = REGISTRY.register("chili_pepper_seed", () -> new ItemSeed(Blocks.CHILI_PEPPER_PLANT.get(), PlantType.CHILI_PEPPER));
    public static RegistryObject<Item> GREEN_PEPPER_SEED = REGISTRY.register("green_pepper_seed", () -> new ItemSeed(Blocks.GREEN_PEPPER_PLANT.get(), PlantType.GREEN_PEPPER));
    //public static RegistryObject<Item> EGGPLANT_SEED = REGISTRY.register("eggplant_seed", () -> new ItemSeed(Blocks.EGGPLANT_PLANT.get(), PlantType.EGGPLANT));
    //public static RegistryObject<Item> CABBAGE_SEED = REGISTRY.register("cabbage_seed", () -> new ItemSeed(Blocks.CABBAGE_PLANT.get(), PlantType.CABBAGE));
    //public static RegistryObject<Item> RICE_SEED = REGISTRY.register("rice_seed", () -> new ItemSeed(Blocks.RICE_PLANT.get(), PlantType.RICE));
    //public static RegistryObject<Item> MILLET_SEED = REGISTRY.register("millet_seed", () -> new ItemSeed(Blocks.MILLET_PLANT.get(), PlantType.MILLET));
    //public static RegistryObject<Item> SORGHUM_SEED = REGISTRY.register("sorghum_seed", () -> new ItemSeed(Blocks.SORGHUM_PLANT.get(), PlantType.SORGHUM));

    //public static RegistryObject<Item> SOYBEAN = REGISTRY.register("soybean", () -> new ItemSeed(Blocks.SOYBEAN_PLANT.get(), PlantType.SOYBEAN));
    public static RegistryObject<Item> RICE = REGISTRY.register("rice", () -> new Item(new Item.Properties().group(Groups.AGRICULTURE)));
    public static RegistryObject<Item> MILLET = REGISTRY.register("millet", () -> new Item(new Item.Properties().group(Groups.AGRICULTURE)));
    public static RegistryObject<Item> SORGHUM = REGISTRY.register("sorghum", () -> new Item(new Item.Properties().group(Groups.AGRICULTURE)));
    //public static RegistryObject<Item> SILKWORM = REGISTRY.register("silkworm", () -> new Item(new Item.Properties().group(Groups.AGRICULTURE)));

    public static RegistryObject<Item> FLOUR = REGISTRY.register("flour", () -> new Item(new Item.Properties().group(Groups.FOODS)));
    public static RegistryObject<Item> DOUGH = REGISTRY.register("dough", () -> new Item(new Item.Properties().group(Groups.FOODS)));
    public static RegistryObject<Item> DUMPLING_WRAPPER = REGISTRY.register("dumpling_wrapper", () -> new Item(new Item.Properties().group(Groups.FOODS)));
    public static RegistryObject<Item> STUFFING = REGISTRY.register("stuffing", () -> new Item(new Item.Properties().group(Groups.FOODS)));
    public static RegistryObject<Item> DUMPLING = REGISTRY.register("dumpling", () -> new Item(new Item.Properties().group(Groups.FOODS)));
    public static RegistryObject<Item> COOKED_DUMPLING = REGISTRY.register("cooked_dumpling", () -> new ItemFood(new Food.Builder().hunger(6).saturation(0.2f).build()));
    public static RegistryObject<Item> PEACH = REGISTRY.register("peach", () -> new ItemFood(new Food.Builder().hunger(2).saturation(3).build()));
    public static RegistryObject<Item> CHILI_PEPPER = REGISTRY.register("chili_pepper", () -> new ItemFood(new Food.Builder().hunger(1).saturation(2).build()));
    public static RegistryObject<Item> GREEN_PEPPER = REGISTRY.register("green_pepper", () -> new ItemFood(new Food.Builder().hunger(2).saturation(3).build()));
    public static RegistryObject<Item> EGGPLANT = REGISTRY.register("eggplant", () -> new ItemFood(new Food.Builder().hunger(3).saturation(4).build()));
    public static RegistryObject<Item> CABBAGE = REGISTRY.register("cabbage", () -> new ItemFood(new Food.Builder().hunger(3).saturation(4).build()));

    public static RegistryObject<Item> BARK = REGISTRY.register("bark",()->new Item(new Item.Properties().group(Groups.MISC)));
    public static RegistryObject<Item> BUCKET_WOOD_PULP = REGISTRY.register("bucket_wood_pulp", () -> new BucketItem(Fluids.WOOD_PULP, new Item.Properties().group(Groups.MISC).containerItem(net.minecraft.item.Items.BUCKET)));
    public static RegistryObject<Item> XUAN_PAPER = REGISTRY.register("xuan_paper",()->new Item(new Item.Properties().group(Groups.MISC).maxStackSize(1)) {
        @Override
        public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
            if (world.isRemote) {
                DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
                    Minecraft.getInstance().displayGuiScreen(GuiXuanPaper.create(player.getHeldItem(hand).getOrCreateTag().getByteArray("pixels")));
                });
            }
            return ActionResult.resultSuccess(player.getHeldItem(hand));
        }
    });
    public static RegistryObject<Item> CHINA_INK = REGISTRY.register("china_ink",()->new Item(new Item.Properties().group(Groups.MISC).defaultMaxDamage(186)) {
        @Override
        public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
            return 0;
        }
    });
    public static RegistryObject<Item> CHARCOAL_BLACK = REGISTRY.register("charcoal_black",()->new Item(new Item.Properties().group(Groups.MISC)));
    
    public static RegistryObject<Item> KNIFE_IRON = REGISTRY.register("knife_iron",()->new ItemKnife(ItemTier.IRON));
    public static RegistryObject<Item> KNIFE_GOLD = REGISTRY.register("knife_gold",()->new ItemKnife(ItemTier.GOLD));
    public static RegistryObject<Item> KNIFE_DIAMOND = REGISTRY.register("knife_diamond",()->new ItemKnife(ItemTier.DIAMOND));
    public static RegistryObject<Item> CHINESE_BRUSH = REGISTRY.register("chinese_brush",()->new ItemChineseBrush());
    public static RegistryObject<Item> INK_STONE = REGISTRY.register("ink_stone",()->new Item(new Item.Properties().group(Groups.TOOLS)));

    public static RegistryObject<Item> BUFFALO_SPAWN_EGG = REGISTRY.register("spawn_egg_buffalo", () -> new ModSpawnEggItem(Entities.ENTITY_BUFFALO, 0xae782d, 0xc6c6c6, new Item.Properties().group(ItemGroup.MISC)));

    public Items(IEventBus bus) {
        SinoCraft.getLog().info("Registering items.");
        REGISTRY.register(bus);
    }
}
