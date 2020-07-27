package cx.rain.mc.forgemod.sinocraft.client.renderer.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sun.javafx.geom.Vec3f;
import com.sun.javafx.geom.Vec4d;
import com.sun.javafx.geom.Vec4f;
import cx.rain.mc.forgemod.sinocraft.tileentity.TileEntityVat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec2f;

public class TileEntityVatRender extends TileEntityRenderer<TileEntityVat> {
    public TileEntityVatRender(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    public static void add(IVertexBuilder builder, MatrixStack matrixStack, Vec3f pos, Vec2f uv){
        add(builder,matrixStack,pos,new Vec4f(1.0f,1.0f,1.0f,1.0f),uv);
    }

    public static void add(IVertexBuilder builder, MatrixStack matrixStack, Vec3f pos,Vec4f color, Vec2f uv){
        builder.pos(matrixStack.getLast().getMatrix(), pos.x, pos.y, pos.z)
                .color(color.x, color.y, color.z, color.w)
                .tex(uv.x, uv.y)
                .lightmap(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }

    public static void addSquare(IVertexBuilder builder,MatrixStack matrixStack,Vec3f pos1,Vec2f uv1,Vec3f pos2,Vec2f uv2,Vec3f pos3,Vec2f uv3,Vec3f pos4,Vec2f uv4){
        add(builder,matrixStack,pos1,uv1);
        add(builder,matrixStack,pos2,uv2);
        add(builder,matrixStack,pos3,uv3);
        add(builder,matrixStack,pos4,uv4);
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack, Vec3f pos1, Vec3f pos2, Vec3f pos3, Vec3f pos4, Vec4f uv){
        addSquare(builder,matrixStack,
                pos1,new Vec2f(uv.x,uv.z),
                pos2,new Vec2f(uv.y,uv.z),
                pos3,new Vec2f(uv.x,uv.w),
                pos4,new Vec2f(uv.y,uv.w));
    }

    public static void addSquare(IVertexBuilder builder,MatrixStack matrixStack,
                                 Vec3f pos1,Vec2f uv1,Vec4f color1,
                                 Vec3f pos2,Vec2f uv2,Vec4f color2,
                                 Vec3f pos3,Vec2f uv3,Vec4f color3,
                                 Vec3f pos4,Vec2f uv4,Vec4f color4){
        add(builder,matrixStack,pos1,color1,uv1);
        add(builder,matrixStack,pos2,color2,uv2);
        add(builder,matrixStack,pos3,color3,uv3);
        add(builder,matrixStack,pos4,color4,uv4);
    }

    public static void addSquare(IVertexBuilder builder, MatrixStack matrixStack, Vec3f pos1, Vec3f pos2, Vec3f pos3, Vec3f pos4, Vec4f uv,Vec4f color){
        addSquare(builder,matrixStack,
                pos1,new Vec2f(uv.x,uv.z),color,
                pos2,new Vec2f(uv.y,uv.z),color,
                pos3,new Vec2f(uv.x,uv.w),color,
                pos4,new Vec2f(uv.y,uv.w),color);
    }

    public static Vec4f RGBAToGLColor(Vec4f rgba){
        rgba.x/=256;
        rgba.y/=256;
        rgba.w/=256;
        rgba.z/=100;
        return rgba;
    }
    public static Vec4f GLColorToRGBA(Vec4f glColor){
        glColor.x*=256;
        glColor.y*=256;
        glColor.w*=256;
        glColor.z*=100;
        return glColor;
    }
    public static Vec4f NumberToRGBA(int rgb,int alpha){
        int b=rgb/(256*256);
        int g=(rgb-b*256*256)/256;
        int r=rgb-b*256*256-g*256;
        return new Vec4f(r,g,b,alpha);
    }
    public static Vec4f NumberToGLColor(int rgb,int alpha){
        Vec4f rgba = NumberToRGBA(rgb,alpha);
        return RGBAToGLColor(rgba);
    }

    @Override
    public void render(TileEntityVat te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).
                apply(Fluids.WATER.getAttributes().getStillTexture());
        IVertexBuilder vertex = buffer.getBuffer(RenderType.getText(sprite.getAtlasTexture().getTextureLocation()));
        matrixStack.push();
       // matrixStack.scale(0.7f,0.7f,0.7f);
        matrixStack.translate(0.0,1.5,0.0);
        addSquare(vertex,matrixStack,
                new Vec3f(0,1,1.0f),new Vec3f(1,1,1.0f),
                new Vec3f(1,0,1.0f),new Vec3f(0,0,1.0f),
                new Vec4f(sprite.getMinU(),sprite.getMaxU(),sprite.getMinV(),sprite.getMaxV()),
                NumberToGLColor(0x3f76e4,1)
        );
        matrixStack.pop();
    }
}
