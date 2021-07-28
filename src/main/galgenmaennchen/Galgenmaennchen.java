package main.galgenmaennchen;


public class Galgenmaennchen {

    private final String zuRatendesWort;
    private String bisherGeratenesWort;
    private int fehlversuche;
    private static final String LETTER_VALIDATOR = "[a-zA-Z]";
    private static final String WORD_VALIDATOR = "[a-zA-Z]+";


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
        if (fehlversuche > 10) {
            return "Spiel vorbei!";
        }
        validateBuchstabe(buchstabe);
        buchstabe = buchstabe.toLowerCase();
        if (zuRatendesWort.contains(buchstabe)) {
            manipuliereBisherGeratenesWort(buchstabe);
            if (bisherGeratenesWort.equals(zuRatendesWort))
                return "Das Wort '" + zuRatendesWort + "' ist richtig!";
        } else {
            fehlversuche++;
        }
        if (fehlversuche > 10) {
            return "Loser";
        }
        return bisherGeratenesWort;
    }

    private void manipuliereBisherGeratenesWort(String buchstabe) {

        StringBuilder stringBuilder = new StringBuilder();
        for (Character letter : zuRatendesWort.toCharArray()) {
            if (buchstabe.equals(letter.toString())) {
                stringBuilder.append(letter);
            } else {
                stringBuilder.append(getBisherigenBuchstaben(letter));
            }
        }
        bisherGeratenesWort = stringBuilder.toString();
    }

    private char getBisherigenBuchstaben(Character letter) {
        return bisherGeratenesWort.charAt(zuRatendesWort.indexOf(letter));
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
