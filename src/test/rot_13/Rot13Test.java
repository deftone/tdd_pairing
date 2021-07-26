package test.rot_13;

import main.rot_13.Encrypter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Rot13Test {

    @Test
    void test(){
        Encrypter encrypter = new Encrypter();
        Assertions.assertEquals("N", encrypter.rot13("A"));
    }

}
