package main.rot_13;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Encrypter {

    private static final Map<Character, Integer> alphabetIndex = new HashMap<>();

    static {
        alphabetIndex.put('A', 1);
        alphabetIndex.put('B', 2);
        alphabetIndex.put('C', 3);
        alphabetIndex.put('D', 4);
        alphabetIndex.put('E', 5);
        alphabetIndex.put('F', 6);
        alphabetIndex.put('G', 7);
        alphabetIndex.put('H', 8);
        alphabetIndex.put('I', 9);
        alphabetIndex.put('J', 10);
        alphabetIndex.put('K', 11);
        alphabetIndex.put('L', 12);
        alphabetIndex.put('M', 13);
        alphabetIndex.put('N', 14);
        alphabetIndex.put('O', 15);
        alphabetIndex.put('P', 16);
        alphabetIndex.put('Q', 17);
        alphabetIndex.put('R', 18);
        alphabetIndex.put('S', 19);
        alphabetIndex.put('T', 20);
        alphabetIndex.put('U', 21);
        alphabetIndex.put('V', 22);
        alphabetIndex.put('W', 23);
        alphabetIndex.put('X', 24);
        alphabetIndex.put('Y', 25);
        alphabetIndex.put('Z', 26);
    }

    public String rot13(String word) {

        StringBuilder sb = new StringBuilder();
        for (char character : word.toUpperCase().toCharArray()) {
            Integer index = alphabetIndex.get(character);
            Optional<Map.Entry<Character, Integer>> rotatedCharacter =
                    alphabetIndex.entrySet()
                            .stream()
                            .filter(i -> isRotatedIndex(i, index))
                            .findFirst();
            rotatedCharacter.ifPresent(characterIntegerEntry -> sb.append(characterIntegerEntry.getKey()));
        }
        return sb.toString();
    }

    private boolean isRotatedIndex(Map.Entry<Character, Integer> i, Integer index) {
        if (isIndexOverflown(index)) {
            index = index - 26;
        }
        return i.getValue().equals(index + 13);
    }

    private boolean isIndexOverflown(Integer index){
       return index + 13 > 26;
    }

}
