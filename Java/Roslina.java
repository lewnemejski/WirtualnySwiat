import java.awt.*;
import java.util.Random;

public class Roslina extends Organizm{

    protected Roslina(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.inicjatywa = 0;
        this.sila = 0;
    }
    void wykonajRuch()
    {
        this.akcja();
        this.rozmnoz(this.x, this.y);
    }

    boolean rozmnoz(int toX, int toY)
    {
        System.out.println("Roslina "+this.getZnak()+" probuje sie rozsiac");
        Random random = new Random();
        if (random.nextInt(12) == 1)
        {
            for (int y = -1; y < 2; y++)
            {
                for (int x = -1; x < 2; x++)
                {
                    if (swiat.wGranicach(toX+x, toY+y) && swiat.pustePole(toX + x, toY + y))
                    {
                        System.out.println((toX + x)+" "+(toY + y)+" "+this.getZnak());
                        Organizm tmp =DodajOrganizm.nowyOrganizm(this.getSwiat(),toX + x, toY + y, this.znak);
                        getSwiat().DodajOrganizm(tmp);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
