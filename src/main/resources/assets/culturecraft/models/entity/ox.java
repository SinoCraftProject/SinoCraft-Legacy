//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class custom_model extends ModelBase {
	private final ModelRenderer AAA00;
	private final ModelRenderer B0;
	private final ModelRenderer bone;
	private final ModelRenderer bone11;
	private final ModelRenderer B1;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer B2;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer B3;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer AAA11;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer AAA22;
	private final ModelRenderer HHSSYY22;
	private final ModelRenderer AZ11;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone5;
	private final ModelRenderer AZ2;
	private final ModelRenderer bone6;
	private final ModelRenderer bone8;
	private final ModelRenderer bone18;
	private final ModelRenderer bone7;
	private final ModelRenderer bone4;

	public custom_model() {
		textureWidth = 128;
		textureHeight = 128;

		AAA00 = new ModelRenderer(this);
		AAA00.setRotationPoint(0.0F, 24.0F, 0.0F);

		B0 = new ModelRenderer(this);
		B0.setRotationPoint(0.0F, 0.0F, 0.0F);
		AAA00.addChild(B0);
		B0.cubeList.add(new ModelBox(B0, 56, 11, 1.9F, -2.0F, -9.0F, 4, 2, 4, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone, 0.3491F, 0.0F, 0.0F);
		B0.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 0, 50, 2.0F, -11.0F, -6.0F, 4, 5, 4, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone11, -0.2618F, 0.0F, 0.0F);
		B0.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 48, 48, 2.0F, -4.0F, -9.0F, 4, 5, 4, 0.0F, false));

		B1 = new ModelRenderer(this);
		B1.setRotationPoint(-8.0F, 0.0F, 0.0F);
		AAA00.addChild(B1);
		B1.cubeList.add(new ModelBox(B1, 56, 5, 1.9F, -2.0F, -9.0F, 4, 2, 4, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone14, 0.3491F, 0.0F, 0.0F);
		B1.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 46, 32, 2.0F, -11.0F, -6.0F, 4, 5, 4, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone15, -0.2618F, 0.0F, 0.0F);
		B1.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 44, 0, 2.0F, -4.0F, -9.0F, 4, 5, 4, 0.0F, false));

		B2 = new ModelRenderer(this);
		B2.setRotationPoint(-8.0F, 0.0F, -1.0F);
		AAA00.addChild(B2);
		B2.cubeList.add(new ModelBox(B2, 0, 59, 2.0F, -1.0F, 6.0F, 4, 1, 4, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone12, 0.2618F, 0.0F, 0.0F);
		B2.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 36, 42, 2.0F, -7.0F, 7.0F, 4, 6, 4, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone13, -0.0873F, 0.0F, 0.0F);
		B2.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 32, 52, 2.0F, -5.0F, 6.0F, 4, 4, 4, 0.0F, false));

		B3 = new ModelRenderer(this);
		B3.setRotationPoint(0.0F, 0.0F, -1.0F);
		AAA00.addChild(B3);
		B3.cubeList.add(new ModelBox(B3, 56, 17, 2.0F, -1.0F, 6.0F, 4, 1, 4, 0.0F, false));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone16, 0.2618F, 0.0F, 0.0F);
		B3.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 20, 42, 2.0F, -7.0F, 7.0F, 4, 6, 4, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone17, -0.0873F, 0.0F, 0.0F);
		B3.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 16, 52, 2.0F, -5.0F, 6.0F, 4, 4, 4, 0.0F, false));

		AAA11 = new ModelRenderer(this);
		AAA11.setRotationPoint(0.0F, 26.0F, 0.0F);
		AAA11.cubeList.add(new ModelBox(AAA11, 37, 23, -6.0F, -19.0F, -12.0F, 12, 7, 2, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone9, -0.0873F, 0.0F, 0.0F);
		AAA11.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 0, 23, -7.0F, -21.0F, -1.0F, 14, 10, 9, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone10, 0.0873F, 0.0F, 0.0F);
		AAA11.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 0, 0, -8.0F, -22.0F, -9.0F, 16, 11, 12, 0.0F, false));

		AAA22 = new ModelRenderer(this);
		AAA22.setRotationPoint(0.0F, 29.0F, -1.0F);
		AAA22.cubeList.add(new ModelBox(AAA22, 45, 57, -2.0F, -21.0F, -14.0F, 4, 4, 3, 0.0F, false));

		HHSSYY22 = new ModelRenderer(this);
		HHSSYY22.setRotationPoint(0.0F, 0.0F, 0.0F);
		AAA22.addChild(HHSSYY22);

		AZ11 = new ModelRenderer(this);
		AZ11.setRotationPoint(0.0F, 0.0F, 0.0F);
		HHSSYY22.addChild(AZ11);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, -1.3963F);
		AZ11.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 5, 3, 22.9442F, 1.0914F, -15.0F, 2, 1, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.1745F);
		AZ11.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 5, 1, 6.0F, -21.0F, -15.0F, 2, 1, 1, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.6109F);
		AZ11.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 5, 5, 15.7028F, -15.7452F, -15.0F, 2, 1, 1, 0.0F, false));

		AZ2 = new ModelRenderer(this);
		AZ2.setRotationPoint(0.0F, 0.0F, -28.5F);
		setRotationAngle(AZ2, 0.0F, 3.1416F, 0.0F);
		HHSSYY22.addChild(AZ2);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotationAngle(bone6, 0.0F, 0.0F, -1.3963F);
		AZ2.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 4, 22.9442F, 1.0914F, -15.0F, 2, 1, 1, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.1745F);
		AZ2.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 0, 2, 6.0F, -21.0F, -15.0F, 2, 1, 1, 0.0F, false));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotationAngle(bone18, 0.0F, 0.0F, -0.6109F);
		AZ2.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 0, 0, 15.7028F, -15.7452F, -15.0F, 2, 1, 1, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, -16.0F, -16.0F);
		setRotationAngle(bone7, 0.9599F, 0.0F, 0.0F);
		AAA22.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 0, 42, -3.0F, -2.1808F, 1.4264F, 6, 4, 4, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -16.0F, -16.0F);
		setRotationAngle(bone4, 0.6109F, 0.0F, 0.0F);
		AAA22.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 52, 41, -2.0F, -2.1808F, -2.5736F, 4, 3, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		AAA00.render(f5);
		AAA11.render(f5);
		AAA22.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}