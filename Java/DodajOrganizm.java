public class DodajOrganizm {
    public static Organizm nowyOrganizm(Swiat swiat,int x, int y, Organizm.TypOrganizmu znak){
        switch (znak) {
            case WILK:
                return new Wilk(swiat, x, y);
            case OWCA:
                return new Owca(swiat, x, y);
            case LIS:
                return new Lis(swiat, x, y);
            case ZOLW:
                return new Zolw(swiat, x, y);
            case ANTYLOPA:
                return new Antylopa(swiat, x, y);
            case CZLOWIEK:
                return new Czlowiek(swiat, x, y);
            case TRAWA:
                return new Trawa(swiat, x, y);
            case MLECZ:
                return new Mlecz(swiat, x, y);
            case GUARANA:
                return new Guarana(swiat, x, y);
            case WILCZE_JAGODY:
                return new WilczeJagody(swiat, x, y);
            case BARSZCZ_SOSNOWSKIEGO:
                return new Barszcz(swiat, x, y);
            default:
                return null;//UNDEFINED TYP
        }
    }
}
