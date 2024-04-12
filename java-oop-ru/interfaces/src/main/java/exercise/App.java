package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> houses, int count) {

        if (houses.isEmpty()) {
            return new ArrayList<String>();
        }

         List<String> result1 = houses.stream()
                .sorted(Home::compareTo)
                .map(Home::toString)
                .toList();
         ArrayList<String> result2 = new ArrayList<>();
         for (var i = 0; i < count; i++) {
             result2.add(result1.get(i));
        }
         return result2;
    }
}
// END
