package main.pairing_manuel;

import java.util.ArrayList;
import java.util.List;

public class RoemischeZahlenConverter {
    public int convert(String roemischeZahl) {
        if (roemischeZahl == null || roemischeZahl.isEmpty()) {
            throw new IllegalArgumentException("Darf nicht leer sein");
        }

        List<Integer> teilWerte = new ArrayList<>();
        for (int i = 0; i < roemischeZahl.length(); i++) {
            if (esGibtEinNachfolgendesZeichen(i, roemischeZahl)
                    && nachfolgendesZeichenIstGroesser(i, roemischeZahl)) {
                teilWerte.add(getValueForLetter(roemischeZahl.substring(i, i + 1)) * -1);
            } else {
                teilWerte.add(getValueForLetter(roemischeZahl.substring(i, i + 1)));
            }
        }
        if (teilWerteSindOk(teilWerte)) {
            return sumTeilwerte(teilWerte);
        } else {
            throw new IllegalArgumentException("Ungueltige roemische Zahl");
        }
    }

    private boolean teilWerteSindOk(List<Integer> teilWerte) {
        List<Integer> modifizierteTeilwerte = verarbeiteNegativeWerte(teilWerte);
        for (int i = 0; i < modifizierteTeilwerte.size(); i++) {
            if (esGibtEinNachfolgendenTeilwert(i, modifizierteTeilwerte.size())
                    && derVorgaengeristKleiner(modifizierteTeilwerte.get(i), modifizierteTeilwerte.get(i + 1))) {
                // wenn es mehrere zahlen gibt, so muessen die einzelnen werte absteigend sein!
                // sonst ist es keine korrekte roemische zahl
                // bsp: 19: XIX = 10+9 ist richtig, IXX = 9+10 ist falsch, reihenfolge ist falsch
                return false;
            }
        }
        return true;
    }

    private List<Integer> verarbeiteNegativeWerte(List<Integer> teilWerte) {
        List<Integer> alleIndexeVonNegativenWerten = new ArrayList<>();
        for (int i = 0; i < teilWerte.size(); i++) {
            if (teilWerte.get(i) < 0) {
                alleIndexeVonNegativenWerten.add(i);
            }
        }

        if (alleIndexeVonNegativenWerten.isEmpty()) {
            return teilWerte;
        }

        // der code ist nicht gut, daher ein kommentar zur erklaerung
        // verechne die negativen werte mit dem nachfolgenden wert
        // d.h. aus zwei werten,zB -1 und 5, wird ein wert, d.h. 4
        // so entsteht eine liste mit nur positiven werten
        List<Integer> modifizierteTeilwerte = new ArrayList<>();
        for (int i = 0; i < teilWerte.size(); i++) {
            if (alleIndexeVonNegativenWerten.contains(i)) {
                modifizierteTeilwerte.add((teilWerte.get(i) + teilWerte.get(i + 1)));
                i++;
            } else {
                modifizierteTeilwerte.add(teilWerte.get(i));
            }
        }
        return modifizierteTeilwerte;
    }

    private boolean derVorgaengeristKleiner(int vorgaenger, int nachfolger) {
        return vorgaenger < nachfolger;
    }

    private int sumTeilwerte(List<Integer> teilWerte) {
        int summe = 0;
        for (Integer i : teilWerte) {
            summe = summe + i;
        }
        return summe;
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

    private boolean esGibtEinNachfolgendenTeilwert(int index, int size) {
        return index + 1 < size;
    }

}
