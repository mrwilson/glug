package uk.co.probablyfine.glug;

import java.util.HashMap;
import java.util.Map;

public class GlugState {

    private static final Map<String, Glug> glugTasks = new HashMap<>();

    public static void addTask(final String taskName, final Glug glugTask) {
        glugTasks.put(taskName, glugTask);
    }

    public static void runTask(final String taskName) {
        glugTasks.get(taskName).run();
    }
}
