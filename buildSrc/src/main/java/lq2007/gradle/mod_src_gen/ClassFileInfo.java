package lq2007.gradle.mod_src_gen;

import java.nio.file.Path;

public class ClassFileInfo {
    public final String packageName;
    public final String className;
    public final Path filePath;

    public ClassFileInfo(String packageName, String className, Path filePath) {
        this.packageName = packageName;
        this.className = className;
        this.filePath = filePath;
    }
}
