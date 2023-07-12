import java.awt.*;
import java.util.Random;

public class Czlowiek extends Zwierze{
    public Czlowiek(Swiat swiat, int x, int y){
        super(swiat, x, y);
        this.znak = TypOrganizmu.CZLOWIEK;
        this.inicjatywa = 4;
        this.sila = 5;
        this.superMocCounter = 0;
        this.superMocWaitCounter = 0;
        setKolor(new Color(158, 129, 52));
        this.kierunek=Kierunek.NIC;
    }
    public int superMoc(){
        int pola = 0;
        if(this.superMoc) {

            Random random = new Random();
            if (this.superMocCounter == 0 && this.superMocWaitCounter == 0) {
                this.superMocCounter++;
                this.superMoc = true;
            }
            if (this.superMocCounter > 0 && this.superMocCounter <= 5) {
                if (superMocCounter < 4)
                    pola += 1;
                else {
                    if (random.nextInt(3) == 0)
                        pola += 1;
                }
                superMocCounter++;
            }
        }
        if (this.superMocWaitCounter > 0) superMocWaitCounter--;
        if (this.superMocCounter > 5)
        {
            this.superMocCounter = 0;
            this.superMocWaitCounter = 5;
            this.superMoc=false;
        }
        return pola;
    }
    public int getSuperMocCd(){
        return this.superMocWaitCounter;
    }
    public int getSuperMocCount(){
        return this.superMocCounter;
    }
    public void setSuperMocCd(int liczba){
        this.superMocWaitCounter = liczba;
    }
    public void setSuperMocCount(int liczba){
        this.superMocCounter = liczba;
    }
    public boolean kolizja(Organizm other){
        return true;
    }
    public boolean rozmnoz(int x, int y){
        return false;
    }
    private int superMocCounter;
    private int superMocWaitCounter;

    private boolean superMoc=false;

    public Kierunek kierunek;
    enum Kierunek{
        GORA,
        DOL,
        LEWO,
        PRAWO,
        NIC
    }

    public void setSuper(){
        this.superMoc=true;
    }

    @Override
    protected void ruch(int pola){
        int dx = 0, dy = 0;
        pola += superMoc();
        switch (kierunek){
            case LEWO: //gora
            {
                dx = -1 * pola;
                break;
            }
            case PRAWO:  //dol
            {
                dx = 1 * pola;
                break;
            }
            case DOL:	//prawo
            {
                dy = 1 * pola;
                break;
            }
            case GORA:	//lewo
            {
                dy = -1 * pola;
                break;
            }
            case NIC:
            {
                break;
            }
        }
        //wlasciwy ruch
        if (swiat.wGranicach(this.x + dx, this.y + dy))
        {
            swiat.zarzadzanieRuchem(this.x, this.y, this.x + dx, this.y + dy);
        }
    }
}
