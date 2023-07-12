import java.awt.*;
import java.util.Random;

public class Zolw extends Zwierze{
    protected Zolw(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.sila=2;
        this.inicjatywa=1;
        this.znak=TypOrganizmu.ZOLW;
        setKolor(new Color(0, 102, 0));
    }
    boolean akcja()
    {
        Random random = new Random();
        if (random.nextInt(4) == 0) return false;
        return true;
    }

    boolean kolizja(Organizm org)
    {
        if (org.getSila() < 5) return false;
        return true;
    }
}
