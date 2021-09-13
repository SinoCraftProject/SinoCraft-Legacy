package lq2007.gradle.mod_src_gen.generator;

import com.squareup.javapoet.ClassName;
import lq2007.gradle.mod_src_gen.ModSourceGenerator;
import lq2007.gradle.mod_src_gen.Utils;
import org.eclipse.jdt.core.dom.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModFluids {

    private static List<String> FLUIDS = null;
    public static final ClassName ModFluids = ClassName.get("cx.rain.mc.forgemod.sinocraft.fluid", "ModFluids");

    public static List<String> getAllFluids(ModSourceGenerator task) throws IOException {
        if (FLUIDS == null) {
            loadAllFluids(task);
        }
        return FLUIDS;
    }

    private static void loadAllFluids(ModSourceGenerator task) throws IOException {
        List<String> builder = new ArrayList<>();
        FieldDeclaration[] fields = Utils.getFields(ModFluids, task);
        for (FieldDeclaration field : fields) {
            int modifiers = field.getModifiers();
            if (!org.eclipse.jdt.core.dom.Modifier.isStatic(modifiers)
                    || !org.eclipse.jdt.core.dom.Modifier.isPublic(modifiers)) {
                continue;
            }
            Type type = field.getType();
            String typeName;
            if (type instanceof ParameterizedType) {
                typeName = ((SimpleType) ((ParameterizedType) type).getType()).getName().getFullyQualifiedName();
            } else if (type instanceof SimpleType) {
                typeName = ((SimpleType) type).getName().getFullyQualifiedName();
            } else {
                continue;
            }
            if (!"RegistryObject".equals(typeName)) {
                continue;
            }
            VariableDeclarationFragment variable = (VariableDeclarationFragment) field.fragments().get(0);
            String fieldName = variable.getName().getIdentifier();
            if (fieldName.endsWith("_FLOWING")) {
                continue;
            }

            builder.add(fieldName);
        }
        FLUIDS = Collections.unmodifiableList(builder);
    }
}
