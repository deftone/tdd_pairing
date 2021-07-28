package test.galgenmaennchen;

import main.galgenmaennchen.Galgenmaennchen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class GalgenmaennchenTest {

    private Galgenmaennchen galgenmaennchen;
    private Galgenmaennchen galgenmaennchenDefault = new Galgenmaennchen("x");


    @Test
    void testeEinenBuchstaben() {
        Assertions.assertEquals("-", galgenmaennchenDefault.rateBuchstabe("a"));
        Assertions.assertEquals("x", galgenmaennchenDefault.rateBuchstabe("x"));
    }

    @Test
    void testeWort() {
        galgenmaennchen = new Galgenmaennchen("ab");
        Assertions.assertEquals("--", galgenmaennchen.rateBuchstabe("c"));
        Assertions.assertEquals("a-", galgenmaennchen.rateBuchstabe("a"));
        Assertions.assertEquals("ab", galgenmaennchen.rateBuchstabe("b"));
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
        Assertions.assertEquals(buchstabe.toLowerCase(), galgenmaennchen.rateBuchstabe(buchstabe));
    }

//    @Test
//    void testeEingabewortUmwandeln(){
//        galgenmaennchen = new Galgenmaennchen("AbcÜöÄß");
//        Assertions.assertEquals("abcueoeaess", galgenmaennchen.getZuRatendesWort());
//    }

    @ParameterizedTest
    @ValueSource(strings = {"!", "#", "@FDA", "AD1"})
    @EmptySource
    void testeEingabewortFehleingabe(String fehlwort) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Galgenmaennchen(fehlwort));
    }
}
