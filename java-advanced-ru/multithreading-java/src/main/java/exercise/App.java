package exercise;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] array){

        MinThread minThread = new MinThread(array);
        MaxThread maxThread = new MaxThread(array);
        minThread.start();
        LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " started");
        maxThread.start();
        LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " started");

        try {
            minThread.join();
            LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " finished");
            maxThread.join();
            LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Map<String, Integer> result = Map.of(
                "min", minThread.getMin(),
                "max", maxThread.getMax()
        );
        LOGGER.log(Level.INFO, "Result: " + result);
        return result;
    }
    // END
}
