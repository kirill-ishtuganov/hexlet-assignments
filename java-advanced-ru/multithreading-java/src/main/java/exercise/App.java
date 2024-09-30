package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] array){

        MinThread minThread = new MinThread(array);
        MaxThread maxThread = new MaxThread(array);
        minThread.start();
        try {
            minThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        maxThread.start();
        try {
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("max", maxThread.getMax());
        result.put("min", minThread.getMin());
        return result;
    }
    // END
}
