import java.awt.*;

public class Wilk extends Zwierze{
    protected Wilk(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.sila=9;
        this.inicjatywa=5;
        this.znak=TypOrganizmu.WILK;
        setKolor(new Color(120, 114, 118));
    }
    boolean akcja()
    {
        return true;
    }

    boolean kolizja(Organizm other)
    {
        return true;
    }
}
