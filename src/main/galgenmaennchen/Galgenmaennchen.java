package main.galgenmaennchen;


public class Galgenmaennchen {

    private final String zuRatendesWort;
    private String bisherGeratenesWort;
    private final String LETTER_VALIDATOR = "[a-zA-Z]";
    private final String WORD_VALIDATOR = "[a-zA-Z]+";


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
        if (zuRatendesWort.contains(buchstabe)) {
            manipuliereBisherGeratenesWort(buchstabe);
        }
        return bisherGeratenesWort;
    }

    private void manipuliereBisherGeratenesWort(String buchstabe) {

        //meine alternative braucht eine zusaetzliche instanzvariable
//            private List<Character> alleBisherRichtigenBuchstaben = new ArrayList<>();
//            alleBisherRichtigenBuchstaben.add(buchstabe.charAt(0));

//            StringBuilder stringBuilder = new StringBuilder();
//            for (Character letter : zuRatendesWort.toCharArray()) {
//                if (alleBisherRichtigenBuchstaben.contains(letter)){
//                    stringBuilder.append(letter);
//                } else {
//                    stringBuilder.append("-");
//                }
//            }

        //manuels idee braucht eine variable weniger:
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < zuRatendesWort.length(); i++) {
            if (buchstabe.equals(zuRatendesWort.substring(i, i + 1))) {
                stringBuilder.append(zuRatendesWort.charAt(i));
            } else {
                stringBuilder.append(bisherGeratenesWort.charAt(i));
            }
        }
        bisherGeratenesWort = stringBuilder.toString();
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
