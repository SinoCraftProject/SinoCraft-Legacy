package cx.rain.mc.forgemod.sinocraft.data.gen.provider.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cx.rain.mc.forgemod.sinocraft.utility.ProtectedHelper;
import net.minecraft.advancements.*;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class ProviderBaseAdvancement implements IDataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private DataGenerator generator;
    protected final Map<ResourceLocation, Advancement.Builder> Advancements = new HashMap<>();

    public ProviderBaseAdvancement(DataGenerator generatorIn) {
        generator = generatorIn;
    }

    protected abstract void registerAdvancements();

    @Override
    public void act(DirectoryCache directoryCache){
        registerAdvancements();

        Map<ResourceLocation, Advancement> advancements = new HashMap<>();
        for (Map.Entry<ResourceLocation, Advancement.Builder> entry : Advancements.entrySet()) {
            advancements.put(entry.getKey(), entry.getValue().build(entry.getKey()));
        }
        writeAdvancements(directoryCache, advancements);
    }

    private void writeAdvancements(DirectoryCache cache, Map<ResourceLocation, Advancement> tables) {
        Path outputFolder = generator.getOutputFolder();
        tables.forEach((key, advancement) -> {
            Path path = getPath(outputFolder, key);
            try {
                IDataProvider.save(GSON, cache, ProtectedHelper.AdvancementToJson(advancement), path);
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

    protected Advancement.Builder RootAdvancement(ItemStack icon, ITextComponent title, ITextComponent description,
                                                  @Nullable ResourceLocation background, FrameType frame, boolean showToast,
                                                  boolean announceToChat, boolean hidden, AdvancementRewards.Builder rewardsBuilder,
                                                  IRequirementsStrategy requirementsStrategy, CriterionInfo... criteria){
        Advancement.Builder builder = Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,title,description,background,frame,showToast,announceToChat,hidden)
                )
                .withRequirementsStrategy(requirementsStrategy)
                .withRewards(rewardsBuilder);
        for(CriterionInfo criterion : criteria){
            builder.withCriterion(criterion.name,criterion.criterion);
        }
        return builder;
    }

    protected Advancement.Builder RootAdvancement(ItemStack icon, String title, String description,
                                                  @Nullable ResourceLocation background, FrameType frame, boolean showToast,
                                                  boolean announceToChat, boolean hidden, AdvancementRewards.Builder rewardsBuilder,
                                                  IRequirementsStrategy requirementsStrategy, CriterionInfo... criteria){
        Advancement.Builder builder = Advancement.Builder.builder()
                .withDisplay(
                        new DisplayInfo(icon,new StringTextComponent(title),new StringTextComponent(description),background,frame,showToast,announceToChat,hidden)
                )
                .withRequirementsStrategy(requirementsStrategy)
                .withRewards(rewardsBuilder);
        for(CriterionInfo criterion : criteria){
            builder.withCriterion(criterion.name,criterion.criterion);
        }
        return builder;
    }
}
