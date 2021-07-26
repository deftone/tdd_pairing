package test.roemische_zahlen;


import main.zahlen_converter.ArabischeZahlen;
import main.zahlen_converter.RoemischeZahlen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ZahlenConverterTest {
    // test
    @ParameterizedTest
    @CsvSource({"0, 0", "I,1", "II,2", "III,3", "IIII,4",
            "V,5", "VI,6", "VII,7", "VIII,8", "VIIII,9"})
    public void testConvertFrom0To9(String roemischeZahl, int arabischeZahl) {
        assertEquals(roemischeZahl, RoemischeZahlen.convertFromArabischToRoemisch(arabischeZahl));
    }

    @ParameterizedTest
    @CsvSource({"X, 10", "XI,11", "XII,12", "XIII,13", "XIIII,14",
            "XV,15", "XVI,16", "XVII,17", "XVIII,18", "XVIIII,19"})
    public void testConvertFrom10To19(String roemischeZahl, int arabischeZahl) {
        assertEquals(roemischeZahl, RoemischeZahlen.convertFromArabischToRoemisch(arabischeZahl));
    }

    @Test()
    public void testConvert20() {
        assertThrows(IllegalArgumentException.class, () ->
                RoemischeZahlen.convertFromArabischToRoemisch(20));
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "I,1", "II,2", "III,3", "IIII,4",
            "V,5", "VI,6", "VII,7", "VIII,8", "VIIII,9"})
    public void testConvertRoemischFrom0To9(String roemischeZahl, int arabischeZahl) {
        assertEquals(arabischeZahl, ArabischeZahlen.convertFromRoemischToArabisch(roemischeZahl));
    }

    @ParameterizedTest
    @CsvSource({"X, 10", "XI,11", "XII,12", "XIII,13", "XIIII,14",
            "XV,15", "XVI,16", "XVII,17", "XVIII,18", "XVIIII,19"})
    public void testConvertRoemischFrom10To19(String roemischeZahl, int arabischeZahl) {
        assertEquals(arabischeZahl, ArabischeZahlen.convertFromRoemischToArabisch(roemischeZahl));
    }

}