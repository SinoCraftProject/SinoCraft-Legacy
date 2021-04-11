package cx.rain.mc.forgemod.sinocraft.utility;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cx.rain.mc.forgemod.sinocraft.utility.math.Vec2;
import cx.rain.mc.forgemod.sinocraft.utility.math.Vec3;
import cx.rain.mc.forgemod.sinocraft.utility.math.Vec4;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;

import javax.annotation.Nullable;

import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.*;

public class RenderHelper {
    public static final VertexFormat POSITION_COLOR_NORMAL = new VertexFormat(ImmutableList.<VertexFormatElement>builder().add(POSITION_3F).add(COLOR_4UB).add().add(NORMAL_3B).build());

    public static void add(IVertexBuilder builder, MatrixStack matrixStack, Vec3<Float> pos,@Nullable Vec2<Float> uv){
        add(builder,matrixStack,pos,new Vec4(1.0f,1.0f,1.0f,1.0f),uv);
    }

    public static void add(IVertexBuilder builder, Vec3<Float> pos,@Nullable Vec2<Float> uv){
        add(builder,null,pos,new Vec4(1.0f,1.0f,1.0f,1.0f),uv);
    }

    public static void add(IVertexBuilder builder, @Nullable MatrixStack matrixStack, Vec3<Float> pos, Vec4<Float> color,@Nullable Vec2<Float> uv){
        if (matrixStack == null) {
            builder.pos(pos.x, pos.y, pos.z)
                    .color(color.x, color.y, color.z, color.w)
                    .endVertex();
        }
        else {
            builder.pos(matrixStack.getLast().getMatrix(), pos.x, pos.y, pos.z)
                    .color(color.x, color.y, color.z, color.w);
            if (uv != null) {
                builder.tex(uv.x, uv.y);
            }
            else {
                builder.tex(0, 0);
            }
            builder.lightmap(0, 240)
                    .normal(1, 0, 0);
            builder.endVertex();
        }
    }

    public static void addSquare(IVertexBuilder builder,MatrixStack matrixStack,Vec3<Float> pos1,@Nullable Vec2<Float> uv1,Vec3<Float> pos2,@Nullable Vec2<Float> uv2,Vec3<Float> pos3
            ,@Nullable Vec2<Float> uv3,Vec3<Float> pos4,@Nullable Vec2<Float> uv4){
        add(builder,matrixStack,pos1,uv1);
        add(builder,matrixStack,pos2,uv2);
        add(builder,matrixStack,pos3,uv3);
        add(builder,matrixStack,pos4,uv4);
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack, Vec3<Float> pos1, Vec3<Float> pos2, Vec3<Float> pos3, Vec3<Float> pos4, @Nullable Vec4<Float> uv){
        if (uv != null) {
            addSquare(builder,matrixStack,
                    pos1,new Vec2(uv.x,uv.z),
                    pos2,new Vec2(uv.y,uv.z),
                    pos3,new Vec2(uv.x,uv.w),
                    pos4,new Vec2(uv.y,uv.w));
        }
        else {
            addSquare(builder,matrixStack,
                    pos1,null,
                    pos2,null,
                    pos3,null,
                    pos4,null);
        }
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack,
                                 Vec3<Float> pos1, @Nullable Vec2<Float> uv1, Vec4<Float> color1,
                                 Vec3<Float> pos2, @Nullable Vec2<Float> uv2, Vec4<Float> color2,
                                 Vec3<Float> pos3, @Nullable Vec2<Float> uv3, Vec4<Float> color3,
                                 Vec3<Float> pos4, @Nullable Vec2<Float> uv4, Vec4<Float> color4){
        add(builder,matrixStack,pos1,color1,uv1);
        add(builder,matrixStack,pos2,color2,uv2);
        add(builder,matrixStack,pos3,color3,uv3);
        add(builder,matrixStack,pos4,color4,uv4);
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack, Vec3<Float> pos1, Vec3<Float> pos2, Vec3<Float> pos3,
                                 Vec3<Float> pos4,@Nullable Vec4<Float> uv,Vec4<Float> color){
        if (uv != null) {
            addSquare(builder,matrixStack,
                    pos1,new Vec2(uv.x,uv.z),color,
                    pos2,new Vec2(uv.y,uv.z),color,
                    pos3,new Vec2(uv.x,uv.w),color,
                    pos4,new Vec2(uv.y,uv.w),color);
        }
        else {
            addSquare(builder,matrixStack,
                    pos1,null,color,
                    pos2,null,color,
                    pos3,null,color,
                    pos4,null,color);
        }
    }

    public static void addSquare(IVertexBuilder builder,Vec3<Float> pos1, @Nullable Vec2<Float> uv1,Vec3<Float> pos2, @Nullable Vec2<Float> uv2,Vec3<Float> pos3
            ,@Nullable Vec2<Float> uv3,Vec3<Float> pos4, @Nullable Vec2<Float> uv4){
        add(builder,pos1,uv1);
        add(builder,pos2,uv2);
        add(builder,pos3,uv3);
        add(builder,pos4,uv4);
    }

    public static void addSquare(IVertexBuilder builder, Vec3<Float> pos1, Vec3<Float> pos2, Vec3<Float> pos3, Vec3<Float> pos4, @Nullable Vec4<Float> uv){
        if (uv != null) {
            addSquare(builder,
                    pos1,new Vec2(uv.x,uv.z),
                    pos2,new Vec2(uv.y,uv.z),
                    pos3,new Vec2(uv.x,uv.w),
                    pos4,new Vec2(uv.y,uv.w));
        }
        else {
            addSquare(builder,
                    pos1,null,
                    pos2,null,
                    pos3,null,
                    pos4,null);
        }
    }

    public static void addSquare(IVertexBuilder builder,
                                 Vec3<Float> pos1, @Nullable Vec2<Float> uv1, Vec4<Float> color1,
                                 Vec3<Float> pos2, @Nullable Vec2<Float> uv2, Vec4<Float> color2,
                                 Vec3<Float> pos3, @Nullable Vec2<Float> uv3, Vec4<Float> color3,
                                 Vec3<Float> pos4, @Nullable Vec2<Float> uv4, Vec4<Float> color4){
        add(builder,null,pos1,color1,uv1);
        add(builder,null,pos2,color2,uv2);
        add(builder,null,pos3,color3,uv3);
        add(builder,null,pos4,color4,uv4);
    }

    public static void addSquare(IVertexBuilder builder, Vec3<Float> pos1, Vec3<Float> pos2, Vec3<Float> pos3,
                                 Vec3<Float> pos4, @Nullable Vec4<Float> uv,Vec4<Float> color){
        if (uv != null) {
            addSquare(builder,
                    pos1,new Vec2(uv.x,uv.z),color,
                    pos2,new Vec2(uv.y,uv.z),color,
                    pos3,new Vec2(uv.x,uv.w),color,
                    pos4,new Vec2(uv.y,uv.w),color);
        }
        else {
            addSquare(builder,
                    pos1,null,color,
                    pos2,null,color,
                    pos3,null,color,
                    pos4,null,color);
        }
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
}
