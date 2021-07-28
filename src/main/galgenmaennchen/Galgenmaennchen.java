package main.galgenmaennchen;


public class Galgenmaennchen {

    private final String zuRatendesWort;
    private final String LETTER_VALIDATOR = "[a-zA-Z]";
    private final String WORD_VALIDATOR = "[a-zA-Z]+";

    public Galgenmaennchen(String zuRatendesWort) {
        validateWort(zuRatendesWort);
        this.zuRatendesWort = cleanUp(zuRatendesWort);
    }

    private void validateWort(String wort) {
        if (!wort.matches(WORD_VALIDATOR)) {
            throw new IllegalArgumentException("Nur Buchstabend, keine Zahlen o.ae. eingeben!");
        }
    }

    private String cleanUp(String wort) {
        return wort.toLowerCase()
                .replace("ö", "oe")
                .replace("ä", "ae")
                .replace("ü", "ue")
                .replace("ß", "ss");
    }

    public String getZuRatendesWort() {
        return zuRatendesWort;
    }

    public String rateBuchstabe(String buchstabe) {
        validateBuchstabe(buchstabe);
        buchstabe = buchstabe.toLowerCase();
        if (zuRatendesWort.contains(buchstabe))
        return buchstabe;

        return "-";
    }

    private void validateBuchstabe(String buchstabe) {
        if (buchstabe.length() > 1) {
            throw new IllegalArgumentException("Nur einen Buchstabend eingeben!");
        }

        if (!buchstabe.matches(LETTER_VALIDATOR)) {
            throw new IllegalArgumentException("Nur Buchstabend, keine Zahlen o.ae. eingeben!");
        }
    }


}
