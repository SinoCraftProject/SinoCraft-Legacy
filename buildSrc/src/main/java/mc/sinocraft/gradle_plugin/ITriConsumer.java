package mc.sinocraft.gradle_plugin;

import org.eclipse.jdt.core.dom.Type;

import javax.annotation.Nullable;
import java.nio.file.Path;

public interface ITriConsumer<A, B, C> {

    void consume(A a, B b, C c);

    interface RegistryObjectInfoConsumer extends ITriConsumer<Type, String, String> {
        @Override
        void consume(@Nullable Type type, String name, String id);
    }

    interface ClassInfoConsumer extends ITriConsumer<String, String, Path> {
        @Override
        void consume(String packageName, String className, Path filePath);
    }
}
