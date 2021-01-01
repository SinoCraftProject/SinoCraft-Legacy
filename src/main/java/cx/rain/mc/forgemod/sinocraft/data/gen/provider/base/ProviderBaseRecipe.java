package cx.rain.mc.forgemod.sinocraft.data.gen.provider.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cx.rain.mc.forgemod.sinocraft.utility.ProtectedHelper;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;

public abstract class ProviderBaseRecipe implements IDataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    protected Map<ResourceLocation,IFinishedRecipe> recipes = new HashMap();
    protected Consumer<IFinishedRecipe>  consumer = (recipe) -> {
        recipes.put(recipe.getID(),recipe);
    };
    private DataGenerator generator;

    public ProviderBaseRecipe(DataGenerator generatorIn) {
        generator = generatorIn;
    }

    protected abstract void registerRecipes();

    @Override
    public void act(DirectoryCache cache) {
        registerRecipes();

        writeRecipe(cache, recipes);
    }

    private void writeRecipe(DirectoryCache cache, Map<ResourceLocation,IFinishedRecipe> recipes) {
        Path outputFolder = generator.getOutputFolder();
        recipes.forEach((key, recipe) -> {
            Path path = getPath(outputFolder, key);
            try {
                IDataProvider.save(GSON, cache, recipe.getRecipeJson(), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write advancements {}", path, e);
            }
        });

    }

    private static Path getPath(Path pathIn, ResourceLocation id) {
        return pathIn.resolve("data/" + id.getNamespace() + "/recipes/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Recipes";
    }

    // Fixme: Broken!
//    protected InventoryChangeTrigger.Instance hasItem(IItemProvider itemIn) {
//        return this.hasItem(ItemPredicate.Builder.create().item(itemIn).build());
//    }
//
//    protected InventoryChangeTrigger.Instance hasItem(Tag<Item> tagIn) {
//        return this.hasItem(ItemPredicate.Builder.create().tag(tagIn).build());
//    }
//
//    protected InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicates) {
//        return new InventoryChangeTrigger.Instance(MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, predicates);
//    }
}
