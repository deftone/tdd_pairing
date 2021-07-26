package main.zahlen_converter;

public class RoemischeZahlen {

    private static int wieOftPasst10Rein;
    private static int restVonMod10;
    private static int wieOftPasst5Rein;
    private static int restVonMod5;

    private RoemischeZahlen() {
    }

    public static String convertFromArabischToRoemisch(int arabischeZahl) {

        if (arabischeZahl >= 20) {
            throw new IllegalArgumentException("Nur Zahlen bis 20 koennen konvertiert werden.");
        }

        bestimmeAlleParameter(arabischeZahl);

        if (dieZahlIstZwischen0Und5())
            return "I".repeat(restVonMod5);

        if (dieZahlIst5())
            return "V";

        if (dieZahlIstGroesserAls5())
            return "V" + "I".repeat(restVonMod5);

        if (dieZahlIst10())
            return "X";

        if (dieZahlIstGroesserAls10())
            return "X" + "I".repeat(restVonMod10);

        if (dieZahlIst15())
            return "XV";

        if (dieZahlIstGroesserAls15())
            return "XV" + "I".repeat(restVonMod5);

        return "0";
    }

    private static void bestimmeAlleParameter(int lateinischeZahl) {
        wieOftPasst10Rein = lateinischeZahl / 10;
        restVonMod10 = lateinischeZahl % 10;

        wieOftPasst5Rein = lateinischeZahl / 5;
        restVonMod5 = lateinischeZahl % 5;
    }

    private static boolean dieZahlIstZwischen0Und5() {
        return wieOftPasst5Rein == 0 && restVonMod5 > 0;
    }

    private static boolean dieZahlIst5() {
        return wieOftPasst5Rein == 1 && restVonMod5 == 0;
    }

    private static boolean dieZahlIstGroesserAls5() {
        return wieOftPasst5Rein == 1 && restVonMod5 > 0;
    }

    private static boolean dieZahlIst10() {
        return wieOftPasst10Rein == 1 && restVonMod10 == 0;
    }

    private static boolean dieZahlIstGroesserAls10() {
        return wieOftPasst10Rein == 1 && wieOftPasst5Rein < 3 && restVonMod5 > 0;
    }

    private static boolean dieZahlIst15() {
        return wieOftPasst10Rein == 1 && wieOftPasst5Rein == 3 && restVonMod5 == 0;
    }

    private static boolean dieZahlIstGroesserAls15() {
        return wieOftPasst10Rein == 1 && wieOftPasst5Rein == 3 && restVonMod5 > 0;
    }
}
