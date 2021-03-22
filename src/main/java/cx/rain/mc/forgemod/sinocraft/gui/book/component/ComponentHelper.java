package cx.rain.mc.forgemod.sinocraft.gui.book.component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.common.model.TransformationHelper;

import java.util.function.Function;
import java.util.function.Supplier;

public class ComponentHelper {
    public static class Transformer {
        public Vector3d translate;
        public Vector3f scale;
        public Vector3f rotate;

        public Transformer(JsonObject json) {
            if (json == null) {
                return;
            }
            if (json.has("translate")) {
                JsonArray j_translate = json.getAsJsonArray("translate");
                translate = new Vector3d(j_translate.get(0).getAsDouble(), j_translate.get(1).getAsDouble(), j_translate.get(2).getAsDouble());
            }
            if (json.has("scale")) {
                JsonArray j_scale = json.getAsJsonArray("scale");
                scale = new Vector3f(j_scale.get(0).getAsFloat(), j_scale.get(1).getAsFloat(), j_scale.get(2).getAsFloat());
            }
            if (json.has("rotate")) {
                JsonArray j_rotate = json.getAsJsonArray("rotate");
                rotate = new Vector3f(j_rotate.get(0).getAsFloat(), j_rotate.get(1).getAsFloat(), j_rotate.get(2).getAsFloat());
            }
        }

        public MatrixStack doTranslate(MatrixStack stack)
        {
            if (translate != null)
                stack.translate(translate.x, translate.y, translate.z);
            if (scale != null)
                stack.scale(scale.getX(), scale.getY(), scale.getZ());
            if (rotate != null)
                stack.rotate(TransformationHelper.quatFromXYZ(rotate, true));
            return stack;
        }
    }

    public static <T> T orDo(T obj, Supplier<T> func) {
        return obj == null ? func.get() : obj;
    }
    
    public static <T> T orElse(T obj, T def) {
        return orDo(obj, () -> def);
    }

    public static <T> T orExec(T obj) {
        return orDo(obj, () -> {
            throw new IllegalStateException("Null Value!");
        });
    }
}
