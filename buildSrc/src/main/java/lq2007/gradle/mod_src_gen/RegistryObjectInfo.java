package lq2007.gradle.mod_src_gen;

import java.util.Objects;

public class RegistryObjectInfo {

    public final String name, id, type;

    public RegistryObjectInfo(String name, String id, String type) {
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
