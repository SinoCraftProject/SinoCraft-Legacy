package cx.rain.mc.forgemod.culturecraft.utility;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;

public class AnnotationsHelper {
    public static Set<Class<?>> getClassAnnotated(String pkg, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(pkg))
                .setScanners(new SubTypesScanner(false),
                                new TypeAnnotationsScanner())
        );
        return reflections.getTypesAnnotatedWith(annotation);
    }
}
