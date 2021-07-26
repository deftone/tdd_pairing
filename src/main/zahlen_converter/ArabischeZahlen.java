package main.zahlen_converter;

import java.util.regex.Pattern;

public class ArabischeZahlen {

    private static int wieOftPasst10Rein;
    private static int restVonMod10;
    private static int wieOftPasst5Rein;
    private static int restVonMod5;

    private ArabischeZahlen() {
    }

    public static int convertFromRoemischToArabisch(String roemischeZahl) {
        return getArabischeEinerZahlen(roemischeZahl)
                + getArabischeFuenferZahenl(roemischeZahl)
                + getArabischeZehnerZahlen(roemischeZahl);
    }

    private static int getArabischeEinerZahlen(String roemischeZahl) {
        return getArabischeZahlAnteil(roemischeZahl, new Zahl("I", 1));
    }

    private static int getArabischeFuenferZahenl(String roemischeZahl) {
        return getArabischeZahlAnteil(roemischeZahl, new Zahl("V", 5));
    }

    private static int getArabischeZehnerZahlen(String roemischeZahl) {
        return getArabischeZahlAnteil(roemischeZahl, new Zahl("X", 10));
    }

    private static int getArabischeZahlAnteil(String roemischeZahl, Zahl zahl) {
        var arabischeZahl = 0;
        var matcherI = Pattern.compile(zahl.roemischeZahl).matcher(roemischeZahl);
        while (matcherI.find())
            arabischeZahl = arabischeZahl + zahl.arabischeZahl;
        return arabischeZahl;
    }

    public static class Zahl {
        private final String roemischeZahl;
        private final int arabischeZahl;

        public Zahl(String roemischeZahl, int arabischeZahl) {
            this.roemischeZahl = roemischeZahl;
            this.arabischeZahl = arabischeZahl;
        }
    }
}
