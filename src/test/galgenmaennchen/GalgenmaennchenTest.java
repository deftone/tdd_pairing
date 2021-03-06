package test.galgenmaennchen;

import main.galgenmaennchen.Galgenmaennchen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class GalgenmaennchenTest {

    private Galgenmaennchen galgenmaennchen;
    private final Galgenmaennchen galgenmaennchenDefault = new Galgenmaennchen("x");


    @Test
    void testeEinenBuchstaben() {
        Assertions.assertEquals("-", galgenmaennchenDefault.rateBuchstabe("a"));
        Assertions.assertEquals("Das Wort 'x' ist richtig!", galgenmaennchenDefault.rateBuchstabe("x"));
    }

    @Test
    void testeWortEinfach() {
        galgenmaennchen = new Galgenmaennchen("abax");
        Assertions.assertEquals("----", galgenmaennchen.rateBuchstabe("c"));
        Assertions.assertEquals("a-a-", galgenmaennchen.rateBuchstabe("a"));
        Assertions.assertEquals("a-a-", galgenmaennchen.rateBuchstabe("d"));
        Assertions.assertEquals("aba-", galgenmaennchen.rateBuchstabe("b"));
        Assertions.assertEquals("Das Wort 'abax' ist richtig!", galgenmaennchen.rateBuchstabe("x"));
    }

    @Test
    void testeEingabewortUmwandelnInklUmlaute() {
        galgenmaennchen = new Galgenmaennchen("AbcÜöÄß");
        Assertions.assertEquals("a------a---", galgenmaennchen.rateBuchstabe("A"));
        Assertions.assertEquals("ab-----a---", galgenmaennchen.rateBuchstabe("b"));
        Assertions.assertEquals("abc----a---", galgenmaennchen.rateBuchstabe("c"));
        Assertions.assertEquals("abc-e-eae--", galgenmaennchen.rateBuchstabe("e"));
        Assertions.assertEquals("abcue-eae--", galgenmaennchen.rateBuchstabe("u"));
        Assertions.assertEquals("abcueoeae--", galgenmaennchen.rateBuchstabe("o"));
        Assertions.assertEquals("abcueoeae--", galgenmaennchen.rateBuchstabe("x"));
        Assertions.assertEquals("Das Wort 'abcueoeaess' ist richtig!", galgenmaennchen.rateBuchstabe("s"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"xx", "!", "#", "@", "1", "ü", "ß"})
    void testeFehleingabe(String fehleingabe) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> galgenmaennchenDefault.rateBuchstabe(fehleingabe));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "z", "A", "Z"})
    void testeRegex(String buchstabe) {
        galgenmaennchen = new Galgenmaennchen(buchstabe);
        Assertions.assertEquals("Das Wort '" + buchstabe.toLowerCase() + "' ist richtig!", galgenmaennchen.rateBuchstabe(buchstabe));
    }

    @ParameterizedTest
    @ValueSource(strings = {"!", "#", "@FDA", "AD1"})
    @EmptySource
    void testeEingabewortFehleingabe(String fehlwort) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Galgenmaennchen(fehlwort));
    }

    @Test
    void testeZuvieleFehlversuche() {
        for (int i = 0; i < 10; i++) {
            galgenmaennchenDefault.rateBuchstabe("A");
        }
        Assertions.assertEquals("Loser", galgenmaennchenDefault.rateBuchstabe("A"));
        Assertions.assertEquals("Spiel vorbei!", galgenmaennchenDefault.rateBuchstabe("A"));

    }
}
