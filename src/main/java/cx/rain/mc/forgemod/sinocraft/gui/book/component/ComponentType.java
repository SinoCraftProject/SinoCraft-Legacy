package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.gui.book.GuiTutorialBook;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;

import java.util.function.Function;

public class ComponentType<T extends TutorialComponent> extends ForgeRegistryEntry<ComponentType<? extends TutorialComponent>> {
    public static ComponentType<TutorialEmpty> EMPTY = ComponentType.create(TutorialEmpty::new);

    public static IForgeRegistry<ComponentType<? extends TutorialComponent>> TUTORIAL_COMPONENT = new RegistryBuilder<ComponentType<? extends TutorialComponent>>().setType((Class<ComponentType<? extends TutorialComponent>>) EMPTY.getClass()).setName(new ResourceLocation(SinoCraft.MODID, "component_type")).allowModification().create();
    public static DeferredRegister<ComponentType<?>> REGISTRY =
            DeferredRegister.create(TUTORIAL_COMPONENT, SinoCraft.MODID);

    public static RegistryObject<ComponentType<?>> TEXT = REGISTRY.register("sinocraft:text", () -> ComponentType.create(TutorialText::new));

    protected Function<GuiTutorialBook, T> genComponent;
    protected ComponentType(Function<GuiTutorialBook, T> genComponent) {
        this.genComponent = genComponent;
    }

    public static <R extends TutorialComponent> ComponentType<R> create(Function<GuiTutorialBook, R> genComponent) {
        return new ComponentType(genComponent);
    }
}
