package cx.rain.mc.forgemod.sinocraft.client;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.ModTileEntities;
import cx.rain.mc.forgemod.sinocraft.client.renderer.entity.RendererBuffalo;
import cx.rain.mc.forgemod.sinocraft.client.renderer.item.XuanPaperBakedModel;
import cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity.TileEntityStoneMillRender;
import cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity.TileEntityTeaTableRender;
import cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity.TileEntityVatRender;
import cx.rain.mc.forgemod.sinocraft.entity.ModEntities;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.gui.GuiChineseBrush;
import cx.rain.mc.forgemod.sinocraft.gui.container.ModContainers;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.utility.CapabilityHelper;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;
import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = SinoCraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    public static final ResourceLocation TEAPOT_TEA = new ResourceLocation("sinocraft:tea");

    @SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        // item
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENTITY_BUFFALO.get(), RendererBuffalo::new);
        ItemModelsProperties.registerProperty(ModItems.TEAPOT.get(), TEAPOT_TEA, (stack, world, entity) ->
                CapabilityHelper.getTeapot(stack).isPouring() ? 1.0f : 0.0f);
        // block
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.VAT.get(), TileEntityVatRender::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.STONE_MILL.get(), TileEntityStoneMillRender::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.TEA_TABLE.get(), TileEntityTeaTableRender::new);
        SinoCraft.getLogger().info("Registering TileEntity renderer.");
        // gui
        ScreenManager.registerFactory(ModContainers.CHINESE_BRUSH.get(), GuiChineseBrush::create);
        // render type
        ModBlocks.REGISTRY.getEntries().stream()
                .map(RegistryObject::get)
                .filter(block -> block instanceof CropsBlock || block instanceof SaplingBlock)
                .forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getTranslucent()));
        ModFluids.REGISTRY.getEntries().forEach(entry -> RenderTypeLookup.setRenderLayer(entry.get(), RenderType.getTranslucent()));
        RenderTypeLookup.setRenderLayer(ModBlocks.STONE_MILL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.PAPER_DRYING_RACK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.STOVE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.TEAPOT.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(ModBlocks.TEACUP.get(), RenderType.getTranslucent());
    }

    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent event) {
        replaceModel(event.getModelRegistry(), ModItems.XUAN_PAPER.getId(), XuanPaperBakedModel::new);
    }

    private static void replaceModel(Map<ResourceLocation, IBakedModel> modelRegistry, ResourceLocation id, Function<IBakedModel, IBakedModel> model) {
        ModelResourceLocation location = new ModelResourceLocation(id, "inventory");
        IBakedModel exist_model = modelRegistry.get(location);
        if (exist_model == null)
            throw new IllegalStateException(location + " has no existing model");
        modelRegistry.put(location, model.apply(exist_model));
    }
}