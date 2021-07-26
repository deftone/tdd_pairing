package test.rot_13;

import main.rot_13.Encrypter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Rot13Test {

    private final Encrypter encrypter = new Encrypter();

    @Test
    void testeBuchstaben(){
        Assertions.assertEquals("N", encrypter.rot13("A"));
    }

    @Test
    void testeWort(){
        Assertions.assertEquals("NOP", encrypter.rot13("ABC"));
    }

    @Test
    void testeWortMitKleinenBuchstaben(){
        Assertions.assertEquals("NOP", encrypter.rot13("abc"));
    }

    @Test
    void testeWortWiederVonVorne(){
        Assertions.assertEquals("KLM", encrypter.rot13("XYZ"));
    }

    @ParameterizedTest
    @CsvSource({"Ö,BR", "Ä,NR", "Ü,HR", "ß,FF"})
    void testeUmlaute(String given, String expected){
        Assertions.assertEquals(expected, encrypter.rot13(given));
    }

    @ParameterizedTest
    @CsvSource({"1,1", "!,!", "\\#,\\#", "?,?", "@,@"})
    void testeAndereZeichen(String given, String expected){
        Assertions.assertEquals(expected, encrypter.rot13(given));
    }

    @Test
    void testeLeerzeichen(){
        Assertions.assertEquals("N P", encrypter.rot13("A C"));
    }
}
