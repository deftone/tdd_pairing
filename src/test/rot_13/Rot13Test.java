package test.rot_13;

import main.rot_13.Encrypter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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


}
