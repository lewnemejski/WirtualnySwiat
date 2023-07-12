public class Dziennik {
    private static String tekst = "";

    public static void dodajWpis(String komentarz) {
        tekst += komentarz + "\n";
    }

    public static String getTekst() {
        return tekst;
    }

    public static void WyczyscDziennik() {
        tekst = "";
    }
}
