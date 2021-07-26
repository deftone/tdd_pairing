package main.pairing_manuel;

public class RoemischeZahlenConverterMitRegex {

    // Idee von Lutz: mit einer Regex alles ausschliessen:
    private static final String ROMAN_FORMAT = "^M*(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    public int convert(String roemischeZahl) {
        validate(roemischeZahl);

       int sum = 0;
        for (int i = 0; i < roemischeZahl.length(); i++) {
            if (esGibtEinNachfolgendesZeichen(i, roemischeZahl)
                    && nachfolgendesZeichenIstGroesser(i, roemischeZahl)) {
                sum = sum - getValueForLetter(roemischeZahl.substring(i, i + 1));
            } else {
                sum = sum + getValueForLetter(roemischeZahl.substring(i, i + 1));
            }
        }
        return sum;
    }

    private void validate(String roemischeZahl) {
        if (roemischeZahl == null || roemischeZahl.isEmpty()) {
            throw new IllegalArgumentException("Darf nicht leer sein");
        }

        if (!roemischeZahl.matches(ROMAN_FORMAT)){
            throw new IllegalArgumentException("Falsche Eingabe oder ungueltige roemische Zahl");
        }
    }

    private int getValueForLetter(String letter) {
        switch (letter) {
            case "I":
                return 1;
            case "V":
                return 5;
            case "X":
                return 10;
            case "L":
                return 50;
            case "C":
                return 100;
            case "D":
                return 500;
            default:
                throw new IllegalArgumentException("Falsche Eingabe");
        }
    }

    private boolean esGibtEinNachfolgendesZeichen(int index, String roemischeZahl) {
        return index + 1 < roemischeZahl.length();
    }

    private boolean nachfolgendesZeichenIstGroesser(int index, String roemischeZahl) {
        return getValueForLetter(roemischeZahl.substring(index, index + 1)) <
                getValueForLetter(roemischeZahl.substring(index + 1, index + 2));
    }

}
