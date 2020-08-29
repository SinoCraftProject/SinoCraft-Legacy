package cx.rain.mc.forgemod.sinocraft.side.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityBuffalo;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author 豆焰
 * Made with Blockbench 3.5.4
 * Exported for Minecraft version 1.15
 * Extra code and adjustment by SQwatermark
 */
@OnlyIn(Dist.CLIENT)
public class ModelBuffalo extends AgeableModel<EntityBuffalo> {

    private final ModelRenderer LEG_BL;
    private final ModelRenderer THIGH_BL;
    private final ModelRenderer CALF_BL;
    private final ModelRenderer LEG_BR;
    private final ModelRenderer THIGH_BR;
    private final ModelRenderer CALF_BR;
    private final ModelRenderer LEG_FR;
    private final ModelRenderer THIGH_FR;
    private final ModelRenderer CALF_FR;
    private final ModelRenderer LEG_FL;
    private final ModelRenderer THIGH_FL;
    private final ModelRenderer CALF_FL;
    private final ModelRenderer BODY;
    private final ModelRenderer BACK;
    private final ModelRenderer CHEST;
    private final ModelRenderer HEAD;
    private final ModelRenderer HORN;
    private final ModelRenderer HORN_L;
    private final ModelRenderer HORN_L_3;
    private final ModelRenderer HORN_L_1;
    private final ModelRenderer HORN_L_2;
    private final ModelRenderer HORN_R;
    private final ModelRenderer HORN_R_3;
    private final ModelRenderer HORN_R_1;
    private final ModelRenderer HORN_R_2;
    private final ModelRenderer FACE;
    private final ModelRenderer NOSE;

    public ModelBuffalo() {
        textureWidth = 128;
        textureHeight = 128;

        LEG_BL = new ModelRenderer(this);
        LEG_BL.setRotationPoint(4.0F, 16.0F, 6.0F);
        LEG_BL.setTextureOffset(56, 17).addBox(-2.0F, 7.0F, -1.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

        THIGH_BL = new ModelRenderer(this);
        THIGH_BL.setRotationPoint(-4.0F, 8.0F, -7.0F);
        LEG_BL.addChild(THIGH_BL);
        setRotationAngle(THIGH_BL, 0.2618F, 0.0F, 0.0F);
        THIGH_BL.setTextureOffset(20, 42).addBox(2.0F, -7.0F, 7.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        CALF_BL = new ModelRenderer(this);
        CALF_BL.setRotationPoint(-4.0F, 8.0F, -7.0F);
        LEG_BL.addChild(CALF_BL);
        setRotationAngle(CALF_BL, -0.0873F, 0.0F, 0.0F);
        CALF_BL.setTextureOffset(16, 52).addBox(2.0F, -5.0F, 6.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        LEG_BR = new ModelRenderer(this);
        LEG_BR.setRotationPoint(-4.0F, 16.0F, 6.0F);
        LEG_BR.setTextureOffset(0, 59).addBox(-2.0F, 7.0F, -1.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

        THIGH_BR = new ModelRenderer(this);
        THIGH_BR.setRotationPoint(-4.0F, 8.0F, -7.0F);
        LEG_BR.addChild(THIGH_BR);
        setRotationAngle(THIGH_BR, 0.2618F, 0.0F, 0.0F);
        THIGH_BR.setTextureOffset(36, 42).addBox(2.0F, -7.0F, 7.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        CALF_BR = new ModelRenderer(this);
        CALF_BR.setRotationPoint(-4.0F, 8.0F, -7.0F);
        LEG_BR.addChild(CALF_BR);
        setRotationAngle(CALF_BR, -0.0873F, 0.0F, 0.0F);
        CALF_BR.setTextureOffset(32, 52).addBox(2.0F, -5.0F, 6.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        LEG_FR = new ModelRenderer(this);
        LEG_FR.setRotationPoint(-4.0F, 16.0F, -7.0F);
        LEG_FR.setTextureOffset(56, 5).addBox(-2.1F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        THIGH_FR = new ModelRenderer(this);
        THIGH_FR.setRotationPoint(-4.0F, 8.0F, 7.0F);
        LEG_FR.addChild(THIGH_FR);
        setRotationAngle(THIGH_FR, 0.3491F, 0.0F, 0.0F);
        THIGH_FR.setTextureOffset(46, 32).addBox(2.0F, -11.0F, -6.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

        CALF_FR = new ModelRenderer(this);
        CALF_FR.setRotationPoint(-4.0F, 8.0F, 7.0F);
        LEG_FR.addChild(CALF_FR);
        setRotationAngle(CALF_FR, -0.2618F, 0.0F, 0.0F);
        CALF_FR.setTextureOffset(44, 0).addBox(2.0F, -4.0F, -9.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

        LEG_FL = new ModelRenderer(this);
        LEG_FL.setRotationPoint(4.0F, 16.0F, -7.0F);
        LEG_FL.setTextureOffset(56, 11).addBox(-2.1F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        THIGH_FL = new ModelRenderer(this);
        THIGH_FL.setRotationPoint(-4.0F, 8.0F, 7.0F);
        LEG_FL.addChild(THIGH_FL);
        setRotationAngle(THIGH_FL, 0.3491F, 0.0F, 0.0F);
        THIGH_FL.setTextureOffset(0, 50).addBox(2.0F, -11.0F, -6.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

        CALF_FL = new ModelRenderer(this);
        CALF_FL.setRotationPoint(-4.0F, 8.0F, 7.0F);
        LEG_FL.addChild(CALF_FL);
        setRotationAngle(CALF_FL, -0.2618F, 0.0F, 0.0F);
        CALF_FL.setTextureOffset(48, 48).addBox(2.0F, -4.0F, -9.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

        BODY = new ModelRenderer(this);
        BODY.setRotationPoint(0.0F, 26.0F, 0.0F);
        BODY.setTextureOffset(37, 23).addBox(-6.0F, -19.0F, -12.0F, 12.0F, 7.0F, 2.0F, 0.0F, false);

        BACK = new ModelRenderer(this);
        BACK.setRotationPoint(0.0F, 0.0F, 0.0F);
        BODY.addChild(BACK);
        setRotationAngle(BACK, -0.0873F, 0.0F, 0.0F);
        BACK.setTextureOffset(0, 23).addBox(-7.0F, -21.0F, -1.0F, 14.0F, 10.0F, 9.0F, 0.0F, false);

        CHEST = new ModelRenderer(this);
        CHEST.setRotationPoint(0.0F, 0.0F, 0.0F);
        BODY.addChild(CHEST);
        setRotationAngle(CHEST, 0.0873F, 0.0F, 0.0F);
        CHEST.setTextureOffset(0, 0).addBox(-8.0F, -22.0F, -9.0F, 16.0F, 11.0F, 12.0F, 0.0F, false);

        HEAD = new ModelRenderer(this);
        HEAD.setRotationPoint(0.0F, 10.0F, -13.0F);
        HEAD.setTextureOffset(45, 57).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);

        HORN = new ModelRenderer(this);
        HORN.setRotationPoint(0.0F, -1.0F, -2.0F);
        HEAD.addChild(HORN);


        HORN_L = new ModelRenderer(this);
        HORN_L.setRotationPoint(0.0F, 20.0F, 14.0F);
        HORN.addChild(HORN_L);


        HORN_L_3 = new ModelRenderer(this);
        HORN_L_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        HORN_L.addChild(HORN_L_3);
        setRotationAngle(HORN_L_3, 0.0F, 0.0F, -1.3963F);
        HORN_L_3.setTextureOffset(5, 3).addBox(22.9442F, 1.0914F, -15.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        HORN_L_1 = new ModelRenderer(this);
        HORN_L_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        HORN_L.addChild(HORN_L_1);
        setRotationAngle(HORN_L_1, 0.0F, 0.0F, -0.1745F);
        HORN_L_1.setTextureOffset(5, 1).addBox(6.0F, -21.0F, -15.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        HORN_L_2 = new ModelRenderer(this);
        HORN_L_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        HORN_L.addChild(HORN_L_2);
        setRotationAngle(HORN_L_2, 0.0F, 0.0F, -0.6109F);
        HORN_L_2.setTextureOffset(5, 5).addBox(15.7028F, -15.7452F, -15.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        HORN_R = new ModelRenderer(this);
        HORN_R.setRotationPoint(0.0F, 20.0F, -14.5F);
        HORN.addChild(HORN_R);
        setRotationAngle(HORN_R, 0.0F, 3.1416F, 0.0F);


        HORN_R_3 = new ModelRenderer(this);
        HORN_R_3.setRotationPoint(0.0F, 0.0F, 0.5F);
        HORN_R.addChild(HORN_R_3);
        setRotationAngle(HORN_R_3, 0.0F, 0.0F, -1.3963F);
        HORN_R_3.setTextureOffset(0, 4).addBox(23.0F, 1.0914F, -15.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        HORN_R_1 = new ModelRenderer(this);
        HORN_R_1.setRotationPoint(0.0F, 0.0F, 0.5F);
        HORN_R.addChild(HORN_R_1);
        setRotationAngle(HORN_R_1, 0.0F, 0.0F, -0.1745F);
        HORN_R_1.setTextureOffset(0, 2).addBox(6.0F, -21.0F, -15.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        HORN_R_2 = new ModelRenderer(this);
        HORN_R_2.setRotationPoint(0.0F, 0.0F, 0.5F);
        HORN_R.addChild(HORN_R_2);
        setRotationAngle(HORN_R_2, 0.0F, 0.0F, -0.6109F);
        HORN_R_2.setTextureOffset(0, 0).addBox(15.7028F, -15.7452F, -15.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        FACE = new ModelRenderer(this);
        FACE.setRotationPoint(0.0F, 3.0F, -4.0F);
        HEAD.addChild(FACE);
        setRotationAngle(FACE, 0.9599F, 0.0F, 0.0F);
        FACE.setTextureOffset(0, 42).addBox(-3.0F, -2.1808F, 1.4264F, 6.0F, 4.0F, 4.0F, 0.0F, false);

        NOSE = new ModelRenderer(this);
        NOSE.setRotationPoint(0.0F, 2.5F, -3.5F);
        HEAD.addChild(NOSE);
        setRotationAngle(NOSE, 0.6109F, 0.0F, 0.0F);
        NOSE.setTextureOffset(52, 41).addBox(-2.0F, -2.1808F, -2.5736F, 4.0F, 3.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.isChild) {
            matrixStack.push();
            matrixStack.scale(0.75F, 0.75F, 0.75F);

            matrixStack.translate(0.0D, 1.0F, 0.25F);
            this.getHeadParts().forEach((p_228230_8_) -> {
                p_228230_8_.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            });
            matrixStack.pop();
            matrixStack.push();
            matrixStack.scale(0.5F, 0.5F, 0.5F);
            matrixStack.translate(0.0D, 24.0F / 16.0F, 0.0D);
            this.getBodyParts().forEach((p_228229_8_) -> {
                p_228229_8_.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            });
            matrixStack.pop();
        } else {
            this.getHeadParts().forEach((p_228228_8_) -> {
                p_228228_8_.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            });
            this.getBodyParts().forEach((p_228227_8_) -> {
                p_228227_8_.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            });
        }
    }

    //The method is written by BlockBench to set the model, not setRotationAngles.
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.HEAD);
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.BODY, this.LEG_BR, this.LEG_BL, this.LEG_FR, this.LEG_FL);
    }

    @Override
    public void setRotationAngles(EntityBuffalo entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.HEAD.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.HEAD.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.LEG_BR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LEG_BL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LEG_FR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LEG_FL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}