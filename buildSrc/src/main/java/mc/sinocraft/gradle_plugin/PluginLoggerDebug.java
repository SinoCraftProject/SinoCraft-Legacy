package mc.sinocraft.gradle_plugin;

public class PluginLoggerDebug extends PluginLogger {

    @Override
    public void printDetailedLog(String message) {
        System.out.println(message);
    }
}
