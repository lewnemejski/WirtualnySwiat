import java.awt.*;

public class Organizm {

    public enum TypOrganizmu {
        CZLOWIEK,
        WILK,
        OWCA,
        LIS,
        ZOLW,
        ANTYLOPA,
        TRAWA,
        MLECZ,
        GUARANA,
        WILCZE_JAGODY,
        BARSZCZ_SOSNOWSKIEGO;
    }
    protected Swiat swiat;
    protected int x;
    protected int y;
    protected TypOrganizmu znak;
    private Color kolor;
    protected int sila;
    protected int inicjatywa;
    protected int wiek;
    protected boolean rozmnozylSie;

    private boolean czyUmarl;

    public Organizm(Swiat swiat, int x, int y){
        this.swiat=swiat;
        this.x=x;
        this.y=y;
        this.wiek=0;
        this.czyUmarl=false;
        this.rozmnozylSie=false;
    }
    TypOrganizmu getZnak(){
        return znak;
    }
    int getInicjatywa(){
        return inicjatywa;
    }
    void starzejSie(){
        wiek++;
    }

    void setCzyUmarl(boolean value){
        this.czyUmarl=value;
    }

    public Color getKolor() {
        return kolor;
    }

    public void setKolor(Color kolor) {
        this.kolor = kolor;
    }
    public boolean getCzyUmarl() {
        return czyUmarl;
    }
    int getWiek(){
        return wiek;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
    int getSila(){
        return sila;
    }
    boolean getRozmnozylSie(){
       return rozmnozylSie;
    }

    public Swiat getSwiat() {
        return swiat;
    }
    void setX(int x){
        this.x=x;
    }
    void setY(int y){
        this.y=y;
    }
    void setSila(int sila){
        this.sila=sila;
    }
    void setInicjatywaWiek(int inicjatywa, int wiek){
        this.inicjatywa=inicjatywa;
        this.wiek=wiek;
    }

    void setRozmnozylSie(boolean rozmnozylSie){
        this.rozmnozylSie=rozmnozylSie;
    }
    String nazwaOrganizmu(){
        return "Mleko";
    }
    boolean akcja()
    {
        return true;
    };
    boolean rozmnoz(int x, int y){return false;}
    void  wykonajRuch(){}
    boolean kolizja(Organizm other)
    {
        return true;
    };
}
