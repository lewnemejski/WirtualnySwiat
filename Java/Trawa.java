import java.awt.*;

public class Trawa extends Roslina{
    protected Trawa(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.znak=TypOrganizmu.TRAWA;
        setKolor(Color.GREEN);
    }
}
