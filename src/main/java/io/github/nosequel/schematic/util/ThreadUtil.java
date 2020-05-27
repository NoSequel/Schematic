package io.github.nosequel.schematic.util;

import java.util.PriorityQueue;
import java.util.Queue;

public class ThreadUtil {

    private static Thread currentThread;
    private static long lastCallTime = System.currentTimeMillis();

    private static final int priority = Thread.MIN_PRIORITY;
    private static final Queue<Callback> queue = new PriorityQueue<>();

    /**
     * Get the current thread
     *
     * @return the current thread
     */
    public static Thread getCurrentThread() {
        final Thread thread = currentThread == null ? new Thread(() -> {
            if (!queue.isEmpty()) {
                lastCallTime = System.currentTimeMillis();
            }

            queue.forEach(Callback::accept);

            try {
                Thread.sleep(System.currentTimeMillis() - lastCallTime < 10000 ? 1000 : 300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Schematic-Thread") : currentThread;

        thread.setPriority(priority);

        currentThread = thread;

        return thread;
    }

    /**
     * Execute a method in a runnable
     *
     * @param callback the method
     */
    public static void execute(Callback callback) {
        final Thread thread = getCurrentThread();

        System.out.println("Executing method in \"" + thread.getName() + "\"");
        queue.add(callback);
    }
}