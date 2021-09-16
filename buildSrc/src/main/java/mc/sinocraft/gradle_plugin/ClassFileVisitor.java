package mc.sinocraft.gradle_plugin;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;

public class ClassFileVisitor implements FileVisitor<Path> {

    private String rootPackage;
    private Path rootPath;
    private boolean children;
    private ITriConsumer.ClassInfoConsumer consumer;

    private LinkedList<String> prevPackages = new LinkedList<>();
    private String currentPackage;

    public ClassFileVisitor(String packageName, Path rootPath, boolean children, ITriConsumer.ClassInfoConsumer consumer) {
        this.rootPackage = packageName;
        this.rootPath = rootPath;
        this.children = children;
        this.consumer = consumer;

        currentPackage = packageName;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        String currentDirectory = dir.getFileName().toString();
        if (currentDirectory.equals(rootPath.getFileName().toString())) {
            return FileVisitResult.CONTINUE;
        }
        if (!children) {
            return FileVisitResult.SKIP_SUBTREE;
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
        consumer.consume(currentPackage, className, file);
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
