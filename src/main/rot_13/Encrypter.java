package main.rot_13;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Encrypter {

    private static final Map<Character, Integer> alphabetIndex = new HashMap<>();
    private int offset = 0;

    public int getOffset() {
        return offset;
    }

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

    public Encrypter(int offset) {
        this.offset = offset % 26;
    }

    public String rotateCharacters(String word) {
        word = cleanUp(word);

        StringBuilder sb = new StringBuilder();
        for (char character : word.toCharArray()) {
            Integer index = alphabetIndex.get(character);
            if (index != null) {
                sb.append(getRotatedCharacter(index));
            } else {
                sb.append(character);
            }
        }
        return sb.toString();
    }

    private String cleanUp(String word) {
        return word.toUpperCase()
                .replace("Ö", "OE")
                .replace("Ä", "AE")
                .replace("Ü", "UE");
    }

    private Character getRotatedCharacter(Integer index) {
        Optional<Map.Entry<Character, Integer>> rotatedCharacter =
                alphabetIndex.entrySet()
                        .stream()
                        .filter(i -> isRotatedIndex(i, index))
                        .findFirst();
        if (rotatedCharacter.isPresent()) {
            return rotatedCharacter.get().getKey();
        }

        throw new RuntimeException("der charakter fuer " + index + "wurde nicht gefunden!");
    }


    private boolean isRotatedIndex(Map.Entry<Character, Integer> i, Integer index) {
        if (isIndexOverflown(index)) {
            index = index - 26;
        }
        return i.getValue().equals(index + offset);
    }

    private boolean isIndexOverflown(Integer index) {
        return index + offset > 26;
    }

}
