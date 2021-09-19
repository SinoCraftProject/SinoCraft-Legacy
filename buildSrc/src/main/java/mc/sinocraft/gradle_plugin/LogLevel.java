package mc.sinocraft.gradle_plugin;

import org.gradle.api.Project;

public enum LogLevel {
    detail {

        @Override
        public void detail(Project project, String message) {
            project.getLogger().info(message);
        }

        @Override
        public void warn(Project project, String message) {
            project.getLogger().warn(message);
        }
    },
    warn {

        @Override
        public void detail(Project project, String message) {

        }

        @Override
        public void warn(Project project, String message) {
            project.getLogger().warn(message);
        }
    },
    quiet {

        @Override
        public void detail(Project project, String message) {

        }

        @Override
        public void warn(Project project, String message) {

        }
    },
    ;

    public abstract void detail(Project project, String message);

    public abstract void warn(Project project, String message);
}
