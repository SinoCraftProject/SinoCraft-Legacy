package cx.rain.mc.forgemod.sinocraft.data.provider.base;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import cx.rain.mc.forgemod.sinocraft.data.tag.TagItem;
import cx.rain.mc.forgemod.sinocraft.utility.ProtectedHelper;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.*;
import net.minecraft.command.FunctionObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ProviderBaseAdvancement implements IDataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private DataGenerator generator;
    protected final Map<ResourceLocation, Advancement.Builder> advancements = new LinkedHashMap<>();
    private static final EntityType<?>[] MOB_ENTITIES = new EntityType[]{EntityType.CAVE_SPIDER, EntityType.SPIDER, EntityType.ZOMBIFIED_PIGLIN,
            EntityType.ENDERMAN, EntityType.BLAZE, EntityType.CREEPER, EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HUSK,
            EntityType.MAGMA_CUBE, EntityType.SHULKER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.STRAY,
            EntityType.VINDICATOR, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.PHANTOM,
            EntityType.DROWNED, EntityType.PILLAGER, EntityType.RAVAGER};

    private static final EntityType<?>[] ENTITIES = new EntityType[]{EntityType.CAVE_SPIDER, EntityType.SPIDER, EntityType.ZOMBIFIED_PIGLIN,
            EntityType.ENDERMAN, EntityType.BLAZE, EntityType.CREEPER, EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HUSK,
            EntityType.MAGMA_CUBE, EntityType.SHULKER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.STRAY,
            EntityType.VINDICATOR, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.PHANTOM,
            EntityType.DROWNED, EntityType.PILLAGER, EntityType.RAVAGER, EntityType.BAT, EntityType.BEE, EntityType.CAT, EntityType.CHICKEN, EntityType.COW,
            EntityType.DOLPHIN, EntityType.DONKEY, EntityType.FOX, EntityType.HORSE, EntityType.IRON_GOLEM, EntityType.LLAMA, EntityType.MOOSHROOM, EntityType.MULE,
            EntityType.OCELOT, EntityType.PANDA, EntityType.PARROT, EntityType.PIG, EntityType.POLAR_BEAR, EntityType.PUFFERFISH, EntityType.RABBIT,
            EntityType.SALMON, EntityType.SHEEP, EntityType.SNOW_GOLEM, EntityType.SQUID, EntityType.VILLAGER, EntityType.WOLF};
    protected String modid;

    public ProviderBaseAdvancement(DataGenerator generatorIn, String modidIn) {
        generator = generatorIn;
        modid = modidIn;
    }

    protected static class AdvancementToJson {
        private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(Advancement.class, new TypeAdapter<Advancement>() {
            @Override
            public void write(JsonWriter out, Advancement value) throws IOException {
                out.beginObject();
                writeDisplay(out, value);
                if (value.getParent() != null) {
                    out.name("parent").value(value.getParent().getId().toString());
                }
                writeCriteria(out, value);
                writeRequirements(out, value);
                writeRewards(out, value);
                out.endObject();
            }

            public void writeDisplay(JsonWriter out, Advancement adv) throws IOException {
                DisplayInfo info = adv.getDisplay();
                out.name("display").beginObject();
                out.name("icon").beginObject();
                out.name("item").value(info.getIcon().getItem().getRegistryName().toString());
                if (info.getIcon().hasTag())
                    out.name("nbt").value(info.getIcon().getTag().toString());
                out.endObject();
                out.name("title").beginObject();
                out.name("translate").value(info.getTitle().getString());
                out.endObject();
                out.name("description").beginObject();
                out.name("translate").value(info.getDescription().getString());
                out.endObject();
                out.name("frame").value(info.getFrame().getName());
                if (info.getBackground() != null) {
                    out.name("background").value(info.getBackground().toString());
                }
                out.name("show_toast").value(info.shouldShowToast());
                out.name("announce_to_chat").value(info.shouldAnnounceToChat());
                out.name("hidden").value(info.isHidden());
                out.endObject();
            }

            public void writeRequirements(JsonWriter out, Advancement adv) throws IOException {
                String[][] req = adv.getRequirements();
                if (req == null || req.length == 0) {
                    return;
                }
                out.name("requirements").beginArray();
                for (String[] arr : req) {
                    out.beginArray();
                    for (String r : arr) {
                        out.value(r);
                    }
                    out.endArray();
                }
                out.endArray();
            }

            public void writeCriteria(JsonWriter out, Advancement adv) throws IOException {
                Map<String, Criterion> criteria = adv.getCriteria();
                out.name("criteria").beginObject();
                for (Map.Entry<String, Criterion> entry : criteria.entrySet()) {
                    Criterion criterion = entry.getValue();
                    JsonObject obj;
                    if (criterion.serialize().getAsJsonObject().get("conditions") == JsonNull.INSTANCE) {
                        obj = null;
                    } else {
                        obj = criterion.serialize().getAsJsonObject().getAsJsonObject("conditions");
                    }
                    out.name(entry.getKey()).beginObject();
                    out.name("trigger").value(criterion.getCriterionInstance().getId().toString());
                    if (obj != null) {
                        out.name("conditions").beginObject();
                        writeConditions(out, obj);
                        out.endObject();
                    }
                    out.endObject();
                }
                out.endObject();
            }

            public void writeConditions(JsonWriter out, JsonObject obj) throws IOException {
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                    JsonElement ele = entry.getValue();
                    if (ele.isJsonObject()) {
                        out.name(entry.getKey()).beginObject();
                        writeConditions(out, ele.getAsJsonObject());
                        out.endObject();
                    } else if (ele.isJsonPrimitive()) {
                        JsonPrimitive pri = ele.getAsJsonPrimitive();
                        if (pri.isBoolean()) {
                            out.name(entry.getKey()).value(pri.getAsBoolean());
                        } else if (pri.isNumber()) {
                            out.name(entry.getKey()).value(pri.getAsNumber());
                        } else if (pri.isString()) {
                            out.name(entry.getKey()).value(pri.getAsString());
                        }
                    } else if (ele.isJsonArray()) {
                        JsonArray array = ele.getAsJsonArray();
                        out.name(entry.getKey()).beginArray();
                        for (int i = 0; i < array.size(); i++) {
                            writeArrayValues(out, array.get(i));
                        }
                        out.endArray();
                    }
                }
            }

            public void writeArrayValues(JsonWriter out, JsonElement ele) throws IOException {
                if (ele.isJsonObject()) {
                    out.beginObject();
                    writeConditions(out, ele.getAsJsonObject());
                    out.endObject();
                } else if (ele.isJsonPrimitive()) {
                    JsonPrimitive pri = ele.getAsJsonPrimitive();
                    if (pri.isBoolean()) {
                        out.value(pri.getAsBoolean());
                    } else if (pri.isNumber()) {
                        out.value(pri.getAsNumber());
                    } else if (pri.isString()) {
                        out.value(pri.getAsString());
                    }
                } else if (ele.isJsonArray()) {
                    JsonArray array = ele.getAsJsonArray();
                    out.beginArray();
                    for (int i = 0; i < array.size(); i++) {
                        writeArrayValues(out, array.get(i));
                    }
                    out.endArray();
                }
            }

            public void writeRewards(JsonWriter out, Advancement adv) throws IOException {
                AdvancementRewards rewards = adv.getRewards();
                int experience = (int) ProtectedHelper.getField(AdvancementRewards.class, rewards, "field_192115_b");
                ResourceLocation[] recipes = (ResourceLocation[]) ProtectedHelper.getField(AdvancementRewards.class, rewards, "field_192117_d");
                ResourceLocation[] loots = (ResourceLocation[]) ProtectedHelper.getField(AdvancementRewards.class, rewards, "field_192116_c");
                FunctionObject.CacheableFunction function = (FunctionObject.CacheableFunction) ProtectedHelper.getField(AdvancementRewards.class, rewards, "field_193129_e");
                out.name("rewards").beginObject();
                if (experience != 0) {
                    out.name("experience").value(experience);
                }
                if (recipes.length != 0) {
                    out.name("recipes").beginArray();
                    for (ResourceLocation recipe : recipes) {
                        out.value(recipe.toString());
                    }
                    out.endArray();
                }
                if (loots.length != 0) {
                    out.name("loot").beginArray();
                    for (ResourceLocation loot : loots) {
                        out.value(loot.toString());
                    }
                    out.endArray();
                }
                if (function != null) {
                    if (function.getId() != null) {
                        out.name("function").value(function.getId().toString());
                    }
                }
                out.endObject();
            }

            @Override
            public Advancement read(JsonReader in) {
                return null;
            }
        }).create();

        public static JsonElement toJsonTree(Advancement advancement) {
            return GSON.toJsonTree(advancement);
        }
    }

    protected abstract void registerAdvancements();

    @Override
    public void act(DirectoryCache directoryCache) {
        registerAdvancements();

        Map<ResourceLocation, Advancement> advancements = new LinkedHashMap<>();
        for (Map.Entry<ResourceLocation, Advancement.Builder> entry : this.advancements.entrySet()) {
            if (ProtectedHelper.getField(Advancement.Builder.class, entry.getValue(), "parentId") != null) {
                if (advancements.containsKey(ProtectedHelper.getField(Advancement.Builder.class, entry.getValue(), "parentId"))) {
                    entry.getValue().withParent(advancements.get(ProtectedHelper.getField(Advancement.Builder.class, entry.getValue(), "parentId")));
                }
            }
            advancements.put(entry.getKey(), entry.getValue().build(entry.getKey()));
        }
        writeAdvancements(directoryCache, advancements);
    }

    private void writeAdvancements(DirectoryCache cache, Map<ResourceLocation, Advancement> tables) {
        Path outputFolder = generator.getOutputFolder();
        tables.forEach((key, advancement) -> {
            Path path = getPath(outputFolder, key);
            try {
                IDataProvider.save(GSON, cache, AdvancementToJson.toJsonTree(advancement), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write advancements {}", path, e);
            }
        });
    }

    public void add(ResourceLocation id, Advancement.Builder advancement) {
        advancements.put(id, advancement);
    }

    private static Path getPath(Path pathIn, ResourceLocation id) {
        return pathIn.resolve("data/" + id.getNamespace() + "/advancements/" + id.getPath() + ".json");
    }

    protected Advancement.Builder root(ItemStack icon, String title, String description, ResourceLocation background, FrameType frame,
                                       boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards.Builder rewardsBuilder) {
        return Advancement.Builder.builder()
                .withDisplay(new DisplayInfo(icon, new StringTextComponent(title), new StringTextComponent(description),
                        background, frame, showToast, announceToChat, hidden))
                .withRewards(rewardsBuilder);
    }

    protected Advancement.Builder root(ItemStack icon, String title, String description, ResourceLocation background, FrameType frame,
                                       boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.builder()
                .withDisplay(new DisplayInfo(icon, new StringTextComponent(title), new StringTextComponent(description),
                        background, frame, showToast, announceToChat, hidden));
    }

    protected Advancement.Builder root(ItemStack icon, String name, ResourceLocation parent, FrameType frame,
                                       boolean hidden) {
        String title = "advancement." + modid + "." + name + ".title";
        String description = "advancement." + modid + "." + name + ".description";
        return root(icon, title, description, parent, frame, false, false, hidden);
    }

    protected Advancement.Builder child(ItemStack icon, String name, ResourceLocation parent, FrameType frame,
                                        boolean showToast, boolean announceToChat, boolean hidden) {
        String title = "advancement." + modid + "." + name + ".title";
        String description = "advancement." + modid + "." + name + ".description";
        return child(icon, title, description, parent, frame, showToast, announceToChat, hidden);
    }

    protected Advancement.Builder child(ItemStack icon, String title, String description, ResourceLocation parent, FrameType frame,
                                        boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.builder()
                .withDisplay(new DisplayInfo(icon, new StringTextComponent(title), new StringTextComponent(description),
                        null, frame, showToast, announceToChat, hidden))
                .withParentId(parent);
    }

    protected InventoryChangeTrigger.Instance hasItem(IItemProvider itemIn) {
        return this.hasItem(ItemPredicate.Builder.create().item(itemIn).build());
    }

    protected InventoryChangeTrigger.Instance hasItem(ITag<Item> tagIn) {
        return this.hasItem(ItemPredicate.Builder.create().tag(tagIn).build());
    }

    protected ItemPredicate baseProvider(IItemProvider itemIn) {
        return ItemPredicate.Builder.create().item(itemIn).build();
    }

    protected ItemPredicate baseProvider(ITag<Item> tagIn) {
        return ItemPredicate.Builder.create().tag(tagIn).build();
    }

    protected InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicates) {
        return new InventoryChangeTrigger.Instance(
                EntityPredicate.AndPredicate.ANY_AND,
                MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED,
                MinMaxBounds.IntBound.UNBOUNDED, predicates);
    }

    protected ResourceLocation mcLoc(String path) {
        return new ResourceLocation(path);
    }

    protected ResourceLocation modLoc(String path) {
        return new ResourceLocation(modid, path);
    }

    protected AdvancementRewards.Builder rewardExp(int exp) {
        return new AdvancementRewards.Builder().addExperience(exp);
    }
}
