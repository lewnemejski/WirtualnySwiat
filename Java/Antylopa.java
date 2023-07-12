import java.awt.*;
import java.util.Random;

public class Antylopa extends Zwierze {
    Antylopa(Swiat swiat, int x, int y)
    {
        super(swiat,x,y);
        this.sila = 4;
        this.inicjatywa = 4;
        this.znak = TypOrganizmu.ANTYLOPA;
        setKolor(new Color(153, 76, 0));
    }
    public boolean akcja()
    {
        this.ruch(2);
        return false;
    }

    public boolean kolizja(Organizm other)
    {
        Random random = new Random();
        if (random.nextInt(6) == 0)
        {
            for (int x = -1; x < 2; x++)
            {
                for (int y = -1; y < 2; y++)
                {
                    if (swiat.wGranicach(x,y) && swiat.pustePole(x, y))
                    {
                        swiat.zamienMiejscami(this.x, this.y, this.x + x, this.y + y);
                    }
                }
            }
            return false;
        }
        return true;
    }
}
