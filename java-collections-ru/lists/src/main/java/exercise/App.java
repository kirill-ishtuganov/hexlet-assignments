package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {

    public static boolean scrabble(String letters, String word) {

        char[] arrayOfWordLetters = word.toLowerCase().toCharArray();
        char[] arrayOfLetters = letters.toCharArray();
        List<Character> listOfLetters = new ArrayList<>();
        for (var letter : arrayOfLetters) {
            listOfLetters.add(letter);
        }

        for (var letter : arrayOfWordLetters) {
            if (listOfLetters.contains(letter)) {
                listOfLetters.remove(listOfLetters.indexOf(letter));
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
