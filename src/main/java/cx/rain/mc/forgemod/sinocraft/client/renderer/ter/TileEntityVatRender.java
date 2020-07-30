package cx.rain.mc.forgemod.sinocraft.client.renderer.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cx.rain.mc.forgemod.sinocraft.api.util.math.Vec2;
import cx.rain.mc.forgemod.sinocraft.api.util.math.Vec3;
import cx.rain.mc.forgemod.sinocraft.api.util.math.Vec4;
import cx.rain.mc.forgemod.sinocraft.tileentity.TileEntityVat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

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
            if(te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null).getFluidInTank(0)!= FluidStack.EMPTY){
                Fluid fluid = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null).
                        getFluidInTank(0).getFluid();
                TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).
                        apply(fluid.getAttributes().getStillTexture());
                IVertexBuilder vertex = buffer.getBuffer(RenderType.getText(sprite.getAtlasTexture().getTextureLocation()));
                matrixStack.push();
                matrixStack.scale(0.75f,1.0f,0.75f);
                matrixStack.translate(0.18,0.00001,0.18);
                addSquare(vertex,matrixStack,
                        new Vec3(0.0f,1.0f,1.0f),new Vec3(1.0f,1.0f,1.0f),
                        new Vec3(1.0f,1.0f,0.0f),new Vec3(0.0f,1.0f,0.0f),
                        new Vec4(sprite.getMinU(),sprite.getMaxU(),sprite.getMinV(),sprite.getMaxV()),
                        NumberToGLColor(fluid.getAttributes().getColor())
                );
                matrixStack.pop();
            }
        }
    }
}
