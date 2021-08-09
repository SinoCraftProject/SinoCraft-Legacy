package cx.rain.mc.forgemod.sinocraft.client.renderer.model.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ClothModel<T extends LivingEntity> extends BipedModel<T> {
    public ClothModel(float modelSizeIn) {
        super(modelSizeIn);

        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F -0.4F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, modelSizeIn);
        this.bipedHead.setRotationPoint(0.0F -0.2F, 0.0F, 0.0F);

        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.addBox(-4.0F -0.4F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, modelSizeIn + 0.5F);
        this.bipedHeadwear.setRotationPoint(0.0F -0.2F, 0.0F, 0.0F);

        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-4.0F -0.4F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, modelSizeIn);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-3.0F -0.4F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn);
        this.bipedRightArm.setRotationPoint(-5.0F -0.2F, 2.0F, 0.0F);

        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F -0.4F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn);
        this.bipedLeftArm.setRotationPoint(5.0F -0.2F, 2.0F , 0.0F);

        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-2.0F -0.4F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn);
        this.bipedRightLeg.setRotationPoint(-1.9F -0.2F, 12.0F, 0.0F);

        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2.0F -0.4F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn);
        this.bipedLeftLeg.setRotationPoint(1.9F -0.2F, 12.0F, 0.0F);
    }
}
