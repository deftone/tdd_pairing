package test.pairing_manuel;

import main.pairing_manuel.RoemischeZahlenConverter;
import main.pairing_manuel.RoemischeZahlenConverterMitRegex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class RoemischeZahlenConverterTest {

    @ParameterizedTest(name = "Test [{index}], arab. {0}, roem. {1}")
    @DisplayName("teste basiszahlen/werte")
    @CsvSource({"1, I", "5, V", "10, X", "50, L", "100, C"})
    void testBasis(int arabischeZahl, String roemischeZahl) {
        RoemischeZahlenConverter converter = new RoemischeZahlenConverter();
        Assertions.assertEquals(arabischeZahl, converter.convert(roemischeZahl));

        RoemischeZahlenConverterMitRegex converterMitRegex = new RoemischeZahlenConverterMitRegex();
        Assertions.assertEquals(arabischeZahl, converterMitRegex.convert(roemischeZahl));
    }

    @ParameterizedTest
    @DisplayName("teste 'addition' von roemischen ziffern")
    @CsvSource({"3, III", "8, VIII", "12, XII", "15, XV", "17, XVII",
            "52, LII", "103, CIII"})
    void testAddition(int arabischeZahl, String roemischeZahl) {
        RoemischeZahlenConverter converter = new RoemischeZahlenConverter();
        Assertions.assertEquals(arabischeZahl, converter.convert(roemischeZahl));

        RoemischeZahlenConverterMitRegex converterMitRegex = new RoemischeZahlenConverterMitRegex();
        Assertions.assertEquals(arabischeZahl, converterMitRegex.convert(roemischeZahl));
    }

    @ParameterizedTest
    @DisplayName("Teste 'subtraktion' in komplexen roemischen ziffernfolgen")
    @CsvSource({"4, IV", "9, IX", "14, XIV", "19, XIX", "24, XXIV", "29, XXIX",
            "44, XLIV", "49, XLIX ", "79, LXXIX", "94, XCIV", "99, XCIX"})
    void testSubtraktion(int arabischeZahl, String roemischeZahl) {
        RoemischeZahlenConverter converter = new RoemischeZahlenConverter();
        Assertions.assertEquals(arabischeZahl, converter.convert(roemischeZahl));

        RoemischeZahlenConverterMitRegex converterMitRegex = new RoemischeZahlenConverterMitRegex();
        Assertions.assertEquals(arabischeZahl, converterMitRegex.convert(roemischeZahl));
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "E", "4", "24", "?", "XW", "$", "#", "%", " "})
    void testeFehleingaben( String fehleingabe) {
        RoemischeZahlenConverter converter = new RoemischeZahlenConverter();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(fehleingabe);
        },"Falsche Eingabe");

        RoemischeZahlenConverterMitRegex converterMitRegex = new RoemischeZahlenConverterMitRegex();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            converterMitRegex.convert(fehleingabe);
        },"Falsche Eingabe oder ungueltige roemische Zahl");
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testeNullUndEmptyString(String emptyOderNull){
        RoemischeZahlenConverter converter = new RoemischeZahlenConverter();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(emptyOderNull);
        }, "Darf nicht leer sein");

        RoemischeZahlenConverterMitRegex converterMitRegex = new RoemischeZahlenConverterMitRegex();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            converterMitRegex.convert(emptyOderNull);
        }, "Darf nicht leer sein");
    }

    @ParameterizedTest
    @DisplayName("Teste 'subtraktion' in falschen roemischen ziffernfolgen")
    @CsvSource( {"19, IXX", "24, IVXX", "4, IIII", "49, IL"})
    void testFalscheKombinationBeiSubtraktion(int arabischeZahl, String roemischeZahl) {
        RoemischeZahlenConverter converter = new RoemischeZahlenConverter();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(roemischeZahl);
        }, "Ungueltige roemische Zahl");

        RoemischeZahlenConverterMitRegex converterMitRegex = new RoemischeZahlenConverterMitRegex();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            converterMitRegex.convert(roemischeZahl);
        },"Falsche Eingabe oder ungueltige roemische Zahl");
    }


}
