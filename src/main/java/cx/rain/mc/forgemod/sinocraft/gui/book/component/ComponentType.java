package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;

import java.util.function.Function;

public class ComponentType<T extends TutorialComponent> extends ForgeRegistryEntry<ComponentType<? extends TutorialComponent>> {
    public static ComponentType<TutorialEmpty> EMPTY = ComponentType.create(TutorialEmpty::new);

    public static IForgeRegistry<ComponentType<? extends TutorialComponent>> TUTORIAL_COMPONENT = new RegistryBuilder<ComponentType<? extends TutorialComponent>>().setType((Class<ComponentType<? extends TutorialComponent>>) EMPTY.getClass()).setName(new ResourceLocation(SinoCraft.MODID, "component_type")).allowModification().create();
    public static DeferredRegister<ComponentType<?>> REGISTRY =
            DeferredRegister.create(TUTORIAL_COMPONENT, SinoCraft.MODID);

    public static RegistryObject<ComponentType<TutorialText>> TEXT = REGISTRY.register(
            "text", () -> ComponentType.create(TutorialText::new));

    public static RegistryObject<ComponentType<TutorialImage>> IMAGE = REGISTRY.register(
            "image", () -> ComponentType.create(TutorialImage::new));

    public static RegistryObject<ComponentType<TutorialItem>> ITEM = REGISTRY.register(
            "item", () -> ComponentType.create(TutorialItem::new));

    public static RegistryObject<ComponentType<TutorialCraftingRecipe>> CRAFTING_RECIPE = REGISTRY.register(
            "crafting_recipe", () -> ComponentType.create(TutorialCraftingRecipe::new));

    public static void init(IEventBus bus) {
        REGISTRY.register(bus);
    }

    protected Function<GuiTutorialBook.Page, T> genComponent;
    protected ComponentType(Function<GuiTutorialBook.Page, T> genComponent) {
        this.genComponent = genComponent;
    }

    public static <R extends TutorialComponent> ComponentType<R> create(Function<GuiTutorialBook.Page, R> genComponent) {
        return new ComponentType(genComponent);
    }

    public T getComponent(GuiTutorialBook.Page page) {
        return genComponent.apply(page);
    }
}
