import java.awt.*;

public class WilczeJagody extends Roslina{
    protected WilczeJagody(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.sila=99;
        this.znak=TypOrganizmu.WILCZE_JAGODY;
        setKolor(new Color(25, 0, 51));
    }
    boolean kolizja(Organizm other)
    {
        swiat.UsunOrganizm(other);
        swiat.UsunOrganizm(this);
        return false;
    }
}
