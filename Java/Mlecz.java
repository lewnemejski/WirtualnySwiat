import java.awt.*;

public class Mlecz extends Roslina{
    public Mlecz(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.znak = TypOrganizmu.MLECZ;
        setKolor(Color.YELLOW);
    }
    boolean akcja()
    {
        this.rozmnoz(this.x, this.y);
        this.rozmnoz(this.x, this.y);
        return true;
    }
}
