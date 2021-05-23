package cx.rain.mc.forgemod.sinocraft.utility;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cx.rain.mc.forgemod.sinocraft.api.utility.math.Vec2;
import cx.rain.mc.forgemod.sinocraft.api.utility.math.Vec3;
import cx.rain.mc.forgemod.sinocraft.api.utility.math.Vec4;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.StringUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.*;

@OnlyIn(Dist.CLIENT)
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

    /**
     * 绘制限制大小的文字
     * @param text 文字
     * @param matrix matrix
     * @param renderer font renderer
     * @param x x 坐标
     * @param y y 坐标
     * @param width 最大宽度，无限制则为 0
     * @param height 最大高度，无限制则为 0
     * @param color 颜色
     * @param center 文字是否居中
     */
    public static void drawText(String text, MatrixStack matrix, FontRenderer renderer, int x, int y, int width, int height, int color, boolean center) {
        if (StringUtils.isNullOrEmpty(text)) {
            return;
        }
        int fontWidth = renderer.getStringWidth(text);
        int fontHeight = renderer.getWordWrappedHeight(text, fontWidth);
        if ((width <= 0 || fontWidth <= width) && (height <= 0 || fontHeight <= height)) {
            if (!center) {
                renderer.drawString(matrix, text, x, y, color);
            } else {
                int dx = (width - fontWidth) / 2;
                int dy = (height - fontHeight) / 2;
                renderer.drawString(matrix, text, x + dx, y + dy, color);
            }
        } else {
            float scaleX = width <= 0 ? 1 : (float) width / fontWidth;
            float scaleY = height <= 0 ? 1 : (float) height / fontHeight;
            if (!center) {
                float scale = Math.min(scaleX, scaleY);
                matrix.push();
                matrix.translate(x, y, 0);
                matrix.scale(scale, scale, 1);
                renderer.drawString(matrix, text, 0, 0, color);
                matrix.pop();
            } else {
                if (scaleX < scaleY) {
                    matrix.push();
                    // scaleX
                    if (height <= 0) {
                        matrix.translate(x, y, 0);
                    } else {
                        matrix.translate(x, y + (height - fontHeight * scaleX) / 2, 0);
                    }
                    matrix.scale(scaleX, scaleX, 1);
                    renderer.drawString(matrix, text, 0, 0, color);
                    matrix.pop();
                } else {
                    matrix.push();
                    // scaleY
                    if (width <= 0) {
                        matrix.translate(x, y, 0);
                    } else {
                        matrix.translate(x + (width - fontWidth * scaleY) / 2, y, 0);
                    }
                    matrix.scale(scaleY, scaleY, 1);
                    renderer.drawString(matrix, text, 0, 0, color);
                    matrix.pop();
                }
            }
        }
    }
}
