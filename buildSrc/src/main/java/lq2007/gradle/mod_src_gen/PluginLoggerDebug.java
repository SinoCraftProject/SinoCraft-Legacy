package lq2007.gradle.mod_src_gen;

public class PluginLoggerDebug extends PluginLogger {

    @Override
    public void printDetailedLog(String message) {
        System.out.println(message);
    }
}
