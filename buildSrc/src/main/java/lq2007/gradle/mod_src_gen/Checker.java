package lq2007.gradle.mod_src_gen;

import com.squareup.javapoet.ClassName;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Checker {

    public static void checkRegistryObjects(ModSourceGenerator task, ClassName originalClass, ClassName newClass, Map<String, String> equalsClass) throws IOException {
        Map<String, RegistryObjectInfo> oldInfos = new HashMap<>();
        Map<String, RegistryObjectInfo> newInfos = new HashMap<>();
        Utils.forAllRegistryObjects(originalClass, task, (type, name, id) -> oldInfos.put(name, new RegistryObjectInfo(name, id, type)));
        Utils.forAllRegistryObjects(newClass, task, (type, name, id) -> newInfos.put(name, new RegistryObjectInfo(name, id, type)));
        StringBuilder sb = new StringBuilder();
        oldInfos.forEach((name, oldInfo) -> {
            RegistryObjectInfo newInfo = newInfos.remove(name);
            if (newInfo == null) {
                sb.append(" ").append(name).append(" not exist in ").append(newClass).append("\n");
            } else {
                boolean diffId = !oldInfo.id.equals(newInfo.id);
                boolean diffType;
                if (oldInfo.type.equals(newInfo.type)) diffType = false;
                else if (equalsClass.containsKey(oldInfo.type)) diffType = !newInfo.type.equals(equalsClass.get(oldInfo.type));
                else diffType = true;
                boolean diffBoth = diffId && diffType;
                if (diffId || diffType) {
                    sb.append(" ").append(name).append(" has different");
                    if (diffBoth) {
                        sb.append(" id and type:\n")
                          .append("  id: ").append(oldInfo.id).append(" -> ").append(newInfo.id).append("\n")
                          .append("  type: ").append(oldInfo.type).append(" -> ").append(newInfo.type).append("\n");
                    } else if (diffId) {
                        sb.append(" id").append("(").append(oldInfo.id).append(" -> ").append(newInfo.id).append(")\n");
                    } else {
                        sb.append(" type").append("(").append(oldInfo.type).append(" -> ").append(newInfo.type).append(")\n");
                    }
                }
            }
        });
        newInfos.forEach((name, info) -> {
            sb.append(" ").append(name).append(" not exist in ").append(originalClass).append("\n");
        });
        if (sb.length() > 0) {
            throw new IOException("Difference list: \n" + sb);
        }
    }
}
