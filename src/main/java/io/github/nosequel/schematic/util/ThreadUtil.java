package io.github.nosequel.schematic.util;

public class ThreadUtil {

    private static final int priority = Thread.MIN_PRIORITY;

    private static Callback method;
    private static Thread thread;

    /**
     * Execute a method in a runnable
     *
     * @param callback the method
     */
    public static void execute(Callback callback) {
        method = callback;

        if (thread == null) {
            thread = new Thread(() -> method.accept(), "Schematic Thread");

            thread.setPriority(priority);
        }

        thread.run();
    }
}