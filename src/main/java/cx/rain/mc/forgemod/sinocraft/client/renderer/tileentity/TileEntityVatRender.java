package cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.util.math.Vec2;
import cx.rain.mc.forgemod.sinocraft.api.util.math.Vec3;
import cx.rain.mc.forgemod.sinocraft.api.util.math.Vec4;
import cx.rain.mc.forgemod.sinocraft.tileentity.TileEntityVat;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.GrassPathBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.Random;

public class TileEntityVatRender extends TileEntityRenderer<TileEntityVat> {
    public TileEntityVatRender(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    public static void add(IVertexBuilder builder, MatrixStack matrixStack, Vec3<Float> pos, Vec2<Float> uv){
        add(builder,matrixStack,pos,new Vec4(1.0f,1.0f,1.0f,1.0f),uv);
    }

    public static void add(IVertexBuilder builder, MatrixStack matrixStack, Vec3<Float> pos, Vec4<Float> color, Vec2<Float> uv){
        builder.pos(matrixStack.getLast().getMatrix(), pos.x, pos.y, pos.z)
                .color(color.x, color.y, color.z, color.w)
                .tex(uv.x, uv.y)
                .lightmap(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }

    public static void addSquare(IVertexBuilder builder,MatrixStack matrixStack,Vec3<Float> pos1,Vec2<Float> uv1,Vec3<Float> pos2,Vec2<Float> uv2,Vec3<Float> pos3
            ,Vec2<Float> uv3,Vec3<Float> pos4,Vec2<Float> uv4){
        add(builder,matrixStack,pos1,uv1);
        add(builder,matrixStack,pos2,uv2);
        add(builder,matrixStack,pos3,uv3);
        add(builder,matrixStack,pos4,uv4);
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack, Vec3<Float> pos1, Vec3<Float> pos2, Vec3<Float> pos3, Vec3<Float> pos4, Vec4<Float> uv){
        addSquare(builder,matrixStack,
                pos1,new Vec2(uv.x,uv.z),
                pos2,new Vec2(uv.y,uv.z),
                pos3,new Vec2(uv.x,uv.w),
                pos4,new Vec2(uv.y,uv.w));
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack,
                                 Vec3<Float> pos1, Vec2<Float> uv1, Vec4<Float> color1,
                                 Vec3<Float> pos2, Vec2<Float> uv2, Vec4<Float> color2,
                                 Vec3<Float> pos3, Vec2<Float> uv3, Vec4<Float> color3,
                                 Vec3<Float> pos4, Vec2<Float> uv4, Vec4<Float> color4){
        add(builder,matrixStack,pos1,color1,uv1);
        add(builder,matrixStack,pos2,color2,uv2);
        add(builder,matrixStack,pos3,color3,uv3);
        add(builder,matrixStack,pos4,color4,uv4);
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack, Vec3<Float> pos1, Vec3<Float> pos2, Vec3<Float> pos3,
                                 Vec3<Float> pos4, Vec4<Float> uv,Vec4<Float> color){
        addSquare(builder,matrixStack,
                pos1,new Vec2(uv.x,uv.z),color,
                pos2,new Vec2(uv.y,uv.z),color,
                pos3,new Vec2(uv.x,uv.w),color,
                pos4,new Vec2(uv.y,uv.w),color);
    }

    public static Vec4<Float> RGBAToGLColor(Vec4<Float> rgba){
        rgba.x/=256;
        rgba.y/=256;
        rgba.z/=256;
        rgba.w/=100;
        return rgba;
    }

    public static Vec4<Float> GLColorToRGBA(Vec4<Float> glColor){
        glColor.x*=256;
        glColor.y*=256;
        glColor.z*=256;
        glColor.w*=100;
        return glColor;
    }

    public static Vec4<Float> NumberToRGBA(int color){
        float a=color>>>24;
        float r=(color&0xff0000)>>16;
        float g=(color&0xff00)>>8;
        float b=color&0xff;
        return new Vec4(r,g,b,a);
    }

    public static Vec4<Float> NumberToGLColor(int color){
        Vec4<Float> rgba = NumberToRGBA(color);
        return RGBAToGLColor(rgba);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(TileEntityVat te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        if(te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null) !=null){
            if(te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null).getFluidInTank(0) != FluidStack.EMPTY){
                matrixStack.push();
                matrixStack.scale(0.75f,1.0f,0.75f);
                matrixStack.translate(0.18,-0.1,0.18);
                Fluid fluid = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null).getFluidInTank(0).getFluid();
                TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).
                        apply(fluid.getAttributes().getStillTexture());
                IVertexBuilder vertex = buffer.getBuffer(RenderType.getText(sprite.getAtlasTexture().getTextureLocation()));
                addSquare(vertex,matrixStack,
                        new Vec3(0.0f,1.0f,1.0f),new Vec3(1.0f,1.0f,1.0f),
                        new Vec3(1.0f,1.0f,0.0f),new Vec3(0.0f,1.0f,0.0f),
                        new Vec4(sprite.getMinU(),sprite.getMaxU(),sprite.getMinV(),sprite.getMaxV()),
                        NumberToGLColor(fluid.getAttributes().getColor()));
                matrixStack.pop();
            }
        }
        if(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null)!=null){
            ItemStack stack = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null).getStackInSlot(0);
            int lc = 0;
            if(stack!=ItemStack.EMPTY){
                lc = stack.getCount();
                for (int i=0;i<Math.min(stack.getCount(), 16);i++){
                    matrixStack.push();
                    matrixStack.scale(0.2f, 0.2f, 0.2f);
                    //matrixStack.translate(0,1,0);
                    matrixStack.translate(i % 4 + 1,4.5f, i / 4 + 1);
                    itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED,true,matrixStack,buffer,
                            combinedLightIn,combinedOverlayIn,itemRenderer.getItemModelWithOverrides(stack,te.getWorld(),null));
                    matrixStack.pop();
                }
            }
            stack = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null).getStackInSlot(1);
            if(stack!=ItemStack.EMPTY) {
                for (int i = lc; i < Math.min(stack.getCount() + lc, 16); i++) {
                    matrixStack.push();
                    matrixStack.scale(0.2f, 0.2f, 0.2f);
                    //matrixStack.translate(0,1,0);
                    matrixStack.translate(i % 4 + 1,4.5f, i / 4 + 1);
                    itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer,
                            combinedLightIn, combinedOverlayIn, itemRenderer.getItemModelWithOverrides(stack, te.getWorld(), null));
                    matrixStack.pop();
                }
            }
        }
    }
}
