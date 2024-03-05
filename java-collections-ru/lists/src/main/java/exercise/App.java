package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {

    public static boolean scrabble(String letters, String word) {

        String[] arrayOfWordLetters = word.toLowerCase().split("");
        String[] arrayOfLetters = letters.split("");
        List<String> listOfLetters = new ArrayList<>(Arrays.asList(arrayOfLetters));

        for (var letter : arrayOfWordLetters) {
            if (listOfLetters.contains(letter)) {
                listOfLetters.remove(letter);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
