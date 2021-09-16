package mc.sinocraft.gradle_plugin;

import org.eclipse.jdt.core.dom.Type;

import java.util.Objects;

public class RegistryObjectInfo {

    public final String name, id;
    public final Type type;

    public RegistryObjectInfo(String name, String id, Type type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistryObjectInfo that = (RegistryObjectInfo) o;
        return name.equals(that.name) && id.equals(that.id) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, type);
    }
}
