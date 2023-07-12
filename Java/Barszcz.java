import java.awt.*;

public class Barszcz extends Roslina{

    public Barszcz(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.sila=10;
        this.znak = TypOrganizmu.BARSZCZ_SOSNOWSKIEGO;
        setKolor(new Color(128, 172, 0));
    }
    boolean kolizja(Organizm other)
    {
        swiat.UsunOrganizm(other);
        return false;
    }

    boolean akcja()
    {
        for (int dx = -1; dx < 2; dx++)
        {
            for (int dy = -1; dy < 2; dy++) {
                if (swiat.wGranicach(this.x + dx, this.y + dy) && !swiat.pustePole(this.x + dx, this.y + dy))
                {
                    if (dx != 0 && dy != 0)
                        swiat.barszczSmierci(dx+this.x, dy+this.y);
                }
            }
        }
        return true;
    }
}
