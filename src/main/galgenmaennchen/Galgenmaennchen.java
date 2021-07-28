package main.galgenmaennchen;


public class Galgenmaennchen {

    private final String zuRatendesWort;
    private final String LETTER_VALIDATOR = "[a-zA-Z]";
    private final String WORD_VALIDATOR = "[a-zA-Z]+";

    private String bisherGeratenesWort;

    public Galgenmaennchen(String zuRatendesWort) {
        zuRatendesWort = cleanUp(zuRatendesWort);
        validateWort(zuRatendesWort);
        this.zuRatendesWort = zuRatendesWort;
        bisherGeratenesWort = createRatefeld(zuRatendesWort.length());
    }

    private String createRatefeld(int length) {
        return "-".repeat(Math.max(0, length));
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

    public String rateBuchstabe(String buchstabe) {
        validateBuchstabe(buchstabe);
        buchstabe = buchstabe.toLowerCase();
        if (zuRatendesWort.contains(buchstabe))
            manipuliereBisherGeratenesWort(buchstabe);

        return bisherGeratenesWort;
    }

    private void manipuliereBisherGeratenesWort(String buchstabe) {

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
