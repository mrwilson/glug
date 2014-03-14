package uk.co.probablyfine.glug;

import java.util.HashMap;
import java.util.Map;

public class Glug {

    public static GlugTask src(String... sources) {
        return new GlugTask(sources);
    }

    private static final Map<String, GlugTask> glugTasks = new HashMap<>();

    public static void task(final String taskName, final GlugTask glugTask) {
        glugTasks.put(taskName, glugTask);
    }

    public static void run(final String taskName) throws NoSuchTaskNameException {
        if (!glugTasks.containsKey(taskName)) {
            throw new NoSuchTaskNameException("Could not find task \""+taskName+"\"");
        }
        glugTasks.get(taskName).run();
    }

}