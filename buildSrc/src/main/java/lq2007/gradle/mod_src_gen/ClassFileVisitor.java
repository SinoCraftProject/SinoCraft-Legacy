package lq2007.gradle.mod_src_gen;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClassFileVisitor implements FileVisitor<Path> {

    public List<ClassFileInfo> result = new ArrayList<>();

    private String rootPackage;
    private Path rootPath;

    private LinkedList<String> prevPackages = new LinkedList<>();
    private String currentPackage;

    public ClassFileVisitor(String packageName, Path rootPath) {
        this.rootPackage = packageName;
        this.rootPath = rootPath;

        currentPackage = packageName;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        String currentDirectory = dir.getFileName().toString();
        if (currentDirectory.equals(rootPath.getFileName().toString())) {
            return FileVisitResult.CONTINUE;
        }
        prevPackages.add(currentPackage);
        if (currentDirectory.isEmpty()) {
            currentPackage = currentDirectory;
        } else {
            currentPackage = currentPackage + "." + currentDirectory;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        String fileName = file.getFileName().toString();
        if (!fileName.endsWith(".java")) {
            return FileVisitResult.CONTINUE;
        }
        String className = fileName.substring(0, fileName.length() - 5);
        ClassFileInfo info = new ClassFileInfo(currentPackage, className, file);
        result.add(info);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        if (prevPackages.isEmpty()) {
            currentPackage = rootPackage;
        } else {
            currentPackage = prevPackages.removeLast();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        exc.printStackTrace();
        return FileVisitResult.CONTINUE;
    }
}
