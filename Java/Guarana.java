import java.awt.*;

public class Guarana extends Roslina{

    public Guarana(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.znak = TypOrganizmu.GUARANA;
        setKolor(Color.RED);
    }
    boolean kolizja(Organizm other)
    {
        other.setSila(other.getSila() + 3);
        return true;
    }
}
