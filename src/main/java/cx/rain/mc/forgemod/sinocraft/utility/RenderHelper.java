package cx.rain.mc.forgemod.sinocraft.utility;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.*;

@OnlyIn(Dist.CLIENT)
public class RenderHelper {

    public static void add(IVertexBuilder builder, MatrixStack matrixStack, Vector3f pos, @Nullable Vector2f uv) {
        add(builder, matrixStack, pos, new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), uv);
    }

    public static void add(IVertexBuilder builder, Vector3f pos, @Nullable Vector2f uv) {
        add(builder, null, pos, new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), uv);
    }

    public static void add(IVertexBuilder builder, @Nullable MatrixStack matrixStack, Vector3f pos, Vector4f color, @Nullable Vector2f uv) {
        if (matrixStack == null) {
            builder.pos(pos.getX(), pos.getY(), pos.getZ())
                    .color(color.getX(), color.getY(), color.getZ(), color.getW())
                    .endVertex();
        } else {
            builder.pos(matrixStack.getLast().getMatrix(), pos.getX(), pos.getY(), pos.getZ())
                    .color(color.getX(), color.getY(), color.getZ(), color.getW());
            if (uv != null) {
                builder.tex(uv.x, uv.y);
            } else {
                builder.tex(0, 0);
            }
            builder.lightmap(0, 240)
                    .normal(1, 0, 0);
            builder.endVertex();
        }
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack, Vector3f pos1, @Nullable Vector2f uv1, Vector3f pos2, @Nullable Vector2f uv2, Vector3f pos3
            , @Nullable Vector2f uv3, Vector3f pos4, @Nullable Vector2f uv4) {
        add(builder, matrixStack, pos1, uv1);
        add(builder, matrixStack, pos2, uv2);
        add(builder, matrixStack, pos3, uv3);
        add(builder, matrixStack, pos4, uv4);
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack, Vector3f pos1, Vector3f pos2, Vector3f pos3, Vector3f pos4, @Nullable Vector4f uv) {
        if (uv != null) {
            addSquare(builder, matrixStack,
                    pos1, new Vector2f(uv.getX(), uv.getZ()),
                    pos2, new Vector2f(uv.getY(), uv.getZ()),
                    pos3, new Vector2f(uv.getX(), uv.getW()),
                    pos4, new Vector2f(uv.getY(), uv.getW()));
        } else {
            addSquare(builder, matrixStack,
                    pos1, null,
                    pos2, null,
                    pos3, null,
                    pos4, null);
        }
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack,
                                 Vector3f pos1, @Nullable Vector2f uv1, Vector4f color1,
                                 Vector3f pos2, @Nullable Vector2f uv2, Vector4f color2,
                                 Vector3f pos3, @Nullable Vector2f uv3, Vector4f color3,
                                 Vector3f pos4, @Nullable Vector2f uv4, Vector4f color4) {
        add(builder, matrixStack, pos1, color1, uv1);
        add(builder, matrixStack, pos2, color2, uv2);
        add(builder, matrixStack, pos3, color3, uv3);
        add(builder, matrixStack, pos4, color4, uv4);
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack, Vector3f pos1, Vector3f pos2, Vector3f pos3,
                                 Vector3f pos4, @Nullable Vector4f uv, Vector4f color) {
        if (uv != null) {
            addSquare(builder, matrixStack,
                    pos1, new Vector2f(uv.getX(), uv.getZ()), color,
                    pos2, new Vector2f(uv.getY(), uv.getZ()), color,
                    pos3, new Vector2f(uv.getX(), uv.getW()), color,
                    pos4, new Vector2f(uv.getY(), uv.getW()), color);
        } else {
            addSquare(builder, matrixStack,
                    pos1, null, color,
                    pos2, null, color,
                    pos3, null, color,
                    pos4, null, color);
        }
    }

    public static void addSquare(IVertexBuilder builder, Vector3f pos1, @Nullable Vector2f uv1, Vector3f pos2, @Nullable Vector2f uv2, Vector3f pos3
            , @Nullable Vector2f uv3, Vector3f pos4, @Nullable Vector2f uv4) {
        add(builder, pos1, uv1);
        add(builder, pos2, uv2);
        add(builder, pos3, uv3);
        add(builder, pos4, uv4);
    }

    public static void addSquare(IVertexBuilder builder, Vector3f pos1, Vector3f pos2, Vector3f pos3, Vector3f pos4, @Nullable Vector4f uv) {
        if (uv != null) {
            addSquare(builder,
                    pos1, new Vector2f(uv.getX(), uv.getZ()),
                    pos2, new Vector2f(uv.getY(), uv.getZ()),
                    pos3, new Vector2f(uv.getX(), uv.getW()),
                    pos4, new Vector2f(uv.getY(), uv.getW()));
        } else {
            addSquare(builder,
                    pos1, null,
                    pos2, null,
                    pos3, null,
                    pos4, null);
        }
    }

    public static void addSquare(IVertexBuilder builder,
                                 Vector3f pos1, @Nullable Vector2f uv1, Vector4f color1,
                                 Vector3f pos2, @Nullable Vector2f uv2, Vector4f color2,
                                 Vector3f pos3, @Nullable Vector2f uv3, Vector4f color3,
                                 Vector3f pos4, @Nullable Vector2f uv4, Vector4f color4) {
        add(builder, null, pos1, color1, uv1);
        add(builder, null, pos2, color2, uv2);
        add(builder, null, pos3, color3, uv3);
        add(builder, null, pos4, color4, uv4);
    }

    public static void addSquare(IVertexBuilder builder, Vector3f pos1, Vector3f pos2, Vector3f pos3,
                                 Vector3f pos4, @Nullable Vector4f uv, Vector4f color) {
        if (uv != null) {
            addSquare(builder,
                    pos1, new Vector2f(uv.getX(), uv.getZ()), color,
                    pos2, new Vector2f(uv.getY(), uv.getZ()), color,
                    pos3, new Vector2f(uv.getX(), uv.getW()), color,
                    pos4, new Vector2f(uv.getY(), uv.getW()), color);
        } else {
            addSquare(builder,
                    pos1, null, color,
                    pos2, null, color,
                    pos3, null, color,
                    pos4, null, color);
        }
    }

    public static Vector4f RGBAToGLColor(Vector4f rgba) {
        rgba.set(rgba.getX() / 256, rgba.getY() / 256, rgba.getZ() / 256, rgba.getW() / 100);
        return rgba;
    }

    public static Vector4f GLColorToRGBA(Vector4f glColor) {
        glColor.set(glColor.getX() * 256, glColor.getY() * 256, glColor.getZ() * 256, glColor.getW() * 100);
        return glColor;
    }

    public static Vector4f NumberToRGBA(int color) {
        float a = color >>> 24;
        float r = (color & 0xff0000) >> 16;
        float g = (color & 0xff00) >> 8;
        float b = color & 0xff;
        return new Vector4f(r, g, b, a);
    }

    public static Vector4f NumberToGLColor(int color) {
        Vector4f rgba = NumberToRGBA(color);
        return RGBAToGLColor(rgba);
    }

    /**
     * 绘制限制大小的文字
     *
     * @param text     文字
     * @param matrix   matrix
     * @param renderer font renderer
     * @param x        x 坐标
     * @param y        y 坐标
     * @param width    最大宽度，无限制则为 0
     * @param height   最大高度，无限制则为 0
     * @param color    颜色
     * @param center   文字是否居中
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
