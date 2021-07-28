package test.rot_13;

import main.rot_13.Encrypter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Rot13Test {

    private final Encrypter encrypterRot13 = new Encrypter(13);

    @Test
    void testeBuchstaben() {
        Assertions.assertEquals("N", encrypterRot13.rotateCharacters("A"));
    }

    @Test
    void testeWort() {
        Assertions.assertEquals("NOP", encrypterRot13.rotateCharacters("ABC"));
    }

    @Test
    void testeWortMitKleinenBuchstaben() {
        Assertions.assertEquals("NOP", encrypterRot13.rotateCharacters("abc"));
    }

    @Test
    void testeWortWiederVonVorne() {
        Assertions.assertEquals("KLM", encrypterRot13.rotateCharacters("XYZ"));
    }

    @ParameterizedTest
    @CsvSource({"Ö,BR", "Ä,NR", "Ü,HR", "ß,FF"})
    void testeUmlaute(String given, String expected) {
        Assertions.assertEquals(expected, encrypterRot13.rotateCharacters(given));
    }

    @ParameterizedTest
    @CsvSource({"1,1", "!,!", "\\#,\\#", "?,?", "@,@"})
    void testeAndereZeichen(String given, String expected) {
        Assertions.assertEquals(expected, encrypterRot13.rotateCharacters(given));
    }

    @Test
    void testeLeerzeichen() {
        Assertions.assertEquals("N P", encrypterRot13.rotateCharacters("A C"));
    }

    @Test
    void testeOffset() {
        for (int i = 0; i < 26; i++) {
            Encrypter encrypter = new Encrypter(i);
            Assertions.assertEquals(i, encrypter.getOffset());

            Encrypter encrypter26 = new Encrypter(i+26);
            Assertions.assertEquals(i, encrypter26.getOffset());

            Encrypter encrypter52 = new Encrypter(i+52);
            Assertions.assertEquals(i, encrypter52.getOffset());
        }
    }
}
