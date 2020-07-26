package cx.rain.mc.forgemod.sinocraft.data.gen.provider.base;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import cx.rain.mc.forgemod.sinocraft.data.TagItem;
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
import net.minecraft.tags.Tag;
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
    protected final Map<ResourceLocation, Advancement.Builder> Advancements = new LinkedHashMap<>();
    private static final EntityType<?>[] MOB_ENTITIES = new EntityType[]{EntityType.CAVE_SPIDER, EntityType.SPIDER, EntityType.ZOMBIE_PIGMAN,
            EntityType.ENDERMAN, EntityType.BLAZE, EntityType.CREEPER, EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HUSK,
            EntityType.MAGMA_CUBE, EntityType.SHULKER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.STRAY,
            EntityType.VINDICATOR, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.PHANTOM,
            EntityType.DROWNED, EntityType.PILLAGER, EntityType.RAVAGER};

    private static final EntityType<?>[] ENTITIES = new EntityType[]{EntityType.CAVE_SPIDER, EntityType.SPIDER, EntityType.ZOMBIE_PIGMAN,
            EntityType.ENDERMAN, EntityType.BLAZE, EntityType.CREEPER, EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HUSK,
            EntityType.MAGMA_CUBE, EntityType.SHULKER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.STRAY,
            EntityType.VINDICATOR, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.PHANTOM,
            EntityType.DROWNED, EntityType.PILLAGER, EntityType.RAVAGER, EntityType.BAT, EntityType.BEE, EntityType.CAT, EntityType.CHICKEN, EntityType.COW,
            EntityType.DOLPHIN,EntityType.DONKEY,EntityType.FOX,EntityType.HORSE,EntityType.IRON_GOLEM,EntityType.LLAMA,EntityType.MOOSHROOM,EntityType.MULE,
            EntityType.OCELOT,EntityType.PANDA,EntityType.PARROT,EntityType.PIG,EntityType.POLAR_BEAR,EntityType.PUFFERFISH,EntityType.RABBIT,
            EntityType.SALMON,EntityType.SHEEP,EntityType.SNOW_GOLEM,EntityType.SQUID,EntityType.VILLAGER,EntityType.WOLF};

    public ProviderBaseAdvancement(DataGenerator generatorIn) {
        generator = generatorIn;
    }

    protected static class AdvancementToJson{
        private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(Advancement.class, new TypeAdapter<Advancement>() {
            @Override
            public void write(JsonWriter out, Advancement value) throws IOException{
                out.beginObject();
                writeDisplay(out,value);
                if(value.getParent()!=null){
                    out.name("parent").value(value.getParent().getId().toString());
                }
                writeCriteria(out,value);
                writeRequirements(out, value);
                writeRewards(out,value);
                out.endObject();
            }

            public void writeDisplay(JsonWriter out, Advancement adv) throws IOException {
                DisplayInfo info = adv.getDisplay();
                out.name("display").beginObject();
                    out.name("icon").beginObject();
                        out.name("item").value(info.getIcon().getItem().getRegistryName().toString());
                    out.endObject();
                    out.name("title").beginObject();
                           out.name("translate").value(info.getTitle().getString());
                    out.endObject();
                    out.name("description").beginObject();
                            out.name("translate").value(info.getDescription().getString());
                    out.endObject();
                    out.name("frame").value(info.getFrame().getName());
                    if(info.getBackground()!=null){
                        out.name("background").value(info.getBackground().toString());
                    }
                    out.name("show_toast").value(info.shouldShowToast());
                    out.name("announce_to_chat").value(info.shouldAnnounceToChat());
                    out.name("hidden").value(info.isHidden());
                out.endObject();
            }

            public void writeRequirements(JsonWriter out,Advancement adv) throws IOException {
                String[][] req = adv.getRequirements();
                if(req==null||req.length==0){
                    return;
                }
                out.name("requirements").beginArray();
                for(String[] arr : req){
                    out.beginArray();
                    for(String r : arr){
                        out.value(r);
                    }
                    out.endArray();
                }
                out.endArray();
            }

            public void writeCriteria(JsonWriter out,Advancement adv) throws IOException{
                Map<String,Criterion> criteria = adv.getCriteria();
                out.name("criteria").beginObject();
                    for(Map.Entry<String,Criterion> entry : criteria.entrySet()){
                        Criterion criterion = entry.getValue();
                        JsonObject obj;
                        if(criterion.serialize().getAsJsonObject().get("conditions")==JsonNull.INSTANCE){
                            obj=null;
                        }
                        else{
                            obj = criterion.serialize().getAsJsonObject().getAsJsonObject("conditions");
                        }
                        out.name(entry.getKey()).beginObject();
                            out.name("trigger").value(criterion.getCriterionInstance().getId().toString());
                            if(obj!=null){
                                out.name("conditions").beginObject();
                                writeConditions(out,obj);
                                out.endObject();
                            }
                        out.endObject();
                    }
                out.endObject();
            }

            public void writeConditions(JsonWriter out,JsonObject obj) throws IOException {
                for(Map.Entry<String,JsonElement> entry : obj.entrySet()){
                    JsonElement ele = entry.getValue();
                    if(ele.isJsonObject()){
                        out.name(entry.getKey()).beginObject();
                        writeConditions(out,ele.getAsJsonObject());
                        out.endObject();
                    }
                    else if(ele.isJsonPrimitive()){
                        JsonPrimitive pri = ele.getAsJsonPrimitive();
                        if(pri.isBoolean()){
                            out.name(entry.getKey()).value(pri.getAsBoolean());
                        }
                        else if(pri.isNumber()){
                            out.name(entry.getKey()).value(pri.getAsNumber());
                        }
                        else if(pri.isString()){
                            out.name(entry.getKey()).value(pri.getAsString());
                        }
                    }
                    else if(ele.isJsonArray()){
                        JsonArray array = ele.getAsJsonArray();
                        out.name(entry.getKey()).beginArray();
                        for(int i=0;i<array.size();i++){
                            writeArrayValues(out,array.get(i));
                        }
                        out.endArray();
                    }
                }
            }

            public void writeArrayValues(JsonWriter out,JsonElement ele) throws IOException {
                if(ele.isJsonObject()){
                    out.beginObject();
                    writeConditions(out,ele.getAsJsonObject());
                    out.endObject();
                }
                else if(ele.isJsonPrimitive()){
                    JsonPrimitive pri = ele.getAsJsonPrimitive();
                    if(pri.isBoolean()){
                        out.value(pri.getAsBoolean());
                    }
                    else if(pri.isNumber()){
                        out.value(pri.getAsNumber());
                    }
                    else if(pri.isString()){
                        out.value(pri.getAsString());
                    }
                }
                else if(ele.isJsonArray()){
                    JsonArray array = ele.getAsJsonArray();
                    out.beginArray();
                    for(int i=0;i<array.size();i++){
                        writeArrayValues(out,array.get(i));
                    }
                    out.endArray();
                }
            }

            //complete
            public void writeRewards(JsonWriter out,Advancement adv) throws IOException {
                AdvancementRewards rewards = adv.getRewards();
                int experience = (int)ProtectedHelper.getField(AdvancementRewards.class,rewards,"experience");
                ResourceLocation[] recipes = (ResourceLocation[]) ProtectedHelper.getField(AdvancementRewards.class,rewards,"recipes");
                ResourceLocation[] loots = (ResourceLocation[]) ProtectedHelper.getField(AdvancementRewards.class,rewards,"loot");
                FunctionObject.CacheableFunction function =  (FunctionObject.CacheableFunction)ProtectedHelper.getField(AdvancementRewards.class,rewards,"function");
                out.name("rewards").beginObject();
                    if(experience!=0){
                        out.name("experience").value(experience);
                    }
                    if(recipes.length!=0){
                        out.name("recipes").beginArray();
                        for(ResourceLocation recipe : recipes){
                            out.value(recipe.toString());
                        }
                        out.endArray();
                    }
                    if(loots.length!=0){
                        out.name("loot").beginArray();
                        for(ResourceLocation loot : loots){
                            out.value(loot.toString());
                        }
                        out.endArray();
                    }
                    if(function!=null){
                        if(function.getId()!=null){
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

        public static JsonElement toJsonTree(Advancement advancement){
            return GSON.toJsonTree(advancement);
        }
    }

    protected abstract void registerAdvancements();

    @Override
    public void act(DirectoryCache directoryCache){
        registerAdvancements();

        Map<ResourceLocation, Advancement> advancements = new LinkedHashMap<>();
        for (Map.Entry<ResourceLocation, Advancement.Builder> entry : Advancements.entrySet()) {
            if(ProtectedHelper.getField(Advancement.Builder.class,entry.getValue(),"parentId")!=null) {
                if(advancements.containsKey(ProtectedHelper.getField(Advancement.Builder.class,entry.getValue(),"parentId"))){
                    entry.getValue().withParent(advancements.get(ProtectedHelper.getField(Advancement.Builder.class,entry.getValue(),"parentId")));
                }
            }
            try {
                advancements.put(entry.getKey(), entry.getValue().build(entry.getKey()));
            }
            catch (IllegalStateException e){

            }
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

    protected static class CriterionInfo{
        public String name;
        public Criterion criterion;

        public CriterionInfo(String nameIn,Criterion criterionIn){
            name=nameIn;
            criterion=criterionIn;
        }
    }

    private static Path getPath(Path pathIn, ResourceLocation id) {
        return pathIn.resolve("data/" + id.getNamespace() + "/advancements/" + id.getPath() + ".json");
    }

    protected Advancement.Builder RootAdvancement(ItemStack icon, ITextComponent title, ITextComponent description, ResourceLocation background, FrameType frame,
                                                  boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards.Builder rewardsBuilder){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,title,description,background,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewardsBuilder);
    }

    protected Advancement.Builder RootAdvancement(ItemStack icon, String title, String description, ResourceLocation background, FrameType frame,
                                                  boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards.Builder rewardsBuilder){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,new StringTextComponent(title),new StringTextComponent(description),background,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewardsBuilder);
    }

    protected Advancement.Builder ChildAdvancement(ItemStack icon, String title, String description, ResourceLocation parent, FrameType frame,
                                                   boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards.Builder rewardsBuilder){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,new StringTextComponent(title),new StringTextComponent(description),null,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewardsBuilder)
                .withParentId(parent);
    }

    protected Advancement.Builder ChildAdvancement(ItemStack icon, ITextComponent title, ITextComponent description, Advancement parent, FrameType frame,
                                                   boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards.Builder rewardsBuilder){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,title,description,null,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewardsBuilder)
                .withParent(parent);
    }

    protected Advancement.Builder ChildAdvancement(ItemStack icon, ITextComponent title, ITextComponent description, ResourceLocation parent, FrameType frame,
                                                    boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards.Builder rewardsBuilder){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,title,description,null,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewardsBuilder)
                .withParentId(parent);
    }

    protected Advancement.Builder ChildAdvancement(ItemStack icon, String title, String description, Advancement parent, FrameType frame,
                                                   boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards.Builder rewardsBuilder){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,new StringTextComponent(title),new StringTextComponent(description),null,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewardsBuilder)
                .withParent(parent);
    }

    protected Advancement.Builder RootAdvancement(ItemStack icon, ITextComponent title, ITextComponent description, ResourceLocation background, FrameType frame,
                                                   boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards rewards){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,title,description,background,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewards);
    }

    protected Advancement.Builder RootAdvancement(ItemStack icon, String title, String description, ResourceLocation background, FrameType frame,
                                                  boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards rewards){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,new StringTextComponent(title),new StringTextComponent(description),background,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewards);
    }

    protected Advancement.Builder ChildAdvancement(ItemStack icon, String title, String description, ResourceLocation parent, FrameType frame,
                                                   boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards rewards){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,new StringTextComponent(title),new StringTextComponent(description),null,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewards)
                .withParentId(parent);
    }

    protected Advancement.Builder ChildAdvancement(ItemStack icon, ITextComponent title, ITextComponent description, Advancement parent, FrameType frame,
                                                   boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards rewards){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,title,description,null,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewards)
                .withParent(parent);
    }

    protected Advancement.Builder ChildAdvancement(ItemStack icon, ITextComponent title, ITextComponent description, ResourceLocation parent, FrameType frame,
                                                   boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards rewards){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,title,description,null,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewards)
                .withParentId(parent);
    }

    protected Advancement.Builder ChildAdvancement(ItemStack icon, String title, String description, Advancement parent, FrameType frame,
                                                   boolean showToast, boolean announceToChat, boolean hidden, AdvancementRewards rewards){
        return Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,new StringTextComponent(title),new StringTextComponent(description),null,frame,showToast,announceToChat,hidden)
                )
                .withRewards(rewards)
                .withParent(parent);
    }

    protected InventoryChangeTrigger.Instance hasItem(IItemProvider itemIn) {
        return this.hasItem(ItemPredicate.Builder.create().item(itemIn).build());
    }

    protected InventoryChangeTrigger.Instance hasItem(Tag<Item> tagIn) {
        return this.hasItem(ItemPredicate.Builder.create().tag(tagIn).build());
    }

    protected ItemPredicate baseProvider(IItemProvider itemIn){
        return ItemPredicate.Builder.create().item(itemIn).build();
    }

    protected  ItemPredicate baseProvider(Tag<Item> tagIn){
        return ItemPredicate.Builder.create().tag(tagIn).build();
    }

    protected InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicates) {
        return new InventoryChangeTrigger.Instance(MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, predicates);
    }

    protected Advancement.Builder makeMobAdvancement(Advancement.Builder builder) {
        for(EntityType<?> entitytype : MOB_ENTITIES) {
            builder.withCriterion(Registry.ENTITY_TYPE.getKey(entitytype).toString(), KilledTrigger.Instance.playerKilledEntity());
        }

        return builder;
    }

    protected Advancement.Builder makeEntityAdvancement(Advancement.Builder builder) {
        for(EntityType<?> entitytype : ENTITIES) {
            builder.withCriterion(Registry.ENTITY_TYPE.getKey(entitytype).toString(), KilledTrigger.Instance.playerKilledEntity(EntityPredicate.Builder.create().type(entitytype)));
        }

        return builder;
    }

    protected Advancement.Builder makeMobKnifeAdvancement(Advancement.Builder builder) {
        for(EntityType<?> entitytype : MOB_ENTITIES) {
            builder.withCriterion(Registry.ENTITY_TYPE.getKey(entitytype).toString(), new KilledTrigger.Instance(
                    CriteriaTriggers.PLAYER_KILLED_ENTITY.getId(),EntityPredicate.Builder.create().type(entitytype).build(),new DamageSourcePredicate(
                    false,false,false,false, false,
                    false,false,false,EntityPredicate.ANY,
                    EntityPredicate.Builder.create().equipment(new EntityEquipmentPredicate(
                            ItemPredicate.ANY,ItemPredicate.ANY,ItemPredicate.ANY,ItemPredicate.ANY,
                            this.baseProvider(TagItem.KNIFE),ItemPredicate.ANY)).build())));
        }

        return builder;
    }

    protected Advancement.Builder makeEntityKnifeAdvancement(Advancement.Builder builder) {
        for(EntityType<?> entitytype : ENTITIES) {
            builder.withCriterion(Registry.ENTITY_TYPE.getKey(entitytype).toString(), new KilledTrigger.Instance(
                    CriteriaTriggers.PLAYER_KILLED_ENTITY.getId(),EntityPredicate.Builder.create().type(entitytype).build(),new DamageSourcePredicate(
                    false,false,false,false, false,
                    false,false,false,EntityPredicate.ANY,
                    EntityPredicate.Builder.create().equipment(new EntityEquipmentPredicate(
                            ItemPredicate.ANY,ItemPredicate.ANY,ItemPredicate.ANY,ItemPredicate.ANY,
                            this.baseProvider(TagItem.KNIFE),ItemPredicate.ANY)).build())));
        }

        return builder;
    }
}
