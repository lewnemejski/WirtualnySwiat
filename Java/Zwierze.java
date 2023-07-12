import java.util.Random;

public class Zwierze extends Organizm{
    protected Zwierze(Swiat swiat, int x, int y) {
        super(swiat, x, y);
    }
    public boolean rozmnoz(int toX, int toY){
        boolean done = false;
        for (int x = -1; x < 2; x++)
        {
            for (int y = -1; y < 2; y++)
            {
                if (x == 0 && y == 0)continue;
                if (swiat.wGranicach(toX + x, toY + y) && swiat.pustePole(toX + x, toY + y))
                {
                    Organizm tmp =DodajOrganizm.nowyOrganizm(this.getSwiat(),toX + x, toY + y, this.znak);
                    getSwiat().DodajOrganizm(tmp);
                    return true;
                }
            }
        }
        return false;
    }
    protected void wykonajRuch(){
        if (!this.akcja())return;
        this.ruch(1);
    }
    protected void ruch(int pola){
        int dx, dy;
        Random random = new Random();
        do
        {
            dx = random.nextInt(2+pola) - 1;
            dy = random.nextInt(2+pola) - 1;
        } while (!swiat.wGranicach(this.x + dx, this.y + dy) || (dy == 0 && dx == 0));
        swiat.zarzadzanieRuchem(this.x, this.y, this.x + dx, this.y + dy);
    }
}
