package cx.rain.mc.forgemod.culturecraft.client.renderer.entity;

import cx.rain.mc.forgemod.culturecraft.client.renderer.entity.model.ModelBuffalo;
import cx.rain.mc.forgemod.culturecraft.entity.passive.EntityBuffalo;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererBuffalo extends MobRenderer<EntityBuffalo, ModelBuffalo> {

    public RendererBuffalo(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelBuffalo(), 1F);
    }

    public RendererBuffalo(EntityRendererManager renderManagerIn, ModelBuffalo entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityBuffalo entity) {
        return new ResourceLocation("culturecraft", "textures/entity/ox.png");
    }
}