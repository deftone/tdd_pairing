package main.galgenmaennchen;


public class Galgenmaennchen {

    private final String zuRatendesWort;
    private final String LETTER_VALIDATOR = "[a-zA-Z]";

    public Galgenmaennchen(String zuRatendesWort) {
        //todo validieren
        this.zuRatendesWort = zuRatendesWort.toLowerCase();
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
