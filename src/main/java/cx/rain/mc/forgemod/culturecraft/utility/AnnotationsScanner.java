package cx.rain.mc.forgemod.culturecraft.utility;

import org.objectweb.asm.ClassVisitor;

public class AnnotationsScanner extends ClassVisitor {
    public AnnotationsScanner(int api) {
        super(api);
    }
}
