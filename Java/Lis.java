import java.awt.*;
import java.util.Random;

public class Lis extends Zwierze{

    public Lis(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.sila = 3;
        this.inicjatywa = 7;
        this.znak = TypOrganizmu.LIS;
        setKolor(new Color(255, 128, 0));
    }
    boolean akcja()
    {
        return true;
    }

    boolean kolizja(Organizm other) //Tak nie do końca jest to kolizja
    {
        if (other.getSila() > this.sila) {
        int dx, dy;
        for (int i = 0; i < 10; i++)
        {
            Random random = new Random();
            dx = random.nextInt(3) - 1;
            dy = random.nextInt(3) - 1;
            if (swiat.wGranicach(this.x + dx,this.y + dy))
            {
                if(swiat.pustePole(this.x + dx, this.y + dy)) {
                    swiat.zarzadzanieRuchem(this.x, this.y, this.x + dx, this.y + dy);
                }
                return false;
            }
        }
    }
        return true;
    }
}
