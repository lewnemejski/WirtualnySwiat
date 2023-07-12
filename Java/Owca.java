import java.awt.*;

public class Owca extends Zwierze{
    public Owca(Swiat swiat, int x, int y) {
        super(swiat, x, y);
        this.sila= 4;
        this.inicjatywa= 4;
        this.znak = TypOrganizmu.OWCA;
        setKolor(new Color(255, 153, 204));
    }
}
