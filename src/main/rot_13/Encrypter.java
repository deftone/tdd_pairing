package main.rot_13;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Encrypter {

    private static final Map<String, Integer> alphabetIndex = new HashMap<>();
    static {
        alphabetIndex.put("A", 1);
        alphabetIndex.put("N", 14);
    }

    public String rot13(String a) {
        Integer index = alphabetIndex.get(a);
        Optional<Map.Entry<String, Integer>> first = alphabetIndex.entrySet().stream().filter(i -> i.getValue().equals(index + 13)).findFirst();
        return first.get().getKey();
    }
}
