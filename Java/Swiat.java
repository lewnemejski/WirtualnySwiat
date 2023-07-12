import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Swiat {
    private int x;
    private int y;
    private int tura;
    private Organizm[][] plansza;
    private boolean czyCzlowiekZyje;
    private boolean pauza;
    private ArrayList<Organizm> organizmy;
    private Czlowiek czlowiekWsk;
    private SwiatGUI swiatGUI;

    public Swiat(SwiatGUI swiatGUI){
        this.x= 0;
        this.y = 0;
        tura = 0;
        czyCzlowiekZyje = true;
        pauza = true;
        Organizm[] organizmy = null;
        this.swiatGUI = swiatGUI;
    }

    public Swiat(int sizeX, int sizeY, SwiatGUI swiatGUI) {
        this.x= sizeX;
        this.y= sizeY;
        tura = 0;
        czyCzlowiekZyje = true;
        pauza = true;
        plansza = new Organizm[sizeY][sizeX];
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                plansza[i][j] = null;
            }
        }
        organizmy = new ArrayList<>();
        this.swiatGUI = swiatGUI;
    }

    protected void stworzSwiat(double wypelnienie){
        this.czlowiekWsk = (Czlowiek) DodajOrganizm.nowyOrganizm(this, 0, 0, Organizm.TypOrganizmu.CZLOWIEK);
        DodajOrganizm(czlowiekWsk);
        Random random = new Random();
        int x,y; wypelnienie=wypelnienie*10*2;
        for (int i = 0; i < wypelnienie; i++)
        {
            x = random.nextInt(this.x);
            y = random.nextInt(this.y);
            Organizm.TypOrganizmu[] table = {
                    Organizm.TypOrganizmu.ANTYLOPA, Organizm.TypOrganizmu.WILCZE_JAGODY, Organizm.TypOrganizmu.BARSZCZ_SOSNOWSKIEGO,
                    Organizm.TypOrganizmu.LIS, Organizm.TypOrganizmu.TRAWA, Organizm.TypOrganizmu.GUARANA, Organizm.TypOrganizmu.OWCA,
                    Organizm.TypOrganizmu.MLECZ, Organizm.TypOrganizmu.ZOLW, Organizm.TypOrganizmu.WILK
            };
            if (this.plansza[y][x]==null)
                DodajOrganizm(DodajOrganizm.nowyOrganizm(this, x, y, table[random.nextInt(table.length)]));
        }
    }
    void zarzadzanieRuchem(int fromX, int fromY, int toX, int toY)
    {
        System.out.println(fromX+" x "+fromY+" y x1 "+toX+" y1 "+toY);
        if (this.plansza[toY][toX]==null)
        {
            System.out.println("Rusza się na puste");
            this.zamienMiejscami(fromX, fromY, toX, toY);
            return;
        };
        if (this.plansza[fromY][fromX].getZnak() == this.plansza[toY][toX].getZnak() &&
                this.plansza[fromY][fromX] != this.plansza[toY][toX])			//rozmnazanie
        {
            System.out.println("Rozmnaza sie");
            if (!this.plansza[toY][toX].getRozmnozylSie() && !this.plansza[fromY][fromX].getRozmnozylSie() && this.plansza[fromY][fromX].rozmnoz(toX, toY)) {
                this.plansza[fromY][fromX].setRozmnozylSie(true); this.plansza[toY][toX].setRozmnozylSie(true);
        }
        }
	    else
        {
            if (this.plansza[fromY][fromX] != this.plansza[toY][toX]) {
            if (!this.plansza[fromY][fromX].kolizja(this.plansza[toY][toX])) return;
            if (!this.plansza[toY][toX].kolizja(this.plansza[fromY][fromX])) return;
            //walka
            if (this.plansza[fromY][fromX].getSila() >= this.plansza[toY][toX].getSila())
            {
                System.out.println("Zjada ktos kogos");
                this.UsunOrganizm((this.plansza[toY][toX]));
                this.zamienMiejscami(fromX, fromY, toX, toY);
            }
			else
            {
                System.out.println("Umiera");
                this.UsunOrganizm((this.plansza[fromY][fromX]));
            }
        }
        };
    }
    void barszczSmierci(int x, int y) {
        if (this.plansza[y][x] instanceof Zwierze)
            this.UsunOrganizm(plansza[y][x]);
    }
    void zamienMiejscami(int ad1X, int ad1Y, int ad2X, int ad2Y)
    {

        if (this.plansza[ad1Y][ad1X]!=null)
        {
            this.plansza[ad1Y][ad1X].setX(ad2X);
            this.plansza[ad1Y][ad1X].setY(ad2Y);
        }
        if (this.plansza[ad2Y][ad2X] != null)
        {
            this.plansza[ad2Y][ad2X].setY(ad1Y);
            this.plansza[ad2Y][ad2X].setX(ad1X);
        }
        Organizm temp = plansza[ad1Y][ad1X];
        plansza[ad1Y][ad1X] = plansza[ad2Y][ad2X];
        plansza[ad2Y][ad2X] = temp;
    }
    boolean pustePole(int x, int y){
        if(plansza[y][x]!=null)
            return false;
        else
            return true;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }

    void zapisz(String nameOfFile){
        try {
            nameOfFile += ".txt";
            File file = new File(nameOfFile);
            file.createNewFile();

            PrintWriter pw = new PrintWriter(file);
            pw.print(x + " ");
            pw.print(y + " ");
            pw.print(tura + " ");
            pw.print(czyCzlowiekZyje + " ");
            for (int i = 0; i < organizmy.size(); i++) {
                pw.print(organizmy.get(i).getZnak() + " ");
                pw.print(organizmy.get(i).getX() + " ");
                pw.print(organizmy.get(i).getY() + " ");
                pw.print(organizmy.get(i).getSila() + " ");
                pw.print(organizmy.get(i).getWiek() + " ");
                pw.print(organizmy.get(i).getRozmnozylSie());
                if (organizmy.get(i).getZnak() == Organizm.TypOrganizmu.CZLOWIEK) {
                    pw.print(" " + czlowiekWsk.getSuperMocCount() + " ");
                    pw.print(czlowiekWsk.getSuperMocCd() + " ");
                }
                pw.println();
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    public static Swiat wczytaj(String nameOfFile){
        try {
            nameOfFile += ".txt";
            File file = new File(nameOfFile);

            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            String[] properties = line.split(" ");
            int sizeX = Integer.parseInt(properties[0]);
            int sizeY = Integer.parseInt(properties[1]);
            Swiat tmpSwiat = new Swiat(sizeX, sizeY, null);
            int numerTury = Integer.parseInt(properties[2]);
            tmpSwiat.tura = numerTury;
            boolean czyCzlowiekZyje = Boolean.parseBoolean(properties[3]);
            tmpSwiat.czyCzlowiekZyje = czyCzlowiekZyje;
            tmpSwiat.czlowiekWsk = null;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                properties = line.split(" ");
                Organizm.TypOrganizmu typOrganizmu = Organizm.TypOrganizmu.valueOf(properties[0]);
                int x = Integer.parseInt(properties[1]);
                int y = Integer.parseInt(properties[2]);

                Organizm tmpOrganizm = DodajOrganizm.nowyOrganizm(tmpSwiat,x,y,typOrganizmu);
                int sila = Integer.parseInt(properties[3]);
                tmpOrganizm.setSila(sila);
                int turaUrodzenia = Integer.parseInt(properties[4]);
                tmpOrganizm.setInicjatywaWiek(tmpOrganizm.getInicjatywa(),turaUrodzenia);
                boolean czyRozmnozyl = Boolean.parseBoolean(properties[5]);
                tmpOrganizm.setRozmnozylSie(czyRozmnozyl);

                if (typOrganizmu == Organizm.TypOrganizmu.CZLOWIEK) {
                    tmpSwiat.czlowiekWsk = (Czlowiek) tmpOrganizm;
                    int czasTrwania = Integer.parseInt(properties[6]);
                    tmpSwiat.czlowiekWsk.setSuperMocCount(czasTrwania);
                    int cooldown = Integer.parseInt(properties[7]);
                    tmpSwiat.czlowiekWsk.setSuperMocCd(cooldown);
                }
                tmpSwiat.DodajOrganizm(tmpOrganizm);
            }
            scanner.close();
            return tmpSwiat;
        } catch (
                IOException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    public void DodajOrganizm(Organizm organizm) {
        organizmy.add(organizm);
        System.out.println(organizm.getX()+" "+organizm.getY());
        plansza[organizm.getY()][organizm.getX()] = organizm;
    }
    void nowaTura(){
        tura++;
        Dziennik.dodajWpis("\nTURA " + tura);
        System.out.println(tura);
        System.out.println(organizmy.size() + "\n");
        SortujOrganizmy();
        for (int i = 0; i < organizmy.size(); i++) {
            if (organizmy.get(i).getWiek() != tura && organizmy.get(i).getCzyUmarl() == false) {
                organizmy.get(i).wykonajRuch();
                System.out.println(organizmy.get(i).getZnak());
            }
        }
        for (int i = 0; i < organizmy.size(); i++) {
            if (organizmy.get(i).getCzyUmarl() == true) {
                organizmy.remove(i);
                i--;
            }
        }
        for (int i = 0; i < organizmy.size(); i++) {
            organizmy.get(i).setRozmnozylSie(false);
        }
    }

    private void SortujOrganizmy() {
        Collections.sort(organizmy, new Comparator<Organizm>() {
            @Override
            public int compare(Organizm o1, Organizm o2) {
                if (o1.getInicjatywa() != o2.getInicjatywa())
                    return Integer.valueOf(o2.getInicjatywa()).compareTo(o1.getInicjatywa());
                else
                    return Integer.valueOf(o1.getWiek()).compareTo(o2.getWiek());
            }
        });
    }
    public void UsunOrganizm(Organizm organizm) {
        plansza[organizm.getY()][organizm.getX()] = null;
        organizm.setCzyUmarl(true);
        if (organizm.getZnak() == Organizm.TypOrganizmu.CZLOWIEK) {
            czyCzlowiekZyje = false;
            czlowiekWsk = null;
        }
    }
    public SwiatGUI getSwiatGUI() {
        return swiatGUI;
    }

    public void setSwiatGUI(SwiatGUI swiatGUI) {
        this.swiatGUI = swiatGUI;
    }

    public boolean isPauza() {
        return pauza;
    }

    public void setPauza(boolean pauza) {
        this.pauza = pauza;
    }

    public Czlowiek getCzlowiek() {
        return czlowiekWsk;
    }
    public Organizm[][] getPlansza() {
        return plansza;
    }

    public void setCzlowiek(Czlowiek czlowiek) {
        this.czlowiekWsk = czlowiek;
    }
    public void setCzyCzlowiekZyje(boolean czyCzlowiekZyje) {
        this.czyCzlowiekZyje = czyCzlowiekZyje;
    }
    public boolean getCzyCzlowiekZyje() {
        return czyCzlowiekZyje;
    }
    boolean wGranicach(int x, int y)
    {
        if (x < this.x && x >= 0 && y >= 0 && y < this.y)
        {
            return true;
        }
        return false;
    }
}
