package test.galgenmaennchen;

import main.galgenmaennchen.Galgenmaennchen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class GalgenmaennchenTest {

    private Galgenmaennchen galgenmaennchen;
    private Galgenmaennchen galgenmaennchenDefault = new Galgenmaennchen("x");


    @Test
    void testeEinenBuchstaben() {
        Assertions.assertEquals("-", galgenmaennchenDefault.rateBuchstabe("a"));
        Assertions.assertEquals("x", galgenmaennchenDefault.rateBuchstabe("x"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"xx", "!", "#", "@", "1"})
    void testeFehleingabe(String fehleingabe) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> galgenmaennchenDefault.rateBuchstabe(fehleingabe));
    }
    //umlaute

    @ParameterizedTest
    @ValueSource(strings = {"a", "z", "A", "Z"})
    void testeRegex(String buchstabe) {
        galgenmaennchen = new Galgenmaennchen(buchstabe);
        Assertions.assertEquals(buchstabe.toLowerCase(), galgenmaennchen.rateBuchstabe(buchstabe));
    }
}
